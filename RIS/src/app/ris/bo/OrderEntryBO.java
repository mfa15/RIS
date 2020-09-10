/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.bo;

public class OrderEntryBO {
    
    String OrderID,OrderLocationID,patientID,patientLocationID,bodayPartID,contrast,orderDateTime, history, orderStatus,
            patientName,orderLocationDesc, bodyPart,modality,status;
    String cptId,cptDesc,cptContrast;
    String patientType;
    String imageCount;
    String ageGender;

    public String getAgeGender() {
        return ageGender;
    }

    public void setAgeGender(String ageGender) {
        this.ageGender = ageGender;
    }
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    
    
    public String getCptContrast() {
        return cptContrast;
    }

    public void setCptContrast(String cptContrast) {
        this.cptContrast = cptContrast;
    }
    
    public String getCptDesc() {
        return cptDesc;
    }

    public void setCptDesc(String cptDesc) {
        this.cptDesc = cptDesc;
    }
    
    
    public String getCptId() {
        return cptId;
    }

    public void setCptId(String cptId) {
        this.cptId = cptId;
    }

    
    
    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }
    
    public String getImageCount() {
        return imageCount;
    }

    public void setImageCount(String imageCount) {
        this.imageCount = imageCount;
    }
    public static int counter = 0;
    public String getOrderID() {
        return OrderID;
    }

    public void setOrderID(String OrderID) {
        this.OrderID = OrderID;
    }

    public String getOrderLocationID() {
        return OrderLocationID;
    }

    public void setOrderLocationID(String OrderLocationID) {
        this.OrderLocationID = OrderLocationID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPatientLocationID() {
        return patientLocationID;
    }

    public void setPatientLocationID(String patientLocationID) {
        this.patientLocationID = patientLocationID;
    }

    public String getBodayPartID() {
        return bodayPartID;
    }

    public void setBodayPartID(String bodayPartID) {
        this.bodayPartID = bodayPartID;
    }

    public String getContrast() {
        return contrast;
    }

    public void setContrast(String contrast) {
        this.contrast = contrast;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getOrderLocationDesc() {
        return orderLocationDesc;
    }

    public void setOrderLocationDesc(String patientLocationDesc) {
        this.orderLocationDesc = patientLocationDesc;
    }

    public String getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart) {
        this.bodyPart = bodyPart;
    }
    
    
    
}
