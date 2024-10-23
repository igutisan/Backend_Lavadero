/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.salchichon.lavadero.repositories;

import com.salchichon.lavadero.models.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author igutisan
 */
public interface ReceiptRepository extends JpaRepository<Receipt, Long>{
    
}
