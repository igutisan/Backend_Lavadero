
package com.salchichon.lavadero.models;


import java.math.BigDecimal;


public class ReceiptSummaryDTO {
    private Long id;
    private String dni;
    private String date;
    private BigDecimal total;

    public ReceiptSummaryDTO() {
    }

    public ReceiptSummaryDTO(Long id, String dni, String date, BigDecimal total) {
        this.id = id;
        this.dni = dni;
        this.date = date;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    
    
    
}
