/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.src;

import app.ris.bo.Constants;
import app.ris.bo.LocationBO;
import app.ris.bo.OrderReportBO;
import app.ris.bo.PerformanceSummaryBo;
import app.ris.dam.dam;
import app.ris.reporting.CounterDetails;
import app.ris.reporting.CounterSummary;
import app.ris.reporting.FinalSignedDetail;
import app.ris.reporting.FinalSignedSummary;
import app.ris.reporting.FullSummary;
import app.ris.reporting.ImagesReceivedDetail;
import app.ris.reporting.ImagesReceivedSummary;
import app.ris.reporting.OrderGenerationDetail;
import app.ris.reporting.OrderGenerationSummary;
import app.ris.reporting.PendingResultsDetail;
import app.ris.reporting.PendingResultsSummary;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

/**
 *

 */
public class AdminReportingPanel extends javax.swing.JInternalFrame  {
    LinkedHashMap <String,OrderReportBO> orderReportMap = new  LinkedHashMap<>();
    LinkedHashMap <String,Integer> orderModalityMap = new  LinkedHashMap<>();
    LinkedHashMap <String,Integer> receivedModalityMap = new  LinkedHashMap<>();
    LinkedHashMap <String,Integer> techAckMap = new  LinkedHashMap<>();
    LinkedHashMap <String,Integer> finalSignedModalityMap = new  LinkedHashMap<>();
    LinkedHashMap <String,PerformanceSummaryBo> summaryMap = new  LinkedHashMap<>();
    ArrayList<String> list=new ArrayList<String>();  
    dam dam = new dam();
    LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
   /**
     * Creates new form Reports
     */
    public AdminReportingPanel() {
        initComponents();
        this.setVisible(true);
        Dimension desktopSize = Constants.pane1.getSize();
        Dimension jInternalFrameSize = this.getSize();
        this.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
            (desktopSize.height- jInternalFrameSize.height)/2);
        
        
        ButtonGroup reportGroup = new ButtonGroup();
        reportGroup.add(radioFinal);
        reportGroup.add(radioOrders);
        reportGroup.add(radioPending);
        reportGroup.add(radioTech);
        reportGroup.add(radioFullReport);
        reportGroup.add(radioCounterAck);
        radioOrders.setSelected(true);
        
        
        if (Constants.locationMap.size() > 0) {
            for (String key : Constants.locationMap.keySet()) {
                LocationBO lb = Constants.locationMap.get(key);
                lhm.put(key, lb.getName());
                if (lb.getActive().equalsIgnoreCase("Y")) {
                    comboLocation.addItem(lhm.get(key).toString());
                }
            }
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
        jPanel3 = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        btnSummary = new javax.swing.JButton();
        btnDetails = new javax.swing.JButton();
        btnCl = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        fromDate = new com.toedter.calendar.JDateChooser();
        toDate = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        comboLocation = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        radioOrders = new javax.swing.JRadioButton();
        radioTech = new javax.swing.JRadioButton();
        radioPending = new javax.swing.JRadioButton();
        radioFinal = new javax.swing.JRadioButton();
        radioFullReport = new javax.swing.JRadioButton();
        radioCounterAck = new javax.swing.JRadioButton();

        setMaximizable(true);
        setResizable(true);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel21.setText("Order Status Reports");

        btnExit.setBackground(new java.awt.Color(255, 0, 0));
        btnExit.setText("Exit");
        btnExit.setActionCommand("EXIT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnSummary.setText("Summary");
        btnSummary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSummaryActionPerformed(evt);
            }
        });

        btnDetails.setText("Details");
        btnDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetailsActionPerformed(evt);
            }
        });

        btnCl.setText("Clear");
        btnCl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnSummary, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExit)
                    .addComponent(btnSummary)
                    .addComponent(btnDetails)
                    .addComponent(btnCl))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("From Date");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("To Date");

        fromDate.setDateFormatString("yyyy-MM-dd");

        toDate.setDateFormatString("yyyy-MM-dd");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Location:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboLocation, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jLabel16)
                        .addComponent(comboLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        radioOrders.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioOrders.setText("Ordered");

        radioTech.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioTech.setText("Tech Acknoeledge Queue");

        radioPending.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioPending.setText("Pending Reports");

        radioFinal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioFinal.setText("Final Signed");

        radioFullReport.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioFullReport.setText("Performance Report");

        radioCounterAck.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        radioCounterAck.setText("Counter Acknowledged");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioPending)
                    .addComponent(radioOrders))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioFinal)
                    .addComponent(radioCounterAck))
                .addGap(45, 45, 45)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radioFullReport)
                    .addComponent(radioTech))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioOrders)
                    .addComponent(radioTech)
                    .addComponent(radioCounterAck))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioFullReport)
                    .addComponent(radioFinal)
                    .addComponent(radioPending))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 38, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
