package app.ris.bo;

import app.ris.src.FingerPrint;
import java.awt.Image;

import com.digitalpersona.onetouch.DPFPCaptureFeedback;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPImageQualityEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import javax.swing.SwingUtilities;

public class BioMetricReader {

    private DPFPCapture capturer = DPFPGlobal.getCaptureFactory().createCapture();
    public DPFPTemplate template;
    public DPFPSample sample = null;
    public String report = "";
    public static boolean saveFPinDB = false;
    public static boolean verifyFPinDB = false;
    public static boolean saveFPinFile = false;
    public static boolean verifyFPinDBForAttendance = false;
    public static boolean verifyFPinTemplateArrayForAttendance = false;
    // For CME Attendance System
    public static boolean verifyFPinDBForCmeAttendance = false;
    public static boolean verifyFPinTemplateArrayForCMEAttendance = false;
    public static int saveFpFileName = 0;

    public BioMetricReader() {
        saveFPinDB = false;
        verifyFPinDB = false;
        saveFPinFile = false;
        verifyFPinDBForAttendance = false;
        verifyFPinTemplateArrayForAttendance = false;
        saveFpFileName = 0;
        init();

    }

    protected void init() {
        capturer.addDataListener(new DPFPDataAdapter() {

            @Override
            public void dataAcquired(final DPFPDataEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        makeReport("The finger print sample was captured.");
                        setPrompt("Scan the same fingerprint again.");
                        process(e.getSample());
                    }
                });
            }
        });
        capturer.addReaderStatusListener(new DPFPReaderStatusAdapter() {

            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        makeReport("The fingerprint reader was connected.");
                    }
                });
            }

            @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        makeReport("The fingerprint reader was disconnected.");
                    }
                });
            }
        });
        capturer.addSensorListener(new DPFPSensorAdapter() {

            @Override
            public void fingerTouched(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        makeReport("The fingerprint reader was touched.");
                    }
                });
            }

            @Override
            public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        makeReport("The finger was removed from the fingerprint reader.");
                    }
                });
            }
        });
        capturer.addImageQualityListener(new DPFPImageQualityAdapter() {

            @Override
            public void onImageQuality(final DPFPImageQualityEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        if (e.getFeedback().equals(DPFPCaptureFeedback.CAPTURE_FEEDBACK_GOOD)) {
                            makeReport("The quality of the fingerprint sample is good.");
                        } else {
                            makeReport("The quality of the fingerprint sample is poor.");
                        }
                    }
                });
            }
        });
    }

    // The call is asynchronous and returns immediately. The events will be sent
    // to the listeners until the stopCapture method is called.
    public void start() {
        capturer.startCapture();
    }

    // Stops the previously started capture operation.
    public void stop() {
        capturer.stopCapture();
    }

    public void setStatus(String string) {
        System.out.println("Status = " + string);
    }

    public void setPrompt(String string) {
        this.report = string;
    }

    public void makeReport(String string) {
        // backend logging only
        System.out.println("Report = " + string);
        this.report = string;
    }

    public void process(DPFPSample sample) {
        // Draw finger print sample image.
        // drawPicture(convertSampleToBitmap(sample));
        if (saveFPinFile) {
            Image image = this.convertSampleToBitmap(sample);
            FingerPrint.setImage(image);
            try {
                // retrieve image
                BufferedImage bi = (BufferedImage) image;

                System.out.println("Width  = " + bi.getWidth());
                System.out.println("Height = " + bi.getHeight());

                String folderName =System.getProperty("java.io.tmpdir") +"\\FingerPrint";
                File outputfile = new File(folderName);
                if (!outputfile.exists()) {
                    outputfile.mkdirs();
                }
                outputfile = new File(folderName + "\\Fingerprint.png");
                System.out.println(folderName + "\\Fingerprint.png");
                Constants.thumbImpressionFile = folderName + "\\Fingerprint.png";
                outputfile.delete();

                int width = 100, height = 100;
                BufferedImage resizedImage = new BufferedImage(width, height, bi.getType());
                Graphics2D g = resizedImage.createGraphics();
                g.setComposite(AlphaComposite.Src);
                g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.drawImage(bi, 0, 0, width, height, null);
                g.dispose();
                ImageIO.write(resizedImage, "png", outputfile);
                
            } catch (Exception e) {
                //e.printStackTrace();
            }
        } else {
            System.out.println(".............");
        }
    }


    public Image convertSampleToBitmap(DPFPSample sample) {
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);

    }

    public DPFPFeatureSet extractFeatures(DPFPSample sample, DPFPDataPurpose purpose) {
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            return null;
        }
    }

    // -------------------------------------------------------------
    public DPFPTemplate makeTemplate(byte[] data) {
        DPFPTemplate t = DPFPGlobal.getTemplateFactory().createTemplate();
        // Imports the data from the binary representation.
        t.deserialize(data);
        return t;
    }

    public void setTemplate(DPFPTemplate template) {
        this.template = template;
    }

    public DPFPTemplate getTemplate() {
        return this.template;
    }
} // class closer
