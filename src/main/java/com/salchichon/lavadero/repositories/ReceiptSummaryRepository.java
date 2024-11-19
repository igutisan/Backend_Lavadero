/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.salchichon.lavadero.repositories;


import com.salchichon.lavadero.models.Receipt;
import com.salchichon.lavadero.models.ReceiptDetail;
import com.salchichon.lavadero.models.ReceiptSummaryDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ReceiptSummaryRepository extends JpaRepository<ReceiptDetail, Long> {

    @Query("SELECT new com.salchichon.lavadero.models.ReceiptSummaryDTO(" +
            "r.id, c.dni, r.date, r.total ) " +
            "FROM ReceiptDetail rd " +
            "JOIN rd.idReceipt r " +
            "JOIN rd.idService s " +
            "JOIN s.idClient c " +
            "GROUP BY r.id, c.dni, r.date, r.total")
     List<ReceiptSummaryDTO> findAllInvoices();
}
