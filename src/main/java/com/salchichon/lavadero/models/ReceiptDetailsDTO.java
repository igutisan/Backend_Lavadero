package com.salchichon.lavadero.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


public class ReceiptDetailsDTO {

    private Long idReceipt;
    private String dniClient;
    private String nameClient;
    private String dniUsuario;
    private String nameUsuario;
    private String nameService;
    private BigDecimal total;

    public ReceiptDetailsDTO() {

    }

    public ReceiptDetailsDTO(Long idReceipt, String dniClient, String nameClient, String dniUsuario,
                             String nameUsuario, String nameService, BigDecimal total) {
        this.idReceipt = idReceipt;
        this.dniClient = dniClient;
        this.nameClient = nameClient;
        this.dniUsuario = dniUsuario;
        this.nameUsuario = nameUsuario;
        this.nameService = nameService;
        this.total = total;
    }

    public Long getIdReceipt() {
        return idReceipt;
    }

    public void setIdReceipt(Long idReceipt) {
        this.idReceipt = idReceipt;
    }

    public String getDniClient() {
        return dniClient;
    }

    public void setDniClient(String dniClient) {
        this.dniClient = dniClient;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getDniUsuario() {
        return dniUsuario;
    }

    public void setDniUsuario(String dniUsuario) {
        this.dniUsuario = dniUsuario;
    }

    public String getNameUsuario() {
        return nameUsuario;
    }

    public void setNameUsuario(String nameUsuario) {
        this.nameUsuario = nameUsuario;
    }

    public String getNameService() {
        return nameService;
    }

    public void setNameService(String nameService) {
        this.nameService = nameService;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
