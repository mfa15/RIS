/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.bo;

import com.github.sarxos.webcam.Webcam;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JInternalFrame;

public class Constants {

    public static String[] locations = {"Lahore", "Hospital Location 1", "Kasur", "Mianwali", "Layyah", "Bhakkar", "Vehari", "Okara", "Sheikhupura", "Khushab", "CHAKWAL", "RAJANPUR", "MUZAFARGARH", "JHELUM", "JEHLUM", "NAROWAL", "KHANEWAL", "TOBA TAK SINGH", "TAUNSA SHARIF", "PAKPATTAN", "CHINIOT", "LODHRAN"};

    public static String getThumbImpression = System.getProperty("java.io.tmpdir") + "\\FingerPrint\\getThumb.png";
    public static String getThumbImpressionAttendant = System.getProperty("java.io.tmpdir") + "\\FingerPrint\\getThumb.png";
    public static JInternalFrame obj = null;
    public static String terminalName = "";
    public static javax.swing.JDesktopPane pane1;
    public static JInternalFrame bgFrame;
    public static String dbIP = "localhost";
    public static String dbPort = "3336";
    public static String employeeRole = "";
    public static Webcam webcam = null;
    public static String locationID = "";
    public static String employeeID = "";
    public static HashMap<String, LocationBO> locationMap = new HashMap<String, LocationBO>();
    public static String primeryServer = "";
    public static String secondaryServer = "";
    public static String viewerPath = System.getProperty("user.dir") + "\\dvd\\viewer";
    public static String burnMediaPath = System.getProperty("user.dir") + "\\dvd\\BurnMedia.exe";
    public static String logoPath = System.getProperty("user.dir") + "\\resources\\mayo.jpg";
    public static String logoGov = System.getProperty("user.dir") + "\\resources\\gov.png";
    public static String profilePath = System.getProperty("java.io.tmpdir") + "\\get\\pro.png";
    public static String attendProfilePath = System.getProperty("java.io.tmpdir") + "\\get\\pro.png";
    public static String idFront = System.getProperty("java.io.tmpdir") + "\\get\\idFront.png";
    public static String idBack = System.getProperty("java.io.tmpdir") + "\\get\\idFront.png";
    public static String referLetter = System.getProperty("java.io.tmpdir") + "\\get\\referLetter.png";
    public static String refund = System.getProperty("user.dir") + "\\resources\\refund.jpg";
    public static String free = System.getProperty("user.dir") + "\\resources\\free.jpg";
    public static String thumbImpressionFile = "";
    public static String status[] = {"1", "2", "3", "4", "5", "6", "0"};  //[0]1-order //[1]2-invoice //[2]3-counter ack //[3]4-tech ack //[4]5-report saved //[5]6-final signed //[6]0-refund
    public static HashMap<String, String> statusMap = new HashMap<String, String>();
    public static HashMap<String, String> userRolesMap = new HashMap<String, String>();
    public static ArrayList<String> preDefinedHist = new ArrayList<>();
    public static ArrayList<String> findingList = new ArrayList<>();
//    public static ArrayList<String> userRoles = new ArrayList<>();
    public static String JPEG_Server_Path = "";
    public static String employeeName = "";

