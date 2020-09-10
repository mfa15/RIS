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
public class PerformanceSummaryBo {
    
    String modality,total,order,invoiced,techAck,drafted,signed,cancelled,counterAck;

    public PerformanceSummaryBo() {
        this.total = "0";
        this.order = "0";
        this.invoiced = "0";
        this.techAck = "0";
        this.drafted = "0";
        this.signed = "0";
        this.cancelled = "0";
        this.counterAck="0";
    }

    public String getCounterAck() {
        return counterAck;
    }

    public void setCounterAck(String counterAck) {
        this.counterAck = counterAck;
    }
    
    

    public String getCancelled() {
        return cancelled;
    }

    public void setCancelled(String cancelled) {
        this.cancelled = cancelled;
    }

    
    public String getModality() {
        return modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getInvoiced() {
        return invoiced;
    }

    public void setInvoiced(String invoiced) {
        this.invoiced = invoiced;
    }

    public String getTechAck() {
        return techAck;
    }

    public void setTechAck(String techAck) {
        this.techAck = techAck;
    }

    public String getDrafted() {
        return drafted;
    }

    public void setDrafted(String drafted) {
        this.drafted = drafted;
    }

    public String getSigned() {
        return signed;
    }

    public void setSigned(String signed) {
        this.signed = signed;
    }
    
    
}
