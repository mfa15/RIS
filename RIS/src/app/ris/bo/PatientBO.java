/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.bo;

public class PatientBO {

    String patientId;
    String otherMrno;
    String location_id;
    String name;
    String fatherName;
    String cnic;
    String contactNum;
    String gender;
    String dob;
    String address;
    String city;
    String weight;
    String height;
    String age;
    String attendant_flag;
    String pmuNumber;

    public String getPmuNumber() {
        return pmuNumber;
    }

    public void setPmuNumber(String pmuNumber) {
        this.pmuNumber = pmuNumber;
    }

    public String getAttendant_flag() {
        return attendant_flag;
    }

    public void setAttendant_flag(String attendant_flag) {
        this.attendant_flag = attendant_flag;
    }
    
   

    public String getOtherMrno() {
        return otherMrno;
    }

    public void setOtherMrno(String otherMrno) {
        this.otherMrno = otherMrno;
    }

    
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
