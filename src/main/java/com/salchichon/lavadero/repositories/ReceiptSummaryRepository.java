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
     
  @Query("SELECT new com.salchichon.lavadero.models.ReceiptSummaryDTO(\n" +
          "    r.id, \n" +
          "    c.dni, \n" +
          "    r.date, \n" +
          "    r.total\n" +
          ")\n" +
          "FROM ReceiptDetail rd\n" +
          "JOIN rd.idReceipt r\n" +
          "JOIN rd.idService s\n" +
          "JOIN s.idClient c\n" +
          "GROUP BY r.id, c.dni, r.date, r.total")
     List<ReceiptSummaryDTO> findAllInvoices();
}
