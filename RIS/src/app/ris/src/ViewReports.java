/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.src;

import app.ris.bo.Constants;
import app.ris.bo.PatientBO;
import app.ris.dam.dam;
import com.sun.glass.events.KeyEvent;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 *
 */
public class ViewReports extends javax.swing.JInternalFrame {

    /**
     * Creates new form NewJInternalFrame
     */
    DoctorReporting regObj = null;
    HashMap<String, PatientBO> viewMap = new HashMap<String, PatientBO>();
    LinkedHashMap<String, String> previousData = new LinkedHashMap<String, String>();
    dam d = new dam();
    String id = null;

    final JPanel jPanelWebcam = new JPanel();
    private String tempDir = null;
//    static {
//		Webcam.setDriver(new WebcamDummyDriver(3));
//	}
    JInternalFrame window = new JInternalFrame();
//    ImagePanel panel=null;
//    Webcam

    public ViewReports(String id) {
//        String baseDir = CapturePicture.class.getProtectionDomain().getCodeSource().getLocation().getPath();
//        baseDir = baseDir.substring(0, baseDir.lastIndexOf("/"));

        tempDir = System.getProperty("java.io.tmpdir") + "\\capture";
        new File(tempDir).mkdirs();

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        this.id = id;
//        Constants.webcam = Webcam.getDefault();
//        Constants.webcam.setViewSize(new Dimension(320, 240));

//        WebcamPanel webcamPanel = new WebcamPanel(Constants.webcam);
//        webcamPanel.setMirrored(false);
//        jPanelWebcam.add(webcamPanel);
//        window.add(jPanelWebcam);
//
//        window.setPreferredSize(new Dimension(320, 240));
//        window.pack();
//        window.setVisible(true);
//
//
//        window.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
//        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
//        ((BasicInternalFrameUI) window.getUI()).setNorthPane(null);
//        window.setBorder(null);
        initComponents();
//        this.setLayout(new FlowLayout());
//        add(l);
//        add(window);

//        initComponents();
//        jp.add(window);
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        Dimension desktopSize = Constants.pane1.getSize();
        Dimension jInternalFrameSize = this.getSize();
        this.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);
        this.setVisible(true);

        if (!this.id.equals("")) {
            this.txtSid.setText(id);
            searchPatient();
            searchPreviousReports();
        }

        setCursor(Cursor.getDefaultCursor());

    }

    public ImageIcon resizeIcon(ImageIcon imageIcon, int width, int height) {
        return new ImageIcon(imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }

    public static Image iconToImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jp2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtSid = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtSname = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRecords = new javax.swing.JTable();
        l = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setMaximizable(true);
        setResizable(true);

        jp2.setBorder(javax.swing.BorderFactory.createTitledBorder("Preview"));
        jp2.setPreferredSize(new java.awt.Dimension(640, 480));

        javax.swing.GroupLayout jp2Layout = new javax.swing.GroupLayout(jp2);
        jp2.setLayout(jp2Layout);
        jp2Layout.setHorizontalGroup(
            jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 628, Short.MAX_VALUE)
        );
        jp2Layout.setVerticalGroup(
            jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 457, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Patient"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Patient ID:");

        txtSid.setToolTipText("");
        txtSid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSidKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Name:");

        txtSname.setEditable(false);
        txtSname.setEnabled(false);

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnExit.setBackground(new java.awt.Color(255, 0, 0));
        btnExit.setText("Exit");
        btnExit.setActionCommand("EXIT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jButton12.setText("Clear");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExit))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtSid, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtSname, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtSname, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtSid, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Previous Records"));

        tableRecords.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Date", "Description"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableRecords.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableRecordsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableRecords);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        l.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        l.setText("   View Attached Reports");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(l)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jp2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(l, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jp2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        searchPatient();
        searchPreviousReports();

    }//GEN-LAST:event_btnSearchActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        clear();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
//        Constants.bgFrame.show();
        window.dispose();
//        Constants.webcam.close();
        this.dispose();
        regObj.show();
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnExitActionPerformed

    private void txtSidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSidKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            searchPatient();
            searchPreviousReports();
        }
    }//GEN-LAST:event_txtSidKeyPressed

    private void tableRecordsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableRecordsMouseClicked
