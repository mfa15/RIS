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
public class PatientSummaryReportBo {

    public PatientSummaryReportBo() {
        this.patientType = "";
        this.withContrast = "0";
        this.withoutContrast = "0";
        this.total = "0";
    }
    
    
    
    String patientType,withContrast,withoutContrast,total;

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public String getWithContrast() {
        return withContrast;
    }

    public void setWithContrast(String withContrast) {
        this.withContrast = withContrast;
    }

    public String getWithoutContrast() {
        return withoutContrast;
    }

    public void setWithoutContrast(String withoutContrast) {
        this.withoutContrast = withoutContrast;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
    
    
}
