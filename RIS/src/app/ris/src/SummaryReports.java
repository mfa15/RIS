/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.src;

import app.ris.bo.Constants;
import app.ris.bo.JCheckCombo;
import app.ris.bo.LocationBO;
import app.ris.bo.PatientDetailReportBo;
import app.ris.bo.PatientSummaryReportBo;
import app.ris.bo.PatientTypeBo;
import app.ris.dam.dam;
import app.ris.reporting.CPTwiseSummaryReport;
import app.ris.reporting.FindingWiseSummaryReport;
import app.ris.reporting.HistoryWiseSummaryReport;
import app.ris.reporting.PatientNatureWiseSummaryReport;
import app.ris.reporting.ReferringDoctorSummaryReport;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 *
 */
public class SummaryReports extends javax.swing.JInternalFrame {

    LinkedHashMap<String, String> cptMap = new LinkedHashMap<String, String>();
    dam dam = new dam();
    String allLocationIds="";
    private JFrame jf = new JFrame();
    private JFrame jf2 = new JFrame();
    LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
    Vector vecCheckBoxesPatient = new Vector();
    Vector vecCheckBoxesContrast = new Vector();
    JCheckCombo jcc2, jcc;
    LinkedHashMap<String, PatientTypeBo> patientTypeMap = new LinkedHashMap<String, PatientTypeBo>();
    LinkedHashMap<String, PatientDetailReportBo> patientDetailMap = new LinkedHashMap<String, PatientDetailReportBo>();
    LinkedHashMap<String, PatientSummaryReportBo> patientSummaryMap = new LinkedHashMap<String, PatientSummaryReportBo>();

