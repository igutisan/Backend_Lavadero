package com.salchichon.lavadero.repositories;

import com.salchichon.lavadero.models.ReceiptDetail;
import com.salchichon.lavadero.models.ReceiptDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface ReceiptDetailDTORepo extends JpaRepository<ReceiptDetail, Long> {
    @Query("SELECT new com.salchichon.lavadero.models.ReceiptDetailsDTO(rd.id, s.idClient.dni, s.idClient.name, s.idUser.dni, s.idUser.name, s.idPlan.name, s.idPlan.price) "
            + "FROM ReceiptDetail rd "
            + "JOIN rd.idService s "
            + "JOIN rd.idReceipt r "
            + "WHERE r.id = :receiptId")
    List<ReceiptDetailsDTO> findAllServicesByReceiptId(@Param("receiptId") Long receiptId);
}
