package app.ris.src;


import app.ris.bo.Constants;
import app.ris.bo.PatientAttendantInfo;
import app.ris.bo.PatientBO;
import app.ris.dam.dam;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 
 */
public class PatientRegistrationForm extends javax.swing.JInternalFrame {

    /**
     * Creates new form PatientRegistration
     */
    HashMap<String,PatientBO> viewMap = new HashMap<String, PatientBO>();
    File file = new File(System.getProperty("user.dir")+File.separator+"patient.txt");
    dam d = new dam();
    boolean updateFlag=false;
    PatientBO value;
    int patientID=0;
    String id="";
//    public boolean attendantFlag=false;
    public PatientRegistrationForm() {
        initComponents();
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        Dimension desktopSize = Constants.pane1.getSize();
        Dimension jInternalFrameSize = this.getSize();
        this.setLocation((desktopSize.width - jInternalFrameSize.width)/2,
            (desktopSize.height- jInternalFrameSize.height)/2);
        this.setVisible(true);
        
        
        ButtonGroup gp = new ButtonGroup();
        gp.add(jbF);
        gp.add(jbM);
        
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioAttendant);
        bg.add(radioPatient);
        
        
        
        txtCnic.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e)
                {
                    
                    if(txtCnic.getText().length()<13){
                        char c = e.getKeyChar();
                        int value = Character.getNumericValue(c);
                        if(value>=0 && value<=9)
                        {
                        }
                        else
                        {
                            e.consume();
                        }
                    }
                    else
                    {
                        e.consume();
                    }
                                
                }
        });
        
        txtCnic1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e)
                {
                    
                    if(txtCnic1.getText().length()<13){
                        char c = e.getKeyChar();
                        int value = Character.getNumericValue(c);
                        if(value>=0 && value<=9)
                        {
                        }
                        else
                        {
                            e.consume();
                        }
                    }
                    else
                    {
                        e.consume();
                    }
                                
                }
        });
        
        
        txtContact.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e)
                {
                    char c = e.getKeyChar();
                    int value = Character.getNumericValue(c);
                    if(value>=0 && value<=9)
                    {
                    }
                    else
                    {
                        e.consume();
                    }
                                
                }
        });
        txtContact1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e)
                {
                    char c = e.getKeyChar();
                    int value = Character.getNumericValue(c);
                    if(value>=0 && value<=9)
                    {
                    }
                    else
                    {
                        e.consume();
                    }
                                
                }
        });
        
        cmbAge.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                dobCalculator();
            }
        });
        
        txtAge.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e)
                {
                dobCalculator();
            }
        });
        
        txtOther.setText("");
        txtOther.grabFocus();
        txtOther.requestFocus();
        
        radioPatient();
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtScontact = new javax.swing.JTextField();
        txtScnic = new javax.swing.JTextField();
        txtSname = new javax.swing.JTextField();
        txtSid = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnClear = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePatient = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtFather = new javax.swing.JTextField();
        txtContact = new javax.swing.JTextField();
        txtCnic = new javax.swing.JTextField();
        jbM = new javax.swing.JRadioButton();
        jbF = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtOther = new javax.swing.JTextField();
        txtDoB = new com.toedter.calendar.JDateChooser();
        txtAge = new javax.swing.JTextField();
        cmbAge = new javax.swing.JComboBox<String>();
        jLabel19 = new javax.swing.JLabel();
        txtAdd = new javax.swing.JTextField();
        txtCity = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtPmuNumber = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        btnCl = new javax.swing.JButton();
        btnReg = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnReg1 = new javax.swing.JButton();
        btnReg2 = new javax.swing.JButton();
        btnOrder = new javax.swing.JButton();
        btnAttachReports = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtName1 = new javax.swing.JTextField();
        txtContact1 = new javax.swing.JTextField();
        txtCnic1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtAdd1 = new javax.swing.JTextField();
        txtCity1 = new javax.swing.JTextField();
        lblID1 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtRelation = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        radioPatient = new javax.swing.JRadioButton();
        radioAttendant = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximizable(true);
        setResizable(true);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("   Patient Registration");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Existing Patients"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Patient ID:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Name:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("CNIC:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Contact No:");

        txtScontact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtScontactActionPerformed(evt);
            }
        });

        txtScnic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtScnicActionPerformed(evt);
            }
        });

        txtSname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSnameActionPerformed(evt);
            }
        });

        txtSid.setToolTipText("");
        txtSid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSidActionPerformed(evt);
            }
        });
        txtSid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSidKeyPressed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear)
                    .addComponent(btnSearch))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(44, 44, 44))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtSid, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSname, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtScnic, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(txtScontact, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtScnic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtScontact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Patient Found"));

        tablePatient.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PatientID", "Name", "CNIC", "ContactNo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePatient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePatientMouseClicked(evt);
            }
        });
        tablePatient.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablePatientKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablePatient);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Register Patient"));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Patient ID:*");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Name:*");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Father/Husband Name:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("CNIC:*");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Contact No:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Gender:");
        jLabel11.setToolTipText("");

        txtName.setToolTipText("");

        txtFather.setToolTipText("");

        txtContact.setToolTipText("");

        txtCnic.setToolTipText("");

        jbM.setSelected(true);
        jbM.setText("Male");

        jbF.setText("Female");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Date of Birth*");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Address");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("City");

        lblID.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Hospital MRN:");

        txtOther.setToolTipText("");
        txtOther.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtOtherMouseClicked(evt);
            }
        });
        txtOther.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOtherActionPerformed(evt);
            }
        });

        txtDoB.setDate(new Date());
        txtDoB.setDateFormatString("yyyy-MM-dd");

        txtAge.setToolTipText("");
        txtAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAgeActionPerformed(evt);
            }
        });

        cmbAge.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Years", "Months", "Days" }));
        cmbAge.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbAgeMouseClicked(evt);
            }
        });
        cmbAge.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbAgeKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Age");

        txtAdd.setToolTipText("");

        txtCity.setToolTipText("");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("PMU Number");

        txtPmuNumber.setToolTipText("");
        txtPmuNumber.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPmuNumberMouseClicked(evt);
            }
        });
        txtPmuNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPmuNumberActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jbM)
                                        .addGap(18, 18, 18)
                                        .addComponent(jbF))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(cmbAge, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtContact, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCnic, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFather, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(61, 61, 61)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtDoB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtOther, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPmuNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(lblID))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel12))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOther, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel17))
                    .addComponent(txtPmuNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtFather, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtCnic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDoB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jbM)
                    .addComponent(jbF))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCl.setText("Clear");
        btnCl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClActionPerformed(evt);
            }
        });

        btnReg.setText("Save");
        btnReg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegActionPerformed(evt);
            }
        });

        btnCancel.setBackground(new java.awt.Color(255, 0, 0));
        btnCancel.setText("Exit");
        btnCancel.setActionCommand("EXIT");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnReg1.setText("Capture Picture");
        btnReg1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReg1ActionPerformed(evt);
            }
        });

        btnReg2.setText("Capture Thumb Impression");
        btnReg2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReg2ActionPerformed(evt);
            }
        });

        btnOrder.setBackground(new java.awt.Color(0, 255, 0));
        btnOrder.setText("Order Entry");
        btnOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrderActionPerformed(evt);
            }
        });

        btnAttachReports.setText("Attach Reports");
        btnAttachReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttachReportsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnReg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReg1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnOrder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAttachReports, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnReg2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCl, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCl, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReg, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAttachReports, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReg2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReg1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Attendant Information"));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Name:*");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setText("CNIC:*");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Contact No:*");

        txtName1.setToolTipText("");

        txtContact1.setToolTipText("");

        txtCnic1.setToolTipText("");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Address:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("City:");

        txtAdd1.setToolTipText("");

        txtCity1.setToolTipText("");

        lblID1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Age");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Relation:*");

        txtRelation.setToolTipText("");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(380, 380, 380))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(lblID1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtAdd1)
                                    .addComponent(txtName1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                    .addComponent(txtContact1)
                                    .addComponent(txtCnic1)
                                    .addComponent(txtCity1)
                                    .addComponent(txtRelation))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblID1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtName1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtCnic1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(txtContact1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtAdd1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtCity1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtRelation, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addComponent(jLabel27)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Information of:");

        radioPatient.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        radioPatient.setSelected(true);
        radioPatient.setText("Patient ");
        radioPatient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioPatientMouseClicked(evt);
            }
        });
        radioPatient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioPatientActionPerformed(evt);
            }
        });

        radioAttendant.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        radioAttendant.setText("Attendant");
        radioAttendant.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                radioAttendantMouseClicked(evt);
            }
        });
        radioAttendant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioAttendantActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(radioPatient)
                .addGap(18, 18, 18)
                .addComponent(radioAttendant)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(radioPatient)
                    .addComponent(radioAttendant)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 315, Short.MAX_VALUE))
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 923, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        clear();
        txtOther.setText("");
        txtOther.grabFocus();
        txtOther.requestFocus();
        
    }//GEN-LAST:event_formFocusGained

    private void btnClActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClActionPerformed
        clear();
    }//GEN-LAST:event_btnClActionPerformed

    private void btnRegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegActionPerformed
        if(radioPatient.isSelected())
        {
//            attendantFlag=true;
            patient();
        }
        else if(radioAttendant.isSelected())
        {
            attendant();
//            attendantFlag=true;
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please Select Information Type First");
        }
        
    }//GEN-LAST:event_btnRegActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        this.txtSid.setText("");
        this.txtSname.setText("");
        this.txtScontact.setText("");
        this.txtScnic.setText("");
        clearTable();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        this.searchPatient();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void searchPatient() {
        clearTable();
        viewMap = new HashMap<>();
        String arr[] = new String[2];
        arr = txtSid.getText().split("-");
        if(arr.length>1)
            d.searchPatient(arr[0],arr[1],txtSname.getText(),txtScnic.getText(),txtScontact.getText(),viewMap);
        else
            d.searchPatient(arr[0],"",txtSname.getText(),txtScnic.getText(),txtScontact.getText(),viewMap);
        if(viewMap.size()>0)
            fillTable();
    }
    
    private void tablePatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePatientMouseClicked

            selectRow();

    }//GEN-LAST:event_tablePatientMouseClicked

    private void tablePatientKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablePatientKeyReleased
        selectRow();
        
    }//GEN-LAST:event_tablePatientKeyReleased

    private void btnReg1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReg1ActionPerformed
        try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            id = "";
            if(!lblID.getText().equalsIgnoreCase(""))
                id = lblID.getText();
            
            this.hide();
            
            try{
                if(Constants.webcam!=null || Constants.webcam.open())
                Constants.webcam.close();
            }catch(Exception e)
            {}
            CapturePicture reg = new CapturePicture(id);
            reg.regObj = this;
            Thread thread = new Thread(){
                public void run(){
                     
                    BasicInternalFrameUI ui = (BasicInternalFrameUI)reg.getUI();
                    Container north = (Container)ui.getNorthPane();
                    north.remove(0);
                    north.validate();
                    north.repaint();
                    Constants.pane1.add(reg);
                        
                        
                }
              };

              thread.start();
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            
            } catch (Exception e) {
                e.printStackTrace();
            }
    }//GEN-LAST:event_btnReg1ActionPerformed

    private void txtSidKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSidKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.searchPatient();
        }
    }//GEN-LAST:event_txtSidKeyPressed

    private void btnReg2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReg2ActionPerformed
            try {
//            Constants.pane1.removeAll();
//            Constants.pane1.repaint();

            try {
                if (Constants.webcam != null || Constants.webcam.open()) {
                    Constants.webcam.close();
                }
            } catch (Exception e) {
            }
            this.hide();
            FingerPrint reg = new FingerPrint(lblID.getText());
            reg.regObj = this;
            BasicInternalFrameUI ui = (BasicInternalFrameUI) reg.getUI();
            Container north = (Container) ui.getNorthPane();
            north.remove(0);
            north.validate();
            north.repaint();
            Constants.pane1.add(reg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnReg2ActionPerformed

    private void btnOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrderActionPerformed
//        if(Constants.obj!=null)
//        {
//            Constants.obj.dispose();
//        }
        try {
            Constants.bgFrame.hide();
            try {
                if (Constants.webcam != null || Constants.webcam.open()) {
                    Constants.webcam.close();
                }
            } catch (Exception e) {
            }
//            OrderEntry reg = new OrderEntry();
            OrderEntryMultipleCpts reg = new OrderEntryMultipleCpts();
            
            reg.txtID.setText(lblID.getText());
            
            BasicInternalFrameUI ui = (BasicInternalFrameUI) reg.getUI();
            Container north = (Container) ui.getNorthPane();
            north.remove(0);
            north.validate();
            north.repaint();
            Constants.pane1.add(reg);
            Constants.obj=reg;
            reg.grabFocus();
            reg.pr = this;
            this.hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnOrderActionPerformed

    private void txtOtherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOtherActionPerformed
        
        String text = txtOther.getText().toString().toUpperCase();
        text = text.replace("BEGIN:VCARDVERSION:3.0", "");
        text = text.replace("END:VCARD", "");
        text = text.replace("MRN:", "");
        text = text.replace("NAME", "");
        text = text.replace("GENDER", "");
        text = text.replace("DOB", "");
        
        String[] val = text.split(":");
        String mrn = val[0];
        String name = val[1];
        String gender = val[2];
        String dob = val[3];
        txtOther.setText(mrn);
        txtName.setText(name);
        jbF.setSelected(gender.equalsIgnoreCase("female"));
        String dobArr[] =dob.split("/");
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
        
        Date startDate= new Date();
        try {
            startDate = df.parse(dob);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        txtDoB.setDate(startDate);
        System.out.println(startDate+"\n"+dobArr[0]+"/"+dobArr[1]+"/"+dobArr[2]+"\n"+dob);
        PatientBO pbo = new PatientBO();
        pbo.setPatientId("");
        d.searchOtherMrno(txtOther.getText(),pbo);
        if(!pbo.getPatientId().equalsIgnoreCase(""))
        {
            txtAdd.setText(pbo.getAddress());
            txtCity.setText(pbo.getCity());
            txtCnic.setText(pbo.getCnic());
            txtContact.setText(pbo.getContactNum());
            txtFather.setText(pbo.getFatherName());
            lblID.setText(pbo.getLocation_id()+"-"+pbo.getPatientId());
            value = pbo;
            updateFlag=true;
        }
        else
        {
            JOptionPane.showMessageDialog(this, "New Patient. Please Save Information before procedding.");
        }
    }//GEN-LAST:event_txtOtherActionPerformed

    private void txtOtherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtOtherMouseClicked
        txtOther.setText("");
    }//GEN-LAST:event_txtOtherMouseClicked

    private void txtAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAgeActionPerformed
        dobCalculator();
    }//GEN-LAST:event_txtAgeActionPerformed

    private void txtSnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSnameActionPerformed
        searchPatient();
    }//GEN-LAST:event_txtSnameActionPerformed

    private void txtScnicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtScnicActionPerformed
        searchPatient();
        
    }//GEN-LAST:event_txtScnicActionPerformed

    private void txtScontactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtScontactActionPerformed
        searchPatient();
    }//GEN-LAST:event_txtScontactActionPerformed

    private void txtSidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSidActionPerformed
        searchPatient();
    }//GEN-LAST:event_txtSidActionPerformed

    private void cmbAgeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbAgeMouseClicked
        dobCalculator();
    }//GEN-LAST:event_cmbAgeMouseClicked

    private void cmbAgeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbAgeKeyReleased
        dobCalculator();
    }//GEN-LAST:event_cmbAgeKeyReleased

    private void radioPatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioPatientMouseClicked
        radioPatient();
    }//GEN-LAST:event_radioPatientMouseClicked

    private void radioAttendantMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_radioAttendantMouseClicked
        radioAttendant();
    }//GEN-LAST:event_radioAttendantMouseClicked

    private void radioAttendantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioAttendantActionPerformed
        radioAttendant();
    }//GEN-LAST:event_radioAttendantActionPerformed

    private void radioPatientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioPatientActionPerformed
        radioPatient();
    }//GEN-LAST:event_radioPatientActionPerformed

    private void btnAttachReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttachReportsActionPerformed
            try {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            id = "";
            if(!lblID.getText().equalsIgnoreCase(""))
                id = lblID.getText();
            
            this.hide();
            
            try{
                if(Constants.webcam!=null || Constants.webcam.open())
                Constants.webcam.close();
            }catch(Exception e)
            {}
            CaptureReports reg = new CaptureReports(id);
            reg.regObj = this;
            Thread thread = new Thread(){
                public void run(){
                     
                    BasicInternalFrameUI ui = (BasicInternalFrameUI)reg.getUI();
                    Container north = (Container)ui.getNorthPane();
                    north.remove(0);
                    north.validate();
                    north.repaint();
                    Constants.pane1.add(reg);
                        
                        
                }
              };

              thread.start();
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            
            } catch (Exception e) {
                e.printStackTrace();
            }
    }//GEN-LAST:event_btnAttachReportsActionPerformed

    private void txtPmuNumberMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPmuNumberMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPmuNumberMouseClicked

    private void txtPmuNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPmuNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPmuNumberActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(PatientRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(PatientRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(PatientRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(PatientRegistration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new PatientRegistration().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAttachReports;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCl;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnOrder;
    private javax.swing.JButton btnReg;
    private javax.swing.JButton btnReg1;
    private javax.swing.JButton btnReg2;
    private javax.swing.JButton btnSearch;
    private javax.swing.JComboBox<String> cmbAge;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jbF;
    private javax.swing.JRadioButton jbM;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblID1;
    private javax.swing.JRadioButton radioAttendant;
    private javax.swing.JRadioButton radioPatient;
    private javax.swing.JTable tablePatient;
    private javax.swing.JTextField txtAdd;
    private javax.swing.JTextField txtAdd1;
    private javax.swing.JTextField txtAge;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtCity1;
    private javax.swing.JTextField txtCnic;
    private javax.swing.JTextField txtCnic1;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtContact1;
    private com.toedter.calendar.JDateChooser txtDoB;
    private javax.swing.JTextField txtFather;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtName1;
    private javax.swing.JTextField txtOther;
    private javax.swing.JTextField txtPmuNumber;
    private javax.swing.JTextField txtRelation;
    private javax.swing.JTextField txtScnic;
    private javax.swing.JTextField txtScontact;
    private javax.swing.JTextField txtSid;
    private javax.swing.JTextField txtSname;
    // End of variables declaration//GEN-END:variables

//    private void read() {
//        
//        try {
//
//            
//
//            BufferedReader b = new BufferedReader(new FileReader(file));
//
//            String readLine = "";
//
//            System.out.println("Reading file using Buffered Reader");
//
//            while ((readLine = b.readLine()) != null) {
//                System.out.println(readLine);
//                parse(readLine);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

//    private void parse(String readLine) {
//        String values[] = readLine.split(",");
////        vmap.put(values[0], values);
//        String temp[]= new String[4];
//        temp[0] = new String(values[0]);
//        temp[1] = new String(values[1]);
//        temp[2] = new String(values[3]);
//        temp[3] = new String(values[4]);
////        smap.put(temp[0], temp);
//    }

    private void fillTable() {
        ListSelectionModel lsm = this.tablePatient.getSelectionModel();
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //------------- Information Filled in table
        DefaultTableModel model = (DefaultTableModel) tablePatient.getModel();
        
        for ( String key : viewMap.keySet()){
            PatientBO pb = viewMap.get(key);
            String arr[] = {pb.getLocation_id()+"-"+pb.getPatientId(),pb.getName(),pb.getCnic(),pb.getContactNum()};
                model.addRow(arr);
            }
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    private void selectRow() {
        int row = tablePatient.getSelectedRow();
        if(row>=0)
        {
            String info[] = tablePatient.getValueAt(row, 0).toString().split("-");
            value = new PatientBO();
            value = d.getPatientInformation(info[0],info[1],value);
            if(!value.getName().isEmpty())
            {
                lblID.setText(value.getLocation_id()+"-"+value.getPatientId());
                txtName.setText(value.getName());
                txtFather.setText(value.getFatherName());
                txtCnic.setText(value.getCnic());
                txtContact.setText(value.getContactNum());
                txtPmuNumber.setText(value.getPmuNumber());
                switch(value.getGender())
                {
                    case "M":
                        jbM.setSelected(true);
                        break;
                    case "F":
                        jbF.setSelected(true);
                        break;
                }
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
                Date startDate= new Date();
                try {
                    startDate = df.parse(value.getDob());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                
                txtDoB.setDate(startDate);
                
                txtAdd.setText(value.getAddress());
                txtCity.setText(value.getCity());
                txtOther.setText(value.getOtherMrno());
                
                
                if(value.getAttendant_flag().equalsIgnoreCase("Y"))
                {
                    radioAttendant.setSelected(true);
                    radioAttendant();
                    PatientAttendantInfo pai = d.getAttendantInfo(value.getLocation_id(),value.getPatientId());
                    txtName1.setText(pai.getName());
                    txtCnic1.setText(pai.getCnic());
                    txtContact1.setText(pai.getContact());
                    txtAdd1.setText(pai.getAddress());
                    txtCity1.setText(pai.getCity());
                    txtRelation.setText(pai.getRelation());
                }
                
                
                updateFlag=true;
            }
        }
    }

    private void clearTable() {
        this.tablePatient.clearSelection();
        
        DefaultTableModel dm = (DefaultTableModel)tablePatient.getModel();
        dm.getDataVector().removeAllElements();
        dm.fireTableDataChanged(); 
    }


    private void clear() {
        this.lblID.setText("");
        this.txtName.setText("");
        this.txtName1.setText("");
        this.txtFather.setText("");
        this.txtCnic.setText("");
        this.txtCnic1.setText("");
        this.txtContact.setText("");
        this.txtContact1.setText("");
        this.txtPmuNumber.setText("");
        this.txtOther.setText("");
        this.jbM.setSelected(true);
        txtCity.setText("");
        txtCity1.setText("");
        txtAdd.setText("");
        txtAdd1.setText("");
        txtDoB.setDate(new Date());
        updateFlag=false;
        txtAge.setText("");
        cmbAge.setSelectedIndex(0);
        txtRelation.setText("");
        radioPatient.setSelected(true);
        radioPatient();
//        patientID();
    }

//    }

    private void dobCalculator() {

        Date input = new Date();
        LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();            
        
        String value = cmbAge.getSelectedItem().toString().toUpperCase();
        try{
            switch(value)
            {
                default:
                case "YEARS":
                    date = date.minusYears(Long.valueOf(txtAge.getText()));
                    break;
                case "MONTHS":
                    date = date.minusMonths(Long.valueOf(txtAge.getText()));
                    break;
                case "DAYS":
                    date = date.minusDays(Long.valueOf(txtAge.getText()));
                    break; 

            }
        }
        catch(NumberFormatException ex)
                {}
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); 
                Date startDate= new Date();
                try {
                    startDate = df.parse(date.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                
                txtDoB.setDate(startDate);
        
        
    }

    private void patient() {
        PatientBO pbo = new PatientBO();
        if(txtName.getText().isEmpty() || txtName.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Name of Patient is Required. Please Specify Patient Name");
            txtName.grabFocus();
            return;
        }
        
        if(!txtCnic.getText().equals("") && txtCnic.getText().length()<13)
        {
            JOptionPane.showMessageDialog(this, "CNIC Must be of 13 Characters without Dashes (-). Kindly correctly specify CNIC Number");
            txtCnic.grabFocus();
            return;
        }
        
        
        
//        String arr[] = txtID.getText().split("-");
//        pbo.setPatientId(arr[1]);
        try{
            SimpleDateFormat simpledatafo = new SimpleDateFormat("yyyy-MM-dd");
            String expectedToDate= simpledatafo.format(txtDoB.getDate());
            pbo.setDob(expectedToDate);
        }catch(java.lang.NullPointerException e)
        {
            JOptionPane.showMessageDialog(this, "Incorrect Date of Birth. Kindly Provide Correct Date");
            return;
        }
        
        pbo.setName(txtName.getText());
        pbo.setPmuNumber(txtPmuNumber.getText());
        pbo.setFatherName(txtFather.getText());
        pbo.setCnic(txtCnic.getText());
        pbo.setContactNum(txtContact.getText());
        pbo.setGender(jbM.isSelected()?"M":"F");
        
        pbo.setAddress(txtAdd.getText());
        pbo.setCity(txtCity.getText());
        pbo.setOtherMrno(txtOther.getText());
        if(updateFlag)
        {
//            pbo.setLocation_id(value.getLocation_id());
            String arr[] = lblID.getText().split("-");
            pbo.setLocation_id(arr[0]);
            pbo.setPatientId(arr[1]);
        }
        pbo.setAttendant_flag(radioAttendant.isSelected()?"Y":"N");
        String id = d.insertPatientInfo(pbo,updateFlag);
        
        if(!id.equals(""))
        {
            if(!updateFlag)
            {
                lblID.setText(Constants.locationID+"-"+id);
                updateFlag=true;
            }
            JOptionPane.showMessageDialog(this, "Information Saved Correctly");
//            clear();
        }
        else if(id.equals("") && !lblID.getText().equalsIgnoreCase(""))
        {
            JOptionPane.showMessageDialog(this, "Information Saved Correctly");
        }
    }

    private void attendant() 
    {
        boolean flag=false;
        if(
                txtName.getText().equalsIgnoreCase("") ||
                txtName1.getText().equalsIgnoreCase("") ||
                txtCnic1.getText().equalsIgnoreCase("") ||
                txtContact1.getText().equalsIgnoreCase("") ||
                txtRelation.getText().equalsIgnoreCase("") ||
                txtName.getText().equalsIgnoreCase("") ||
                txtCnic.getText().equalsIgnoreCase("")
                )
        {
            JOptionPane.showMessageDialog(this, "Kindly Fill All Mandatory Fields marke with (*)");
            return;
        }
        else
        {
            PatientAttendantInfo pai = new PatientAttendantInfo();
            pai.setName(txtName1.getText());
            pai.setCnic(txtCnic1.getText());
            pai.setContact(txtContact1.getText());
            pai.setAddress(txtAdd1.getText());
            pai.setCity(txtCity1.getText());
            pai.setRelation(txtRelation.getText());
            
            pai.setPatientName(txtName.getText());
            pai.setPatientCnic(txtCnic.getText());
            
            try{
            SimpleDateFormat simpledatafo = new SimpleDateFormat("yyyy-MM-dd");
            String dob= simpledatafo.format(txtDoB.getDate());
            pai.setDob(dob);
            }catch(java.lang.NullPointerException e)
            {
                JOptionPane.showMessageDialog(this, "Incorrect Date of Birth. Kindly Provide Correct Date");
                return;
            }
            pai.setGender(jbM.isSelected()?"M":"F");
            String[] id= lblID.getText().split("-");
            if(id.length==2)
            {
                pai.setPatient_location_id(id[0]);
                pai.setPatient_id(id[1]);
                flag=true;
            }
            String  patient_id = d.savePatientAndAttendantInformation(pai,flag);
            if(!patient_id.equalsIgnoreCase("true") && !patient_id.equalsIgnoreCase("false") && !patient_id.equalsIgnoreCase(""))
            {
                lblID.setText(pai.getPatient_location_id()+"-"+pai.getPatient_id());
                JOptionPane.showMessageDialog(this, "Information Saved Successfully");
            }
            else if(patient_id.equalsIgnoreCase("true") && !pai.getPatient_id().equalsIgnoreCase(""))
            {
                JOptionPane.showMessageDialog(this, "Information Saved Successfully");
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Unable to Save Information");
            }
        }
    }

    private void radioPatient() {
        if(radioPatient.isSelected())
        {
            txtCnic1.setEnabled(false);
            txtName1.setEnabled(false);
            txtContact1.setEnabled(false);
            txtAdd1.setEnabled(false);
            txtCity1.setEnabled(false);
            txtRelation.setEnabled(false);
            
            
            txtOther.setEnabled(true);
            txtName.setEnabled(true);
            txtFather.setEnabled(true);
            txtCnic.setEnabled(true);
            txtContact.setEnabled(true);
            txtAdd.setEnabled(true);
            txtCity.setEnabled(true);
            txtDoB.setEnabled(true);
            txtAge.setEnabled(true);
            cmbAge.setEnabled(true);
        }
    }

    private void radioAttendant() {
            if(radioAttendant.isSelected())
        {
            txtCnic1.setEnabled(true);
            txtName1.setEnabled(true);
            txtContact1.setEnabled(true);
            txtAdd1.setEnabled(true);
            txtCity1.setEnabled(true);
            txtRelation.setEnabled(true);
            
            
            txtOther.setEnabled(false);
            txtName.setEnabled(true);
            txtFather.setEnabled(false);
            txtCnic.setEnabled(true);
            txtContact.setEnabled(false);
            txtAdd.setEnabled(false);
            txtCity.setEnabled(false);
            txtDoB.setEnabled(true);
            txtAge.setEnabled(true);
            cmbAge.setEnabled(true);
        }
    }

    
}
