/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salchichon.lavadero.controllers;


import com.salchichon.lavadero.models.ReceiptDetail;
import com.salchichon.lavadero.models.ReceiptSummaryDTO;

import com.salchichon.lavadero.repositories.ReceiptDetailRepository;
import com.salchichon.lavadero.repositories.ReceiptSummaryRepository;
import com.salchichon.lavadero.services.ReceiptService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
 @RestController
 @RequestMapping("/api/receiptdetail")
public class ReceiptDetailController {
     
     @Autowired
     private  ReceiptDetailRepository reeceiptrepo;
     @Autowired
     private ReceiptSummaryRepository receiptService;
     
     
     
  
      
      @GetMapping("/history")
    public List<ReceiptSummaryDTO> getInvoiceHistory() {
        return receiptService.findAllInvoices();
    }
     
     @GetMapping("/all")
     public List<ReceiptDetail> getAllReceipt(){
         return reeceiptrepo.findAll();
     }
     @GetMapping("/{id}")
     public ResponseEntity<ReceiptDetail> getReceiptById(@PathVariable Long id){
         Optional<ReceiptDetail> receiptDetail = reeceiptrepo.findById(id);
         return receiptDetail.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
     }
     @PostMapping("/create")
     public ResponseEntity<ReceiptDetail>saveReceipt(@RequestBody ReceiptDetail receipt){
          ReceiptDetail saveReceipt = reeceiptrepo.save(receipt);
         return ResponseEntity.status(HttpStatus.CREATED).body(saveReceipt);
     }
     @DeleteMapping("/delete/{id}")
     public ResponseEntity<Void> deleteReceipt(@PathVariable Long id){
         if(reeceiptrepo.existsById(id)){
             return ResponseEntity.notFound().build();
         }
         reeceiptrepo.deleteById(id);
         return ResponseEntity.ok().build();
     }
     @PutMapping("/edit/{id}")
     public ResponseEntity<ReceiptDetail> updateAppointment(@PathVariable Long id, @RequestBody ReceiptDetail updatedReceipt){
         if(reeceiptrepo.existsById(id)){
             return ResponseEntity.notFound().build();
         }
         updatedReceipt.setId(id);
         ReceiptDetail saveReceipt= reeceiptrepo.save(updatedReceipt);
         return ResponseEntity.ok(saveReceipt);
     }
}