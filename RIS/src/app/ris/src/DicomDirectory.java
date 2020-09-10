// 
// Decompiled by Procyon v0.5.36
// 

package app.ris.src;

import java.io.File;
import javax.swing.ListSelectionModel;
import java.awt.Cursor;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import app.ris.bo.LocationBO;
import java.util.Iterator;
import java.awt.Color;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.LayoutStyle;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.GroupLayout;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Dimension;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import app.ris.bo.Constants;
import com.toedter.calendar.JDateChooser;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import app.ris.dam.dam;
import app.ris.bo.DicomBo;
import app.ris.bo.ModalityBO;
import java.util.LinkedHashMap;
import javax.swing.JInternalFrame;

public class DicomDirectory extends JInternalFrame
{
    LinkedHashMap<String, ModalityBO> modalityMap;
    LinkedHashMap<String, DicomBo> ldbo;
    LinkedHashMap<String, String> lhm;
    dam d;
    private JButton btnCancel;
    private JButton btnSearch;
    private JButton btnSearch1;
    private JComboBox<String> cmbLocation;
    private JComboBox<String> cmbModality;
    private JLabel jLabel1;
    private JLabel jLabel10;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JScrollPane jScrollPane1;
    private JTable tblDicom;
    private JTextField txtCnic;
    private JDateChooser txtFromDate;
    private JTextField txtMrno;
    private JDateChooser txtToDate;
    