    static {

        userRolesMap.put("A", "Admin");
        userRolesMap.put("R", "Receptionist");
        userRolesMap.put("T", "Technologist");
        userRolesMap.put("D", "Doctor");
        userRolesMap.put("P", "PMU Admin");
        userRolesMap.put("U", "PMU");

        statusMap.put("1", "Ordered");
        statusMap.put("2", "Invoiced");
        statusMap.put("3", "Pending Tech Ack.");
        statusMap.put("4", "Tech Ack.");
        statusMap.put("5", "Pending Final Sign");
        statusMap.put("6", "Report Available");
        statusMap.put("0", "Refunded");

        preDefinedHist.add("**Select**");
        preDefinedHist.add("Headache");
        preDefinedHist.add("Trauma");
        preDefinedHist.add("Vertigo/Dizziness");
        preDefinedHist.add("CVA symptoms");
        preDefinedHist.add("Hydrocephalus");
        preDefinedHist.add("Intracranial Hemorrhage");
        preDefinedHist.add("Subdural Hematoma");
        preDefinedHist.add("Aphasia/Dysphasia");
        preDefinedHist.add("Extremity Weakness/Paresthesia");
        preDefinedHist.add("Confusion/ Dementia");
        preDefinedHist.add("Memory/ Concentration Problems  ");
        preDefinedHist.add("Seizure");
        preDefinedHist.add("Ataxia");
        preDefinedHist.add("TIA");
        preDefinedHist.add("Brain Mets");
        preDefinedHist.add("Brain Abscess");
        preDefinedHist.add("Sinusitis");
        preDefinedHist.add("Congestion");
        preDefinedHist.add("Sinus HA");
        preDefinedHist.add("Polyps Drainage Chronic cough");
        preDefinedHist.add("Rhinitis");
        preDefinedHist.add("Known Sinus Tumor");
        preDefinedHist.add("Hearing Loss");
        preDefinedHist.add("Cholesteatoma");
        preDefinedHist.add("Mastoiditis");
        preDefinedHist.add("Acoustic Neuroma");
        preDefinedHist.add("Adenopathy");
        preDefinedHist.add("Swelling");
        preDefinedHist.add("Abscess/ Infection");
        preDefinedHist.add("Palpable Lump/ Lesion");
        preDefinedHist.add("Tumor");
        preDefinedHist.add("Oral Cancer");
        preDefinedHist.add("Cellulitis");
        preDefinedHist.add("Dysphasia");
        preDefinedHist.add("Foreign Body");
        preDefinedHist.add("Tracheal Stenosis");
        preDefinedHist.add("Pneumothorax");
        preDefinedHist.add("Rib Fractures");
        preDefinedHist.add("Nodule F/U");
        preDefinedHist.add("Interstitial Lung Disease");
        preDefinedHist.add("Asbestosis");
        preDefinedHist.add("Ground Glass Appearance");
        preDefinedHist.add("COPD");
        preDefinedHist.add("Mass/ Tumor");
        preDefinedHist.add("Nodule 1st Time Imaged");
        preDefinedHist.add("Adenopathy");
        preDefinedHist.add("SOB/ Dyspnea");
        preDefinedHist.add("Elevated D-Dimer");
        preDefinedHist.add("Pulmonary Embolus");
        preDefinedHist.add("Lung Metastasis");
        preDefinedHist.add("Chest Pain");
        preDefinedHist.add("Adrenal Adenoma");
        preDefinedHist.add("Renal Mass");
        preDefinedHist.add("Renal Stone");
        preDefinedHist.add("Renal Colic");
        preDefinedHist.add("Flank Pain");
        preDefinedHist.add("Gross Hematuria");
        preDefinedHist.add("Free Air/ Perforation");
        preDefinedHist.add("Abdominal Bleed/ Hematoma");
        preDefinedHist.add("Hernia Work-Up");
        preDefinedHist.add("CT Urogram");
        preDefinedHist.add("Microscopic Hematuria");
        preDefinedHist.add("Abdominal Pain");
        preDefinedHist.add("Appendicitis");
        preDefinedHist.add("Diverticulitis");
        preDefinedHist.add("Lymphadenopathy");
        preDefinedHist.add("Pancreatic mass; Pancreatitis");
        preDefinedHist.add("Abscess/ Infection");
        preDefinedHist.add("Crohn Disease ");
        preDefinedHist.add("Colitis");
        preDefinedHist.add("Cancer Work-Up/ Staging");
        preDefinedHist.add("Weight Loss/ Anorexia/ Early Satiety");
        preDefinedHist.add("Cirrhosis");
        preDefinedHist.add("GI Bleed");
        preDefinedHist.add("Constipation/ Obstruction");
        preDefinedHist.add("Gastroenteritis");
        preDefinedHist.add("Elevated Liver Functions");
        preDefinedHist.add("Portal Vein Thrombosis");
        preDefinedHist.add("Nausea/ Vomiting/ Diarrhea");

        findingList.add("-");
        findingList.add("Normal");
        findingList.add("Abnormal");
        findingList.add("Referred");
    }

    public static String makeHash(String pass) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(pass.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
//        System.out.println(generatedPassword);
        return generatedPassword;

    }

    public static String calculateAge(String value) {
        if (value == null || !value.contains("-")) {
            return value + " DOB";
        }
        String dob[] = value.split("-");
        String age = "0";
        LocalDate start = LocalDate.of((int) Integer.valueOf(dob[0]), (int) Integer.valueOf(dob[1]), (int) Integer.valueOf(dob[2]));
        LocalDate end = LocalDate.now(); // use for age-calculation: LocalDate.now()
        long years = ChronoUnit.YEARS.between(start, end);
        long month = ChronoUnit.MONTHS.between(start, end);
        long day = ChronoUnit.DAYS.between(start, end);
        // System.out.println(years); // 17
        if (years > 0) {
            age = (String.valueOf(years) + " Years");
        } else if (month > 0) {
            age = (String.valueOf(month) + " Months");
        } else if (day > 0) {
            age = (String.valueOf(day) + " Days");
        } else {
            age = (String.valueOf(0));
        }

        return age;
    }

}