//        if(evt.getClickCount() == 2)
        {
            jp2.removeAll();
            int row = tableRecords.getSelectedRow();
            if (row >= 0) {
                String id = tableRecords.getValueAt(row, 0).toString();
                String path = d.getAttachedReport(id);
                if (!path.equalsIgnoreCase("")) {
                    File file = new File(path);

                    BackgroundPanel bp = new BackgroundPanel(file.getAbsolutePath());
                    jp2.add(bp);
                    jp2.validate();
                    jp2.repaint();
                }
            }
        }
    }//GEN-LAST:event_tableRecordsMouseClicked

    private void searchPatient() {
//        radioPatient.setSelected(true);
        if (!txtSid.getText().isEmpty()) {

            String id[] = txtSid.getText().split("-");
            if (id.length < 2) {
                String temp[] = new String[2];
                temp[0] = id[0];
                temp[1] = "";
                id = temp;
            }
            d.searchPatient(id[0], id[1], "", "", "", viewMap);
            if (viewMap.size() > 0) {
                PatientBO pbo = viewMap.get(id[1]);
                txtSname.setText(pbo.getName());
            }
            try {
//            d.getPatientPicture(id[0], id[1]);
//            d.getPatientIdFront(id[0], id[1]);
//            d.getPatientIdBack(id[0], id[1]);
//            d.getPatientLetter(id[0], id[1]);
//            setProfilePicture(Constants.profilePath,jpp);;
//            setProfilePicture(Constants.idFront,jp1);
                setProfilePicture(Constants.idBack, jp2);
//            setProfilePicture(Constants.referLetter,jp3);
            } catch (Exception e) {
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Provide Patient ID");
        }
    }

    private void clear() {
//        radioPatient.setSelected(true);
        txtSid.setText("");
        txtSname.setText("");
//        txtDesc.setText("");
        jp2.removeAll();
        jp2.invalidate();
        jp2.repaint();
        clearTable();
//        jp1.removeAll();
//        jp1.invalidate();
//        jp1.repaint();
//        jpp.removeAll();
//        jpp.invalidate();
//        jpp.repaint();
//        jp3.removeAll();
//        jp3.invalidate();
//        jp3.repaint();
        this.validate();
        this.repaint();
    }

    private void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) { //some JVMs return null for empty dirs
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }

//    private void searchPatientAttendantInfo() {
//        if (!txtSid.getText().isEmpty()) {
//
//            String id[] = txtSid.getText().split("-");
//            if(id.length<2) {
//                String temp[] = new String[2];
//                temp[0] = id[0];
//                temp[1] = "";
//                id = temp;
//            }
//            try{
//            d.searchPatient(id[0], id[1], "", "", "", viewMap);
//            d.getAttendantPicture(id[0], id[1]);
//            d.getAttendantIdFront(id[0], id[1]);
//            d.getAttendantIdBack(id[0], id[1]);
//            d.getAttendantLetter(id[0], id[1]);
////            setProfilePicture(Constants.attendProfilePath,jpp);
////            setProfilePicture(Constants.idFront,jp1);
//            setProfilePicture(Constants.idBack,jp2);
//            setProfilePicture(Constants.referLetter,jp3);
//            }catch(Exception e)
//            {}
//            if (viewMap.size() > 0) {
//                PatientBO pbo = viewMap.get(id[1]);
//                txtSname.setText(pbo.getName());
//            }
//        } else {
//            JOptionPane.showMessageDialog(this, "Please Provide Patient ID");
//        }
//    }
    private void searchPreviousReports() {
        if (!txtSid.getText().isEmpty()) {
            previousData = new LinkedHashMap<>();
            clearTable();
            String id[] = txtSid.getText().split("-");
            previousData = d.searchAttachedReportsRecord(id);
            if (previousData.size() > 0) {
                fillTable();
            }
        }
    }

//    class ImagePanel extends JPanel {
//
//        private Image img;
//
//        public ImagePanel(String img) {
//          this(new ImageIcon(img).getImage());
//        }
//
//        public ImagePanel(Image img) {
//          this.img = img;
////          this.img = img.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
//          Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
//          setPreferredSize(size);
//          setMinimumSize(size);
//          setMaximumSize(size);
//          setSize(size);
//          setLayout(null);
//        }
//
//        @Override
//        public void paintComponent(Graphics g) {
//          super.paintComponent(g);
//            g.drawImage(img, 0, 0, null);
//        }
//    }
    public class BackgroundPanel extends javax.swing.JPanel {

        java.awt.Image image;

        public BackgroundPanel(String path) {
            try {

                image = javax.imageio.ImageIO.read(new File(path));
                java.awt.Dimension size = new java.awt.Dimension(image.getWidth(null), image.getHeight(null));
                setPreferredSize(size);
                setMinimumSize(size);
                setMaximumSize(size);
                setSize(size);
                setLayout(null);
            } catch (Exception e) {
            }
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton jButton12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jp2;
    private javax.swing.JLabel l;
    private javax.swing.JTable tableRecords;
    private javax.swing.JTextField txtSid;
    private javax.swing.JTextField txtSname;
    // End of variables declaration//GEN-END:variables

    private void setProfilePicture(String filePath, JPanel panel) {

        panel.removeAll();
        BackgroundPanel bp = new BackgroundPanel(filePath);
        panel.add(bp);
        panel.validate();
        panel.repaint();

    }

    private void fillTable() {
        ListSelectionModel lsm = this.tableRecords.getSelectionModel();
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //------------- Information Filled in table
        DefaultTableModel model = (DefaultTableModel) tableRecords.getModel();

        for (String key : previousData.keySet()) {
            String pb = previousData.get(key);
            String info[] = pb.split("####");
            String arr[] = {key, info[1], info[0]};
            model.addRow(arr);
        }
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    private void clearTable() {
        tableRecords.clearSelection();

        DefaultTableModel dm = (DefaultTableModel) tableRecords.getModel();
        dm.getDataVector().removeAllElements();
        dm.fireTableDataChanged();
    }
}
