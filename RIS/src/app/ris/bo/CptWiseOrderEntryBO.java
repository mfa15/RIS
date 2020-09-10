/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.ris.bo;

/**
 *
 */
public class CptWiseOrderEntryBO {

    String orderId, cptId, patientTypeId, price, cptCode, cptDescription, cptCost, accessionNum, history, chkbox;
    String predef_hist, refPhy, companyID;

    String workOrderEmployeeName;

    public String getWorkOrderEmployeeName() {
        return workOrderEmployeeName;
    }

    public void setWorkOrderEmployeeName(String workOrderEmployeeName) {
        this.workOrderEmployeeName = workOrderEmployeeName;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getPredef_hist() {
        return predef_hist;
    }

    public void setPredef_hist(String predef_hist) {
        this.predef_hist = predef_hist;
    }

    public String getRefPhy() {
        return refPhy;
    }

    public void setRefPhy(String refPhy) {
        this.refPhy = refPhy;
    }

    public String getChkbox() {
        return chkbox;
    }

    public void setChkbox(String chkbox) {
        this.chkbox = chkbox;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getAccessionNum() {
        return accessionNum;
    }

    public void setAccessionNum(String accessionNum) {
        this.accessionNum = accessionNum;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCptId() {
        return cptId;
    }

    public void setCptId(String cptId) {
        this.cptId = cptId;
    }

    public String getPatientTypeId() {
        return patientTypeId;
    }

    public void setPatientTypeId(String patientTypeId) {
        this.patientTypeId = patientTypeId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCptCode() {
        return cptCode;
    }

    public void setCptCode(String cptCode) {
        this.cptCode = cptCode;
    }

    public String getCptDescription() {
        return cptDescription;
    }

    public void setCptDescription(String cptDescription) {
        this.cptDescription = cptDescription;
    }

    public String getCptCost() {
        return cptCost;
    }

    public void setCptCost(String cptCost) {
        this.cptCost = cptCost;
    }

}
