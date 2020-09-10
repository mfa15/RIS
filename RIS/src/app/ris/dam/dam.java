/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.dam;

import app.ris.bo.Constants;
import app.ris.bo.CptBo;
import app.ris.bo.CptWiseOrderEntryBO;
import app.ris.bo.CptWisePricingBo;
import app.ris.bo.DicomBo;
import app.ris.bo.EmployeeBO;
import app.ris.bo.ItemBO;
import app.ris.bo.LocationBO;
import app.ris.bo.ModalityBO;
import app.ris.bo.OrderEntryBO;
import app.ris.bo.OrderReportBO;
import app.ris.bo.PatientAttendantInfo;
import app.ris.bo.PatientBO;
import app.ris.bo.PatientDetailReportBo;
import app.ris.bo.PatientReportBO;
import app.ris.bo.PatientSummaryReportBo;
import app.ris.bo.PatientTypeBo;
import app.ris.bo.ReferringPhysicianBO;
import app.ris.bo.TemplateBO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.springframework.util.FileCopyUtils.BUFFER_SIZE;

public class dam {

    public void insertPreDefinedHist() {
        for (String s : Constants.preDefinedHist) {
            try {
                if (con == null || con.isClosed()) {
                    this.connection();
                }
                query = "insert into radiology.pre_defined_hist values('" + s + "')";
                System.out.println(query);
                ps = con.prepareStatement(query);
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    Connection con;
    String query;
    Statement stmt;
    PreparedStatement ps;
    ResultSet rs;

    public void connection() throws SQLException {
        try {
            String driver = "com.mysql.jdbc.Driver";
            String connection = "jdbc:mysql://" + Constants.dbIP + ":" + Constants.dbPort + "/radiology";
            String user = "radiology";
            String password = "Worldcup@1992";
//            String user = "root";
//            String password = "1234";
            Class.forName(driver);
            con = DriverManager.getConnection(connection, user, password);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String insertEmployeeInfo(EmployeeBO emp, boolean updateFlag) {
        String id = "";
        if (!updateFlag) {
//            query = "INSERT INTO radiology.employee\n" +
//                        "  (\n" +
//                        "   name,\n" +
//                        "   father_name,\n" +
//                        "   cnic,\n" +
//                        "   contact_num,\n" +
//                        "   gender,\n" +
//                        "   dob,\n" +
//                        "   address,\n" +
//                        "   city,\n" +
//                        "   employeeRole,\n" +
//                        "   registration_date,\n" +
//                        "   active,\n" +
//                        "   location_id,\n" +
//                        "   password)\n" +
//                        "VALUES\n" +
//                        "  ('"+emp.getName()+"',\n" +
//                        "   '"+emp.getFatherName()+"',\n" +
//                        "   '"+emp.getCnic()+"',\n" +
//                        "   '"+emp.getContactNum()+"',\n" +
//                        "   '"+emp.getGender()+"',\n" +
//                        "   STR_TO_DATE('"+emp.getDob()+"', '%Y-%m-%d'),\n" +
//                        "   '"+emp.getAddress()+"',\n" +
//                        "   '"+emp.getCity()+"',\n" +
//                        "   '"+emp.getRole()+"',\n" +
//                        "   NOW(),\n" +
//                        "   '"+emp.getActive()+"',\n" +
//                        "   '"+emp.getLocation()+"',\n" +
//                        "   '"+emp.getPassword()+"')";
            query = "INSERT INTO `radiology`.`employee`\n"
                    + " (`name`, `father_name`, `cnic`, `contact_num`, `gender`, `dob`, \n"
                    + "`address`, `city`, `role`, `registration_date`, `active`, `location_id`,\n"
                    + " `password`, `user_name`, `trans_date`, `terminal_name`)\n"
                    + " VALUES \n"
                    + "('" + emp.getName() + "', '" + emp.getFatherName() + "',"
                    + " '" + emp.getCnic() + "', '" + emp.getContactNum() + "',\n"
                    + " '" + emp.getGender() + "', STR_TO_DATE('" + emp.getDob() + "', '%Y-%m-%d'),"
                    + " '" + emp.getAddress() + "', '" + emp.getCity() + "',"
                    + " '" + emp.getRole() + "', NOW(), '" + emp.getActive() + "', \n"
                    + "'" + emp.getLocation() + "', '" + emp.getPassword() + "',"
                    + " '" + Constants.employeeID + "', now(), '" + Constants.terminalName + "')";
        } else {
            query = "update radiology.employee\n"
                    + "set\n"
                    + "name ='" + emp.getName() + "',\n"
                    + "father_name='" + emp.getFatherName() + "',\n"
                    + "cnic='" + emp.getCnic() + "',\n"
                    + "contact_num='" + emp.getContactNum() + "',\n"
                    + "gender='" + emp.getGender() + "',\n"
                    + "dob='" + emp.getDob() + "',\n"
                    + "address='" + emp.getAddress() + "',\n"
                    + "city='" + emp.getCity() + "',\n"
                    + "role='" + emp.getRole() + "',\n"
                    + "active='" + emp.getActive() + "',\n"
                    + "location_id='" + emp.getLocation() + "',\n"
                    + "password='" + emp.getPassword() + "',"
                    + "user_name='" + Constants.employeeID + "',\n"
                    + "trans_date=now(),\n"
                    + "terminal_name = '" + Constants.terminalName + "'"
                    + "where \n"
                    + "employee_id='" + emp.getEmployeeID() + "'";
        }

        try {

            if (con == null || con.isClosed()) {
                this.connection();
            }
            System.out.println(query);
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = (String.valueOf(rs.getInt(1)));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public String insertReferringPhysician(ReferringPhysicianBO phy, boolean updateFlag) {
        String id = "";
        if (!updateFlag) {

            query = "INSERT INTO `radiology`.`referring_physician`"
                    + " (`name`, `designation`, `qualification`, `location_id`,\n"
                    + "`active`,`user_name`, `trans_date`, `terminal_name`)\n"
                    + " VALUES \n"
                    + "('" + phy.getName() + "', '" + phy.getDesignation() + "',"
                    + " '" + phy.getQualification() + "', '" + phy.getLocation() + "',\n"
                    + "'" + phy.getActive() + "', '" + Constants.employeeID + "', now(), '" + Constants.terminalName + "')";
        } else {
            query = "update radiology.referring_physician\n"
                    + "set\n"
                    + "name ='" + phy.getName() + "',\n"
                    + "designation='" + phy.getDesignation() + "',\n"
                    + "qualification='" + phy.getQualification() + "',\n"
                    + "active='" + phy.getActive() + "',\n"
                    + "location_id='" + phy.getLocation() + "',\n"
                    + "user_name='" + Constants.employeeID + "',\n"
                    + "trans_date=now(),\n"
                    + "terminal_name = '" + Constants.terminalName + "'"
                    + "where \n"
                    + "id='" + phy.getId() + "'";
        }

        try {

            if (con == null || con.isClosed()) {
                this.connection();
            }
            System.out.println(query);
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = (String.valueOf(rs.getInt(1)));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public void populateLocation() {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select location_id,Description,active from RADIOLOGY.locations";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                LocationBO lb = new LocationBO();
                lb.setLocationId(rs.getString("location_id"));
                lb.setName(rs.getString("Description"));
                lb.setActive(rs.getString("active"));
                Constants.locationMap.put(lb.getLocationId(), lb);
            }

        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getRoleWiseLocationRights(String role) {
        try {
            Constants.locationMap.clear();
            if (con == null || con.isClosed()) {
                this.connection();
            }
            //query = "select location_id,active from RADIOLOGY.role_wise_location_rights where role_id='" + employeeRole + "'";
            query = "select r.location_id,description, r.active \n"
                    + "from RADIOLOGY.role_wise_location_rights r \n"
                    + "join radiology.locations l on r.location_id=l.location_id \n"
                    + "where l.Active='Y' and r.active='Y' and r.role_id='" + role + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                LocationBO lb = new LocationBO();
                lb.setLocationId(rs.getString("location_id"));
                lb.setName(rs.getString("Description"));
                lb.setActive(rs.getString("active"));
                Constants.locationMap.put(lb.getLocationId(), lb);
            }

        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getActiveLocations(LinkedHashMap<String, LocationBO> location) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select location_id,Description,active from RADIOLOGY.locations where active='Y'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                LocationBO lb = new LocationBO();
                lb.setLocationId(rs.getString("location_id"));
                lb.setName(rs.getString("Description"));
                lb.setActive(rs.getString("active"));
                location.put(lb.getLocationId(), lb);
            }

        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void employeeLists(HashMap<String, EmployeeBO> hmap) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "Select employee_id,\n"
                    + "       name,\n"
                    + "       father_name,\n"
                    + "       cnic,\n"
                    + "       contact_num,\n"
                    + "       gender,\n"
                    + "       dob,\n"
                    + "       address,\n"
                    + "       city,\n"
                    + "       role,\n"
                    + "       active,\n"
                    + "       location_id,\n"
                    + "       password\n"
                    + "  from radiology.employee ";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                EmployeeBO ebo = new EmployeeBO();
                ebo.setEmployeeID(rs.getString("employee_id"));
                ebo.setName(rs.getString("name"));
                ebo.setFatherName(rs.getString("father_name"));
                ebo.setCnic(rs.getString("cnic"));
                ebo.setContactNum(rs.getString("contact_num"));
                ebo.setGender(rs.getString("gender"));
                ebo.setDob(rs.getString("dob"));
                ebo.setAddress(rs.getString("address"));
                ebo.setCity(rs.getString("city"));
                ebo.setRole(rs.getString("role"));
                ebo.setActive(rs.getString("active"));
                ebo.setLocation(rs.getString("location_id"));
                ebo.setPassword(rs.getString("password"));

                hmap.put(ebo.getEmployeeID(), ebo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void searchEmployee(String id, String name, String cnic, String contact, HashMap<String, EmployeeBO> hmap) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select employee_id,name,cnic,contact_num from radiology.employee where \n"
                    + " name like'%" + name + "%'\n"
                    + "and cnic like '%" + cnic + "%'\n"
                    + "and contact_num like '%" + contact + "%'";

            if (!id.equalsIgnoreCase("")) {
                query = query + " and employee_id ='" + id + "' \n";
            }

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                EmployeeBO ebo = new EmployeeBO();
                ebo.setEmployeeID(rs.getString("employee_id"));
                ebo.setName(rs.getString("name"));
                ebo.setCnic(rs.getString("cnic"));
                ebo.setContactNum(rs.getString("contact_num"));

                hmap.put(ebo.getEmployeeID(), ebo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchRefPhy(String id, String name, String locID, HashMap<String, ReferringPhysicianBO> hmap) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT id,name,qualification, designation,location_id,active FROM radiology.referring_physician where \n"
                    + " name like'%" + name + "%'\n";
//                    + "and cnic like '%" + cnic + "%'\n"
//                    + "and contact_num like '%" + contact + "%'";

            if (!id.equalsIgnoreCase("")) {
                query = query + " and id ='" + id + "' \n";
            }

            if (!locID.equalsIgnoreCase("")) {
                query = query + " and location_id ='" + locID + "' \n";
            }

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            System.out.println(query);
            while (rs.next()) {
                ReferringPhysicianBO ebo = new ReferringPhysicianBO();
                ebo.setId(rs.getString("id"));
                ebo.setName(rs.getString("name"));
                ebo.setQualification(rs.getString("qualification"));
                ebo.setDesignation(rs.getString("designation"));
                ebo.setActive(rs.getString("active"));
                ebo.setLocation(rs.getString("location_id"));
                hmap.put(ebo.getId(), ebo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean getEmployeeInfo(String id, String locationID, String[] info) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select name,password,role,active from radiology.employee where employee_id='" + id + "'  and location_id='" + locationID + "'";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                info[0] = new String(rs.getString("name"));
                info[1] = new String(rs.getString("password"));
                info[2] = new String(rs.getString("active"));
                Constants.employeeRole = rs.getString("role");
                flag = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public String insertPatientInfo(PatientBO pbo, boolean updateFlag) {
        if (!updateFlag) {
            query = "INSERT INTO `radiology`.`patient` \n"
                    + "(`location_id`, \n"
                    + "`Name`,\n"
                    + "`father_name`, \n"
                    + "`cnic`, \n"
                    + "`contact_num`, \n"
                    + "`gender`, \n"
                    + "`dob`, \n"
                    + "`address`, \n"
                    + "`city`, \n"
                    + "`registration_date`, \n"
                    + "`otherMrno`, \n"
                    + "`user_name`, \n"
                    + "`trans_date`, \n"
                    + "`terminal_name`,\n"
                    + "`attendant_flag`,\n"
                    + "`pmu_number`"
                    + ") \n"
                    + "VALUES \n"
                    + "('" + Constants.locationID + "', \n"
                    + "'" + pbo.getName() + "', \n"
                    + "'" + pbo.getFatherName() + "', \n"
                    + "'" + pbo.getCnic() + "', \n"
                    + "'" + pbo.getContactNum() + "', \n"
                    + "'" + pbo.getGender() + "', \n"
                    + "'" + pbo.getDob() + "', \n"
                    + "'" + pbo.getAddress() + "', \n"
                    + "'" + pbo.getCity() + "', \n"
                    + "now(), \n"
                    + "'" + pbo.getOtherMrno() + "', \n"
                    + "'" + Constants.employeeID + "', \n"
                    + "now(), \n"
                    + "'" + Constants.terminalName + "',\n"
                    + "'" + pbo.getAttendant_flag() + "',\n"
                    + "'" + pbo.getPmuNumber() + "'"
                    + ")";
        } else {
            query = "update radiology.patient\n"
                    + "set\n"
                    + "name ='" + pbo.getName() + "',\n"
                    + "father_name='" + pbo.getFatherName() + "',\n"
                    + "cnic='" + pbo.getCnic() + "',\n"
                    + "contact_num='" + pbo.getContactNum() + "',\n"
                    + "gender='" + pbo.getGender() + "',\n"
                    + "dob='" + pbo.getDob() + "',\n"
                    + "address='" + pbo.getAddress() + "',\n"
                    + "city='" + pbo.getCity() + "',\n"
                    + "location_id='" + pbo.getLocation_id() + "',\n"
                    + "otherMrno = '" + pbo.getOtherMrno() + "' ,\n"
                    + "pmu_number = '" + pbo.getPmuNumber() + "' ,\n"
                    + "user_name='" + Constants.employeeID + "',\n"
                    + "trans_date=now(),\n"
                    + "terminal_name = '" + Constants.terminalName + "',"
                    + "attendant_flag='" + pbo.getAttendant_flag() + "'"
                    + "where \n"
                    + "patient_id='" + pbo.getPatientId() + "' \n"
                    + "and location_id='" + pbo.getLocation_id() + "'";
        }

        String id = "";
        try {

            if (con == null || con.isClosed()) {
                this.connection();
            }

            System.out.println(query);
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = String.valueOf(rs.getInt(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public void searchPatient(String location_ID, String id, String name, String cnic, String contact, HashMap<String, PatientBO> viewMap) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select patient_id,name,cnic,contact_num,location_id, pmu_number from radiology.patient where \n"
                    + "name like'%" + name + "%'\n"
                    + "and cnic like '%" + cnic + "%'\n"
                    + "and contact_num like '%" + contact + "%' ";

            if (!location_ID.equalsIgnoreCase("")) {
                query = query + " and location_id = '" + location_ID + "' ";
            }

            if (!id.equalsIgnoreCase("")) {
                query = query + " and patient_id = '" + id + "' \n";
            }

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            System.out.println(query);
            while (rs.next()) {
                PatientBO pbo = new PatientBO();
                pbo.setPatientId(rs.getString("patient_id"));
                pbo.setName(rs.getString("name"));
                pbo.setCnic(rs.getString("cnic"));
                pbo.setContactNum(rs.getString("contact_num"));
                pbo.setLocation_id(rs.getString("location_id"));
                pbo.setPmuNumber(rs.getString("pmu_number"));
                viewMap.put(pbo.getPatientId(), pbo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PatientBO getPatientInformation(String locationID, String patientID, PatientBO value) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select patient_id,location_id,name,father_name,cnic,contact_num,gender,dob,address,city,otherMrno,attendant_flag, pmu_number from radiology.patient where \n"
                    + "patient_id ='" + patientID + "'  and location_id='" + locationID + "'\n";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            System.out.println(query);
            while (rs.next()) {
//                value = new PatientBO();
                value.setPatientId(rs.getString("patient_id"));
                value.setLocation_id(rs.getString("location_id"));
                value.setName(rs.getString("name"));
                value.setFatherName(rs.getString("father_name"));
                value.setCnic(rs.getString("cnic"));
                value.setContactNum(rs.getString("contact_num"));
                value.setGender(rs.getString("gender"));
                value.setDob(rs.getString("dob"));
                value.setAddress(rs.getString("address"));
                value.setCity(rs.getString("city"));
                value.setOtherMrno(rs.getString("otherMrno"));
                value.setAttendant_flag(rs.getString("attendant_flag"));
                value.setPmuNumber(rs.getString("pmu_number"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return value;
    }

    public void getBodayParts(LinkedHashMap<String, String> bodyMap) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select body_part_id,description from radiology.body_part  order by description";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                bodyMap.put(rs.getString("body_part_id"), rs.getString("description"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void searchOrderEntry(String locationID, String patientId, String cnic, HashMap<String, OrderEntryBO> orderMap) {
//            try {
//                if(con==null || con.isClosed() )
//                {
//                    this.connection();
//                }
//                query = "select p.patient_id,p.location_id ,p.Name,e.order_id,e.order_location_id,c.id,c.description as cpt,l.Description,\n" +
//                    "e.order_date_time,e.contrast,b.description as body,e.body_part_id,e.history,e.modality \n" +
//                    "from radiology.patient p\n" +
//                    "left join radiology.order_entry e on p.patient_id = e.patient_id\n" +
//                    "left join radiology.body_part b on e.body_part_id = b.body_part_id\n" +
//                    "left join radiology.locations l on e.order_location_id = l.location_id\n" +
//                    "left join radiology.cpt c on c.id = e.cpt_id \n" +
//                    "where  p.cnic like '%"+cnic+"%'\n" +
//                    "and p.location_id like '%"+locationID+"%'\n" +
//                    "and e.order_status in ('1','2','3')\n" ;
//
//
//
//                if(!patientId.equalsIgnoreCase(""))
//                    query  = query + " and p.patient_id = '"+patientId+"'\n" ;
//
//                query = query+ "order by e.order_date_time ";
//
//                System.out.println(query);
//                ps = con.prepareStatement(query);
//                rs = ps.executeQuery();
//                OrderEntryBO.counter=0;
//                while(rs.next())
//                {
//                    OrderEntryBO obo= new OrderEntryBO();
//                    obo.setPatientID(rs.getString("patient_id"));
//                    obo.setPatientName(rs.getString("Name"));
//                    obo.setOrderID(rs.getString("order_id"));
//                    obo.setOrderLocationID(rs.getString("order_location_id"));
//                    obo.setOrderLocationDesc(rs.getString("Description"));
//                    obo.setOrderDateTime(rs.getString("order_date_time"));
//                    obo.setContrast(rs.getString("contrast"));
//                    obo.setBodayPartID(rs.getString("body_part_id"));
//                    obo.setBodyPart(rs.getString("body"));
//                    obo.setPatientLocationID(rs.getString("location_id"));
//                    obo.setHistory(rs.getString("history"));
//                    obo.setModality(rs.getString("modality"));
//                    obo.setCptId(rs.getString("id"));
//                    obo.setCptDesc(rs.getString("cpt"));
//                    orderMap.put(Integer.toString(obo.counter), obo);
//                    OrderEntryBO.counter++;
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    public String getHistory(String orderID) {
        String history = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select history from radiology.order_master where order_id='" + orderID + "'";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                history = rs.getString("history");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return history;
    }

//    public boolean checkDuplication(String orderID) {
//        boolean flag=false;
//        try {
//                if(con==null || con.isClosed() )
//                {
//                    this.connection();
//                }
//                query = "select order_status from radiology.order_entry where order_id='"+orderID+"'";
//
//                ps = con.prepareStatement(query);
//                rs = ps.executeQuery();
//
//                if(rs.next())
//                {
//                    flag = rs.getString("order_status").equals("2");
//                }
//
//            } catch (SQLException ex) {
//            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return flag;
//    }
    //------------------------------------------------------------------------------------------
//    public void searchreportPatient(String location_ID, String id,  String cnic, String dateFrom, String dateTo, String modality,HashMap<String, OrderEntryBO> viewMap, String orderStatus) {
//        try {
//            if(con==null || con.isClosed() )
//            {
//                this.connection();
//            }
//            query = "select p.patient_id,p.location_id ,p.Name,e.order_id,\n" +
//                    "d.acquisition_time,c.id,c.description,c.modality from radiology.patient p\n" +
//                    "left join radiology.order_entry e on p.patient_id = e.patient_id\n" +
//                    "left join radiology.body_part b on e.body_part_id = b.body_part_id\n" +
//                    "left join radiology.locations l on e.order_location_id = l.location_id\n" +
//                    "left join radiology.dicom_study d on e.order_id = d.order_id\n" +
//                    "left join radiology.cpt c on c.id = e.cpt_id\n" +
//                    "where p.location_id = '"+location_ID+"'\n" +
//
//                    "and p.cnic like '%"+cnic+"%'\n" +
//                    "and e.order_status='"+orderStatus+"'\n" ;
//
//                    if(!id.equalsIgnoreCase(""))
//                    {
//                        query = query +  " and p.patient_id = '"+id+"'\n" ;
//                    }
//
//            if(!dateFrom.equals("") && !dateTo.equals(""))
//            {
//                query= query+"and date_format(d.acquisition_time,\"%Y-%m-%d\") <= date_format('"+dateFrom+"',\"%Y-%m-%d\")\n" +
//                "and date_format(d.acquisition_time,\"%Y-%m-%d\") >= date_format('"+dateTo+"',\"%Y-%m-%d\")\n";
//            }
//            query = query+"order by patient_id";
//
//            System.out.println(query);
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//
//            while(rs.next())
//            {
//                OrderEntryBO pbo = new OrderEntryBO();
//                pbo.setPatientID(rs.getString("patient_id"));
//                pbo.setPatientName(rs.getString("name"));
//                pbo.setOrderDateTime(rs.getString("acquisition_time"));
//                pbo.setOrderID(rs.getString("order_id"));
//                pbo.setPatientLocationID(rs.getString("location_id"));
//
//                pbo.setModality(rs.getString("modality"));
//                pbo.setCptDesc(rs.getString("description"));
//                pbo.setCptId(rs.getString("id"));
//                viewMap.put(pbo.getOrderID(), pbo);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    //---------------------------------------------------------------------------------------
    public boolean finalSign(String orderid, String orderStatus, String findings) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select * from radiology.patient_reports where  order_id = '" + orderid + "'";
            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                query = "update radiology.order_detail  set "
                        + "status = '" + orderStatus + "',\n"
                        + "user_name='" + Constants.employeeID + "',\n"
                        + "trans_date=now(),\n"
                        + "terminal_name = '" + Constants.terminalName + "'"
                        + "where id = '" + orderid + "'";
                System.out.println(query);
                ps = con.prepareStatement(query);
                ps.executeUpdate();
                ps.close();
                flag = true;

                query = "UPDATE `radiology`.`patient_reports` SET "
                        + "`employee_id`='" + Constants.employeeID + "',\n "
                        + "`findings`='" + findings + "',\n "
                        + "user_name='" + Constants.employeeID + "',\n"
                        + "trans_date=now(),\n"
                        + "terminal_name = '" + Constants.terminalName + "'"
                        + "WHERE `order_id`='" + orderid + "';";
                System.out.println(query);
                ps = con.prepareStatement(query);
                ps.executeUpdate();
                ps.close();
                flag = true;

            } else {
                flag = false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    //--------------------------------------------------------------------------------------
//    public void getPendingPatients(HashMap<String, OrderEntryBO> viewMap) {
//        try {
//            if(con==null || con.isClosed() )
//            {
//                this.connection();
//            }
//            query = "select p.patient_id,p.location_id ,p.Name,e.order_id,e.modality,\n" +
//                            "d.acquisition_time,c.id,c.description as body, c.contrast from radiology.patient p\n" +
//                            "left join radiology.order_entry e on p.patient_id = e.patient_id\n" +
//                            "left join radiology.body_part b on e.body_part_id = b.body_part_id\n" +
//                            "left join radiology.locations l on e.order_location_id = l.location_id\n" +
//                            "left join radiology.dicom_study d on e.order_id = d.order_id\n" +
//                            "left join radiology.cpt c on c.id = e.cpt_id\n" +
//                            "where e.order_status='4'\n" +
//                            "order by patient_id";
//
//            System.out.println(query);
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//
//            while(rs.next())
//            {
//                OrderEntryBO pbo = new OrderEntryBO();
//                pbo.setPatientID(rs.getString("patient_id"));
//                pbo.setPatientName(rs.getString("name"));
//                pbo.setOrderDateTime(rs.getString("acquisition_time"));
//                pbo.setOrderID(rs.getString("order_id"));
//                pbo.setPatientLocationID(rs.getString("location_id"));
//                pbo.setModality(rs.getString("modality"));
//                pbo.setContrast(rs.getString("contrast"));
//
//                pbo.setCptId(rs.getString("id"));
//                pbo.setCptDesc(rs.getString("body"));
//                pbo.setContrast(rs.getString("contrast"));
//                viewMap.put(pbo.getOrderID(), pbo);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//-------------------------------------------------------------------------

    public boolean saveReport(String locId, String orderid, String report, String impression) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select * from radiology.patient_reports "
                    + "where location_id = '" + locId + "' and  order_id = '" + orderid + "'";
            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                query = "update radiology.patient_reports\n"
                        + "set result='" + report + "',\n"
                        + "impression = '" + impression + "',\n"
                        + "report_date = NOW(),\n"
                        + "user_name='" + Constants.employeeID + "',\n"
                        + "trans_date=now(),\n"
                        + "terminal_name = '" + Constants.terminalName + "'"
                        + "where location_id = '" + locId + "' and order_id = '" + orderid + "' ";
            } else {
                query = "INSERT INTO radiology.patient_reports\n"
                        + "  (location_id, order_id, employee_id, result, impression, report_date,"
                        + "`user_name`,`trans_date`,`terminal_name`)\n"
                        + "VALUES\n"
                        + "  ('" + locId + "',\n"
                        + "   '" + orderid + "',\n"
                        + "   '" + Constants.employeeID + "',\n"
                        + "   '" + report + "',\n"
                        + "   '" + impression + "',\n"
                        + "   NOW(),\n"
                        + "'" + Constants.employeeID + "',"
                        + "NOW(),"
                        + "'" + Constants.terminalName + "'"
                        + ")";

            }
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    //----------------------------------------------------------------------------------------

    //-------------------------------------------------------------------------
    public String getStudyPath(String patientId, String orderid) {
        String path = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT s.file_path, s.primary_server_path, s.backup_server_path FROM dicom_study s "
                    + "where s.order_id = '" + orderid + "' and s.patient_id = '" + patientId + "' ";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                path = rs.getString("file_path");
                Constants.primeryServer = rs.getString("primary_server_path");
                Constants.secondaryServer = rs.getString("backup_server_path");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return path;
    }
    //----------------------------------------------------------------------------------------------------
//   public String getReport(String loc, String orderid)
//   {
//       String his = "";
//       try {
//            if(con==null || con.isClosed() )
//            {
//                this.connection();
//            }
//            query = "select p.result, p.impression from radiology.patient_reports p, radiology.order_detail o"
//                    + " where p.location_id = '"+loc+"'  and p.order_id = '"+orderid+"' and o.id = '"+orderid+"'";// and o.order_status = '4' ";
//
//            System.out.println(query);
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//
//            while(rs.next())
//            {
//                his = rs.getString("result")+"######"+rs.getString("impression");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       return his;
//   }
    //---------------------------------------------------------------------------------------
//    public void getOrderPatients(HashMap<String, OrderEntryBO> viewMap,String orderStatus) {
//        try {
//            if(con==null || con.isClosed() )
//            {
//                this.connection();
//            }
//            query = "select e.patient_id, e.patient_location_id ,p.Name,e.order_id,b.description as body,\n" +
//                        "e.order_date_time, d.image_count,c.id,c.description,c.contrast from radiology.patient p\n" +
//                        "left join radiology.order_entry e on p.patient_id = e.patient_id\n" +
//                        "left join radiology.body_part b on e.body_part_id = b.body_part_id\n" +
//                        "left join radiology.locations l on e.order_location_id = l.location_id\n" +
//                        "left join radiology.dicom_study d on e.order_id = d.order_id\n" +
//                        "left join radiology.cpt c on c.id = e.cpt_id\n" +
//                        "where e.order_status='"+orderStatus+"' and e.order_location_id = '"+Constants.locationID+"'\n" +
//                        "order by patient_id";
//
//            System.out.println(query);
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//
//            while(rs.next())
//            {
//                OrderEntryBO pbo = new OrderEntryBO();
//                pbo.setPatientID(rs.getString("patient_id"));
//                pbo.setPatientName(rs.getString("name"));
//                pbo.setOrderDateTime(rs.getString("order_date_time"));
//                pbo.setOrderID(rs.getString("order_id"));
//                pbo.setPatientLocationID(rs.getString("patient_location_id"));
//                pbo.setContrast(rs.getString("contrast"));
//                pbo.setBodyPart(rs.getString("body"));
//                pbo.setImageCount(rs.getString("image_count"));
//                pbo.setCptId("id");
//                pbo.setCptDesc(rs.getString("description"));
//                viewMap.put(pbo.getOrderID(), pbo);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

//    public void searchPatientOrder(String locationID, String patientId, String cnic, HashMap<String, OrderEntryBO> pendingPatientMap, String orderStatus) {
//        try {
//                if(con==null || con.isClosed() )
//                {
//                    this.connection();
//                }
//                query = "select e.order_id,p.location_id, p.patient_id ,p.Name,b.description as body,\n" +
//                            "e.order_date_time,d.image_count,c.id,c.description as cptDesc,c.contrast  from radiology.patient p\n" +
//                            "left join radiology.order_entry e on p.patient_id = e.patient_id\n" +
//                            "left join radiology.body_part b on e.body_part_id = b.body_part_id\n" +
//                            "left join radiology.locations l on e.order_location_id = l.location_id\n" +
//                            "left join radiology.dicom_study d on e.order_id = d.order_id\n" +
//                            "left join radiology.cpt c on c.id = e.cpt_id\n" +
//                            "where e.order_status in ('"+orderStatus+"')\n" +
//                            "and\n" +
//                            "p.patient_id like '%"+patientId+"%'\n" +
//                            "and p.cnic like '%"+cnic+"%'\n" +
//                            "and p.location_id like '%"+locationID+"%'\n" +
//                            "order by p.patient_id";
//
//
//                System.out.println(query);
//                ps = con.prepareStatement(query);
//                rs = ps.executeQuery();
//                while(rs.next())
//                {
//                    OrderEntryBO obo= new OrderEntryBO();
//                    obo.setPatientID(rs.getString("patient_id"));
//                    obo.setPatientName(rs.getString("Name"));
//                    obo.setOrderID(rs.getString("order_id"));
//                    obo.setOrderDateTime(rs.getString("order_date_time"));
//                    obo.setContrast(rs.getString("contrast"));
//                    obo.setBodyPart(rs.getString("body"));
//                    obo.setPatientLocationID(rs.getString("location_id"));
//                    obo.setImageCount(rs.getString("image_count"));
//                    obo.setCptId(rs.getString("id"));
//                    obo.setCptDesc(rs.getString("cptDesc"));
//                    pendingPatientMap.put(obo.getOrderID(), obo);
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//            }
//    }
//     public void acknowledgePatient(String orderID,String orderStatus) {
//       try {
//            if(con==null || con.isClosed() )
//            {
//                this.connection();
//            }
//
//            query = "update radiology.order_entry e,radiology.dicom_study r\n" +
//                    "set\n" +
//                    "e.order_status='"+orderStatus+"',\n"
//                    + "user_name='"+Constants.employeeID+"',\n"
//                    + "trans_date=now(),\n"
//                    + "terminal_name = '"+Constants.terminalName+"'" +
//                    "where e.order_id='"+orderID+"' \n" +
//                    "and e.order_id=r.order_id";
//            System.out.println(query);
//            ps = con.prepareStatement(query);
//            ps.executeUpdate();
//            ps.close();
//        } catch (SQLException ex) {
//            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    //----------------------------------------------------------------------------------------------------
    public String getReportHistory(String patientID, String orderid) {
        String his = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select p.result, p.impression,  p.report_id,m.history from radiology.patient_reports p\n"
                    + "join radiology.order_detail d on p.order_id = d.id\n"
                    + "join radiology.order_master m on m.order_id = d.order_id\n"
                    + "where d.id='" + orderid + "'\n"
                    + "and d.status='" + Constants.status[5] + "'";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                his = rs.getString("history") + "######" + rs.getString("result") + "######" + rs.getString("impression") + "######" + rs.getString("report_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return his;
    }

    public boolean saveAddendum(String text, String reportID) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "INSERT INTO radiology.addendum "
                    + "(text, report_id, doc_id, transaction_date,"
                    + "`user_name`,`trans_date`,`terminal_name`) "
                    + "VALUES ('" + text + "', '" + reportID + "', '" + Constants.employeeID + "', now(),"
                    + "'" + Constants.employeeID + "',"
                    + "NOW(),"
                    + "'" + Constants.terminalName + "'"
                    + ")";
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public void getEmployeeRegData(String from, String to, HashMap<String, String> employee) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select employee_id,name,registration_date from radiology.employee e\n"
                    + "where e.registration_date between STR_TO_DATE('" + from + "', '%Y-%m-%d') and STR_TO_DATE('" + to + "', '%Y-%m-%d')\n"
                    + "and e.location_id='" + Constants.locationID + "'";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                employee.put(rs.getString("employee_id"), rs.getString("name") + "#" + rs.getString("registration_date"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getPatientRegData(String from, String to, HashMap<String, String> employee) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select patient_id,location_id,name,registration_date from radiology.patient\n"
                    + "where registration_date between STR_TO_DATE('" + from + "', '%Y-%m-%d') and STR_TO_DATE('" + to + "', '%Y-%m-%d')"
                    + "and location_id='" + Constants.locationID + "'";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                employee.put(rs.getString("location_id") + "-" + rs.getString("patient_id"), rs.getString("name") + "#" + rs.getString("registration_date"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void getPendingReportData(String from, String to, HashMap<String, String> employee) {
//        try {
//                if(con==null || con.isClosed() )
//                {
//                    this.connection();
//                }
//                query = "select o.order_id,p.patient_id,patient_location_id,p.Name,d.acquisition_time from radiology.order_entry o\n" +
//                            "join radiology.dicom_study d on d.order_id = o.order_id\n" +
//                            "left join radiology.patient p on p.patient_id = o.patient_id\n" +
//                            "and p.patient_id is not null\n" +
//                            "and o.order_location_id='"+Constants.locationID+"'\n" +
//                            "and o.order_status='3'\n" +
//                            "and d.acquisition_time between STR_TO_DATE('"+from+"', '%Y-%m-%d') and STR_TO_DATE('"+to+"', '%Y-%m-%d')";
//
//                ps = con.prepareStatement(query);
//                rs = ps.executeQuery();
//                System.out.println(query);
//                while(rs.next())
//                {
//
//                    employee.put(rs.getString("order_id"),rs.getString("patient_location_id")+"-"+rs.getString("patient_id")+"#"+rs.getString("Name")+"#"+rs.getString("acquisition_time"));
//
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//            }
//    }
    public String getStockInformation(String locationID) {
        String result = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            query = "Select l.description,IFNULL(s.cd,0) as cd,IFNULL(s.film,0) as film,IFNULL(s.contrast,0) as contrast from radiology.stock s \n"
                    + "        join radiology.locations l on l.location_id = s.location_id\n"
                    + "        where l.location_id='" + locationID + "'";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                result = rs.getString("description") + "#" + rs.getString("cd") + "#" + rs.getString("film") + "#" + rs.getString("contrast");

            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public void getSignedReport(String locationID, String empID, String from, String to, HashMap<String, String> signedReport) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "        select  p.report_id,p.employee_id,p.order_id,p.report_date from radiology.patient_reports p \n"
                    + "            where p.location_id='" + locationID + "'\n";

            if (!from.equals("") && !to.equals("")) {
                query = query + "and p.report_date between STR_TO_DATE('" + from + "', '%Y-%m-%d') and STR_TO_DATE('" + to + "', '%Y-%m-%d')\n";
            }
            if (!empID.equals("")) {
                query = query + "and  p.employee_id='" + empID + "'";
            }

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            System.out.println(query);
            while (rs.next()) {
                signedReport.put(rs.getString("report_id"), rs.getString("order_id") + "#" + rs.getString("employee_id") + "#" + rs.getString("report_date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PatientReportBO getReport(String order) {
        PatientReportBO prbo = new PatientReportBO();

        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select r.report_id,r.result,r.impression,r.report_date,r.employee_id  from radiology.patient_reports r where r.order_id='" + order + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            System.out.println(query);
            if (rs.next()) {
                prbo.setReportId(rs.getString("report_id"));
                prbo.setResult(rs.getString("result"));
                prbo.setImpression(rs.getString("impression"));
                prbo.setReportDate(rs.getString("report_date"));
                prbo.setDoctorId(rs.getString("employee_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prbo;
    }

    public HashMap<String, String> getAddendum(String reportId) {
        HashMap<String, String> hm = new HashMap<String, String>();
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select text,transaction_date from radiology.addendum where report_id='" + reportId + "'  order by transaction_date";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            System.out.println(query);
            for (int i = 0; rs.next(); i++) {
                hm.put(String.valueOf(i), rs.getString("text") + "####" + rs.getString("transaction_date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }

        return hm;
    }
///------------------here

    public boolean insertLocation(String text) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "insert into radiology.locations\n"
                    + "set description='" + text + "',\n"
                    + "active='Y',\n"
                    + "user_name='" + Constants.employeeID + "',\n"
                    + "trans_date=now(),\n"
                    + "terminal_name = '" + Constants.terminalName + "'";
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public void updateLocation(String id, String active) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            query = "update radiology.locations e\n"
                    + "set\n"
                    + "e.active='" + active + "',\n"
                    + "user_name='" + Constants.employeeID + "',\n"
                    + "trans_date=now(),\n"
                    + "terminal_name = '" + Constants.terminalName + "'"
                    + "where e.location_id='" + id + "' ";
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean updatePassword(String employeeID, String locationID, String newPassword) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            query = "update radiology.employee e\n"
                    + "set\n"
                    + "e.password='" + newPassword + "',\n"
                    + "user_name='" + Constants.employeeID + "',\n"
                    + "trans_date=now(),\n"
                    + "terminal_name = '" + Constants.terminalName + "'"
                    + "where e.location_id='" + locationID + "' "
                    + "\n and e.employee_id='" + employeeID + "'";
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public boolean insertPatientPictures(String[] pic, String loc, String id) {
        boolean flag = false;
        FileInputStream inputStream[] = new FileInputStream[4];
        PreparedStatement statement = null;
        File[] image = new File[4];
        for (int i = 0; i < pic.length; i++) {
            image[i] = new File(pic[i]);
            try {
                inputStream[i] = new FileInputStream(image[i]);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        File image = new File("C:\\Users\\faizanahmed\\Pictures\\Camera Roll\\download.png");
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "update radiology.patient "
                    + "set picture=?,idCard=?,idCardBack=?,referLetter=?,"
                    + "user_name='" + Constants.employeeID + "',\n"
                    + "trans_date=now(),\n"
                    + "terminal_name = '" + Constants.terminalName + "' "
                    + "where patient_id='" + id + "' and location_id='" + loc + "'";

            statement = con.prepareStatement(query);
            statement.setBinaryStream(1, (InputStream) inputStream[0], (int) image[0].length());
            statement.setBinaryStream(2, (InputStream) inputStream[1], (int) image[1].length());
            statement.setBinaryStream(3, (InputStream) inputStream[2], (int) image[2].length());
            statement.setBinaryStream(4, (InputStream) inputStream[3], (int) image[3].length());
            statement.executeUpdate();
            System.out.println(query);
//            ps = con.prepareStatement(query);
//            ps.executeUpdate();
//            ps.close();
            statement.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public boolean insertPatientAttachment(String pic, String loc, String id, String desc) {
        boolean flag = false;
        FileInputStream inputStream = null;
        PreparedStatement statement = null;
        File image = new File(pic);
//        for(int i=0;i<pic.length;i++)
        {
//            image[i] = new File(pic[i]);
            try {
                inputStream = new FileInputStream(image);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        File image = new File("C:\\Users\\faizanahmed\\Pictures\\Camera Roll\\download.png");
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
//            query = "update radiology.patient "
//                    + "set picture=?,idCard=?,idCardBack=?,referLetter=?,"
//                    + "user_name='"+Constants.employeeID+"',\n"
//                    + "trans_date=now(),\n"
//                    + "terminal_name = '"+Constants.terminalName+"' "
//                    + "where patient_id='"+id+"' and location_id='"+loc+"'";
            query = "INSERT INTO `radiology`.`patient_wise_captured_reports` "
                    + "(`patient_id`, `patient_location_id`,`captured_item`,"
                    + " `user_name`, `terminal_name`, `trans_date`,`item_name`) VALUES "
                    + "(?,?,?,?,?, now(),?);";

            statement = con.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, loc);
            statement.setBinaryStream(3, (InputStream) inputStream, (int) image.length());
            statement.setString(4, Constants.employeeID);
            statement.setString(5, Constants.terminalName);
            statement.setString(6, desc);
            statement.executeUpdate();
            System.out.println(query);
//            ps = con.prepareStatement(query);
//            ps.executeUpdate();
//            ps.close();
            statement.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    //-------------pics
    public boolean insertAttendantPictures(String[] pic, String loc, String id) {
        boolean flag = false;
        FileInputStream inputStream[] = new FileInputStream[4];
        PreparedStatement statement = null;
        File[] image = new File[4];
        for (int i = 0; i < pic.length; i++) {
            image[i] = new File(pic[i]);
            try {
                inputStream[i] = new FileInputStream(image[i]);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        File image = new File("C:\\Users\\faizanahmed\\Pictures\\Camera Roll\\download.png");
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "update radiology.attendant "
                    + "set picture=?,id_front=?,id_back=?,refer_letter=?,"
                    + "user_name='" + Constants.employeeID + "',\n"
                    + "trans_date=now(),\n"
                    + "terminal_name = '" + Constants.terminalName + "' "
                    + "where patient_id='" + id + "' and patient_location_id='" + loc + "'";

            statement = con.prepareStatement(query);
            statement.setBinaryStream(1, (InputStream) inputStream[0], (int) image[0].length());
            statement.setBinaryStream(2, (InputStream) inputStream[1], (int) image[1].length());
            statement.setBinaryStream(3, (InputStream) inputStream[2], (int) image[2].length());
            statement.setBinaryStream(4, (InputStream) inputStream[3], (int) image[3].length());
            statement.executeUpdate();
            System.out.println(query);
//            ps = con.prepareStatement(query);
//            ps.executeUpdate();
//            ps.close();
            statement.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public boolean getPatientPicture(String location, String id) {

        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT picture FROM radiology.patient where patient_id='" + id + "' and location_id='" + location + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            System.out.println(query);
            OutputStream outputStream = null;

            File path = new File(System.getProperty("java.io.tmpdir") + "\\get\\pro" + System.currentTimeMillis() % 1000 + ".png");
            Constants.profilePath = path.getAbsolutePath();
            path.getParentFile().mkdirs();
            if (rs.next()) {
                Blob blob = rs.getBlob("picture");
                InputStream inputStream = null;
                try {
                    inputStream = blob.getBinaryStream();
                    outputStream = new FileOutputStream(path);
                } catch (FileNotFoundException ex) {
                    flag = false;
                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException ex) {
                    flag = false;
                    return flag;
                }

                int bytesRead = -1;
                byte[] buffer = new byte[BUFFER_SIZE];
                try {
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        outputStream.flush();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
                }
                flag = true;
                System.out.println("File saved");

            }

        } catch (SQLException ex) {
            flag = false;
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public String get_profile_picture(String loc, String id) {
        String path = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select \n"
                    + "    p.profile_picture\n"
                    + "from\n"
                    + "    radiology.patient p\n"
                    + "where\n"
                    + "    p.patient_id = " + id + "\n"
                    + "        and p.location_id = " + loc + "";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                path = rs.getString("profile_picture");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return path;
    }

    public String get_profile_picture_attendant(String loc, String id) {
        String path = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select \n"
                    + "    p.profile_picture\n"
                    + "from\n"
                    + "    radiology.attendant p\n"
                    + "where\n"
                    + "    p.patient_id = " + id + "\n"
                    + "        and p.patient_location_id = " + loc + "";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                path = rs.getString("profile_picture");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return path;
    }

    public String get_id_front(String loc, String id) {
        String path = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select \n"
                    + "    p.id_card_front\n"
                    + "from\n"
                    + "    radiology.patient p\n"
                    + "where\n"
                    + "    p.patient_id = " + id + "\n"
                    + "        and p.location_id = " + loc + "";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                path = rs.getString("id_card_front");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return path;
    }

    public String get_id_front_attendant(String loc, String id) {
        String path = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select \n"
                    + "    p.id_front_path\n"
                    + "from\n"
                    + "    radiology.attendant p\n"
                    + "where\n"
                    + "    p.patient_id = " + id + "\n"
                    + "        and p.patient_location_id = " + loc + "";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                path = rs.getString("id_front_path");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return path;
    }

    public String get_id_card_back(String loc, String id) {
        String path = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select \n"
                    + "    p.id_card_back\n"
                    + "from\n"
                    + "    radiology.patient p\n"
                    + "where\n"
                    + "    p.patient_id = " + id + "\n"
                    + "        and p.location_id = " + loc + "";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                path = rs.getString("id_card_back");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return path;
    }

    public String get_id_card_back_attendant(String loc, String id) {
        String path = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select \n"
                    + "    p.id_back_path\n"
                    + "from\n"
                    + "    radiology.attendant p\n"
                    + "where\n"
                    + "    p.patient_id = " + id + "\n"
                    + "        and p.patient_location_id = " + loc + "";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                path = rs.getString("id_back_path");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return path;
    }

//    public boolean getAttendantPicture(String location, String id) {
//
//        boolean flag = false;
//        try {
//            if (con == null || con.isClosed()) {
//                this.connection();
//            }
//            query = "SELECT picture FROM radiology.attendant where patient_id='" + id + "' and patient_location_id='" + location + "'";
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//            System.out.println(query);
//            OutputStream outputStream = null;
//
//            File path = new File(System.getProperty("java.io.tmpdir") + "\\get\\pro" + System.currentTimeMillis() % 1000 + ".png");
//            Constants.attendProfilePath = path.getAbsolutePath();
//            path.getParentFile().mkdirs();
//            if (rs.next()) {
//                Blob blob = rs.getBlob("picture");
//                InputStream inputStream = null;
//                try {
//                    inputStream = blob.getBinaryStream();
//                    outputStream = new FileOutputStream(path);
//                } catch (FileNotFoundException ex) {
//                    flag = false;
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (NullPointerException ex) {
//                    flag = false;
//                    return flag;
//                }
//
//                int bytesRead = -1;
//                byte[] buffer = new byte[BUFFER_SIZE];
//                try {
//                    while ((bytesRead = inputStream.read(buffer)) != -1) {
//                        outputStream.write(buffer, 0, bytesRead);
//                        outputStream.flush();
//                    }
//                } catch (IOException ex) {
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                try {
//                    inputStream.close();
//                    outputStream.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                flag = true;
//                System.out.println("File saved");
//
//            }
//
//        } catch (SQLException ex) {
//            flag = false;
//            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return flag;
//    }
    public boolean saveThumbImpression(String loc, String id) {
        boolean flag = false;
        File image = new File(Constants.thumbImpressionFile);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(image);
        } catch (FileNotFoundException ex) {
            flag = false;
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
            return flag;
        }
        PreparedStatement statement = null;

        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "update radiology.patient set thumb=?,"
                    + "user_name='" + Constants.employeeID + "',\n"
                    + "trans_date=now(),\n"
                    + "terminal_name = '" + Constants.terminalName + "' "
                    + "where patient_id='" + id + "' and location_id='" + loc + "'";

            statement = con.prepareStatement(query);
            statement.setBinaryStream(1, (InputStream) inputStream, (int) image.length());
            statement.executeUpdate();
            System.out.println(query);
            statement.close();
            flag = true;
        } catch (SQLException ex) {
            flag = false;
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
            return flag;
        }
        return flag;
    }

    public boolean saveThumbImpressionAttendant(String loc, String id) {
        boolean flag = false;
        Constants.thumbImpressionFile = System.getProperty("java.io.tmpdir") + "\\FingerPrint\\Fingerprint.png";
        File image = new File(Constants.thumbImpressionFile);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(image);
        } catch (FileNotFoundException ex) {
            flag = false;
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
            return flag;
        }
        PreparedStatement statement = null;

        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "update radiology.attendant set thumb=?,"
                    + "user_name='" + Constants.employeeID + "',\n"
                    + "trans_date=now(),\n"
                    + "terminal_name = '" + Constants.terminalName + "' "
                    + "where patient_id='" + id + "' and patient_location_id='" + loc + "'";

            statement = con.prepareStatement(query);
            statement.setBinaryStream(1, (InputStream) inputStream, (int) image.length());
            statement.executeUpdate();
            System.out.println(query);
            statement.close();
            flag = true;
        } catch (SQLException ex) {
            flag = false;
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
            return flag;
        }
        return flag;
    }

    public boolean getThumbImpression(String location, String id) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT thumb FROM radiology.patient where patient_id='" + id + "' and location_id='" + location + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            System.out.println(query);
            OutputStream outputStream = null;
            InputStream inputStream = null;
            File path = new File(System.getProperty("java.io.tmpdir") + "\\FingerPrint\\getThumb" + System.currentTimeMillis() % 1000 + ".png");
            path.getParentFile().mkdirs();
            Constants.getThumbImpression = path.getAbsolutePath();
            if (rs.next()) {
                Blob blob = rs.getBlob("thumb");
                if (blob != null) {
                    try {
                        inputStream = blob.getBinaryStream();
                        outputStream = new FileOutputStream(path);
                    } catch (FileNotFoundException ex) {
                        flag = false;
                    }

                    int bytesRead = -1;
                    byte[] buffer = new byte[BUFFER_SIZE];
                    try {
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                            outputStream.flush();
                        }
                    } catch (IOException ex) {
                        flag = false;
                    }
                    try {
                        inputStream.close();
                        outputStream.close();
                        flag = true;
                    } catch (IOException ex) {
                        flag = false;
                    }
                } else {
                    flag = false;
                }
            }

        } catch (SQLException ex) {
            flag = false;
        }
        return flag;
    }

    public boolean getThumbImpressionAttendant(String location, String id) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT thumb FROM radiology.attendant where patient_id='" + id + "' and patient_location_id='" + location + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            System.out.println(query);
            OutputStream outputStream = null;
            InputStream inputStream = null;
            File path = new File(System.getProperty("java.io.tmpdir") + "\\FingerPrint\\getThumb" + System.currentTimeMillis() % 1000 + ".png");
            path.getParentFile().mkdirs();
            Constants.getThumbImpressionAttendant = path.getAbsolutePath();
            if (rs.next()) {
                Blob blob = rs.getBlob("thumb");
                if (blob != null) {
                    try {
                        inputStream = blob.getBinaryStream();
                        outputStream = new FileOutputStream(path);
                    } catch (FileNotFoundException ex) {
                        flag = false;
                    }

                    int bytesRead = -1;
                    byte[] buffer = new byte[BUFFER_SIZE];
                    try {
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                            outputStream.flush();
                        }
                    } catch (IOException ex) {
                        flag = false;
                    }
                    try {
                        inputStream.close();
                        outputStream.close();
                        flag = true;
                    } catch (IOException ex) {
                        flag = false;
                    }
                } else {
                    flag = false;
                }
            }

        } catch (SQLException ex) {
            flag = false;
        }
        return flag;
    }

    public boolean insertDoctorInfo(String line1, String line2, String degree, String id, boolean updateFlag) {
        if (!updateFlag) {
            query = "INSERT INTO `radiology`.`doctor_qualification`"
                    + " (`doctor_id`, `title1`, `title2`, `degrees`,"
                    + "`user_name`,`trans_date`,`terminal_name`) "
                    + "VALUES "
                    + "('" + id + "', "
                    + "'" + line1 + "',"
                    + " '" + line2 + "',"
                    + " '" + degree + "',"
                    + "'" + Constants.employeeID + "',"
                    + "NOW(),"
                    + "'" + Constants.terminalName + "'"
                    + ");";
        } else {
            query = "UPDATE `radiology`.`doctor_qualification` SET"
                    + " `title1`='" + line1 + "', "
                    + "`title2`='" + line2 + "', "
                    + "`degrees`='" + degree + "',"
                    + "user_name='" + Constants.employeeID + "',\n"
                    + "trans_date=now(),\n"
                    + "terminal_name = '" + Constants.terminalName + "'"
                    + " WHERE"
                    + " `doctor_id`='" + id + "';";
        }

        boolean flag = false;
        try {

            if (con == null || con.isClosed()) {
                this.connection();
            }
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            flag = false;
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public String getDoctorInfo(String id) {
        String result = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT title1,title2,degrees FROM radiology.doctor_qualification where doctor_id='" + id + "'";
            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getString("title1") + "###" + rs.getString("title2") + "###" + rs.getString("degrees");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public void getModality(LinkedHashMap<String, ModalityBO> modalityMap) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select id,description, modality,active from radiology.modality";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                ModalityBO temp = new ModalityBO();
                temp.setId(rs.getString("id"));
                temp.setDescription(rs.getString("Description"));
                temp.setModality(rs.getString("Modality"));
                temp.setActive(rs.getString("Active"));
                modalityMap.put(temp.getId(), temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getModalityDesc(String modality) {
        String temp = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select description from radiology.modality where modality = '" + modality + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                temp = rs.getString("description");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

    public String getDoctorName(String doctorId) {
        String temp = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select name from radiology.employee where employee_id = '" + doctorId + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                temp = rs.getString("name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

    public void searchDicomDirectoryPatients(String patientLocationID, String patientID, String cnic, Object modality, String locationID, String fromDate, String toDate, LinkedHashMap<String, DicomBo> ldbo) {
        try {
            if (this.con == null || this.con.isClosed()) {
                this.connection();
            }

            this.query = "select \n    d.study_id,\n    d.location_id,\n    d.order_id,\n    c.modality,\n    d.acquisition_time,\n    c.description,\n    d.patient_id,\n    p.location_id,\n    p.name,\n    d.contrast_by_radiographer,\n    d.file_path,\n    d.primary_server_path,\n    d.backup_server_path\nfrom\n    radiology.dicom_study d\n        join\n    radiology.order_detail o ON d.order_id = o.id\n        join\n    radiology.order_master m ON m.order_id = o.order_id\n        join\n    radiology.cpt c ON c.id = o.cpt_id\n        join\n    radiology.patient p ON m.patient_id = p.patient_id\n        and p.location_id = m.patient_location_id\nwhere\n    d.location_id in ('1' , '" + locationID + "')";
            if (fromDate.equalsIgnoreCase("") && toDate.equals("") && patientID.equals("") && cnic.equals("")) {
                this.query = this.query + "and DATE(d.acquisition_time) = DATE(now())\n";
            } else if (!fromDate.equalsIgnoreCase("") && !toDate.equals("")) {
                this.query = this.query + " and\n" + "d.acquisition_time between STR_TO_DATE('" + fromDate + "', '%Y-%m-%d') " + "and STR_TO_DATE('" + toDate + "', '%Y-%m-%d')\n";
            }

            if (!modality.equals("ALL")) {
                this.query = this.query + "and c.modality = '" + modality + "'\n";
            }

            if (!cnic.equalsIgnoreCase("")) {
                this.query = this.query + "and p.cnic = '" + cnic + "'\n";
            }

            if (!patientLocationID.equalsIgnoreCase("") && !patientID.equalsIgnoreCase("")) {
                this.query = this.query + "and p.patient_id = '" + patientID + "'\n";
                this.query = this.query + "and p.location_id='" + patientLocationID + "'\n";
            }

            if (!locationID.equals("1")) {
                this.query = this.query + "and p.location_id='" + locationID + "'\n";
            }

            System.out.println(this.query);
            this.ps = this.con.prepareStatement(this.query);
            this.rs = this.ps.executeQuery();

            while (this.rs.next()) {
                DicomBo dbo = new DicomBo();
                dbo.setStudyId(this.rs.getString("study_id"));
                dbo.setLocation_id(this.rs.getString("d.location_id"));
                dbo.setOrder_id(this.rs.getString("order_id"));
                dbo.setModality(this.rs.getString("modality"));
                dbo.setAcqisition_time(this.rs.getString("acquisition_time"));
                dbo.setBodyDescription(this.rs.getString("description"));
                dbo.setPatientId(this.rs.getString("patient_id"));
                dbo.setPatientLocationID(this.rs.getString("p.location_id"));
                dbo.setName(this.rs.getString("name"));
                dbo.setContrast(this.rs.getString("contrast_by_radiographer"));
                dbo.setFilePath(this.rs.getString("file_path"));
                dbo.setPrimaryServer(this.rs.getString("primary_server_path"));
                dbo.setBackupServer(this.rs.getString("backup_server_path"));
                ldbo.put(dbo.getStudyId(), dbo);
            }
        } catch (SQLException var10) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, (String) null, var10);
        }

    }

//    public void searchDicomDirectoryPatients(String patientLocationID,
//            String patientID, String cnic, Object modality, String locationID, String duration,
//            LinkedHashMap<String, DicomBo> ldbo) {
//        try {
//            if (con == null || con.isClosed()) {
//                this.connection();
//            }
//            query = "select \n"
//                    + "    d.study_id,\n"
//                    + "    d.location_id,\n"
//                    + "    d.order_id,\n"
//                    + "    c.modality,\n"
//                    + "    d.acquisition_time,\n"
//                    + "    c.description,\n"
//                    + "    d.patient_id,\n"
//                    + "    p.location_id,\n"
//                    + "    p.name,\n"
//                    + "    d.contrast_by_radiographer,\n"
//                    + "    d.file_path,\n"
//                    + "    d.primary_server_path,\n"
//                    + "    d.backup_server_path\n"
//                    + "from\n"
//                    + "    radiology.dicom_study d\n"
//                    + "        join\n"
//                    + "    radiology.patient p ON d.patient_id = p.patient_id\n"
//                    + "        join\n"
//                    + "    radiology.order_detail o ON d.order_id = o.id\n"
//                    + "        join\n"
//                    + "    radiology.cpt c ON c.id = o.cpt_id\n"
//                    + "where\n"
//                    + "    d.location_id in ('1' , '" + locationID + "')";
//            if (duration.equalsIgnoreCase("")) {
//                query = query + "and DATE(d.acquisition_time) = DATE(now())\n";
//            } else if (!duration.equalsIgnoreCase("NONE")) {
//
//                query = query + " and d.acquisition_time <= now()\n"
//                        + "and d.acquisition_time>= now() " + duration + "\n";
//            }
//
//            if (!modality.equals("ALL")) {
//                query = query + "and c.modality = '" + modality + "'\n";
//            }
//            if (!cnic.equalsIgnoreCase("")) {
//                query = query + "and p.cnic = '" + cnic + "'\n";
//            }
//            if (!patientLocationID.equalsIgnoreCase("") && !patientID.equalsIgnoreCase("")) {
//                query = query + "and p.patient_id = '" + patientID + "'\n";
//                query = query + "and p.location_id='" + patientLocationID + "'\n";
//            }
//            if (!locationID.equals("1")) {
//                query = query + "and p.location_id='" + locationID + "'\n";
//            }
//
//            System.out.println(query);
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                DicomBo dbo = new DicomBo();
//                dbo.setStudyId(rs.getString("study_id"));
//                dbo.setLocation_id(rs.getString("d.location_id"));
//                dbo.setOrder_id(rs.getString("order_id"));
//                dbo.setModality(rs.getString("modality"));
//                dbo.setAcqisition_time(rs.getString("acquisition_time"));
//                dbo.setBodyDescription(rs.getString("description"));
//                dbo.setPatientId(rs.getString("patient_id"));
//                dbo.setPatientLocationID(rs.getString("p.location_id"));
//                dbo.setName(rs.getString("name"));
//                dbo.setContrast(rs.getString("contrast_by_radiographer"));
//                dbo.setFilePath(rs.getString("file_path"));
//                dbo.setPrimaryServer(rs.getString("primary_server_path"));
//                dbo.setBackupServer(rs.getString("backup_server_path"));
//
//                ldbo.put(dbo.getStudyId(), dbo);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public String getViewerPath() {
        String temp = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT value FROM radiology.system_constants where id='VIEWER_PATH'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                temp = rs.getString("value");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }

//    public void searchReportedPatient(String location_ID, String id,HashMap<String, OrderEntryBO> reportedPatientMap, String orderStatus) {
//        try {
//            if(con==null || con.isClosed() )
//            {
//                this.connection();
//            }
//            query = "select p.patient_id,p.location_id ,p.Name,e.order_id,\n" +
//                    "d.acquisition_time,e.contrast,b.description as body from radiology.patient p\n" +
//                    "left join radiology.order_entry e on p.patient_id = e.patient_id\n" +
//                    "left join radiology.body_part b on e.body_part_id = b.body_part_id\n" +
//                    "left join radiology.locations l on e.order_location_id = l.location_id\n" +
//                    "left join radiology.dicom_study d on e.order_id = d.order_id\n" +
//                    "where p.patient_id like '%"+id+"%'\n" +
//                    "and p.location_id like '%"+location_ID+"%'\n" +
//                    "and e.order_status='"+orderStatus+"'\n"
//                    + "order by patient_id" ;
//
//            System.out.println(query);
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//
//            while(rs.next())
//            {
//                OrderEntryBO pbo = new OrderEntryBO();
//                pbo.setPatientID(rs.getString("patient_id"));
//                pbo.setPatientName(rs.getString("name"));
//                pbo.setOrderDateTime(rs.getString("acquisition_time"));
//                pbo.setOrderID(rs.getString("order_id"));
//                pbo.setPatientLocationID(rs.getString("location_id"));
//                pbo.setBodyPart(rs.getString("body"));
//                reportedPatientMap.put(pbo.getOrderID(), pbo);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public boolean insertTemplate(String id, String name, String report, String impression, String employeeID) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            if (id.equals("")) {
                query = "INSERT INTO "
                        + "`radiology`.`template` "
                        + "(`name`, `result`, `impression`, `doctor_id`,`location_id`,"
                        + "`user_name`,`trans_date`,`terminal_name`)"
                        + " VALUES ('" + name + "', "
                        + "'" + report + "', "
                        + "'" + impression + "', "
                        + "'" + employeeID + "',"
                        + "'" + Constants.locationID + "',"
                        + "'" + Constants.employeeID + "',\n"
                        + "NOW(),\n"
                        + "'" + Constants.terminalName + "'"
                        + ")";
            } else {
                query = "UPDATE `radiology`.`template` SET `name`='" + name + "', "
                        + "`result`='" + report + "', `impression`='" + impression + "',"
                        + "user_name='" + Constants.employeeID + "',\n"
                        + "trans_date=now(),\n"
                        + "terminal_name = '" + Constants.terminalName + "'"
                        + " WHERE "
                        + "`template_id`='" + id + "'";
            }
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public void getTemplateInformation(HashMap<String, TemplateBO> templateMap) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT template_id,name,result, impression FROM radiology.template \n"
                    + "where doctor_id='" + Constants.employeeID + "' and location_id='" + Constants.locationID + "' ";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                TemplateBO temp = new TemplateBO();
                temp.setTemplateId(rs.getString("template_id"));
                temp.setName(rs.getString("name"));
                temp.setResult(rs.getString("result"));
                temp.setImpression(rs.getString("impression"));
                templateMap.put(temp.getTemplateId(), temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchTemplate(HashMap<String, TemplateBO> templateMap, String name) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT template_id,name,result, impression FROM radiology.template \n"
                    + "where name like '%" + name + "%' and doctor_id='" + Constants.employeeID + "' and location_id = '" + Constants.locationID + "'";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                TemplateBO temp = new TemplateBO();
                temp.setTemplateId(rs.getString("template_id"));
                temp.setName(rs.getString("name"));
                temp.setResult(rs.getString("result"));
                temp.setImpression(rs.getString("impression"));
                templateMap.put(temp.getTemplateId(), temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertFinalSignAuthority(String employeeID, String selected, String location, boolean updateFlag, String modalities) {
        if (!updateFlag) {
            query = "INSERT INTO `radiology`.`final_sign_authority`"
                    + " (`doctor_id`, `location_id`, `active`,"
                    + "`user_name`,`trans_date`,`terminal_name`,"
                    + "`modality_ids` )"
                    + " VALUES ('" + employeeID + "', '" + location + "', '" + selected + "',"
                    + "'" + Constants.employeeID + "',\n"
                    + "NOW(),\n"
                    + "'" + Constants.terminalName + "',"
                    + "'" + modalities + "'"
                    + ")";
        } else {
            query = "UPDATE `radiology`.`final_sign_authority` "
                    + "SET  "
                    + "`location_id`='" + location + "', "
                    + "`active`='" + selected + "',\n"
                    + "user_name='" + Constants.employeeID + "',\n"
                    + "trans_date=now(),\n"
                    + "terminal_name = '" + Constants.terminalName + "',"
                    + "modality_ids = '" + modalities + "'"
                    + " WHERE "
                    + "`doctor_id`='" + employeeID + "'";
        }

        boolean flag = false;
        try {

            if (con == null || con.isClosed()) {
                this.connection();
            }
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            flag = false;
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public String getFinalSignInfo(String empId) {
        String active = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select active from radiology.final_sign_authority where doctor_id='" + empId + "'";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                active = rs.getString("active");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return active;
    }

    public boolean insertCpt(CptBo cbo, boolean updateFlag) {
        boolean flag = false;
        if (!updateFlag) {
            query = "INSERT INTO `radiology`.`cpt` "
                    + "(`cpt_code`, "
                    + "`description`, "
                    + "`modality`, "
                    + "`contrast`, "
                    + "`body_part`, "
                    + "`cost`, "
                    + "`trans_date`, "
                    + "`active`, "
                    + "`location_id`,"
                    + "`user_name`,`terminal_name`)"
                    + " VALUES ("
                    + "'" + cbo.getCptCode() + "', "
                    + "'" + cbo.getDescription() + "', "
                    + "'" + cbo.getModality() + "', "
                    + "'" + cbo.getContrast() + "',"
                    + "'" + cbo.getBodyPart() + "',"
                    + "'" + cbo.getCost() + "',"
                    + "now(), "
                    + "'" + cbo.getActive() + "', "
                    + "'" + cbo.getLocationId() + "',"
                    + "'" + Constants.employeeID + "',\n"
                    + "'" + Constants.terminalName + "'"
                    + ");";
        } else {
            query = "UPDATE `radiology`.`cpt` "
                    + "SET `cpt_code`='" + cbo.getCptCode() + "', "
                    + "`description`='" + cbo.getDescription() + "', "
                    + "`modality`='" + cbo.getModality() + "', "
                    + "`contrast`='" + cbo.getContrast() + "', "
                    + "`body_part`='" + cbo.getBodyPart() + "', "
                    + "`cost`='" + cbo.getCost() + "', "
                    + "`trans_date`=now(), "
                    + "`active`='" + cbo.getActive() + "', "
                    + "`location_id`='" + cbo.getLocationId() + "',"
                    + "user_name='" + Constants.employeeID + "',\n"
                    + "trans_date=now(),\n"
                    + "terminal_name = '" + Constants.terminalName + "'"
                    + "WHERE `id`='" + cbo.getId() + "';";
        }

        try {

            if (con == null || con.isClosed()) {
                this.connection();
            }

            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();

            flag = true;

        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }

        return flag;
    }

    public void searchCpts(String[] arr, LinkedHashMap<String, CptBo> cboMap) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT id,body_part,cpt_code,c.description as cptDesc,modality,contrast,cost,price,trans_date,active,location_id\n"
                    + "FROM radiology.cpt c\n"
                    + "where location_id = '" + arr[3] + "'\n"
                    + "and cpt_code like '%" + arr[0] + "%'\n"
                    + "and c.description like '%" + arr[1] + "%'";

            if (!arr[2].equalsIgnoreCase("ALL")) {
                query = query + " \nand modality='" + arr[2] + "'\n";
            }

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                CptBo temp = new CptBo();
                temp.setId(rs.getString("id"));
                temp.setCptCode(rs.getString("cpt_code"));
                temp.setDescription(rs.getString("cptDesc"));
                temp.setModality(rs.getString("modality"));
                temp.setContrast(rs.getString("contrast"));
                temp.setCost(rs.getString("cost"));
                temp.setBodyPart(rs.getString("body_part"));
                temp.setPrice(rs.getString("price"));
                temp.setTransDate(rs.getString("trans_date"));
                temp.setActive(rs.getString("active"));
                temp.setLocationId(rs.getString("location_id"));

                cboMap.put(temp.getId(), temp);

            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getCptInformation(LinkedHashMap<String, String> cptMap) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT id,description FROM radiology.cpt where location_id='1' and active='Y' and modality='CT' order by description;";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                String id = rs.getString("id");
                String desc = rs.getString("description");
                cptMap.put(id, desc);

            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertPatientType(String id, String type, String value, String active) {
        boolean flag = false;
        if (id.equalsIgnoreCase("")) {
            query = "INSERT INTO `radiology`.`patient_type` (`description`, `location_id`, `active`,`payment_status`,"
                    + "`user_name`,`trans_date`,`terminal_name`"
                    + ")"
                    + " VALUES "
                    + "('" + type + "', "
                    + "'" + Constants.locationID + "',"
                    + " '" + active + "',"
                    + "'" + value + "',"
                    + "'" + Constants.employeeID + "',\n"
                    + "NOW(),\n"
                    + "'" + Constants.terminalName + "'"
                    + ");";
        } else {
            query = "UPDATE `radiology`.`patient_type` "
                    + "SET "
                    + "`description`='" + type + "', "
                    + "`active`='" + active + "', "
                    + "`user_name`='" + Constants.employeeID + "', "
                    + "`trans_date`=now(), "
                    + "`terminal_name`='" + Constants.terminalName + "', "
                    + "`payment_status`='" + value + "' "
                    + "WHERE `id`='" + id + "'";
        }
        try {

            if (con == null || con.isClosed()) {
                this.connection();
            }

            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();

            flag = true;

        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }

        return flag;
    }

    public void getPatientType(LinkedHashMap<String, PatientTypeBo> patientTypeMap) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT id,description,active,payment_status FROM radiology.patient_type where location_id='1';";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                PatientTypeBo temp = new PatientTypeBo();
                temp.setId(rs.getString("id"));
                temp.setDesc(rs.getString("description"));
                temp.setActive(rs.getString("active"));
                temp.setPayment_status(rs.getString("payment_status"));
                patientTypeMap.put(temp.getId(), temp);

            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePatientStatus(String id, String active) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            query = "update radiology.patient_type e\n"
                    + "set\n"
                    + "e.active='" + active + "',\n"
                    + "user_name='" + Constants.employeeID + "',\n"
                    + "trans_date=now(),\n"
                    + "terminal_name = '" + Constants.terminalName + "'"
                    + "where e.id='" + id + "' ";
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean SaveCptWisePrice(CptWisePricingBo cwpb) {
        boolean flag = false;

        if (cwpb.getId().equalsIgnoreCase("")) {
            query = "INSERT INTO `radiology`.`cpt_pricing` "
                    + "(`location_id`, `patient_type_id`, `cpt_id`, `price`, "
                    + "`discountable`, `active`, `trans_date`, "
                    + "`employee_id`, `terminal`,`reporting_time`)"
                    + " VALUES ('" + cwpb.getLocationId() + "', "
                    + "'" + cwpb.getPatientTypeId() + "', "
                    + "'" + cwpb.getCptCode() + "', "
                    + "'" + cwpb.getPrice() + "', "
                    + "'" + cwpb.getDiscountable() + "', "
                    + "'" + cwpb.getActive() + "', "
                    + "now(), "
                    + "'" + Constants.employeeID + "', "
                    + "'" + Constants.terminalName + "',"
                    + "'" + cwpb.getExpectedReportDate() + "');";
        } else {
            query = "UPDATE `radiology`.`cpt_pricing` SET "
                    + "`location_id`='" + cwpb.getLocationId() + "',"
                    + "`patient_type_id`='" + cwpb.getPatientTypeId() + "', "
                    + "`cpt_id`='" + cwpb.getCptCode() + "', "
                    + "`price`='" + cwpb.getPrice() + "', "
                    + "`discountable`='" + cwpb.getDiscountable() + "', "
                    + "`active`='" + cwpb.getActive() + "', "
                    + "`trans_date`=now(), "
                    + "`employee_id`='" + Constants.employeeID + "', "
                    + "`reporting_time`='" + cwpb.getExpectedReportDate() + "' "
                    + "WHERE `id`=" + cwpb.getId() + "";
        }
        try {

            if (con == null || con.isClosed()) {
                this.connection();
            }
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            flag = false;
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public void searchInformation(CptWisePricingBo temp, LinkedHashMap<String, CptWisePricingBo> cptWisePriceMap) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT p.id,p.reporting_time,p.price,p.discountable, p.active,l.description as location,\n"
                    + "t.description as patient_type,c.description as cpt FROM radiology.cpt_pricing p\n"
                    + "join radiology.locations l on l.location_id=p.location_id\n"
                    + "join radiology.patient_type t on t.id = p.patient_type_id\n"
                    + "join radiology.cpt c on c.id=p.cpt_id\n"
                    + "where p.location_id='" + temp.getLocationId() + "'\n";

            if (!temp.getPatientTypeId().equalsIgnoreCase("")) {
                query = query + "and p.patient_type_id ='" + temp.getPatientTypeId() + "'";
            }

            query = query + "  order by p.id";
            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                CptWisePricingBo value = new CptWisePricingBo();
                value.setId(rs.getString("id"));
                value.setActive(rs.getString("active"));
                value.setLocationName(rs.getString("location"));
                value.setPatientType(rs.getString("patient_type"));
                value.setCptName(rs.getString("cpt"));
                value.setPrice(rs.getString("price"));
                value.setDiscountable(rs.getString("discountable"));
                value.setExpectedReportDate(rs.getString("reporting_time"));
                cptWisePriceMap.put(value.getId(), value);

            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getCptCode(String orderID) {
        String id = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT p.cpt_code FROM radiology.order_detail o \n"
                    + "join radiology.cpt p on o.cpt_id = p.id\n"
                    + "where o.id='" + orderID + "'";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getString("cpt_code");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public void updateItemStatus(String id, String active) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            query = "UPDATE `radiology`.`items` "
                    + "SET `active`='" + active + "',"
                    + "user_name='" + Constants.employeeID + "',\n"
                    + "trans_date=now(),\n"
                    + "terminal_name = '" + Constants.terminalName + "'"
                    + "WHERE `Item_Id`='" + id + "';";
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getItems(LinkedHashMap<String, String> lhmItem) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select item_id,item_code from radiology.items where "
                    + "active='Y' and location_id='" + Constants.locationID + "'";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                String id = rs.getString("item_id");
                String item_code = rs.getString("item_code");
                lhmItem.put(id, item_code);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getItems(String id, String desc, String locationID, LinkedHashMap<String, ItemBO> itemHm) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT s.item_id,s.item_code,s.Item_desc,s.active,l.description FROM radiology.Items s\n"
                    + "join radiology.locations l on s.location_id = l.location_id\n"
                    + "where s.item_code like '%" + id + "%' \n"
                    + "and s.item_desc like'%" + desc + "%' \n"
                    + "and s.location_id='" + locationID + "'";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                ItemBO temp = new ItemBO();
                temp.setId(rs.getString("item_id"));
                temp.setItemCode(rs.getString("item_code"));
                temp.setItemDesc(rs.getString("Item_desc"));
                temp.setActive(rs.getString("active"));
                temp.setLocation(rs.getString("description"));
                itemHm.put(temp.getId(), temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getStockInformation(String itemId, LinkedHashMap<String, String> lhmStock) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select s.stock_id,sum(s.quantity) as quantity from radiology.stock s \n"
                    + "where location_id='" + Constants.locationID + "' and item_id='" + itemId + "'";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                String id = rs.getString("stock_id");
                String quantity = rs.getString("quantity");
                lhmStock.put(id, quantity);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertStock(String itemId, String quantity) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            query = "INSERT INTO `radiology`.`stock` (`item_id`, `quantity`, `transaction_date`, `location_id`,"
                    + "`user_name`,`trans_date`,`terminal_name`)"
                    + " VALUES ('" + itemId + "', '" + quantity + "', now(), '" + Constants.locationID + "',"
                    + "'" + Constants.employeeID + "',\n"
                    + "NOW(),\n"
                    + "'" + Constants.terminalName + "'"
                    + ");";

            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public boolean insertNewItem(String code, String desc, String locationID) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            query = "INSERT INTO `radiology`.`Items` (`item_code`, `Item_desc`, `location_id`, "
                    + "`active`, `transaction_date`,"
                    + "`user_name`,`trans_date`,`terminal_name`"
                    + ") "
                    + "VALUES "
                    + "('" + code + "', '" + desc + "', '" + locationID + "', 'Y', now(),"
                    + "'" + Constants.employeeID + "',\n"
                    + "NOW(),\n"
                    + "'" + Constants.terminalName + "'"
                    + ");";

            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public boolean getCptInformation(String cptId, CptWiseOrderEntryBO cbo) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT cpt_code,description,cost FROM radiology.cpt where id='" + cptId + "'";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                cbo.setCptCost(rs.getString("cost"));
                cbo.setCptDescription(rs.getString("description"));
                cbo.setCptCode(rs.getString("cpt_code"));
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }

        return flag;
    }

    public boolean getPatientWiseCptPrice(CptWiseOrderEntryBO cbo) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT price FROM radiology.cpt_pricing where "
                    + "cpt_id='" + cbo.getCptId() + "' and "
                    + "patient_type_id='" + cbo.getPatientTypeId() + "' and "
                    + "location_id='1' and "
                    + "active='Y'";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                cbo.setPrice(rs.getString("price"));
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }

        return flag;
    }

    public String saveOrderMaster(String[] arr, String patientTypeId, String history, String weight, String height, String hist, String refPhy, String urgent) {
        query = "INSERT INTO `radiology`.`order_master` "
                + "(`location_id`, `patient_location_id`, `patient_id`, `date`, `patient_type`, `user_id`, `terminal`,`history`,`weight`,`height`,`predefined_hist`,`ref_phy`,"
                + "`user_name`,`trans_date`,`terminal_name`,`urgent`"
                + ")"
                + "VALUES "
                + " ('" + Constants.locationID + "',"
                + " '" + arr[0] + "',"
                + " '" + arr[1] + "',"
                + " now(),"
                + " '" + patientTypeId + "',"
                + " '" + Constants.employeeID + "',"
                + " '" + Constants.terminalName + "',"
                + "'" + history + "',"
                + "'" + weight + "',"
                + "'" + height + "',"
                + "'" + hist + "',"
                + "'" + refPhy + "',"
                + "'" + Constants.employeeID + "',\n"
                + "NOW(),\n"
                + "'" + Constants.terminalName + "',"
                + "'" + urgent + "'"
                + ");";

        String id = "";
        try {

            if (con == null || con.isClosed()) {
                this.connection();
            }
            System.out.println(query);
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = String.valueOf(rs.getInt(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public String saveOrderDetail(CptWiseOrderEntryBO temp, boolean payment) {
        String price = temp.getPrice();
        if (!payment) {
            price = "0";
        }
        query = "INSERT INTO `radiology`.`order_detail` \n"
                + "(`order_id`, `location_id`, `cpt_id`, `status`, `price`,"
                + " `discount`, `user_id`, `terminal`, `date`,"
                + "`user_name`,`trans_date`,`terminal_name`"
                + ") \n"
                + "VALUES \n"
                + "("
                + "'" + temp.getOrderId() + "',"
                + " '" + Constants.locationID + "',"
                + " '" + temp.getCptId() + "',"
                + " '1',"
                + " '" + price + "',"
                + " '0',"
                + " '" + Constants.employeeID + "',"
                + " '" + Constants.terminalName + "',"
                + " now(),"
                + "'" + Constants.employeeID + "',\n"
                + "NOW(),\n"
                + "'" + Constants.terminalName + "'"
                + ")";

        String id = "";
        try {

            if (con == null || con.isClosed()) {
                this.connection();
            }
            System.out.println(query);
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = String.valueOf(rs.getInt(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public void searchOrderMaster(String locationId, String patientId, String cnic, LinkedHashMap<String, OrderEntryBO> orderMap) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select o.order_id,o.date,o.patient_type,o.history,l.description,\n"
                    + "p.name,p.patient_id,p.location_id,d.status  from radiology.order_master o\n"
                    + "left join radiology.locations l on l.location_id = o.location_id\n"
                    + "left join radiology.patient p on p.patient_id = o.patient_id and \n"
                    + "p.location_id = o.patient_location_id\n"
                    + "left join radiology.order_detail d on d.order_id = o.order_id  where";
            if (!locationId.equalsIgnoreCase("")) {
                query = query + " o.patient_location_id='" + locationId + "'";
            }
            if (!patientId.equalsIgnoreCase("")) {
                query = query + " and o.patient_id='" + patientId + "'";
            }
            if (!cnic.equalsIgnoreCase("") && (!patientId.equalsIgnoreCase("") || !locationId.equalsIgnoreCase(""))) {
                query = query + " and p.cnic='" + cnic + "'";
            } else if (!cnic.equalsIgnoreCase("")) {
                query = query + "  p.cnic='" + cnic + "'";
            }

            query = query + " and d.status in (1,2,3,4,5,6) order by o.date ";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            OrderEntryBO.counter = 0;
            while (rs.next()) {
//                    LocationBO lb = Constants.locationMap.get(rs.getString("location_id"));
                OrderEntryBO obo = new OrderEntryBO();
                obo.setPatientID(rs.getString("patient_id"));
                obo.setPatientLocationID(rs.getString("location_id"));
                obo.setPatientName(rs.getString("name"));
                obo.setOrderID(rs.getString("order_id"));
                obo.setOrderLocationDesc(rs.getString("description"));
                obo.setOrderDateTime(rs.getString("date"));
                obo.setPatientType(rs.getString("patient_type"));
                obo.setHistory(rs.getString("history"));
                orderMap.put(obo.getOrderID(), obo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getOrderDetails(String orderId, LinkedHashMap<String, CptWiseOrderEntryBO> cptWiseOrderMap) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT o.order_id,o.price,o.id,m.predefined_hist,m.ref_phy,c.description,c.cpt_code,c.cost FROM radiology.order_detail o\n"
                    + "join radiology.cpt c on o.cpt_id = c.id\n"
                    + "join radiology.order_master m on o.order_id=m.order_id\n"
                    + "where o.order_id='" + orderId + "'";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                CptWiseOrderEntryBO value = new CptWiseOrderEntryBO();
                value.setCptCode(rs.getString("cpt_code"));
                value.setCptDescription(rs.getString("description"));
                value.setPrice(rs.getString("price"));
                value.setAccessionNum(rs.getString("id"));
                value.setOrderId(orderId);
                value.setChkbox("Y");
                value.setPredef_hist(rs.getString("predefined_hist"));
                value.setRefPhy(rs.getString("ref_phy"));
                value.setCptCost(rs.getString("cost"));
//                value.setCompanyID(rs.getString("company_id"));
                cptWiseOrderMap.put(value.getCptCode(), value);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean updateOrderDetailStatus(String id, String status) {
        boolean flag = false;

        query = "UPDATE `radiology`.`order_detail` SET "
                + "`status`='" + status + "' ,"
                + "user_name='" + Constants.employeeID + "',\n"
                + "trans_date=now(),\n"
                + "terminal_name = '" + Constants.terminalName + "'"
                + "WHERE `order_id`='" + id + "';";

        try {

            if (con == null || con.isClosed()) {
                this.connection();
            }
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }

        return flag;
    }

    public boolean updateOrderDetailStatusInvoice(String id, String status) {
        boolean flag = false;

        query = "UPDATE `radiology`.`order_detail` SET "
                + "`status`='" + status + "' ,"
                + "user_name='" + Constants.employeeID + "',\n"
                + "trans_date=now(),\n"
                + "terminal_name = '" + Constants.terminalName + "' , "
                + "invoice_date = now(), "
                + "invoice_by = '" + Constants.employeeID + "' "
                + "WHERE `order_id`='" + id + "';";

        try {

            if (con == null || con.isClosed()) {
                this.connection();
            }
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }

        return flag;
    }

    public void getCptWisePatientOrders(HashMap<String, OrderEntryBO> viewMap, String orderStatus) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select d.id,m.patient_id,m.patient_location_id,p.name,c.description,p.gender,p.dob AS age,d.date,IFNULL(s.image_count,0) as image_count from radiology.order_detail d\n"
                    + "join radiology.order_master m on d.order_id = m.order_id\n"
                    + "join radiology.patient p on p.patient_id = m.patient_id and p.location_id = m.patient_location_id\n"
                    + "join radiology.cpt c on c.id = d.cpt_id\n"
                    + "left join radiology.dicom_study s on s.order_id = d.id\n"
                    + "where d.status  in ('" + orderStatus + "')\n"
                    + "and d.location_id='" + Constants.locationID + "'"
                    + " order by d.date asc";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                OrderEntryBO pbo = new OrderEntryBO();
                pbo.setPatientID(rs.getString("patient_id"));
                pbo.setPatientName(rs.getString("name"));
                pbo.setOrderDateTime(rs.getString("date"));
                pbo.setOrderID(rs.getString("id"));
                pbo.setAgeGender(Constants.calculateAge(rs.getString("Age")) + " / " + rs.getString("gender"));
                pbo.setPatientLocationID(rs.getString("patient_location_id"));
                pbo.setImageCount(rs.getString("image_count"));
                pbo.setCptDesc(rs.getString("description"));
                viewMap.put(pbo.getOrderID(), pbo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void searchCptWisePatientOrder(String locationID, String patientId, String cnic, LinkedHashMap<String, OrderEntryBO> pendingPatientMap, String orderStatus, String modality, String fromDate, String toDate) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select d.id,m.patient_id,m.patient_location_id,p.name,p.gender,p.dob AS age,c.description,d.date,IFNULL(s.image_count,0) as image_count,d.status from radiology.order_detail d\n"
                    + "join radiology.order_master m on d.order_id = m.order_id\n"
                    + "join radiology.patient p on p.patient_id = m.patient_id and p.location_id = m.patient_location_id\n"
                    + "join radiology.cpt c on c.id = d.cpt_id\n"
                    + "left join radiology.dicom_study s on s.order_id = d.id\n"
                    + "where d.status  in ('" + orderStatus + "')\n"
                    + "and d.location_id='" + Constants.locationID + "'\n";

            if (!locationID.equalsIgnoreCase("")) {
                query = query + " and p.patient_id = '" + patientId + "'\n"
                        + "and p.location_id='" + locationID + "'\n";
            }
            if (!cnic.equalsIgnoreCase("")) {
                query = query + " and p.cnic='" + cnic + "'\n";
            }

            if (!modality.equalsIgnoreCase("")) {
                query = query + " and c.modality = '" + modality + "'";
            }

            if (!fromDate.equalsIgnoreCase("") && !toDate.equalsIgnoreCase("")) {
                query = query + " and DATE(d.date) >='" + fromDate + "' and DATE(d.date) <='" + toDate + "'";
            }

            query = query + " order by DATE(d.date) desc,p.patient_id";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderEntryBO pbo = new OrderEntryBO();
                pbo.setPatientID(rs.getString("patient_id"));
                pbo.setPatientName(rs.getString("name"));
                pbo.setOrderDateTime(rs.getString("date"));
                pbo.setOrderID(rs.getString("id"));
                pbo.setAgeGender(Constants.calculateAge(rs.getString("age")) + " / " + rs.getString("gender"));
                pbo.setPatientLocationID(rs.getString("patient_location_id"));
                pbo.setImageCount(rs.getString("image_count"));
                pbo.setCptDesc(rs.getString("description"));
                pbo.setStatus(rs.getString("status"));
                pendingPatientMap.put(pbo.getOrderID(), pbo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchAddendum(String locationID, String patientId, String cnic, LinkedHashMap<String, OrderEntryBO> pendingPatientMap, String orderStatus, String modality, String fromDate, String toDate) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select d.id,m.patient_id,m.patient_location_id,p.name,p.gender,p.dob AS age,c.description,d.date,IFNULL(s.image_count,0) as image_count,d.status from radiology.order_detail d\n"
                    + "join radiology.order_master m on d.order_id = m.order_id\n"
                    + "join radiology.patient p on p.patient_id = m.patient_id and p.location_id = m.patient_location_id\n"
                    + "join radiology.cpt c on c.id = d.cpt_id\n"
                    + "left join radiology.dicom_study s on s.order_id = d.id\n"
                    + "where d.status  in ('" + orderStatus + "')\n";
//                    + "and d.location_id='" + Constants.locationID + "'\n";

            if (!locationID.equalsIgnoreCase("")) {
                query = query + " and p.patient_id = '" + patientId + "'\n"
                        + "and p.location_id='" + locationID + "'\n";
            }
            if (!cnic.equalsIgnoreCase("")) {
                query = query + " and p.cnic='" + cnic + "'\n";
            }

            if (!modality.equalsIgnoreCase("")) {
                query = query + " and c.modality = '" + modality + "'";
            }

            if (!fromDate.equalsIgnoreCase("") && !toDate.equalsIgnoreCase("")) {
                query = query + " and DATE(d.date) >='" + fromDate + "' and DATE(d.date) <='" + toDate + "'";
            }

            query = query + " order by DATE(d.date) desc,p.patient_id";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderEntryBO pbo = new OrderEntryBO();
                pbo.setPatientID(rs.getString("patient_id"));
                pbo.setPatientName(rs.getString("name"));
                pbo.setOrderDateTime(rs.getString("date"));
                pbo.setOrderID(rs.getString("id"));
                pbo.setAgeGender(Constants.calculateAge(rs.getString("age")) + " / " + rs.getString("gender"));
                pbo.setPatientLocationID(rs.getString("patient_location_id"));
                pbo.setImageCount(rs.getString("image_count"));
                pbo.setCptDesc(rs.getString("description"));
                pbo.setStatus(rs.getString("status"));
                pendingPatientMap.put(pbo.getOrderID(), pbo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchPatientWithUrgentTag(String locationID, String patientId, String cnic, LinkedHashMap<String, OrderEntryBO> pendingPatientMap, String orderStatus, String modality, String fromDate, String toDate, String urgent) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select d.id,m.patient_id,m.patient_location_id,p.name,p.gender,p.dob AS age,c.description,d.date,IFNULL(s.image_count,0) as image_count,d.status from radiology.order_detail d\n"
                    + "join radiology.order_master m on d.order_id = m.order_id\n"
                    + "join radiology.patient p on p.patient_id = m.patient_id and p.location_id = m.patient_location_id\n"
                    + "join radiology.cpt c on c.id = d.cpt_id\n"
                    + "left join radiology.dicom_study s on s.order_id = d.id\n"
                    + "where d.status  in ('" + orderStatus + "')\n"
                    + "and d.location_id='" + Constants.locationID + "'\n";

            if (!locationID.equalsIgnoreCase("")) {
                query = query + " and p.patient_id = '" + patientId + "'\n"
                        + "and p.location_id='" + locationID + "'\n";
            }
            if (!cnic.equalsIgnoreCase("")) {
                query = query + " and p.cnic='" + cnic + "'\n";
            }

            if (!modality.equalsIgnoreCase("")) {
                query = query + " and c.modality = '" + modality + "'";
            }

            if (!fromDate.equalsIgnoreCase("") && !toDate.equalsIgnoreCase("")) {
                query = query + " and DATE(d.date) >='" + fromDate + "' and DATE(d.date) <='" + toDate + "'";
            }
            if (urgent.equalsIgnoreCase("Y")) {
                query += " and urgent='Y' ";
            }

            query = query + " order by DATE(d.date) desc,p.patient_id";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderEntryBO pbo = new OrderEntryBO();
                pbo.setPatientID(rs.getString("patient_id"));
                pbo.setPatientName(rs.getString("name"));
                pbo.setOrderDateTime(rs.getString("date"));
                pbo.setOrderID(rs.getString("id"));
                pbo.setAgeGender(Constants.calculateAge(rs.getString("age")) + " / " + rs.getString("gender"));
                pbo.setPatientLocationID(rs.getString("patient_location_id"));
                pbo.setImageCount(rs.getString("image_count"));
                pbo.setCptDesc(rs.getString("description"));
                pbo.setStatus(rs.getString("status"));
                pendingPatientMap.put(pbo.getOrderID(), pbo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void searchCptWisePatientOrderEMT(String locationID, String patientId, String cnic, HashMap<String, OrderEntryBO> pendingPatientMap, String orderStatus, String modality, String fromDate, String toDate) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select d.id,m.patient_id,m.patient_location_id,p.name,c.description,d.date,IFNULL(s.image_count,0) as image_count,d.status from radiology.order_detail d\n"
                    + "join radiology.order_master m on d.order_id = m.order_id\n"
                    + "join radiology.patient p on p.patient_id = m.patient_id and p.location_id = m.patient_location_id\n"
                    + "join radiology.cpt c on c.id = d.cpt_id\n"
                    + "left join radiology.dicom_study s on s.order_id = d.id\n"
                    + "where d.status  in ('" + orderStatus + "')\n";

            if (!patientId.equalsIgnoreCase("")) {
                query = query + " and p.patient_id = '" + patientId + "'\n";
            }
            if (!locationID.equalsIgnoreCase("")) {
                query = query + " and d.location_id='" + locationID + "'\n"
                        + "   and p.location_id='" + locationID + "'\n";;
            }
            if (!cnic.equalsIgnoreCase("")) {
                query = query + " and p.cnic='" + cnic + "'\n";
            }

            if (!modality.equalsIgnoreCase("")) {
                query = query + " and c.modality = '" + modality + "'";
            }

            if (!fromDate.equalsIgnoreCase("") && !toDate.equalsIgnoreCase("")) {
                query = query + " and DATE(d.date) >='" + fromDate + "' and DATE(d.date) <='" + toDate + "'";
            }

            query = query + " order by p.patient_id";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderEntryBO pbo = new OrderEntryBO();
                pbo.setPatientID(rs.getString("patient_id"));
                pbo.setPatientName(rs.getString("name"));
                pbo.setOrderDateTime(rs.getString("date"));
                pbo.setOrderID(rs.getString("id"));
                pbo.setPatientLocationID(rs.getString("patient_location_id"));
                pbo.setImageCount(rs.getString("image_count"));
                pbo.setCptDesc(rs.getString("description"));
                pbo.setStatus(rs.getString("status"));
                pendingPatientMap.put(pbo.getOrderID(), pbo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean updateDetailStatus(String id, String status) {
        boolean flag = false;

        query = "UPDATE `radiology`.`order_detail` "
                + "SET `status`='" + status + "',"
                + "user_name='" + Constants.employeeID + "',\n"
                + "trans_date=now(),\n"
                + "terminal_name = '" + Constants.terminalName + "'"
                + "WHERE `id`='" + id + "';";

        try {

            if (con == null || con.isClosed()) {
                this.connection();
            }
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }

        return flag;
    }

    public boolean updateDetailStatusCounter(String id, String status) {
        boolean flag = false;

        query = "UPDATE `radiology`.`order_detail` "
                + "SET `status`='" + status + "',"
                + "user_name='" + Constants.employeeID + "',\n"
                + "trans_date=now(),\n"
                + "terminal_name = '" + Constants.terminalName + "',"
                + "counter_ack_date = now(), "
                + "counter_ack_by = '" + Constants.employeeID + "' "
                + "WHERE `id`='" + id + "';";

        try {

            if (con == null || con.isClosed()) {
                this.connection();
            }
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }

        return flag;
    }

    public boolean updateDetailStatusRadiographer(String id, String status, String nof) {
        boolean flag = false;

        query = "UPDATE `radiology`.`order_detail` "
                + "SET `status`='" + status + "',"
                + "user_name='" + Constants.employeeID + "',\n"
                + "trans_date=now(),\n"
                + "terminal_name = '" + Constants.terminalName + "', "
                + "number_of_films = '" + nof + "', "
                + "radiographer_ack_date = now(), "
                + "radiographer_id = '" + Constants.employeeID + "' "
                + "WHERE `id`='" + id + "';";

        try {

            if (con == null || con.isClosed()) {
                this.connection();
            }
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }

        return flag;
    }

    public String getMasterId(String orderid) {
        String masterId = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT order_id FROM radiology.order_detail d where d.id='" + orderid + "'";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                masterId = rs.getString("order_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return masterId;
    }

    public String getOrderStatus(String toString) {
        String masterId = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT min(status) as value FROM radiology.order_detail d where d.order_id='" + toString + "'";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                masterId = rs.getString("value");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return masterId;
    }

    public void searchOtherMrno(String text, PatientBO pbo) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            query = "SELECT location_id,patient_id,father_name,cnic,contact_num,address,city \n"
                    + "FROM radiology.patient where otherMrno='" + text + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                pbo.setAddress(rs.getString("address"));
                pbo.setCity(rs.getString("city"));
                pbo.setCnic(rs.getString("cnic"));
                pbo.setContactNum(rs.getString("contact_num"));
                pbo.setFatherName(rs.getString("father_name"));
                pbo.setLocation_id(rs.getString("location_id"));
                pbo.setPatientId(rs.getString("patient_id"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getWeightHeight(String orderDetail, String[] demographics) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            query = "SELECT weight,height FROM radiology.order_master where order_id = '" + orderDetail + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                demographics[0] = rs.getString("weight");
                demographics[1] = rs.getString("height");
            } else {
                demographics[0] = "0";
                demographics[1] = "0";
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getWeightHeightFromMaster(String order, String[] demographics) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            query = "SELECT weight,height FROM radiology.order_detail  d\n"
                    + "join radiology.order_master o on o.order_id = d.order_id\n"
                    + "where d.id ='" + order + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs.next()) {
                demographics[0] = rs.getString("weight");
                demographics[1] = rs.getString("height");
            } else {
                demographics[0] = "0";
                demographics[1] = "0";
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean insertModality(String modality, String desc) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "INSERT INTO `radiology`.`modality` (`Description`, `Modality`, `Active`, `user_name`, `trans_date`, `terminal_name`) "
                    + "VALUES ('" + desc + "', '" + modality + "', 'Y', '" + Constants.employeeID + "',"
                    + " now(), '" + Constants.terminalName + "');";
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public boolean insertHistory(String history, String id) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "UPDATE `radiology`.`order_master` "
                    + "SET `history`='" + history + "' WHERE `order_id`='" + id + "';";
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public boolean getRadiologyOrders(String fromDate, String toDate, LinkedHashMap<String, OrderReportBO> orderReportMap, String locationId) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            query = "select m.order_id,p.name,p.otherMrno,m.patient_id,m.patient_location_id,\n"
                    + "c.cpt_code,c.description,c.modality,d.status,d.id,DATE_FORMAT(m.date,\"%d %M %Y %H:%i\") as trans_date\n"
                    + "from radiology.order_master m\n"
                    + "join radiology.order_detail d on d.order_id = m.order_id\n"
                    + "join radiology.patient p on p.patient_id = m.patient_id \n"
                    + "and p.location_id = m.patient_location_id\n"
                    + "join radiology.cpt c on c.id = d.cpt_id\n"
                    + "where date(d.date)>='" + fromDate + "' and date(d.date)<='" + toDate + "'   "
                    + " and m.location_id='" + locationId + "'"
                    + "order by c.modality,m.trans_date";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            System.out.println(query);
            while (rs.next()) {
                OrderReportBO orb = new OrderReportBO();
                orb.setOrder_id(rs.getString("order_id"));
                orb.setName(rs.getString("name"));
                orb.setHospitalMrn(rs.getString("otherMrno"));
                orb.setPatientId(rs.getString("patient_id"));
                orb.setPateitnLocationId(rs.getString("patient_location_id"));
                orb.setCptCode(rs.getString("cpt_code"));
                orb.setCptDesc(rs.getString("description"));
                orb.setModality(rs.getString("modality"));
                orb.setStatus(rs.getString("status"));
                orb.setId(rs.getString("id"));
                orb.setDate(rs.getString("trans_date"));

                orderReportMap.put(orb.getId(), orb);
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public void getModlities(LinkedHashMap<String, Integer> modalityMap) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            query = "SELECT modality FROM radiology.modality";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                modalityMap.put(rs.getString("modality"), 0);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean getReceivedImagesOrders(String fromDate, String toDate, LinkedHashMap<String, OrderReportBO> orderReportMap, String locationId) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            query = "select s.order_id,DATE_FORMAT(s.acquisition_time,\"%d %M %Y %H:%i\") as acquisition_time ,o.order_id as master_id,m.patient_id,\n"
                    + "m.patient_location_id,p.name,c.description,c.modality,p.otherMrno from radiology.dicom_study s\n"
                    + "join radiology.order_detail o on o.id = s.order_id\n"
                    + "join radiology.order_master m on o.order_id = m.order_id\n"
                    + "join radiology.patient p on p.patient_id = m.patient_id\n"
                    + "join radiology.cpt c on c.id = o.cpt_id\n"
                    + "where date(s.acquisition_time)>='" + fromDate + "' and date(s.acquisition_time)<='" + toDate + "'\n"
                    + " and s.location_id='" + locationId + "'"
                    + "order by c.modality,s.acquisition_time";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            System.out.println(query);
            while (rs.next()) {
                OrderReportBO orb = new OrderReportBO();
                orb.setOrder_id(rs.getString("master_id"));
                orb.setName(rs.getString("name"));
                orb.setHospitalMrn(rs.getString("otherMrno"));
                orb.setPatientId(rs.getString("patient_id"));
                orb.setPateitnLocationId(rs.getString("patient_location_id"));
                orb.setCptDesc(rs.getString("description"));
                orb.setModality(rs.getString("modality"));
                orb.setId(rs.getString("order_id"));
                orb.setDate(rs.getString("acquisition_time"));

                orderReportMap.put(orb.getId(), orb);
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public boolean getPendingResults(String fromDate, String toDate, LinkedHashMap<String, OrderReportBO> orderReportMap, String status, String locationId) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            query = "select m.order_id,p.name,p.otherMrno,m.patient_id,m.patient_location_id,\n"
                    + "c.cpt_code,c.description,c.modality,d.status,d.id,DATE_FORMAT(m.date,\"%d %M %Y %H:%i\") as trans_date\n"
                    + "from radiology.order_master m\n"
                    + "join radiology.order_detail d on d.order_id = m.order_id and d.status in('" + status + "')\n"
                    + "join radiology.patient p on p.patient_id = m.patient_id \n"
                    + "and p.location_id = m.patient_location_id\n"
                    + "join radiology.cpt c on c.id = d.cpt_id\n"
                    + "where date(d.date)>='" + fromDate + "' and date(d.date)<='" + toDate + "' "
                    + ""
                    + " and m.location_id='" + locationId + "'"
                    + " order by c.modality,m.trans_date ";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            System.out.println(query);
            while (rs.next()) {
                OrderReportBO orb = new OrderReportBO();
                orb.setOrder_id(rs.getString("order_id"));
                orb.setName(rs.getString("name"));
                orb.setHospitalMrn(rs.getString("otherMrno"));
                orb.setPatientId(rs.getString("patient_id"));
                orb.setPateitnLocationId(rs.getString("patient_location_id"));
                orb.setCptCode(rs.getString("cpt_code"));
                orb.setCptDesc(rs.getString("description"));
                orb.setModality(rs.getString("modality"));
                orb.setStatus(rs.getString("status"));
                orb.setId(rs.getString("id"));
                orb.setDate(rs.getString("trans_date"));

                orderReportMap.put(orb.getId(), orb);
                flag = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public String getModality() {
        String mod = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select modality from radiology.modality where active='Y'";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                mod = mod + rs.getString("modality") + "^";
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mod;
    }

    public boolean insertTeachingCase(String id, String status) {
        boolean flag = false;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select * from radiology.teaching_cases where order_id='" + id + "' and current_status = '" + status + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Study Already Exists with same status");
                flag = true;
                return flag;
            }

            query = "INSERT INTO `radiology`.`teaching_cases` "
                    + "(`order_id`, `employee_id`, `current_status`, `trans_date`, `terminal`, `location_id`)"
                    + " VALUES "
                    + "('" + id + "',"
                    + " '" + Constants.employeeID + "',"
                    + " '" + status + "',"
                    + " NOW(),"
                    + " '" + Constants.terminalName + "',"
                    + " '" + Constants.locationID + "');";
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public void getOrderSummary(String fromDate, String toDate, ArrayList<String> list, String locationId) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            query = "select c.modality, o.status, count(*) as count\n"
                    + "from radiology.order_detail o, radiology.cpt c\n"
                    + "where o.cpt_id=c.id\n"
                    + "and \n"
                    + "date(o.date)>='" + fromDate + "' and date(o.date)<='" + toDate + "'\n"
                    + " and o.status>=0  and o.location_id='" + locationId + "'"
                    + "group by c.modality, o.status\n"
                    + "union\n"
                    + "select c.modality, -1, count(*)\n"
                    + "from radiology.order_detail o, radiology.cpt c\n"
                    + "where o.cpt_id=c.id\n"
                    + "and \n"
                    + "date(o.date)>='" + fromDate + "' and date(o.date)<='" + toDate + "'\n"
                    + " and o.status>=0 and o.location_id='" + locationId + "'"
                    + "group by c.modality";
            ps = con.prepareStatement(query);
            System.out.println(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("modality") + "^" + rs.getString("status") + "^" + rs.getString("count"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public boolean getPatientIdFront(String location, String id) {
//
//        boolean flag = false;
//        try {
//            if (con == null || con.isClosed()) {
//                this.connection();
//            }
//            query = "SELECT idCard FROM radiology.patient where patient_id='" + id + "' and location_id='" + location + "'";
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//            System.out.println(query);
//            OutputStream outputStream = null;
//
//            File path = new File(System.getProperty("java.io.tmpdir") + "\\get\\idFront" + System.currentTimeMillis() % 1000 + ".png");
//            Constants.idFront = path.getAbsolutePath();
//            path.getParentFile().mkdirs();
//            if (rs.next()) {
//                Blob blob = rs.getBlob("idCard");
//                InputStream inputStream = null;
//                try {
//                    inputStream = blob.getBinaryStream();
//                    outputStream = new FileOutputStream(path);
//                } catch (FileNotFoundException ex) {
//                    flag = false;
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (NullPointerException ex) {
//                    flag = false;
//                    return flag;
//                }
//
//                int bytesRead = -1;
//                byte[] buffer = new byte[BUFFER_SIZE];
//                try {
//                    while ((bytesRead = inputStream.read(buffer)) != -1) {
//                        outputStream.write(buffer, 0, bytesRead);
//                        outputStream.flush();
//                    }
//                } catch (IOException ex) {
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                try {
//                    inputStream.close();
//                    outputStream.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                flag = true;
//                System.out.println("File saved");
//
//            }
//
//        } catch (SQLException ex) {
//            flag = false;
//            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return flag;
//    }
//    public boolean getAttendantIdFront(String location, String id) {
//
//        boolean flag = false;
//        try {
//            if (con == null || con.isClosed()) {
//                this.connection();
//            }
//            query = "SELECT id_front FROM radiology.attendant where patient_id='" + id + "' and patient_location_id='" + location + "'";
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//            System.out.println(query);
//            OutputStream outputStream = null;
//
//            File path = new File(System.getProperty("java.io.tmpdir") + "\\get\\idFront" + System.currentTimeMillis() % 1000 + ".png");
//            Constants.idFront = path.getAbsolutePath();
//            path.getParentFile().mkdirs();
//            if (rs.next()) {
//                Blob blob = rs.getBlob("id_front");
//                InputStream inputStream = null;
//                try {
//                    inputStream = blob.getBinaryStream();
//                    outputStream = new FileOutputStream(path);
//                } catch (FileNotFoundException ex) {
//                    flag = false;
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (NullPointerException ex) {
//                    flag = false;
//                    return flag;
//                }
//
//                int bytesRead = -1;
//                byte[] buffer = new byte[BUFFER_SIZE];
//                try {
//                    while ((bytesRead = inputStream.read(buffer)) != -1) {
//                        outputStream.write(buffer, 0, bytesRead);
//                        outputStream.flush();
//                    }
//                } catch (IOException ex) {
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                try {
//                    inputStream.close();
//                    outputStream.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                flag = true;
//                System.out.println("File saved");
//
//            }
//
//        } catch (SQLException ex) {
//            flag = false;
//            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return flag;
//    }
//    public boolean getPatientIdBack(String location, String id) {
//
//        boolean flag = false;
//        try {
//            if (con == null || con.isClosed()) {
//                this.connection();
//            }
//            query = "SELECT idCardBack FROM radiology.patient where patient_id='" + id + "' and location_id='" + location + "'";
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//            System.out.println(query);
//            OutputStream outputStream = null;
//
//            File path = new File(System.getProperty("java.io.tmpdir") + "\\get\\idBack" + System.currentTimeMillis() % 1000 + ".png");
//            Constants.idBack = path.getAbsolutePath();
//            path.getParentFile().mkdirs();
//            if (rs.next()) {
//                Blob blob = rs.getBlob("idCardBack");
//                InputStream inputStream = null;
//                try {
//                    inputStream = blob.getBinaryStream();
//                    outputStream = new FileOutputStream(path);
//                } catch (FileNotFoundException ex) {
//                    flag = false;
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (NullPointerException ex) {
//                    flag = false;
//                    return flag;
//                }
//
//                int bytesRead = -1;
//                byte[] buffer = new byte[BUFFER_SIZE];
//                try {
//                    while ((bytesRead = inputStream.read(buffer)) != -1) {
//                        outputStream.write(buffer, 0, bytesRead);
//                        outputStream.flush();
//                    }
//                } catch (IOException ex) {
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                try {
//                    inputStream.close();
//                    outputStream.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                flag = true;
//                System.out.println("File saved");
//
//            }
//
//        } catch (SQLException ex) {
//            flag = false;
//            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return flag;
//    }
//    public boolean getAttendantIdBack(String location, String id) {
//
//        boolean flag = false;
//        try {
//            if (con == null || con.isClosed()) {
//                this.connection();
//            }
//            query = "SELECT id_back FROM radiology.attendant where patient_id='" + id + "' and patient_location_id='" + location + "'";
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//            System.out.println(query);
//            OutputStream outputStream = null;
//
//            File path = new File(System.getProperty("java.io.tmpdir") + "\\get\\idBack" + System.currentTimeMillis() % 1000 + ".png");
//            Constants.idBack = path.getAbsolutePath();
//            path.getParentFile().mkdirs();
//            if (rs.next()) {
//                Blob blob = rs.getBlob("id_back");
//                InputStream inputStream = null;
//                try {
//                    inputStream = blob.getBinaryStream();
//                    outputStream = new FileOutputStream(path);
//                } catch (FileNotFoundException ex) {
//                    flag = false;
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (NullPointerException ex) {
//                    flag = false;
//                    return flag;
//                }
//
//                int bytesRead = -1;
//                byte[] buffer = new byte[BUFFER_SIZE];
//                try {
//                    while ((bytesRead = inputStream.read(buffer)) != -1) {
//                        outputStream.write(buffer, 0, bytesRead);
//                        outputStream.flush();
//                    }
//                } catch (IOException ex) {
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                try {
//                    inputStream.close();
//                    outputStream.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                flag = true;
//                System.out.println("File saved");
//
//            }
//
//        } catch (SQLException ex) {
//            flag = false;
//            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return flag;
//    }
//    public boolean getPatientLetter(String location, String id) {
//
//        boolean flag = false;
//        try {
//            if (con == null || con.isClosed()) {
//                this.connection();
//            }
//            query = "SELECT referLetter FROM radiology.patient where patient_id='" + id + "' and location_id='" + location + "'";
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//            System.out.println(query);
//            OutputStream outputStream = null;
//
//            File path = new File(System.getProperty("java.io.tmpdir") + "\\get\\referLetter" + System.currentTimeMillis() % 1000 + ".png");
//            Constants.referLetter = path.getAbsolutePath();
//            path.getParentFile().mkdirs();
//            if (rs.next()) {
//                Blob blob = rs.getBlob("referLetter");
//                InputStream inputStream = null;
//                try {
//                    inputStream = blob.getBinaryStream();
//                    outputStream = new FileOutputStream(path);
//                } catch (FileNotFoundException ex) {
//                    flag = false;
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (NullPointerException ex) {
//                    flag = false;
//                    return flag;
//                }
//
//                int bytesRead = -1;
//                byte[] buffer = new byte[BUFFER_SIZE];
//                try {
//                    while ((bytesRead = inputStream.read(buffer)) != -1) {
//                        outputStream.write(buffer, 0, bytesRead);
//                        outputStream.flush();
//                    }
//                } catch (IOException ex) {
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                try {
//                    inputStream.close();
//                    outputStream.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                flag = true;
//                System.out.println("File saved");
//
//            }
//
//        } catch (SQLException ex) {
//            flag = false;
//            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return flag;
//    }
//    public boolean getAttendantLetter(String location, String id) {
//
//        boolean flag = false;
//        try {
//            if (con == null || con.isClosed()) {
//                this.connection();
//            }
//            query = "SELECT refer_letter FROM radiology.attendant where patient_id='" + id + "' and patient_location_id='" + location + "'";
//            ps = con.prepareStatement(query);
//            rs = ps.executeQuery();
//            System.out.println(query);
//            OutputStream outputStream = null;
//
//            File path = new File(System.getProperty("java.io.tmpdir") + "\\get\\referLetter" + System.currentTimeMillis() % 1000 + ".png");
//            Constants.referLetter = path.getAbsolutePath();
//            path.getParentFile().mkdirs();
//            if (rs.next()) {
//                Blob blob = rs.getBlob("refer_letter");
//                InputStream inputStream = null;
//                try {
//                    inputStream = blob.getBinaryStream();
//                    outputStream = new FileOutputStream(path);
//                } catch (FileNotFoundException ex) {
//                    flag = false;
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (NullPointerException ex) {
//                    flag = false;
//                    return flag;
//                }
//
//                int bytesRead = -1;
//                byte[] buffer = new byte[BUFFER_SIZE];
//                try {
//                    while ((bytesRead = inputStream.read(buffer)) != -1) {
//                        outputStream.write(buffer, 0, bytesRead);
//                        outputStream.flush();
//                    }
//                } catch (IOException ex) {
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                try {
//                    inputStream.close();
//                    outputStream.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                flag = true;
//                System.out.println("File saved");
//
//            }
//
//        } catch (SQLException ex) {
//            flag = false;
//            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return flag;
//    }
    public void getPatientSummary(String from, String to, String locationId, String patientType, String contrast, LinkedHashMap<String, PatientSummaryReportBo> patientSummaryMap) {

        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select pt.description as patient_type, if(cpt.contrast='Y','With Contrast','Without Contrast') as contrast, \n"
                    + "count(*) as qty\n"
                    + "from radiology.order_detail od, order_master om, \n"
                    + "radiology.patient_type pt,radiology.cpt cpt\n"
                    + "where od.order_id = om.order_id\n"
                    + "and om.patient_type=pt.id\n"
                    + "and od.cpt_id=cpt.id\n"
                    + "and od.status in (4,5,6)\n"
                    + "and pt.id in (" + patientType + ")\n"
                    + "and contrast in (" + contrast + ")\n"
                    + "and om.location_id='" + locationId + "'";
            if (from.equalsIgnoreCase(to)) {
                query = query + " and date(od.date)='" + from + "' ";
            } else {
                query = query + " and date(od.date)>='" + from + "' and date(od.date)<='" + to + "'\n ";
            }

            query = query + " group by pt.description, cpt.contrast";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                PatientSummaryReportBo value = patientSummaryMap.get(rs.getString("patient_type"));

                try {
                    if (value != null) {
                        if (rs.getString("contrast").equalsIgnoreCase("with contrast")) {
                            value.setWithContrast(rs.getString("qty"));
                        } else {
                            value.setWithoutContrast(rs.getString("qty"));
                        }

                        patientSummaryMap.replace(value.getPatientType(), value);
                    } else {
                        value = new PatientSummaryReportBo();
                        if (rs.getString("contrast").equalsIgnoreCase("with contrast")) {
                            value.setWithContrast(rs.getString("qty"));
                        } else {
                            value.setWithoutContrast(rs.getString("qty"));
                        }
                        value.setPatientType(rs.getString("patient_type"));
                        patientSummaryMap.put(value.getPatientType(), value);
                    }

                } catch (NullPointerException e) {
                    value = new PatientSummaryReportBo();
                    if (rs.getString("contrast").equalsIgnoreCase("with contrast")) {
                        value.setWithContrast(rs.getString("qty"));
                    } else {
                        value.setWithoutContrast(rs.getString("qty"));
                    }
                    value.setPatientType(rs.getString("patient_type"));
                    patientSummaryMap.put(value.getPatientType(), value);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getPatientDetails(String from, String to, String locationId, String patientType, String contrast, LinkedHashMap<String, PatientDetailReportBo> patientDetailMap) {

        String pType[] = patientType.split(",");

        for (int i = 0; i < pType.length; i++) {
            try {
                if (con == null || con.isClosed()) {
                    this.connection();
                }
                if (contrast.contains("N")) {
                    query = "select od.id,p.patient_id,p.location_id, p.Name, p.cnic, pt.description patient_type,\n"
                            + "cpt.description, od.date, if(cpt.contrast='Y','    Y','    N') contrast\n"
                            + "from radiology.order_detail od, order_master om, radiology.cpt cpt, \n"
                            + "radiology.patient_type pt,\n"
                            + "radiology.patient p\n"
                            + "where od.order_id = om.order_id\n"
                            + "and od.cpt_id=cpt.id\n"
                            + "and om.patient_type=pt.id\n"
                            + "and om.patient_id=p.patient_id \n"
                            + "and om.patient_location_id = p.location_id\n"
                            + "and od.status in (4,5,6)\n"
                            + "and pt.id = (" + pType[i] + ")\n"
                            + "and om.location_id='" + locationId + "'\n"
                            + "and cpt.contrast in ('N','') \n";

                    if (from.equalsIgnoreCase(to)) {
                        query = query + " and date(od.date)='" + from + "' ";
                    } else {
                        query = query + " and date(od.date)>='" + from + "' and date(od.date)<='" + to + "'  ";
                    }

                    query = query + " order by  pt.description, od.date  desc;";
                    System.out.println(query);
                    ps = con.prepareStatement(query);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        PatientDetailReportBo value = new PatientDetailReportBo();
                        value.setId(rs.getString("id"));
                        value.setPatientId(rs.getString("location_id") + "-" + rs.getString("patient_id"));
                        value.setName(rs.getString("name"));
                        value.setCnic(rs.getString("cnic"));
                        value.setPatien_type(rs.getString("patient_type"));
                        value.setDescription(rs.getString("description"));
                        value.setTrans_date(rs.getString("date"));
                        value.setContrast(rs.getString("contrast"));
                        patientDetailMap.put(value.getId(), value);
                    }

                }
                if (contrast.contains("Y")) {
                    query = "select od.id,p.patient_id,p.location_id, p.Name, p.cnic, pt.description patient_type,\n"
                            + "cpt.description, od.date, if(cpt.contrast='Y','    Y','    N') contrast\n"
                            + "from radiology.order_detail od, order_master om, radiology.cpt cpt, \n"
                            + "radiology.patient_type pt,\n"
                            + "radiology.patient p\n"
                            + "where od.order_id = om.order_id\n"
                            + "and od.cpt_id=cpt.id\n"
                            + "and om.patient_type=pt.id\n"
                            + "and om.patient_id=p.patient_id \n"
                            + "and om.patient_location_id = p.location_id\n"
                            + "and od.status in (4,5,6)\n"
                            + "and pt.id = (" + pType[i] + ")\n"
                            + "and om.location_id='" + locationId + "'\n"
                            + "and cpt.contrast = ('Y') \n";

                    if (from.equalsIgnoreCase(to)) {
                        query = query + " and date(od.date)='" + from + "' ";
                    } else {
                        query = query + " and date(od.date)>='" + from + "' and date(od.date)<='" + to + "'  ";
                    }

                    query = query + " order by  pt.description, od.date  desc;";
                    System.out.println(query);
                    ps = con.prepareStatement(query);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        PatientDetailReportBo value = new PatientDetailReportBo();
                        value.setId(rs.getString("id"));
                        value.setPatientId(rs.getString("location_id") + "-" + rs.getString("patient_id"));
                        value.setName(rs.getString("name"));
                        value.setCnic(rs.getString("cnic"));
                        value.setPatien_type(rs.getString("patient_type"));
                        value.setDescription(rs.getString("description"));
                        value.setTrans_date(rs.getString("date"));
                        value.setContrast(rs.getString("contrast"));
                        patientDetailMap.put(value.getId(), value);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String savePatientAndAttendantInformation(PatientAttendantInfo pai, boolean flag) {
        String id = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            if (!flag) {
                query = "INSERT INTO `radiology`.`patient` \n"
                        + "(`location_id`, \n"
                        + "`Name`,\n"
                        + "`cnic`, \n"
                        + "`contact_num`,\n"
                        + "`gender`, \n"
                        + "`dob`, \n"
                        + "`registration_date`, \n"
                        + "`user_name`, \n"
                        + "`trans_date`, \n"
                        + "`terminal_name`,\n"
                        + "`attendant_flag`"
                        + ") \n"
                        + "VALUES \n"
                        + "('" + Constants.locationID + "', \n"
                        + "'" + pai.getPatientName() + "', \n"
                        + "'" + pai.getPatientCnic() + "', \n"
                        + "'',\n"
                        + "'" + pai.getGender() + "', \n"
                        + "'" + pai.getDob() + "', \n"
                        + "now(), \n"
                        + "'" + Constants.employeeID + "', \n"
                        + "now(), \n"
                        + "'" + Constants.terminalName + "',\n"
                        + "'Y'"
                        + ")";

                System.out.println(query);
                ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    id = String.valueOf(rs.getInt(1));
                }

                pai.setPatient_id(id);
                pai.setPatient_location_id(Constants.locationID);

                query = "INSERT INTO `radiology`.`attendant` \n"
                        + "(`patient_id`, `patient_location_id`, `name`, `cnic`, "
                        + "`contact_number`, `address`, `city`, `relation`, "
                        + "`user_name`, `trans_date`, `terminal_name`) \n"
                        + "VALUES "
                        + "('" + pai.getPatient_id() + "', "
                        + "'" + pai.getPatient_location_id() + "', "
                        + "'" + pai.getName() + "', "
                        + "'" + pai.getCnic() + "', "
                        + "'" + pai.getContact() + "', "
                        + "'" + pai.getAddress() + "', "
                        + "'" + pai.getCity() + "', "
                        + "'" + pai.getRelation() + "', "
                        + "'" + Constants.employeeID + "', "
                        + "now(), "
                        + "'" + Constants.terminalName + "');";
            } else {
                query = "UPDATE `radiology`.`patient` SET `attendant_flag`='Y' WHERE `patient_id`='" + pai.getPatient_id() + "' and`location_id`='" + pai.getPatient_location_id() + "'";
                System.out.println(query);
                ps = con.prepareStatement(query);
                ps.executeUpdate();

                query = "select * from radiology.attendant where patient_id='" + pai.getPatient_id() + "' and patient_location_id='" + pai.getPatient_location_id() + "'";
                System.out.println(query);
                ps = con.prepareStatement(query);
                rs = ps.executeQuery();
                if (rs.next()) {
                    query = "UPDATE `radiology`.`attendant` \n"
                            + "SET `name`='" + pai.getName() + "', \n"
                            + "`cnic`='" + pai.getCnic() + "', \n"
                            + "`contact_number`='" + pai.getContact() + "', \n"
                            + "`address`='" + pai.getAddress() + "', \n"
                            + "`city`='" + pai.getCity() + "', \n"
                            + "`relation`='" + pai.getRelation() + "', \n"
                            + "`user_name`='" + Constants.employeeID + "', \n"
                            + "`trans_date`=now(), \n"
                            + "picture='',\n"
                            + "id_back='',\n"
                            + "id_front='',\n"
                            + "refer_letter='',\n"
                            + "`terminal_name`='" + Constants.terminalName + "' \n"
                            + "WHERE patient_id='" + pai.getPatient_id() + "'  and patient_location_id='" + pai.getPatient_location_id() + "'";
                } else {
                    query = "INSERT INTO `radiology`.`attendant` \n"
                            + "(`patient_id`, `patient_location_id`, `name`, `cnic`, "
                            + "`contact_number`, `address`, `city`, `relation`, "
                            + "`user_name`, `trans_date`, `terminal_name`) \n"
                            + "VALUES "
                            + "('" + pai.getPatient_id() + "', "
                            + "'" + pai.getPatient_location_id() + "', "
                            + "'" + pai.getName() + "', "
                            + "'" + pai.getCnic() + "', "
                            + "'" + pai.getContact() + "', "
                            + "'" + pai.getAddress() + "', "
                            + "'" + pai.getCity() + "', "
                            + "'" + pai.getRelation() + "', "
                            + "'" + Constants.employeeID + "', "
                            + "now(), "
                            + "'" + Constants.terminalName + "');";
                }
            }

            System.out.println(query);
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                id = String.valueOf(rs.getInt(1));
            } else {
                id = "true";
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
            id = "false";
        }
        return id;
    }

    public PatientAttendantInfo getAttendantInfo(String location_id, String patientId) {
        PatientAttendantInfo value = null;
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT name,cnic,contact_number,address,city,relation "
                    + "from radiology.attendant where patient_id='" + patientId + "' and patient_location_id='" + location_id + "'";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                value = new PatientAttendantInfo();
                value.setName(rs.getString("name"));
                value.setCnic(rs.getString("cnic"));
                value.setContact(rs.getString("contact_number"));
                value.setAddress(rs.getString("address"));
                value.setCity(rs.getString("city"));
                value.setRelation(rs.getString("relation"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }

        return value;
    }

    public void getDataToday(LinkedHashMap<String, String> today) {

        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT location_id,count(*) as count FROM radiology.order_detail o where status in('4','5','6')\n"
                    + "and date(o.trans_date)>=date(now()) and date(o.trans_date)<=date(now())\n"
                    + "group by location_id;";

            ps = con.prepareStatement(query);
            System.out.println(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                String locId = rs.getString("location_id");
                String value = rs.getString("count");

                today.put(locId, value);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void getCurrentWeek(LinkedHashMap<String, String> currentWeek) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT location_id,count(*) as count FROM radiology.order_detail o where status in('4','5','6')\n"
                    + "and YEARWEEK(o.trans_date, 1) = YEARWEEK(now(), 1) and o.trans_date <= now() \n"
                    + "group by location_id;";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                String locId = rs.getString("location_id");
                String value = rs.getString("count");

                currentWeek.put(locId, value);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lastWeekCount(LinkedHashMap<String, String> lastWeek) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT location_id,count(*)as count FROM radiology.order_detail o where status in('4','5','6')\n"
                    + "and \n"
                    + "date(o.trans_date) >= now() - INTERVAL DAYOFWEEK(now())+6 DAY\n"
                    + "AND (o.trans_date) < now() - INTERVAL DAYOFWEEK(now())-1 DAY\n"
                    + "group by location_id;";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                String locId = rs.getString("location_id");
                String value = rs.getString("count");

                lastWeek.put(locId, value);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getCurrentMonth(LinkedHashMap<String, String> currentMonth) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT location_id,count(*)as count FROM radiology.order_detail o where status in('4','5','6')\n"
                    + "and \n"
                    + "MONTH(o.trans_date) = MONTH(now())\n"
                    + "AND YEAR(o.trans_date) = YEAR(now())\n"
                    + "group by location_id;";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                String locId = rs.getString("location_id");
                String value = rs.getString("count");

                currentMonth.put(locId, value);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getLastMonth(LinkedHashMap<String, String> lastMonth) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT location_id,count(*)as count FROM radiology.order_detail o where status in('4','5','6')\n"
                    + "and \n"
                    + "MONTH(o.trans_date) = MONTH(now()- INTERVAL 1 MONTH)\n"
                    + "AND YEAR(o.trans_date) = YEAR(now()- INTERVAL 1 MONTH)\n"
                    + "group by location_id;";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                String locId = rs.getString("location_id");
                String value = rs.getString("count");

                lastMonth.put(locId, value);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getTotal(LinkedHashMap<String, String> total) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT location_id, count(*)as count FROM radiology.order_detail where status in('4','5','6') group by location_id;";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                String locId = rs.getString("location_id");
                String value = rs.getString("count");

                total.put(locId, value);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LinkedHashMap<String, String> getMothWiseData(int i) {
        LinkedHashMap<String, String> monthlyData = new LinkedHashMap<>();
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT location_id,count(*)as count FROM radiology.order_detail o where status in('4','5','6')\n"
                    + "and \n"
                    + "MONTH(o.date) = MONTH(now()- INTERVAL " + i + " MONTH)\n"
                    + "AND YEAR(o.date) = YEAR(now()- INTERVAL " + i + " MONTH)\n"
                    + "group by location_id;";
            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                String locId = rs.getString("location_id");
                String value = rs.getString("count");

                monthlyData.put(locId, value);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return monthlyData;
    }

    public LinkedHashMap searchAttachedReportsRecord(String[] patient_info) {
        LinkedHashMap<String, String> data = new LinkedHashMap<>();
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT id, item_name,trans_date FROM radiology.patient_wise_captured_reports\n"
                    + "where patient_id='" + patient_info[1] + "' and patient_location_id='" + patient_info[0] + "'\n"
                    + "order by trans_date desc";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                String id = rs.getString("id");
                String desc = rs.getString("item_name");
                String date = rs.getString("trans_date");

                data.put(id, desc + "####" + date);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public String getAttachedReport(String id) {
        String filePath = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT captured_item FROM radiology.patient_wise_captured_reports where id='" + id + "'";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            System.out.println(query);
            OutputStream outputStream = null;
            InputStream inputStream = null;
            File path = new File(System.getProperty("java.io.tmpdir") + "\\attachment\\getAttachment.png");
            path.getParentFile().mkdirs();
            if (rs.next()) {
                Blob blob = rs.getBlob("captured_item");
                if (blob != null) {
                    try {
                        inputStream = blob.getBinaryStream();
                        outputStream = new FileOutputStream(path);
                    } catch (FileNotFoundException ex) {
                    }

                    int bytesRead = -1;
                    byte[] buffer = new byte[BUFFER_SIZE];
                    try {
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                            outputStream.flush();
                        }
                    } catch (IOException ex) {
                    }
                    try {
                        inputStream.close();
                        outputStream.close();
                    } catch (IOException ex) {
                    }
                }
                filePath = path.getAbsolutePath();
            }

        } catch (SQLException ex) {
        }
        return filePath;
    }

    public void getCPTSummary(String from, String to, String locationId, String patientType, String contrast, LinkedHashMap<String, PatientSummaryReportBo> patientSummaryMap, String searchParam) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            query = "select pt.description as patient_type, if(cpt.contrast='Y','With Contrast','Without Contrast') as contrast, \n"
                    + "count(*) as qty\n"
                    + "from radiology.order_detail od, order_master om, \n"
                    + "radiology.patient_type pt,radiology.cpt cpt\n"
                    + "where od.order_id = om.order_id\n"
                    + "and om.patient_type=pt.id\n"
                    + "and od.cpt_id=cpt.id\n"
                    + "and od.status in (6)\n"
                    + "and " + searchParam + "\n"
                    + "and pt.id in (" + patientType + ")\n"
                    + "and contrast in (" + contrast + ")\n";
            if (!locationId.equalsIgnoreCase("")) {
                query = query + "and om.location_id='" + locationId + "'";
            }
            if (from.equalsIgnoreCase(to)) {
                query = query + " and date(od.date)='" + from + "' ";
            } else {
                query = query + " and date(od.date)>='" + from + "' and date(od.date)<='" + to + "'\n ";
            }

            query = query + " group by pt.description, cpt.contrast";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                PatientSummaryReportBo value = patientSummaryMap.get(rs.getString("patient_type"));

                try {
                    if (value != null) {
                        if (rs.getString("contrast").equalsIgnoreCase("with contrast")) {
                            value.setWithContrast(rs.getString("qty"));
                        } else {
                            value.setWithoutContrast(rs.getString("qty"));
                        }

                        patientSummaryMap.replace(value.getPatientType(), value);
                    } else {
                        value = new PatientSummaryReportBo();
                        if (rs.getString("contrast").equalsIgnoreCase("with contrast")) {
                            value.setWithContrast(rs.getString("qty"));
                        } else {
                            value.setWithoutContrast(rs.getString("qty"));
                        }
                        value.setPatientType(rs.getString("patient_type"));
                        patientSummaryMap.put(value.getPatientType(), value);
                    }

                } catch (NullPointerException e) {
                    value = new PatientSummaryReportBo();
                    if (rs.getString("contrast").equalsIgnoreCase("with contrast")) {
                        value.setWithContrast(rs.getString("qty"));
                    } else {
                        value.setWithoutContrast(rs.getString("qty"));
                    }
                    value.setPatientType(rs.getString("patient_type"));
                    patientSummaryMap.put(value.getPatientType(), value);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getReferralWiseSummary(String from, String to, String locationId, String patientType, String contrast, LinkedHashMap<String, PatientSummaryReportBo> patientSummaryMap, String searchParam) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            query = "select pr.findings, if(cpt.contrast='Y','With Contrast','Without Contrast') as contrast, \n"
                    + "count(*) as qty\n"
                    + "from radiology.order_detail od join order_master om on od.order_id = om.order_id and od.status = 6 join \n"
                    + "radiology.patient_type pt on om.patient_type=pt.id join radiology.cpt cpt on od.cpt_id=cpt.id\n"
                    + "join radiology.patient_reports pr on pr.order_id = od.id"
                    + " where " + searchParam + " "
                    //                    + "pr.findings ='" + searchParam + "'\n"
                    + "and pt.id in (" + patientType + ")\n"
                    + "and contrast in (" + contrast + ")\n"
                    + "\n";
            if (!locationId.equalsIgnoreCase("")) {
                query = query + "and om.location_id='" + locationId + "'";
            }
            if (from.equalsIgnoreCase(to)) {
                query = query + " and date(od.date)='" + from + "' ";
            } else {
                query = query + " and date(od.date)>='" + from + "' and date(od.date)<='" + to + "'\n ";
            }

            query = query + " group by pr.findings, cpt.contrast";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            int totalRows = 0;
            while (rs.next()) {
                totalRows++;
                System.out.println("Reading record no. " + totalRows);
                PatientSummaryReportBo value = patientSummaryMap.get(rs.getString("findings"));

                try {
                    if (value != null) {
                        if (rs.getString("contrast").equalsIgnoreCase("with contrast")) {
                            value.setWithContrast(rs.getString("qty"));
                        } else {
                            value.setWithoutContrast(rs.getString("qty"));
                        }

                        patientSummaryMap.replace(value.getPatientType(), value);
                    } else {
                        value = new PatientSummaryReportBo();
                        if (rs.getString("contrast").equalsIgnoreCase("with contrast")) {
                            value.setWithContrast(rs.getString("qty"));
                        } else {
                            value.setWithoutContrast(rs.getString("qty"));
                        }
                        value.setPatientType(rs.getString("findings"));
                        patientSummaryMap.put(value.getPatientType(), value);
                    }

                } catch (NullPointerException e) {
                    value = new PatientSummaryReportBo();
                    if (rs.getString("contrast").equalsIgnoreCase("with contrast")) {
                        value.setWithContrast(rs.getString("qty"));
                    } else {
                        value.setWithoutContrast(rs.getString("qty"));
                    }
                    value.setPatientType(rs.getString("findings"));
                    patientSummaryMap.put(value.getPatientType(), value);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getCPTDetails(String from, String to, String locationId, String patientType, String contrast, LinkedHashMap<String, PatientDetailReportBo> patientDetailMap, String searchParam) {
        String pType[] = patientType.split(",");

        for (int i = 0; i < pType.length; i++) {
            try {
                if (con == null || con.isClosed()) {
                    this.connection();
                }
                if (contrast.contains("N")) {
                    query = "select od.id,p.patient_id,p.gender,p.picture,p.otherMrno, p.dob,om.ref_phy, om.urgent, om.predefined_hist,p.location_id, p.Name, p.cnic, pt.description patient_type,\n"
                            + "cpt.description, od.date, if(cpt.contrast='Y','    Y','    N') contrast,pr.findings\n"
                            + "from radiology.order_detail od, order_master om, radiology.cpt cpt, \n"
                            + "radiology.patient_type pt,\n"
                            + "radiology.patient_reports pr,\n"
                            + "radiology.patient p\n"
                            + "where od.order_id = om.order_id\n"
                            + "and od.cpt_id=cpt.id\n"
                            + "and pr.order_id=od.id\n"
                            + "and " + searchParam + "\n"
                            + "and om.patient_type=pt.id\n"
                            + "and om.patient_id=p.patient_id \n"
                            + "and om.patient_location_id = p.location_id\n"
                            + "and od.status in (6)\n"
                            + "and pt.id = (" + pType[i] + ")\n"
                            + "and cpt.contrast in ('N','') \n"
                            + "\n";

                    if (!locationId.equalsIgnoreCase("")) {
                        query = query + "and om.location_id='" + locationId + "'";
                    }

                    if (from.equalsIgnoreCase(to)) {
                        query = query + " and date(od.date)='" + from + "' ";
                    } else {
                        query = query + " and date(od.date)>='" + from + "' and date(od.date)<='" + to + "'  ";
                    }

                    query = query + " order by  pt.description;";
                    System.out.println(query);
                    ps = con.prepareStatement(query);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        PatientDetailReportBo value = new PatientDetailReportBo();
                        value.setId(rs.getString("id"));
                        value.setPatientId(rs.getString("location_id") + "-" + rs.getString("patient_id"));

                        value.setName(rs.getString("name"));
                        value.setCnic(rs.getString("cnic"));
                        value.setPatien_type(rs.getString("patient_type"));
                        value.setDescription(rs.getString("description"));
                        value.setTrans_date(rs.getString("date"));
                        value.setContrast(rs.getString("contrast"));
                        value.setFindings(rs.getString("findings"));
                        value.setGender(rs.getString("gender"));
                        value.setDob(rs.getString("dob"));
                        value.setRefPhy(rs.getString("ref_phy"));
                        value.setUrgent(rs.getString("urgent"));
                        value.setPreDefHist(rs.getString("predefined_hist"));
                        value.setRegno(rs.getString("otherMrno"));

//                        Blob blob = rs.getBlob("picture");
//                InputStream inputStream = null;
//                try {
//                    inputStream = blob.getBinaryStream();
//                    value.setPicture(inputStream);
//                } catch(Exception ex) {
//
//                }
                        patientDetailMap.put(value.getId(), value);
                    }

                }
                if (contrast.contains("Y")) {
                    query = "select od.id,p.patient_id,p.gender,p.otherMrno, p.dob,om.ref_phy, om.urgent, om.predefined_hist,p.location_id, p.Name, p.cnic, pt.description patient_type,\n"
                            + "cpt.description, od.date, if(cpt.contrast='Y','    Y','    N') contrast,pr.findings\n"
                            + "from radiology.order_detail od, order_master om, radiology.cpt cpt, \n"
                            + "radiology.patient_type pt,\n"
                            + "radiology.patient_reports pr,\n"
                            + "radiology.patient p\n"
                            + "where od.order_id = om.order_id\n"
                            + "and od.cpt_id=cpt.id\n"
                            + "and pr.order_id=od.id\n"
                            + "and " + searchParam + "\n"
                            + "and om.patient_type=pt.id\n"
                            + "and om.patient_id=p.patient_id \n"
                            + "and om.patient_location_id = p.location_id\n"
                            + "and od.status in (6)\n"
                            + "and pt.id = (" + pType[i] + ")\n"
                            + "and cpt.contrast = ('Y') \n";

                    if (!locationId.equalsIgnoreCase("")) {
                        query = query + "and om.location_id='" + locationId + "'";
                    }

                    if (from.equalsIgnoreCase(to)) {
                        query = query + " and date(od.date)='" + from + "' ";
                    } else {
                        query = query + " and date(od.date)>='" + from + "' and date(od.date)<='" + to + "'  ";
                    }

                    query = query + " order by  pt.description;";
                    System.out.println(query);
                    ps = con.prepareStatement(query);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        PatientDetailReportBo value = new PatientDetailReportBo();
//                        value.setId(rs.getString("id"));
//                        value.setPatientId(rs.getString("location_id") + "-" + rs.getString("patient_id"));
//                        value.setName(rs.getString("name"));
//                        value.setCnic(rs.getString("cnic"));
//                        value.setPatien_type(rs.getString("patient_type"));
//                        value.setDescription(rs.getString("description"));
//                        value.setTrans_date(rs.getString("date"));
//                        value.setContrast(rs.getString("contrast"));
//                        value.setFindings(rs.getString("findings"));
                        value.setId(rs.getString("id"));
                        value.setPatientId(rs.getString("location_id") + "-" + rs.getString("patient_id"));

                        value.setName(rs.getString("name"));
                        value.setCnic(rs.getString("cnic"));
                        value.setPatien_type(rs.getString("patient_type"));
                        value.setDescription(rs.getString("description"));
                        value.setTrans_date(rs.getString("date"));
                        value.setContrast(rs.getString("contrast"));
                        value.setFindings(rs.getString("findings"));
                        value.setGender(rs.getString("gender"));
                        value.setDob(rs.getString("dob"));
                        value.setRefPhy(rs.getString("ref_phy"));
                        value.setUrgent(rs.getString("urgent"));
                        value.setPreDefHist(rs.getString("predefined_hist"));
                        value.setRegno(rs.getString("otherMrno"));
                        patientDetailMap.put(value.getId(), value);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void getFindingWiseDetail(String from, String to, String locationId, String patientType, String contrast, LinkedHashMap<String, PatientDetailReportBo> patientDetailMap, String searchParam) {
        String pType[] = patientType.split(",");

//        for (int i = 0; i < pType.length; i++)
        {
            try {
                if (con == null || con.isClosed()) {
                    this.connection();
                }
                {
                    query = "select od.id,p.patient_id,p.gender,p.otherMrno, p.dob,om.ref_phy, om.urgent, om.predefined_hist,p.location_id, p.Name, p.cnic, pt.description patient_type,\n"
                            + "cpt.description, od.date, if(cpt.contrast='Y','    Y','    N') contrast,pr.findings\n"
                            + "from radiology.order_detail od, order_master om, radiology.cpt cpt, \n"
                            + "radiology.patient_type pt,\n"
                            + "radiology.patient_reports pr,\n"
                            + "radiology.patient p\n"
                            + "where od.order_id = om.order_id\n"
                            + "and od.cpt_id=cpt.id\n"
                            + "and pr.order_id=od.id\n"
                            //                            + "and pr.findings ='" + searchParam + "'\n"
                            + "and om.patient_type=pt.id\n"
                            + "and om.patient_id=p.patient_id \n"
                            + "and om.patient_location_id = p.location_id\n"
                            + "and od.status = 6\n"
                            + "and pt.id in (" + patientType + ")\n"
                            + "and cpt.contrast in (" + contrast + ") \n"
                            + "\n";

                    if (!locationId.equalsIgnoreCase("")) {
                        query = query + "and om.location_id='" + locationId + "'";
                    }

                    if (from.equalsIgnoreCase(to)) {
                        query = query + " and date(od.date)='" + from + "' ";
                    } else {
                        query = query + " and date(od.date)>='" + from + "' and date(od.date)<='" + to + "'  ";
                    }

                    query = query + " order by  pt.description;";
                    System.out.println(query);
                    ps = con.prepareStatement(query);
                    rs = ps.executeQuery();
                    int totalRows = 0;
                    while (rs.next()) {
                        totalRows++;
                        System.out.println("Reading record no. " + totalRows);

                        PatientDetailReportBo value = new PatientDetailReportBo();
                        value.setId(rs.getString("id"));
                        value.setPatientId(rs.getString("location_id") + "-" + rs.getString("patient_id"));

                        value.setName(rs.getString("name"));
                        value.setCnic(rs.getString("cnic"));
                        value.setPatien_type(rs.getString("patient_type"));
                        value.setDescription(rs.getString("description"));
                        value.setTrans_date(rs.getString("date"));
                        value.setContrast(rs.getString("contrast"));
                        value.setFindings(rs.getString("findings"));
                        value.setGender(rs.getString("gender"));
                        value.setDob(rs.getString("dob"));
                        value.setRefPhy(rs.getString("ref_phy"));
                        value.setUrgent(rs.getString("urgent"));
                        value.setPreDefHist(rs.getString("predefined_hist"));
                        value.setRegno(rs.getString("otherMrno"));

//                        Blob blob = rs.getBlob("picture");
//                InputStream inputStream = null;
//                try {
//                    inputStream = blob.getBinaryStream();
//                    value.setPicture(inputStream);
//                } catch(Exception ex) {
//
//                }
                        patientDetailMap.put(value.getId(), value);
                    }

                }

//                if (contrast.contains("N"))
//
//                if (contrast.contains("Y")) {
//                    query = "select od.id,p.patient_id,p.gender,p.otherMrno, p.dob,om.ref_phy, om.urgent, om.predefined_hist,p.location_id, p.Name, p.cnic, pt.description patient_type,\n"
//                            + "cpt.description, od.date, if(cpt.contrast='Y','    Y','    N') contrast,pr.findings\n"
//                            + "from radiology.order_detail od, order_master om, radiology.cpt cpt, \n"
//                            + "radiology.patient_type pt,\n"
//                            + "radiology.patient_reports pr,\n"
//                            + "radiology.patient p\n"
//                            + "where od.order_id = om.order_id\n"
//                            + "and od.cpt_id=cpt.id\n"
//                            + "and pr.order_id=od.id\n"
//                            + "and " + searchParam + "\n"
//                            + "and om.patient_type=pt.id\n"
//                            + "and om.patient_id=p.patient_id \n"
//                            + "and om.patient_location_id = p.location_id\n"
//                            + "and od.status in (6)\n"
//                            + "and pt.id = (" + pType[i] + ")\n"
//                            + "and cpt.contrast = ('Y') \n";
//
//                    if (!locationId.equalsIgnoreCase("")) {
//                        query = query + "and om.location_id='" + locationId + "'";
//                    }
//
//                    if (from.equalsIgnoreCase(to)) {
//                        query = query + " and date(od.date)='" + from + "' ";
//                    } else {
//                        query = query + " and date(od.date)>='" + from + "' and date(od.date)<='" + to + "'  ";
//                    }
//
//                    query = query + " order by  pt.description;";
//                    System.out.println(query);
//                    ps = con.prepareStatement(query);
//                    rs = ps.executeQuery();
//                    while (rs.next()) {
//                        PatientDetailReportBo value = new PatientDetailReportBo();
////                        value.setId(rs.getString("id"));
////                        value.setPatientId(rs.getString("location_id") + "-" + rs.getString("patient_id"));
////                        value.setName(rs.getString("name"));
////                        value.setCnic(rs.getString("cnic"));
////                        value.setPatien_type(rs.getString("patient_type"));
////                        value.setDescription(rs.getString("description"));
////                        value.setTrans_date(rs.getString("date"));
////                        value.setContrast(rs.getString("contrast"));
////                        value.setFindings(rs.getString("findings"));
//value.setId(rs.getString("id"));
//                        value.setPatientId(rs.getString("location_id") + "-" + rs.getString("patient_id"));
//
//                        value.setName(rs.getString("name"));
//                        value.setCnic(rs.getString("cnic"));
//                        value.setPatien_type(rs.getString("patient_type"));
//                        value.setDescription(rs.getString("description"));
//                        value.setTrans_date(rs.getString("date"));
//                        value.setContrast(rs.getString("contrast"));
//                        value.setFindings(rs.getString("findings"));
//                        value.setGender(rs.getString("gender"));
//                        value.setDob(rs.getString("dob"));
//                        value.setRefPhy(rs.getString("ref_phy"));
//                        value.setUrgent(rs.getString("urgent"));
//                        value.setPreDefHist(rs.getString("predefined_hist"));
//                        value.setRegno(rs.getString("otherMrno"));
//                        patientDetailMap.put(value.getId(), value);
//                    }
//                }
            } catch (SQLException ex) {
                Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void getFindingWiseSummary(String from, String to, String locationId, String patientType, String contrast, LinkedHashMap<String, PatientSummaryReportBo> patientSummaryMap, String searchParam) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }

            query = "select pt.description as patient_type, if(cpt.contrast='Y','With Contrast','Without Contrast') as contrast, \n"
                    + "count(*) as qty\n"
                    + "from radiology.order_detail od, order_master om, \n"
                    + "radiology.patient_type pt,radiology.cpt cpt\n"
                    + "where od.order_id = om.order_id\n"
                    + "and om.patient_type=pt.id\n"
                    + "and od.cpt_id=cpt.id\n"
                    + "and od.status in (6)\n"
                    + "and " + searchParam + "\n"
                    + "and pt.id in (" + patientType + ")\n"
                    + "and contrast in (" + contrast + ")\n";
            if (!locationId.equalsIgnoreCase("")) {
                query = query + "and om.location_id='" + locationId + "'";
            }
            if (from.equalsIgnoreCase(to)) {
                query = query + " and date(od.date)='" + from + "' ";
            } else {
                query = query + " and date(od.date)>='" + from + "' and date(od.date)<='" + to + "'\n ";
            }

            query = query + " group by pt.description, cpt.contrast";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {

                PatientSummaryReportBo value = patientSummaryMap.get(rs.getString("patient_type"));

                try {
                    if (value != null) {
                        if (rs.getString("contrast").equalsIgnoreCase("with contrast")) {
                            value.setWithContrast(rs.getString("qty"));
                        } else {
                            value.setWithoutContrast(rs.getString("qty"));
                        }

                        patientSummaryMap.replace(value.getPatientType(), value);
                    } else {
                        value = new PatientSummaryReportBo();
                        if (rs.getString("contrast").equalsIgnoreCase("with contrast")) {
                            value.setWithContrast(rs.getString("qty"));
                        } else {
                            value.setWithoutContrast(rs.getString("qty"));
                        }
                        value.setPatientType(rs.getString("patient_type"));
                        patientSummaryMap.put(value.getPatientType(), value);
                    }

                } catch (NullPointerException e) {
                    value = new PatientSummaryReportBo();
                    if (rs.getString("contrast").equalsIgnoreCase("with contrast")) {
                        value.setWithContrast(rs.getString("qty"));
                    } else {
                        value.setWithoutContrast(rs.getString("qty"));
                    }
                    value.setPatientType(rs.getString("patient_type"));
                    patientSummaryMap.put(value.getPatientType(), value);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getFindingWiseDetails(String from, String to, String locationId, String patientType, String contrast, LinkedHashMap<String, PatientDetailReportBo> patientDetailMap, String searchParam) {
        String pType[] = patientType.split(",");

        for (int i = 0; i < pType.length; i++) {
            try {
                if (con == null || con.isClosed()) {
                    this.connection();
                }
                if (contrast.contains("N")) {
                    query = "select od.id,p.patient_id,p.location_id, p.Name, p.cnic, pt.description patient_type,\n"
                            + "cpt.description, od.date, if(cpt.contrast='Y','    Y','    N') contrast,pr.findings\n"
                            + "from radiology.order_detail od, order_master om, radiology.cpt cpt, \n"
                            + "radiology.patient_type pt,\n"
                            + "radiology.patient_reports pr,\n"
                            + "radiology.patient p\n"
                            + "where od.order_id = om.order_id\n"
                            + "and od.cpt_id=cpt.id\n"
                            + "and pr.order_id=od.id\n"
                            + "and " + searchParam + "\n"
                            + "and om.patient_type=pt.id\n"
                            + "and om.patient_id=p.patient_id \n"
                            + "and om.patient_location_id = p.location_id\n"
                            + "and od.status in (6)\n"
                            + "and pt.id = (" + pType[i] + ")\n"
                            + "and cpt.contrast in ('N','') \n";

                    if (!locationId.equalsIgnoreCase("")) {
                        query = query + "and om.location_id='" + locationId + "'";
                    }

                    if (from.equalsIgnoreCase(to)) {
                        query = query + " and date(od.date)='" + from + "' ";
                    } else {
                        query = query + " and date(od.date)>='" + from + "' and date(od.date)<='" + to + "'  ";
                    }

                    query = query + " order by  pt.description, od.date  desc;";
                    System.out.println(query);
                    ps = con.prepareStatement(query);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        PatientDetailReportBo value = new PatientDetailReportBo();
                        value.setId(rs.getString("id"));
                        value.setPatientId(rs.getString("location_id") + "-" + rs.getString("patient_id"));
                        value.setName(rs.getString("name"));
                        value.setCnic(rs.getString("cnic"));
                        value.setPatien_type(rs.getString("patient_type"));
                        value.setDescription(rs.getString("description"));
                        value.setTrans_date(rs.getString("date"));
                        value.setContrast(rs.getString("contrast"));
                        value.setFindings(rs.getString("findings"));
                        patientDetailMap.put(value.getId(), value);
                    }

                }
                if (contrast.contains("Y")) {
                    query = "select od.id,p.patient_id,p.location_id, p.Name, p.cnic, pt.description patient_type,\n"
                            + "cpt.description, od.date, if(cpt.contrast='Y','    Y','    N') contrast,pr.findings\n"
                            + "from radiology.order_detail od, order_master om, radiology.cpt cpt, \n"
                            + "radiology.patient_type pt,\n"
                            + "radiology.patient_reports pr,\n"
                            + "radiology.patient p\n"
                            + "where od.order_id = om.order_id\n"
                            + "and od.cpt_id=cpt.id\n"
                            + "and pr.order_id=od.id\n"
                            + "and " + searchParam + "\n"
                            + "and om.patient_type=pt.id\n"
                            + "and om.patient_id=p.patient_id \n"
                            + "and om.patient_location_id = p.location_id\n"
                            + "and od.status in (6)\n"
                            + "and pt.id = (" + pType[i] + ")\n"
                            + "and cpt.contrast = ('Y') \n";

                    if (!locationId.equalsIgnoreCase("")) {
                        query = query + "and om.location_id='" + locationId + "'";
                    }

                    if (from.equalsIgnoreCase(to)) {
                        query = query + " and date(od.date)='" + from + "' ";
                    } else {
                        query = query + " and date(od.date)>='" + from + "' and date(od.date)<='" + to + "'  ";
                    }

                    query = query + " order by  pt.description, od.date  desc;";
                    System.out.println(query);
                    ps = con.prepareStatement(query);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        PatientDetailReportBo value = new PatientDetailReportBo();
                        value.setId(rs.getString("id"));
                        value.setPatientId(rs.getString("location_id") + "-" + rs.getString("patient_id"));
                        value.setName(rs.getString("name"));
                        value.setCnic(rs.getString("cnic"));
                        value.setPatien_type(rs.getString("patient_type"));
                        value.setDescription(rs.getString("description"));
                        value.setTrans_date(rs.getString("date"));
                        value.setContrast(rs.getString("contrast"));
                        value.setFindings(rs.getString("findings"));
                        patientDetailMap.put(value.getId(), value);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ArrayList<String> getReferringDocList(String loc) {
        ArrayList<String> list = new ArrayList<>();
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT * from radiology.referring_physician where\n"
                    + "active = 'Y' and location_id IN (" + loc + ")";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ref = rs.getString("name") + " (" + rs.getString("designation") + ") : " + rs.getString("id");
                list.add(ref);
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public String getJpegServerPath(String locationID) {
        String path = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT value from radiology.system_constants where\n"
                    + "active = 'Y'  and id like ('%JPEG%')";

            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                path = rs.getString("value");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
            return path;
        }
        return path;
    }

    public boolean insertPatientPaths(String location, String id, String[] pictures) {
        query = "UPDATE `radiology`.`patient` \n"
                + "SET \n"
                + "    `profile_picture` = '" + pictures[0] + "',\n"
                + "    `id_card_front` = '" + pictures[1] + "',\n"
                + "    `id_card_back` = '" + pictures[2] + "',\n"
                + "    `refer_letter` = '" + pictures[3] + "'\n"
                + "WHERE\n"
                + "    `patient_id` = '" + id + "'\n"
                + "        and `location_id` = '" + location + "'";

        boolean flag = false;
        try {

            if (con == null || con.isClosed()) {
                this.connection();
            }
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            flag = false;
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public boolean insertAttendantPatientPaths(String location, String id, String[] pictures) {
        query = "UPDATE `radiology`.`attendant` \n"
                + "SET \n"
                + "    `profile_picture` = '" + pictures[0] + "',\n"
                + "    `id_front_path` = '" + pictures[1] + "',\n"
                + "    `id_back_path` = '" + pictures[2] + "',\n"
                + "    `refer_letter_path` = '" + pictures[3] + "'\n"
                + "WHERE\n"
                + "    `patient_id` = '" + id + "'\n"
                + "        and `patient_location_id` = '" + location + "'";

        boolean flag = false;
        try {

            if (con == null || con.isClosed()) {
                this.connection();
            }
            System.out.println(query);
            ps = con.prepareStatement(query);
            ps.executeUpdate();
            ps.close();
            flag = true;
        } catch (SQLException ex) {
            flag = false;
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    public String get_prescription(String loc, String id) {
        String path = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select \n"
                    + "    p.refer_letter\n"
                    + "from\n"
                    + "    radiology.patient p\n"
                    + "where\n"
                    + "    p.patient_id = " + id + "\n"
                    + "        and p.location_id = " + loc + "";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                path = rs.getString("refer_letter");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return path;
    }

    public String get_prescription_attendant(String loc, String id) {
        String path = "";
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "select \n"
                    + "    p.refer_letter_path\n"
                    + "from\n"
                    + "    radiology.attendant p\n"
                    + "where\n"
                    + "    p.patient_id = " + id + "\n"
                    + "        and p.patient_location_id = " + loc + "";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                path = rs.getString("refer_letter_path");
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
        return path;
    }

    public void getDetailsForPrint(String orderId, LinkedHashMap<String, CptWiseOrderEntryBO> cptMapForPrint) {
        try {
            if (con == null || con.isClosed()) {
                this.connection();
            }
            query = "SELECT \n"
                    + "    o.status,\n"
                    + "    o.order_id,\n"
                    + "    o.price,\n"
                    + "    o.id,\n"
                    + "    m.predefined_hist,\n"
                    + "    m.ref_phy,\n"
                    + "    c.description,\n"
                    + "    c.cpt_code,\n"
                    + "    e.name\n"
                    + "FROM\n"
                    + "    radiology.order_detail o\n"
                    + "        JOIN\n"
                    + "    radiology.cpt c ON o.cpt_id = c.id\n"
                    + "        JOIN\n"
                    + "    radiology.order_master m ON o.order_id = m.order_id\n"
                    + "join\n"
                    + "    radiology.employee e ON e.employee_id = m.user_id\n"
                    + "WHERE\n"
                    + "    o.order_id = '" + orderId + "'\n"
                    + "        AND o.status NOT IN (0 , 7)";

            System.out.println(query);
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {

                CptWiseOrderEntryBO value = new CptWiseOrderEntryBO();
                value.setCptCode(rs.getString("cpt_code"));
                value.setCptDescription(rs.getString("description"));
                value.setPrice(rs.getString("price"));
                value.setAccessionNum(rs.getString("id"));
                value.setOrderId(orderId);
                value.setChkbox("Y");
                value.setPredef_hist(rs.getString("predefined_hist"));
                value.setRefPhy(rs.getString("ref_phy"));
                value.setCptCost(rs.getString("price"));
                value.setWorkOrderEmployeeName(rs.getString("name"));
                cptMapForPrint.put(value.getCptCode(), value);
            }
        } catch (SQLException ex) {
            Logger.getLogger(dam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