//        Constants.bgFrame.show();
        this.dispose();

    }//GEN-LAST:event_btnExitActionPerformed

    private void btnSummaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSummaryActionPerformed
        String from="",to="";
        orderModalityMap.clear();
        finalSignedModalityMap.clear();
        receivedModalityMap.clear();
        techAckMap.clear();
        String locationId  = getLocationId();
        orderReportMap.clear();
        try{
            SimpleDateFormat simpledatafo = new SimpleDateFormat("yyyy-MM-dd");
            from= simpledatafo.format(this.fromDate.getDate());
            to = simpledatafo.format(this.toDate.getDate());
        }catch(java.lang.NullPointerException e)
        {
            JOptionPane.showMessageDialog(this, "Missing Date(s). Kindly Provide Correct Date");
            return;
        }
        
        
        if(from.equalsIgnoreCase("") || to.equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(this, "Please provide From and To Dates");
            return;
        }
        
        if(radioFinal.isSelected())
            finalSignedSummary(from,to,locationId);
        else if(radioOrders.isSelected())
            orderSummary(from,to,locationId);
        else if(radioPending.isSelected())
            pendingSummary(from,to,locationId);
        else if(radioTech.isSelected())
            receivedSummary(from,to,locationId);
        else if(radioFullReport.isSelected())
            fullReportSummary(from,to,locationId);
        else if(radioCounterAck.isSelected())
            counterSummary(from, to, locationId);
    }//GEN-LAST:event_btnSummaryActionPerformed

    private void btnDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetailsActionPerformed

        orderModalityMap.clear();
        finalSignedModalityMap.clear();
        receivedModalityMap.clear();
        techAckMap.clear();
        String locationId  = getLocationId();
        orderReportMap.clear();
        String fromDate="",toDate="";
        try{
            SimpleDateFormat simpledatafo = new SimpleDateFormat("yyyy-MM-dd");
            fromDate= simpledatafo.format(this.fromDate.getDate());
            toDate = simpledatafo.format(this.toDate.getDate());
        }catch(java.lang.NullPointerException e)
        {
            JOptionPane.showMessageDialog(this, "Incorrect Date(s). Kindly Provide Correct Date");
            return;
        }
        if(fromDate.equalsIgnoreCase("") || toDate.equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(this, "Please provide From and To Dates");
            return;
        }
        
        if(radioFinal.isSelected())
            finalSignedDetail(fromDate,toDate,locationId);
        else if(radioOrders.isSelected())
            orderDetail(fromDate,toDate,locationId);
        else if(radioPending.isSelected())
            pendingDetail(fromDate,toDate,locationId);
        else if(radioTech.isSelected())
            receivedDetail(fromDate,toDate,locationId);
        else if(radioCounterAck.isSelected())
            counterDetail(fromDate, toDate, locationId);
        else if(radioFullReport.isSelected())
        {
            JOptionPane.showMessageDialog(this, "Only Summary exists for selected Option.");
        }
    }//GEN-LAST:event_btnDetailsActionPerformed

    private void btnClActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClActionPerformed
        clear();
    }//GEN-LAST:event_btnClActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCl;
    private javax.swing.JButton btnDetails;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnSummary;
    private javax.swing.JComboBox<String> comboLocation;
    private com.toedter.calendar.JDateChooser fromDate;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton radioCounterAck;
    private javax.swing.JRadioButton radioFinal;
    private javax.swing.JRadioButton radioFullReport;
    private javax.swing.JRadioButton radioOrders;
    private javax.swing.JRadioButton radioPending;
    private javax.swing.JRadioButton radioTech;
    private com.toedter.calendar.JDateChooser toDate;
    // End of variables declaration//GEN-END:variables

    private void clear() {
        fromDate.setDate(null);
        toDate.setDate(null);
        radioOrders.setSelected(true);
    }

    private void finalSignedSummary(String fromDate, String toDate,String locationId) {
       orderReportMap.clear();
        if(dam.getPendingResults(fromDate,toDate,orderReportMap,"6",locationId))
        {
            for(String key:orderReportMap.keySet())
            {
                OrderReportBO orb = orderReportMap.get(key);
                int count = 0;
                try{
                    
                    {
                        count = finalSignedModalityMap.get(orb.getModality())+1;
                        finalSignedModalityMap.put(orb.getModality(), count);
                    }
                }
                catch(NullPointerException e)
                {
                    
                    finalSignedModalityMap.putIfAbsent(orb.getModality(), 1);
                }
            }
            if(finalSignedModalityMap.size()>0)
            {
                FinalSignedSummary ogs = new FinalSignedSummary();
                String filePath = ogs.generateOrder(finalSignedModalityMap,fromDate,toDate,locationId);
                
                try {
                    File pdf = new File(filePath);
                    Desktop.getDesktop().open(pdf);
                } catch (IOException ex) {
                    Logger.getLogger(OrderEntryMultipleCpts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No Record Found against specified criteria.");
        } 
    }

    private void orderSummary(String fromDate, String toDate,String locationId) {
        orderReportMap.clear();
        if(dam.getRadiologyOrders(fromDate,toDate,orderReportMap,locationId))
        {
            for(String key:orderReportMap.keySet())
            {
                OrderReportBO orb = orderReportMap.get(key);
                int count = 0;
                try{
                    count = orderModalityMap.get(orb.getModality())+1;
                    orderModalityMap.put(orb.getModality(), count);
                }
                catch(NullPointerException e)
                {
                    orderModalityMap.putIfAbsent(orb.getModality(), 1);
                }
                
                
            }
            if(orderModalityMap.size()>0)
            {
                OrderGenerationSummary ogs = new OrderGenerationSummary();
                String filePath = ogs.generateOrder(orderModalityMap,fromDate,toDate,locationId);
                
                try {
                    File pdf = new File(filePath);
                    Desktop.getDesktop().open(pdf);
                } catch (IOException ex) {
                    Logger.getLogger(OrderEntryMultipleCpts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No Record Found against specified criteria.");
        }
    }

    private void receivedSummary(String fromDate, String toDate,String locationId) {
        orderReportMap.clear();
        if(dam.getPendingResults(fromDate,toDate,orderReportMap,"3",locationId))
        {
            for(String key:orderReportMap.keySet())
            {
                OrderReportBO orb = orderReportMap.get(key);
                int count = 0;
                try{
                    count = receivedModalityMap.get(orb.getModality())+1;
                    receivedModalityMap.put(orb.getModality(), count);
                }
                catch(NullPointerException e)
                {
                    receivedModalityMap.putIfAbsent(orb.getModality(), 1);
                }
            }
            if(receivedModalityMap.size()>0)
            {
                ImagesReceivedSummary ogs = new ImagesReceivedSummary();
                String filePath = ogs.generateOrder(receivedModalityMap,fromDate,toDate,locationId);
                
                try {
                    File pdf = new File(filePath);
                    Desktop.getDesktop().open(pdf);
                } catch (IOException ex) {
                    Logger.getLogger(OrderEntryMultipleCpts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No Record Found against specified criteria.");
        }
    }
    
    private void counterSummary(String fromDate, String toDate,String locationId) {
        orderReportMap.clear();
        if(dam.getPendingResults(fromDate,toDate,orderReportMap,"2",locationId))
        {
            for(String key:orderReportMap.keySet())
            {
                OrderReportBO orb = orderReportMap.get(key);
                int count = 0;
                try{
                    count = receivedModalityMap.get(orb.getModality())+1;
                    receivedModalityMap.put(orb.getModality(), count);
                }
                catch(NullPointerException e)
                {
                    receivedModalityMap.putIfAbsent(orb.getModality(), 1);
                }
            }
            if(receivedModalityMap.size()>0)
            {
                CounterSummary ogs = new CounterSummary();
                String filePath = ogs.generateOrder(receivedModalityMap,fromDate,toDate,locationId);
                
                try {
                    File pdf = new File(filePath);
                    Desktop.getDesktop().open(pdf);
                } catch (IOException ex) {
                    Logger.getLogger(OrderEntryMultipleCpts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No Record Found against specified criteria.");
        }
    }

    private void pendingSummary(String fromDate, String toDate,String locationId) {
        orderReportMap.clear();
        if(dam.getPendingResults(fromDate,toDate,orderReportMap,"4','5",locationId))
        {
            for(String key:orderReportMap.keySet())
            {
                OrderReportBO orb = orderReportMap.get(key);
                int count = 0;
                try{
                    
                    {
                        count = techAckMap.get(orb.getModality())+1;
                        techAckMap.put(orb.getModality(), count);
                    }
                }
                catch(NullPointerException e)
                {
                    
                    techAckMap.putIfAbsent(orb.getModality(), 1);
                }
            }
            if(techAckMap.size()>0)
            {
                PendingResultsSummary ogs = new PendingResultsSummary();
                String filePath = ogs.generateOrder(techAckMap,fromDate,toDate,locationId);
                
                try {
                    File pdf = new File(filePath);
                    Desktop.getDesktop().open(pdf);
                } catch (IOException ex) {
                    Logger.getLogger(OrderEntryMultipleCpts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No Record Found against specified criteria.");
        }
    }

    private void receivedDetail(String fromDate, String toDate,String locationId) {
        orderReportMap.clear();
        if(dam.getPendingResults(fromDate,toDate,orderReportMap,"3",locationId))
        {
            for(String key:orderReportMap.keySet())
            {
                OrderReportBO orb = orderReportMap.get(key);
                int count = 0;
                try{
                    count = receivedModalityMap.get(orb.getModality())+1;
                    receivedModalityMap.put(orb.getModality(), count);
                    
                }
                catch(NullPointerException e)
                {
                    receivedModalityMap.putIfAbsent(orb.getModality(), 1);
                }
                
                
            }
            if(receivedModalityMap.size()>0)
            {
                ImagesReceivedDetail ogs = new ImagesReceivedDetail();
                String filePath = ogs.generateOrder(receivedModalityMap,orderReportMap,fromDate,toDate,locationId);
                
                try {
                    File pdf = new File(filePath);
                    Desktop.getDesktop().open(pdf);
                } catch (IOException ex) {
                    Logger.getLogger(OrderEntryMultipleCpts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No Record Found against specified criteria.");
        }
    }
    
    private void counterDetail(String fromDate, String toDate,String locationId) {
        orderReportMap.clear();
        if(dam.getPendingResults(fromDate,toDate,orderReportMap,"2",locationId))
        {
            for(String key:orderReportMap.keySet())
            {
                OrderReportBO orb = orderReportMap.get(key);
                int count = 0;
                try{
                    count = receivedModalityMap.get(orb.getModality())+1;
                    receivedModalityMap.put(orb.getModality(), count);
                    
                }
                catch(NullPointerException e)
                {
                    receivedModalityMap.putIfAbsent(orb.getModality(), 1);
                }
                
                
            }
            if(receivedModalityMap.size()>0)
            {
                CounterDetails ogs = new CounterDetails();
                String filePath = ogs.generateOrder(receivedModalityMap,orderReportMap,fromDate,toDate,locationId);
                
                try {
                    File pdf = new File(filePath);
                    Desktop.getDesktop().open(pdf);
                } catch (IOException ex) {
                    Logger.getLogger(OrderEntryMultipleCpts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No Record Found against specified criteria.");
        }
    }

    private void pendingDetail(String fromDate, String toDate,String locationId) {
        orderReportMap.clear();
        if(dam.getPendingResults(fromDate,toDate,orderReportMap,"4','5",locationId))
        {
            for(String key:orderReportMap.keySet())
            {
                OrderReportBO orb = orderReportMap.get(key);
                int count = 0;
                
                try{
                    count = techAckMap.get(orb.getModality())+1;
                    techAckMap.put(orb.getModality(), count);
                    
                }
                catch(NullPointerException e)
                {
                    
                    techAckMap.putIfAbsent(orb.getModality(), 1);
                }
                
                
            }
            if(techAckMap.size()>0)
            {
                PendingResultsDetail ogs = new PendingResultsDetail();
                String filePath = ogs.generateOrder(techAckMap,orderReportMap,fromDate,toDate,locationId);
                
                try {
                    File pdf = new File(filePath);
                    Desktop.getDesktop().open(pdf);
                } catch (IOException ex) {
                    Logger.getLogger(OrderEntryMultipleCpts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No Record Found against specified criteria.");
        }
    }

    private void orderDetail(String fromDate, String toDate,String locationId) {
        orderReportMap.clear();
        if(dam.getRadiologyOrders(fromDate,toDate,orderReportMap,locationId))
        {
            for(String key:orderReportMap.keySet())
            {
                OrderReportBO orb = orderReportMap.get(key);
                int count = 0;
                try{
                    count = orderModalityMap.get(orb.getModality())+1;
//                    String temp = modalityOrderMap.get(orb.getModality());
                    orderModalityMap.put(orb.getModality(), count);
//                    modalityOrderMap.replace(orb.getModality(), temp+"\n"+value);
                    
                }
                catch(NullPointerException e)
                {
                    orderModalityMap.putIfAbsent(orb.getModality(), 1);
//                    modalityOrderMap.putIfAbsent(orb.getModality(), value);
                }
                
                
            }
            if(orderModalityMap.size()>0)
            {
                OrderGenerationDetail ogs = new OrderGenerationDetail();
                String filePath = ogs.generateOrder(orderModalityMap,orderReportMap,fromDate,toDate,locationId);
                
                try {
                    File pdf = new File(filePath);
                    Desktop.getDesktop().open(pdf);
                } catch (IOException ex) {
                    Logger.getLogger(OrderEntryMultipleCpts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No Record Found against specified criteria.");
        }
    }

    private void finalSignedDetail(String fromDate, String toDate,String locationId) {
        orderReportMap.clear();
        if(dam.getPendingResults(fromDate,toDate,orderReportMap,"6",locationId))
        {
            for(String key:orderReportMap.keySet())
            {
                OrderReportBO orb = orderReportMap.get(key);
                int count = 0;
                
                try{
                    count = finalSignedModalityMap.get(orb.getModality())+1;
                    finalSignedModalityMap.put(orb.getModality(), count);
                    
                }
                catch(NullPointerException e)
                {
                    finalSignedModalityMap.putIfAbsent(orb.getModality(), 1);
                }
                
                
            }
            if(finalSignedModalityMap.size()>0)
            {
                FinalSignedDetail ogs = new FinalSignedDetail();
                String filePath = ogs.generateOrder(finalSignedModalityMap,orderReportMap,fromDate,toDate,locationId);
                
                try {
                    File pdf = new File(filePath);
                    Desktop.getDesktop().open(pdf);
                } catch (IOException ex) {
                    Logger.getLogger(OrderEntryMultipleCpts.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No Record Found against specified criteria.");
        }
    }

    private void fullReportSummary(String fromDate, String toDate,String locationId) {
        list.clear();
        summaryMap.clear();
        dam.getOrderSummary(fromDate,toDate,list,locationId);
        if(list.size()>0)
        {
            PerformanceSummaryBo psbo =new PerformanceSummaryBo();
            for(int i=0;i<list.size();i++)
            {
                
                String arr[] = list.get(i).split("\\^");
                try{
                     psbo = summaryMap.get(arr[0]);
                     psbo.setModality(arr[0]);
                }catch(NullPointerException ex)
                {
                    psbo = new PerformanceSummaryBo();
                    psbo.setModality(arr[0]);
                    summaryMap.putIfAbsent(arr[0], psbo);
                }
                switch(arr[1])
                {
                    case"0":
                        psbo.setCancelled(arr[2]);
                        break;
                    case"1":
                        psbo.setOrder(arr[2]);
                        break;
                    case "2":
                        psbo.setInvoiced(arr[2]);
                        break;
                    case"-1":
                        psbo.setTotal(arr[2]);
                        break;
                    case"3":
                        psbo.setCounterAck(arr[2]);
                        break;
                    case"4":
                        psbo.setTechAck(arr[2]);
                        break;
                    case"5":
                        psbo.setDrafted(arr[2]);
                        break;
                    case"6":
                        psbo.setSigned(arr[2]);
                        break;
                }
                summaryMap.replace(arr[0], psbo);
                
            }
            FullSummary fs = new FullSummary();
            String filePath = fs.generateOrder(fromDate, toDate, summaryMap,locationId);
            
            try {
                    File pdf = new File(filePath);
                    Desktop.getDesktop().open(pdf);
                } catch (IOException ex) {
                    Logger.getLogger(OrderEntryMultipleCpts.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }

    private String getLocationId() {
        String id="";
        for(String key:lhm.keySet())
        {
            String value = lhm.get(key);
            if(value.equalsIgnoreCase(comboLocation.getSelectedItem().toString()))
            {
                id=key;
                break;
            }
        }
        return id;
    }
}