    /**
     * Creates new form Reports
     */
    public SummaryReports() {
        initComponents();
        this.setVisible(true);
        Dimension desktopSize = Constants.pane1.getSize();
        Dimension jInternalFrameSize = this.getSize();
        this.setLocation((desktopSize.width - jInternalFrameSize.width) / 2,
                (desktopSize.height - jInternalFrameSize.height) / 2);

        jf.setLayout(new GridBagLayout());
        jf2.setLayout(new GridBagLayout());
        vecCheckBoxesContrast.add(new JCheckBox("With Contrast", true));
        vecCheckBoxesContrast.add(new JCheckBox("Without Contrast", true));
        jcc2 = new JCheckCombo(vecCheckBoxesContrast);
        jf2.getContentPane().add(jcc2);
        jPanel6.setLayout(new FlowLayout());
        jPanel6.add(jf2.getContentPane());

        dam.getPatientType(patientTypeMap);
        jcc2.getSelectedObjects();
        if (patientTypeMap.size() > 0) {
            for (String key : patientTypeMap.keySet()) {
                PatientTypeBo pbo = patientTypeMap.get(key);
                if (Constants.employeeRole.equalsIgnoreCase("P") && pbo.getDesc().equalsIgnoreCase("private")) {
                    continue;
                }
                vecCheckBoxesPatient.add(new JCheckBox(pbo.getDesc(), false));
            }

            jcc = new JCheckCombo(vecCheckBoxesPatient);
            jf.getContentPane().add(jcc);
            jPanel5.setLayout(new FlowLayout());
            jPanel5.add(jf.getContentPane());

            if (Constants.locationMap.size() > 0) {
                System.out.println("Role for current user is : " + Constants.employeeRole);
                if (Constants.employeeRole.toLowerCase().equalsIgnoreCase("U") || Constants.employeeRole.toLowerCase().equalsIgnoreCase("P")) {
                    new dam().getRoleWiseLocationRights(Constants.employeeRole);
                }
                allLocationIds="";
                for (String key : Constants.locationMap.keySet()) {
                    LocationBO lb = Constants.locationMap.get(key);
                    lhm.put(key, lb.getName());
                    if (lb.getActive().equalsIgnoreCase("Y")) {
                        comboLoc.addItem(lhm.get(key).toString());
                        allLocationIds= allLocationIds+key+",";
                    }
                }
                allLocationIds=allLocationIds.substring(0, allLocationIds.length()-1);
            }

        }
        this.pack();
        this.setVisible(true);

        chkLocations.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                System.out.println("Checked? " + chkLocations.isSelected());
                if (chkLocations.isSelected()) {
                    comboLoc.setEnabled(false);
                    fillReferringDocLov(allLocationIds);
                } else {
                    comboLoc.setEnabled(true);
                    fillReferringDocLov(Constants.locationID);
                }
            }
        });
        
        
        comboLoc.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                
                String loc = comboLoc.getSelectedItem().toString();
                
                for (String key : Constants.locationMap.keySet()) {
                    LocationBO lb = Constants.locationMap.get(key);
                    if (lb.getName().equalsIgnoreCase(loc)) {
                        fillReferringDocLov(key);
                    }
                }
            }
        });
        
        
        fillCPTCombo();
        fillPreDedinedHist();
        fillReferringDocLov(Constants.locationID);
        AutoCompleteDecorator.decorate(comboCPT);
        AutoCompleteDecorator.decorate(cmbPreDefHist);
        
    }

    private void fillReferringDocLov(String locId) {
        cmbReferringDoc.removeAllItems();
        ArrayList<String> list = dam.getReferringDocList(locId);
        for (String s : list) {
            cmbReferringDoc.addItem(s);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        fromDate = new com.toedter.calendar.JDateChooser();
        toDate = new com.toedter.calendar.JDateChooser();
        btnExit = new javax.swing.JButton();
        btnCl = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        comboLoc = new javax.swing.JComboBox<>();
        chkLocations = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        cmbPreDefHist = new javax.swing.JComboBox<>();
        btnHistReport = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        comboCPT = new javax.swing.JComboBox<>();
        btnCPTReport = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        cmbReferringDoc = new javax.swing.JComboBox<>();
        btnReferringDoc = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        btnFinding = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmbFindings = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        btnPtnType = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setMaximizable(true);
        setResizable(true);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel21.setText(" Summary Reports");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("From Date");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("To Date");

        fromDate.setDateFormatString("yyyy-MM-dd");

        toDate.setDateFormatString("yyyy-MM-dd");

        btnExit.setBackground(new java.awt.Color(255, 0, 0));
        btnExit.setText("Exit");
        btnExit.setActionCommand("EXIT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnCl.setText("Clear");
        btnCl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(251, 251, 251)
                .addComponent(btnCl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(3, 3, 3)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnExit)
                        .addComponent(btnCl)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Patient Types");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Contrast");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 171, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Location");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 31, Short.MAX_VALUE)
        );

        chkLocations.setText("All Locations");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addGap(13, 13, 13)
                .addComponent(comboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chkLocations)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(chkLocations)))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("History wise Scan Reports"));

        cmbPreDefHist.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pain", "Bleeding", "Head Injury" }));

        btnHistReport.setText("Report");
        btnHistReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(cmbPreDefHist, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnHistReport)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHistReport)
                    .addComponent(cmbPreDefHist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("CPT wise Scan Reports"));

        btnCPTReport.setText("Report");
        btnCPTReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCPTReportActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addComponent(comboCPT, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(btnCPTReport)
                .addGap(21, 21, 21))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCPTReport)
                    .addComponent(comboCPT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder("Referring Doctor Wise Scan"));

        btnReferringDoc.setText("Report");
        btnReferringDoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReferringDocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(cmbReferringDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addComponent(btnReferringDoc)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReferringDoc)
                    .addComponent(cmbReferringDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Finding wise Scan Reports"));

        btnFinding.setText("Report");
        btnFinding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindingActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Finding wise Scan Report");

        cmbFindings.setEditable(true);
        cmbFindings.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Normal", "Abnormal", "Referred" }));
        cmbFindings.setEnabled(false);
        cmbFindings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFindingsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmbFindings, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnFinding)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFinding)
                    .addComponent(jLabel1)
                    .addComponent(cmbFindings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Patient Type wise Scan Reports"));

        btnPtnType.setText("Report");
        btnPtnType.setEnabled(false);
        btnPtnType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPtnTypeActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Patient Type wise Scan Report");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(btnPtnType)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPtnType)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(18, 18, 18))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(26, 26, 26)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap(86, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(35, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(186, 186, 186))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        this.dispose();

    }//GEN-LAST:event_btnExitActionPerformed

    private void btnClActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClActionPerformed
        toDate.setDate(new Date());
        fromDate.setDate(new Date());
        fillReferringDocLov(Constants.locationID);
    }//GEN-LAST:event_btnClActionPerformed

    private void btnCPTReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCPTReportActionPerformed
        //getReport("cpt.id");
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        String from = "", to = "";
        try {
            SimpleDateFormat simpledatafo = new SimpleDateFormat("yyyy-MM-dd");
            from = simpledatafo.format(this.fromDate.getDate());
            to = simpledatafo.format(this.toDate.getDate());
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Missing Date(s). Kindly Provide Correct Date");
            return;
        }
        if (jcc2.contrast.length() > 0) {
            if (jcc2.contrast.substring(jcc2.contrast.length() - 1, jcc2.contrast.length()).equalsIgnoreCase(",")) {
                jcc2.contrast = jcc2.contrast.substring(0, jcc2.contrast.length() - 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Select Contrast Value");
            return;
        }
        if (jcc.patientType.length() > 0) {
            if (jcc.patientType.substring(jcc.patientType.length() - 1, jcc.patientType.length()).equalsIgnoreCase(",")) {
                jcc.patientType = jcc.patientType.substring(0, jcc.patientType.length() - 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Select at least one Patient Type");
            return;
        }

        jcc2.contrast = jcc2.contrast.replace("With Contrast", "'Y'");
        jcc2.contrast = jcc2.contrast.replace("Without Contrast", "'N',''");

        for (String key : patientTypeMap.keySet()) {
            PatientTypeBo pbo = patientTypeMap.get(key);
            jcc.patientType = jcc.patientType.replace(pbo.getDesc(), "'" + pbo.getId() + "'");
        }
        String locationId = "";
        if (!chkLocations.isSelected()) {
            for (String key : lhm.keySet()) {
                if (lhm.get(key).equalsIgnoreCase(comboLoc.getSelectedItem().toString())) {
                    locationId = key;
                    break;
                }
            }
        }
        System.out.println(locationId + "\n" + jcc.patientType + "\n" + jcc2.contrast);
        patientSummaryMap.clear();
        patientDetailMap.clear();

        String reportKey = "cpt.id";
        String cptid = getCptId(comboCPT.getSelectedItem().toString());
        reportKey += "='" + cptid + "'";

        dam.getCPTSummary(from, to, locationId, jcc.patientType, jcc2.contrast, patientSummaryMap, reportKey);
        dam.getCPTDetails(from, to, locationId, jcc.patientType, jcc2.contrast, patientDetailMap, reportKey);

        if (patientSummaryMap.size() > 0) {
            CPTwiseSummaryReport report = new CPTwiseSummaryReport();
            //String path = report.generateOrder(comboLoc.getSelectedItem().toString(), from, to, patientSummaryMap, patientDetailMap, comboCPT.getSelectedItem().toString());
            String locationToPrint = "";
            if (chkLocations.isSelected()) {
                locationToPrint = "ALL";
            } else {
                locationToPrint = comboLoc.getSelectedItem().toString();
            }
            String path = report.generateOrder(locationToPrint, from, to, patientSummaryMap, patientDetailMap, comboCPT.getSelectedItem().toString());
            
            if (!path.equals("")) {
                try {
                    File pdf = new File(path);
                    Desktop.getDesktop().open(pdf);
                } catch (IOException ex) {
                    Logger.getLogger(OrderEntryMultipleCpts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No Record Found");
        }
        System.gc();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnCPTReportActionPerformed

    private void btnHistReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistReportActionPerformed
        // TODO add your handling code here:
        // getReport("om.predefined_hist");
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        String from = "", to = "";
        try {
            SimpleDateFormat simpledatafo = new SimpleDateFormat("yyyy-MM-dd");
            from = simpledatafo.format(this.fromDate.getDate());
            to = simpledatafo.format(this.toDate.getDate());
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Missing Date(s). Kindly Provide Correct Date");
            return;
        }
        if (jcc2.contrast.length() > 0) {
            if (jcc2.contrast.substring(jcc2.contrast.length() - 1, jcc2.contrast.length()).equalsIgnoreCase(",")) {
                jcc2.contrast = jcc2.contrast.substring(0, jcc2.contrast.length() - 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Select Contrast Value");
            return;
        }
        if (jcc.patientType.length() > 0) {
            if (jcc.patientType.substring(jcc.patientType.length() - 1, jcc.patientType.length()).equalsIgnoreCase(",")) {
                jcc.patientType = jcc.patientType.substring(0, jcc.patientType.length() - 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Select at least one Patient Type");
            return;
        }

        jcc2.contrast = jcc2.contrast.replace("With Contrast", "'Y'");
        jcc2.contrast = jcc2.contrast.replace("Without Contrast", "'N',''");

        for (String key : patientTypeMap.keySet()) {
            PatientTypeBo pbo = patientTypeMap.get(key);
            jcc.patientType = jcc.patientType.replace(pbo.getDesc(), "'" + pbo.getId() + "'");
        }
        String locationId = "";
        if (!chkLocations.isSelected()) {
            for (String key : lhm.keySet()) {
                if (lhm.get(key).equalsIgnoreCase(comboLoc.getSelectedItem().toString())) {
                    locationId = key;
                    break;
                }
            }
        }
        System.out.println(locationId + "\n" + jcc.patientType + "\n" + jcc2.contrast);
        patientSummaryMap.clear();
        patientDetailMap.clear();

        String reportKey = "";
//        String cptid = getCptId(comboCPT.getSelectedItem().toString());
//        reportKey += "='" + cptid + "'";

        String history = cmbPreDefHist.getSelectedItem().toString();
        if (history.equalsIgnoreCase("**select**")) {
            JOptionPane.showMessageDialog(this, "Please Select Relevant Predefined History.");
            return;
        }
        reportKey = "lower(om.predefined_hist)='" + history.toLowerCase() + "'";

        dam.getCPTSummary(from, to, locationId, jcc.patientType, jcc2.contrast, patientSummaryMap, reportKey);
        dam.getCPTDetails(from, to, locationId, jcc.patientType, jcc2.contrast, patientDetailMap, reportKey);

        if (patientSummaryMap.size() > 0) {
            HistoryWiseSummaryReport report = new HistoryWiseSummaryReport();
            String locationToPrint = "";
            if (chkLocations.isSelected()) {
                locationToPrint = "ALL";
            } else {
                locationToPrint = comboLoc.getSelectedItem().toString();
            }
            String path = report.generateOrder(locationToPrint, from, to, patientSummaryMap, patientDetailMap, history);
            if (!path.equals("")) {
                try {
                    File pdf = new File(path);
                    Desktop.getDesktop().open(pdf);
                } catch (IOException ex) {
                    Logger.getLogger(OrderEntryMultipleCpts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No Record Found");
        }
        System.gc();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnHistReportActionPerformed

    private void btnReferringDocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReferringDocActionPerformed
        // TODO add your handling code here:
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        String from = "", to = "";
        try {
            SimpleDateFormat simpledatafo = new SimpleDateFormat("yyyy-MM-dd");
            from = simpledatafo.format(this.fromDate.getDate());
            to = simpledatafo.format(this.toDate.getDate());
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Missing Date(s). Kindly Provide Correct Date");
            return;
        }
        if (jcc2.contrast.length() > 0) {
            if (jcc2.contrast.substring(jcc2.contrast.length() - 1, jcc2.contrast.length()).equalsIgnoreCase(",")) {
                jcc2.contrast = jcc2.contrast.substring(0, jcc2.contrast.length() - 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Select Contrast Value");
            return;
        }
        if (jcc.patientType.length() > 0) {
            if (jcc.patientType.substring(jcc.patientType.length() - 1, jcc.patientType.length()).equalsIgnoreCase(",")) {
                jcc.patientType = jcc.patientType.substring(0, jcc.patientType.length() - 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Select at least one Patient Type");
            return;
        }

        jcc2.contrast = jcc2.contrast.replace("With Contrast", "'Y'");
        jcc2.contrast = jcc2.contrast.replace("Without Contrast", "'N',''");

        for (String key : patientTypeMap.keySet()) {
            PatientTypeBo pbo = patientTypeMap.get(key);
            jcc.patientType = jcc.patientType.replace(pbo.getDesc(), "'" + pbo.getId() + "'");
        }
        String locationId = "";
        if (!chkLocations.isSelected()) {
            for (String key : lhm.keySet()) {
                if (lhm.get(key).equalsIgnoreCase(comboLoc.getSelectedItem().toString())) {
                    locationId = key;
                    break;
                }
            }
        }
        System.out.println(locationId + "\n" + jcc.patientType + "\n" + jcc2.contrast);
        patientSummaryMap.clear();
        patientDetailMap.clear();

        String reportKey = "";
//        String cptid = getCptId(comboCPT.getSelectedItem().toString());
//        reportKey += "='" + cptid + "'";

        String referringDocId = cmbReferringDoc.getSelectedItem().toString();
        referringDocId = referringDocId.split(" : ")[1];
        String referringDocName = cmbReferringDoc.getSelectedItem().toString().split(" : ")[0];
        if (referringDocId.equalsIgnoreCase("-")) {
            JOptionPane.showMessageDialog(this, "Please Select Referring Doctor");
            return;
        }
        reportKey = "om.ref_phy=" + referringDocId.toLowerCase() + "";

        dam.getReferralWiseSummary(from, to, locationId, jcc.patientType, jcc2.contrast, patientSummaryMap, reportKey);
        dam.getCPTDetails(from, to, locationId, jcc.patientType, jcc2.contrast, patientDetailMap, reportKey);

        if (patientSummaryMap.size() > 0) {
            ReferringDoctorSummaryReport report = new ReferringDoctorSummaryReport();
            String locationToPrint = "";
            if (chkLocations.isSelected()) {
                locationToPrint = "ALL";
            } else {
                locationToPrint = comboLoc.getSelectedItem().toString();
            }
            String path = report.generateOrder(locationToPrint, from, to, patientSummaryMap, patientDetailMap, referringDocName);
            
            if (!path.equals("")) {
                try {
                    File pdf = new File(path);
                    Desktop.getDesktop().open(pdf);
                } catch (IOException ex) {
                    Logger.getLogger(OrderEntryMultipleCpts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No Record Found");
        }
        System.gc();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnReferringDocActionPerformed

    private void btnFindingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindingActionPerformed
        // TODO add your handling code here:
//        String reportKey = cmbFindings.getSelectedItem().toString();
//        if(reportKey.equalsIgnoreCase("-"))
//        {
//            JOptionPane.showMessageDialog(this, "Please select a Findong");
//            return;
//        }
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        String from = "", to = "";
        try {
            SimpleDateFormat simpledatafo = new SimpleDateFormat("yyyy-MM-dd");
            from = simpledatafo.format(this.fromDate.getDate());
            to = simpledatafo.format(this.toDate.getDate());
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Missing Date(s). Kindly Provide Correct Date");
            return;
        }
        if (jcc2.contrast.length() > 0) {
            if (jcc2.contrast.substring(jcc2.contrast.length() - 1, jcc2.contrast.length()).equalsIgnoreCase(",")) {
                jcc2.contrast = jcc2.contrast.substring(0, jcc2.contrast.length() - 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Select Contrast Value");
            return;
        }
        if (jcc.patientType.length() > 0) {
            if (jcc.patientType.substring(jcc.patientType.length() - 1, jcc.patientType.length()).equalsIgnoreCase(",")) {
                jcc.patientType = jcc.patientType.substring(0, jcc.patientType.length() - 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Select at least one Patient Type");
            return;
        }

        jcc2.contrast = jcc2.contrast.replace("With Contrast", "'Y'");
        jcc2.contrast = jcc2.contrast.replace("Without Contrast", "'N'");

        for (String key : patientTypeMap.keySet()) {
            PatientTypeBo pbo = patientTypeMap.get(key);
            jcc.patientType = jcc.patientType.replace(pbo.getDesc(), "'" + pbo.getId() + "'");
        }
        String locationId = "";
        if (!chkLocations.isSelected()) {
            for (String key : lhm.keySet()) {
                if (lhm.get(key).equalsIgnoreCase(comboLoc.getSelectedItem().toString())) {
                    locationId = key;
                    break;
                }
            }
        }
        System.out.println(locationId + "\n" + jcc.patientType + "\n" + jcc2.contrast);
        patientSummaryMap.clear();
        patientDetailMap.clear();

        String reportKey = "";

        String referringDoc = cmbReferringDoc.getSelectedItem().toString();

        reportKey = "1=1";

        dam.getReferralWiseSummary(from, to, locationId, jcc.patientType, jcc2.contrast, patientSummaryMap, reportKey);
        dam.getFindingWiseDetail(from, to, locationId, jcc.patientType, jcc2.contrast, patientDetailMap, reportKey);

        if (patientSummaryMap.size() > 0) {
            FindingWiseSummaryReport report = new FindingWiseSummaryReport();
            String locationToPrint = "";
            if (chkLocations.isSelected()) {
                locationToPrint = "ALL";
            } else {
                locationToPrint = comboLoc.getSelectedItem().toString();
            }
            String path = report.generateOrder(locationToPrint, from, to, patientSummaryMap, patientDetailMap, referringDoc);
            if (!path.equals("")) {
                try {
                    File pdf = new File(path);
                    Desktop.getDesktop().open(pdf);
                } catch (IOException ex) {
                    Logger.getLogger(OrderEntryMultipleCpts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No Record Found");
        }
        patientSummaryMap.clear();
        patientDetailMap.clear();
        System.gc();
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        
    }//GEN-LAST:event_btnFindingActionPerformed

    private void btnPtnTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPtnTypeActionPerformed
        // TODO add your handling code here:
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        String from = "", to = "";
        try {
            SimpleDateFormat simpledatafo = new SimpleDateFormat("yyyy-MM-dd");
            from = simpledatafo.format(this.fromDate.getDate());
            to = simpledatafo.format(this.toDate.getDate());
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Missing Date(s). Kindly Provide Correct Date");
            return;
        }
        if (jcc2.contrast.length() > 0) {
            if (jcc2.contrast.substring(jcc2.contrast.length() - 1, jcc2.contrast.length()).equalsIgnoreCase(",")) {
                jcc2.contrast = jcc2.contrast.substring(0, jcc2.contrast.length() - 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Select Contrast Value");
            return;
        }
        if (jcc.patientType.length() > 0) {
            if (jcc.patientType.substring(jcc.patientType.length() - 1, jcc.patientType.length()).equalsIgnoreCase(",")) {
                jcc.patientType = jcc.patientType.substring(0, jcc.patientType.length() - 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Select at least one Patient Type");
            return;
        }

        jcc2.contrast = jcc2.contrast.replace("With Contrast", "'Y'");
        jcc2.contrast = jcc2.contrast.replace("Without Contrast", "'N',''");

        for (String key : patientTypeMap.keySet()) {
            PatientTypeBo pbo = patientTypeMap.get(key);
            jcc.patientType = jcc.patientType.replace(pbo.getDesc(), "'" + pbo.getId() + "'");
        }
        String locationId = "";
        if (!chkLocations.isSelected()) {
            for (String key : lhm.keySet()) {
                if (lhm.get(key).equalsIgnoreCase(comboLoc.getSelectedItem().toString())) {
                    locationId = key;
                    break;
                }
            }
        }
        System.out.println(locationId + "\n" + jcc.patientType + "\n" + jcc2.contrast);
        patientSummaryMap.clear();
        patientDetailMap.clear();

        String reportKey = "";

        String referringDoc = cmbReferringDoc.getSelectedItem().toString();

        reportKey = "1=1";

        dam.getCPTSummary(from, to, locationId, jcc.patientType, jcc2.contrast, patientSummaryMap, reportKey);
        dam.getCPTDetails(from, to, locationId, jcc.patientType, jcc2.contrast, patientDetailMap, reportKey);

        if (patientSummaryMap.size() > 0) {
            PatientNatureWiseSummaryReport report = new PatientNatureWiseSummaryReport();
            String path = report.generateOrder(comboLoc.getSelectedItem().toString(), from, to, patientSummaryMap, patientDetailMap, referringDoc);
            if (!path.equals("")) {
                try {
                    File pdf = new File(path);
                    Desktop.getDesktop().open(pdf);
                } catch (IOException ex) {
                    Logger.getLogger(OrderEntryMultipleCpts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No Record Found");
        }
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btnPtnTypeActionPerformed

    private void cmbFindingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFindingsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbFindingsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCPTReport;
    private javax.swing.JButton btnCl;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnFinding;
    private javax.swing.JButton btnHistReport;
    private javax.swing.JButton btnPtnType;
    private javax.swing.JButton btnReferringDoc;
    private javax.swing.JCheckBox chkLocations;
    private javax.swing.JComboBox<String> cmbFindings;
    private javax.swing.JComboBox<String> cmbPreDefHist;
    private javax.swing.JComboBox<String> cmbReferringDoc;
    private javax.swing.JComboBox<String> comboCPT;
    private javax.swing.JComboBox<String> comboLoc;
    private com.toedter.calendar.JDateChooser fromDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private com.toedter.calendar.JDateChooser toDate;
    // End of variables declaration//GEN-END:variables

    private void fillCPTCombo() {
        comboCPT.removeAllItems();
        cptMap.clear();

        dam.getCptInformation(cptMap);

        if (cptMap.size() > 0) {
            populateCmbCpt();
        }
    }

    private void populateCmbCpt() {

        for (String key : cptMap.keySet()) {
            comboCPT.addItem(cptMap.get(key));
        }
    }

    private String getCptId(String value) {
        String id = "";

        for (String key : cptMap.keySet()) {
            if (cptMap.get(key).equalsIgnoreCase(value)) {
                id = key;
                break;
            }
        }

        return id;
    }

    private void fillPreDedinedHist() {
        cmbPreDefHist.removeAllItems();
        for (String s : Constants.preDefinedHist) {
            cmbPreDefHist.addItem(s);
        }
    }

    private void getReport(String reportKey) {
        String from = "", to = "";
        try {
            SimpleDateFormat simpledatafo = new SimpleDateFormat("yyyy-MM-dd");
            from = simpledatafo.format(this.fromDate.getDate());
            to = simpledatafo.format(this.toDate.getDate());
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(this, "Missing Date(s). Kindly Provide Correct Date");
            return;
        }
        if (jcc2.contrast.length() > 0) {
            if (jcc2.contrast.substring(jcc2.contrast.length() - 1, jcc2.contrast.length()).equalsIgnoreCase(",")) {
                jcc2.contrast = jcc2.contrast.substring(0, jcc2.contrast.length() - 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Select Contrast Value");
            return;
        }
        if (jcc.patientType.length() > 0) {
            if (jcc.patientType.substring(jcc.patientType.length() - 1, jcc.patientType.length()).equalsIgnoreCase(",")) {
                jcc.patientType = jcc.patientType.substring(0, jcc.patientType.length() - 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Select at least one Patient Type");
            return;
        }

        jcc2.contrast = jcc2.contrast.replace("With Contrast", "'Y'");
        jcc2.contrast = jcc2.contrast.replace("Without Contrast", "'N',''");

        for (String key : patientTypeMap.keySet()) {
            PatientTypeBo pbo = patientTypeMap.get(key);
            jcc.patientType = jcc.patientType.replace(pbo.getDesc(), "'" + pbo.getId() + "'");
        }
        String locationId = "";
        if (!chkLocations.isSelected()) {
            for (String key : lhm.keySet()) {
                if (lhm.get(key).equalsIgnoreCase(comboLoc.getSelectedItem().toString())) {
                    locationId = key;
                    break;
                }
            }
        }
        System.out.println(locationId + "\n" + jcc.patientType + "\n" + jcc2.contrast);
        patientSummaryMap.clear();
        patientDetailMap.clear();
        String searchParam = "";

        if (reportKey.equalsIgnoreCase("cpt.id")) {
            String cptid = getCptId(comboCPT.getSelectedItem().toString());
            reportKey += "='" + cptid + "'";

        } else if (reportKey.equalsIgnoreCase("om.predefined_hist")) {
            String history = cmbPreDefHist.getSelectedItem().toString();
            if (history.equalsIgnoreCase("**select**")) {
                JOptionPane.showMessageDialog(this, "Please Select Relevant Predefined History.");
                return;
            }
            reportKey = "lower(om.predefined_hist)='" + history.toLowerCase() + "'";

        }

        dam.getCPTSummary(from, to, locationId, jcc.patientType, jcc2.contrast, patientSummaryMap, reportKey);
        dam.getCPTDetails(from, to, locationId, jcc.patientType, jcc2.contrast, patientDetailMap, reportKey);

        if (patientSummaryMap.size() > 0) {
            CPTwiseSummaryReport report = new CPTwiseSummaryReport();
            String path = report.generateOrder(comboLoc.getSelectedItem().toString(), from, to, patientSummaryMap, patientDetailMap, comboCPT.getSelectedItem().toString());
            if (!path.equals("")) {
                try {
                    File pdf = new File(path);
                    Desktop.getDesktop().open(pdf);
                } catch (IOException ex) {
                    Logger.getLogger(OrderEntryMultipleCpts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No Record Found");
        }
    }

}
