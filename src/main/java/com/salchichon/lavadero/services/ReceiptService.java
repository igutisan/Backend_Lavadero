/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salchichon.lavadero.services;




import com.salchichon.lavadero.models.ReceiptSummaryDTO;
import com.salchichon.lavadero.repositories.ReceiptSummaryRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ReceiptService {
  
 
    @Autowired
     private ReceiptSummaryRepository receiptRepository;
  
}