    public DicomDirectory() {
        this.modalityMap = new LinkedHashMap<String, ModalityBO>();
        this.ldbo = new LinkedHashMap<String, DicomBo>();
        this.lhm = new LinkedHashMap<String, String>();
        this.d = new dam();
        this.initComponents();
        final Dimension desktopSize = Constants.pane1.getSize();
        final Dimension jInternalFrameSize = this.getSize();
        this.setLocation((desktopSize.width - jInternalFrameSize.width) / 2, (desktopSize.height - jInternalFrameSize.height) / 2);
        this.setVisible(true);
        this.fillModalityTable();
        this.filleLocation();
        this.cmbLocation.setSelectedItem(this.lhm.get(Constants.locationID));
        this.searchPatient();
        this.txtCnic.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(final KeyEvent e) {
                if (DicomDirectory.this.txtCnic.getText().length() < 13) {
                    final char c = e.getKeyChar();
                    final int value = Character.getNumericValue(c);
                    if (value < 0 || value > 9) {
                        e.consume();
                    }
                }
                else {
                    e.consume();
                }
            }
        });
        this.cmbLocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                DicomDirectory.this.searchPatient();
            }
        });
        this.cmbModality.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                DicomDirectory.this.searchPatient();
            }
        });
        AutoCompleteDecorator.decorate((JComboBox)this.cmbLocation);
        AutoCompleteDecorator.decorate((JComboBox)this.cmbModality);
    }
    
    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.jLabel1 = new JLabel();
        this.jPanel2 = new JPanel();
        this.jLabel6 = new JLabel();
        this.txtMrno = new JTextField();
        this.jLabel7 = new JLabel();
        this.txtCnic = new JTextField();
        this.jLabel8 = new JLabel();
        this.cmbModality = new JComboBox<String>();
        this.jLabel10 = new JLabel();
        this.cmbLocation = new JComboBox<String>();
        this.jLabel11 = new JLabel();
        this.jLabel12 = new JLabel();
        this.txtFromDate = new JDateChooser();
        this.txtToDate = new JDateChooser();
        this.jPanel3 = new JPanel();
        this.jScrollPane1 = new JScrollPane();
        this.tblDicom = new JTable();
        this.btnCancel = new JButton();
        this.btnSearch = new JButton();
        this.btnSearch1 = new JButton();
        this.jLabel1.setFont(new Font("Tahoma", 1, 24));
        this.jLabel1.setHorizontalAlignment(0);
        this.jLabel1.setText("DICOM Directory");
        this.jPanel2.setBorder(BorderFactory.createEtchedBorder());
        this.jLabel6.setFont(new Font("Tahoma", 1, 14));
        this.jLabel6.setText("Patient ID:");
        this.txtMrno.setToolTipText("");
        this.txtMrno.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent evt) {
                DicomDirectory.this.txtMrnoKeyReleased(evt);
            }
        });
        this.jLabel7.setFont(new Font("Tahoma", 1, 14));
        this.jLabel7.setText("CNIC:");
        this.txtCnic.setToolTipText("");
        this.txtCnic.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(final KeyEvent evt) {
                DicomDirectory.this.txtCnicKeyReleased(evt);
            }
        });
        this.jLabel8.setFont(new Font("Tahoma", 1, 12));
        this.jLabel8.setText("Modality:");
        this.jLabel10.setFont(new Font("Tahoma", 1, 12));
        this.jLabel10.setText("Location:");
        this.jLabel11.setFont(new Font("Tahoma", 1, 14));
        this.jLabel11.setText("From:");
        this.jLabel12.setFont(new Font("Tahoma", 1, 14));
        this.jLabel12.setText("To:");
        final GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup().addComponent(this.jLabel8, -2, 80, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)).addGroup(jPanel2Layout.createSequentialGroup().addComponent(this.jLabel6).addGap(15, 15, 15))).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false).addComponent(this.txtMrno).addComponent(this.cmbModality, 0, 154, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel7).addComponent(this.jLabel11)).addGap(41, 41, 41).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.txtCnic, -1, 155, 32767).addComponent((Component)this.txtFromDate, -1, -1, 32767)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabel10).addComponent(this.jLabel12)).addGap(18, 18, 18).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent((Component)this.txtToDate, -1, -1, 32767).addComponent(this.cmbLocation, 0, 226, 32767)).addContainerGap()));
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel10, -2, 28, -2).addComponent(this.cmbLocation, -2, 29, -2)).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel6).addComponent(this.txtMrno, -2, 32, -2).addComponent(this.jLabel7).addComponent(this.txtCnic, -2, 32, -2))).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent((Component)this.txtFromDate, -2, 30, -2).addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jLabel8, -2, 28, -2).addComponent(this.cmbModality, -2, 29, -2).addComponent(this.jLabel11).addComponent(this.jLabel12)).addComponent((Component)this.txtToDate, -2, 30, -2)).addContainerGap(17, 32767)));
        this.jPanel3.setBorder(BorderFactory.createEtchedBorder());
        this.tblDicom.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null }, { null, null, null, null, null, null } }, new String[] { "Study ID", "Patient ID", "Name", "CPT Description", "Modality", "Acquisition Date" }) {
            boolean[] canEdit = { false, false, false, false, false, false };
            
            @Override
            public boolean isCellEditable(final int rowIndex, final int columnIndex) {
                return this.canEdit[columnIndex];
            }
        });
        this.tblDicom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent evt) {
                DicomDirectory.this.tblDicomMouseClicked(evt);
            }
        });
        this.jScrollPane1.setViewportView(this.tblDicom);
        final GroupLayout jPanel3Layout = new GroupLayout(this.jPanel3);
        this.jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jScrollPane1, GroupLayout.Alignment.TRAILING));
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addComponent(this.jScrollPane1, -2, 246, -2).addContainerGap(-1, 32767)));
        this.btnCancel.setBackground(new Color(255, 0, 0));
        this.btnCancel.setText("Exit");
        this.btnCancel.setActionCommand("EXIT");
        this.btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DicomDirectory.this.btnCancelActionPerformed(evt);
            }
        });
        this.btnSearch.setText("Search");
        this.btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DicomDirectory.this.btnSearchActionPerformed(evt);
            }
        });
        this.btnSearch1.setText("Clear");
        this.btnSearch1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent evt) {
                DicomDirectory.this.btnSearch1ActionPerformed(evt);
            }
        });
        final GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel3, -1, -1, 32767).addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.btnSearch, -2, 110, -2).addGap(18, 18, 18).addComponent(this.btnSearch1, -2, 98, -2).addGap(18, 18, 18).addComponent(this.btnCancel, -2, 105, -2)).addComponent(this.jPanel2, GroupLayout.Alignment.TRAILING, -1, -1, 32767).addComponent(this.jLabel1, -1, -1, 32767)).addContainerGap()));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(18, 18, 18).addComponent(this.jLabel1, -2, 28, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jPanel2, -2, -1, -2).addGap(18, 18, 18).addComponent(this.jPanel3, -2, -1, -2).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(this.btnCancel, -2, 32, -2).addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.btnSearch1, -2, 30, -2).addComponent(this.btnSearch, -2, 30, -2))).addContainerGap(-1, 32767)));
        final GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jPanel1, -1, -1, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jPanel1, -2, -1, -2).addGap(0, 0, 32767)));
        this.pack();
    }
    
    private void fillModalityTable() {
        this.modalityMap = new LinkedHashMap<String, ModalityBO>();
        this.d.getModality(this.modalityMap);
        this.cmbModality.removeAllItems();
        this.cmbModality.addItem("ALL");
        if (this.modalityMap.size() > 0) {
            for (final String key : this.modalityMap.keySet()) {
                final ModalityBO mbo = this.modalityMap.get(key);
                if (mbo.getActive().equalsIgnoreCase("Y")) {
                    this.cmbModality.addItem(mbo.getModality());
                }
            }
        }
    }
    
    private void filleLocation() {
        if (Constants.locationMap.size() > 0) {
            for (final String key : Constants.locationMap.keySet()) {
                final LocationBO lb = Constants.locationMap.get(key);
                this.lhm.put(key, lb.getName());
                if (lb.getActive().equalsIgnoreCase("Y")) {
                    this.cmbLocation.addItem(this.lhm.get(key).toString());
                }
            }
        }
    }
    
    private void btnSearchActionPerformed(final ActionEvent evt) {
        this.searchPatient();
    }
    
    private void btnSearch1ActionPerformed(final ActionEvent evt) {
        this.clearTable();
        this.clear();
    }
    
    private void btnCancelActionPerformed(final ActionEvent evt) {
        this.dispose();
    }
    
    private void txtMrnoKeyReleased(final KeyEvent evt) {
    }
    
    private void txtCnicKeyReleased(final KeyEvent evt) {
    }
    
    private void tblDicomMouseClicked(final MouseEvent evt) {
        final int row = this.tblDicom.getSelectedRow();
        if (evt.getClickCount() == 2 && row >= 0) {
            final String studyId = this.tblDicom.getValueAt(row, 0).toString();
            final DicomBo dbo = this.ldbo.get(studyId);
            final String frontPath = dbo.getPrimaryServer() + "\\" + dbo.getFilePath();
            final String backupPath = dbo.getBackupServer() + "\\" + dbo.getFilePath();
            final String study = this.verifyPaths(frontPath, backupPath);
            if (!study.equals("")) {
                final String viewerPath = this.d.getViewerPath();
                this.createProcessToOpenImage(viewerPath, study);
            }
            else {
                JOptionPane.showMessageDialog(this, "Study Not Found at both Server. Please contact PACS Helpline");
            }
        }
    }
    
    private void populateDicomTable() {
        this.clearTable();
        this.fillTable();
    }
    
    private void clear() {
        this.txtMrno.setText("");
        this.txtCnic.setText("");
        this.cmbLocation.setSelectedItem(this.lhm.get(Constants.locationID));
        this.txtToDate.setDate((Date)null);
        this.txtFromDate.setDate((Date)null);
        this.cmbModality.setSelectedIndex(0);
    }
    
    private void searchPatient() {
        this.clearTable();
        this.ldbo = new LinkedHashMap<String, DicomBo>();
        final String[] patientInfo = this.txtMrno.getText().split("-");
        String locationID = "";
        for (final String key : this.lhm.keySet()) {
            final String loc = this.lhm.get(key);
            if (loc.equalsIgnoreCase(this.cmbLocation.getSelectedItem().toString())) {
                locationID = key;
            }
        }
        final SimpleDateFormat simpledatafo = new SimpleDateFormat("yyyy-MM-dd");
        String expectedToDate = "";
        String expectedFromDate = "";
        try {
            expectedToDate = simpledatafo.format(this.txtToDate.getDate());
        }
        catch (NullPointerException e) {
            expectedToDate = "";
        }
        try {
            expectedFromDate = simpledatafo.format(this.txtFromDate.getDate());
        }
        catch (NullPointerException e) {
            expectedFromDate = "";
        }
        if (expectedFromDate.length() > 0 && expectedToDate.length() <= 0) {
            JOptionPane.showMessageDialog(this, "Must Enter To Date field.");
        }
        else if (expectedToDate.length() > 0 && expectedFromDate.length() <= 0) {
            JOptionPane.showMessageDialog(this, "Must Enter From Date field.");
        }
        if (patientInfo.length == 2) {
            this.d.searchDicomDirectoryPatients(patientInfo[0], patientInfo[1], this.txtCnic.getText(), this.cmbModality.getSelectedItem(), locationID, expectedFromDate, expectedToDate, this.ldbo);
        }
        else {
            this.d.searchDicomDirectoryPatients("", "", this.txtCnic.getText(), this.cmbModality.getSelectedItem(), locationID, expectedFromDate, expectedToDate, this.ldbo);
        }
        if (this.ldbo.size() > 0) {
            this.populateDicomTable();
        }
    }
    
    private void clearTable() {
        this.tblDicom.clearSelection();
        final DefaultTableModel dm = (DefaultTableModel)this.tblDicom.getModel();
        dm.getDataVector().removeAllElements();
        dm.fireTableDataChanged();
    }
    
    private void fillTable() {
        final ListSelectionModel lsm = this.tblDicom.getSelectionModel();
        lsm.setSelectionMode(0);
        final DefaultTableModel model = (DefaultTableModel)this.tblDicom.getModel();
        for (final String key : this.ldbo.keySet()) {
            final DicomBo dbo = this.ldbo.get(key);
            final String contrast = "";
            final String[] arr = { dbo.getStudyId(), dbo.getPatientLocationID() + "-" + dbo.getPatientId(), dbo.getName(), dbo.getModality() + " " + dbo.getBodyDescription() + " " + contrast, dbo.getModality(), dbo.getAcqisition_time() };
            model.addRow(arr);
        }
        this.setCursor(Cursor.getPredefinedCursor(0));
        this.tblDicom.setAutoResizeMode(4);
        this.tblDicom.getColumnModel().getColumn(0).setMaxWidth(100);
        this.tblDicom.getColumnModel().getColumn(1).setMaxWidth(100);
        this.tblDicom.getColumnModel().getColumn(4).setMaxWidth(80);
    }
    
    private String verifyPaths(final String frontPath, final String backupPath) {
        final String path = "";
        File file = new File(frontPath);
        if (file.exists()) {
            return frontPath;
        }
        file = new File(backupPath);
        if (file.exists()) {
            return backupPath;
        }
        return path;
    }
    
    public void createProcessToOpenImage(final String viewer, final String studyPath) {
        try {
            System.out.println("Viewer     = '" + viewer + "'");
            System.out.println("StudyPath  = '" + studyPath + "'");
            System.out.println("Final Command = " + viewer + " " + studyPath);
            final ProcessBuilder pb = new ProcessBuilder(new String[] { viewer, studyPath });
            pb.redirectErrorStream(true);
            final Process p = pb.start();
            System.out.println("Command executed successfully.\nReturning succesfully after launching viewer to display images.");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
