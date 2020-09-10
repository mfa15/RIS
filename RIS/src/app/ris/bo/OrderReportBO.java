/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.bo;

/**
 *
 * @author faizan ahmed
 */
public class OrderReportBO {
    
    String order_id,name,hospitalMrn,patientId,pateitnLocationId,cptCode,cptDesc,modality, status, id,date;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHospitalMrn() {
        return hospitalMrn;
    }

    public void setHospitalMrn(String hospitalMrn) {
        this.hospitalMrn = hospitalMrn;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPateitnLocationId() {
        return pateitnLocationId;
    }

    public void setPateitnLocationId(String pateitnLocationId) {
        this.pateitnLocationId = pateitnLocationId;
    }

    public String getCptCode() {
        return cptCode;
    }

    public void setCptCode(String cptCode) {
        this.cptCode = cptCode;
    }

    public String getCptDesc() {
        return cptDesc;
    }

    public void setCptDesc(String cptDesc) {
        this.cptDesc = cptDesc;
    }

    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    
}
