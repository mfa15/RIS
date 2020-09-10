/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.bo;

/**
 *
 */
public class DicomBo {
    String studyId, location_id,order_id,modality,
            acqisition_time,bodyPartId, bodyDescription,patientId,name,
            contrast,filePath,PrimaryServer, backupServer,patientLocationID;

    public String getPatientLocationID() {
        return patientLocationID;
    }

    public void setPatientLocationID(String patientLocationID) {
        this.patientLocationID = patientLocationID;
    }

    
    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getAcqisition_time() {
        return acqisition_time;
    }

    public void setAcqisition_time(String acqisition_time) {
        this.acqisition_time = acqisition_time;
    }

    public String getBodyPartId() {
        return bodyPartId;
    }

    public void setBodyPartId(String bodyPartId) {
        this.bodyPartId = bodyPartId;
    }

    public String getBodyDescription() {
        return bodyDescription;
    }

    public void setBodyDescription(String bodyDescription) {
        this.bodyDescription = bodyDescription;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContrast() {
        return contrast;
    }

    public void setContrast(String contrast) {
        this.contrast = contrast;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPrimaryServer() {
        return PrimaryServer;
    }

    public void setPrimaryServer(String PrimaryServer) {
        this.PrimaryServer = PrimaryServer;
    }

    public String getBackupServer() {
        return backupServer;
    }

    public void setBackupServer(String backupServer) {
        this.backupServer = backupServer;
    }
    
    
    
    
}
