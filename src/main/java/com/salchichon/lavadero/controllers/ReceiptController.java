/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salchichon.lavadero.controllers;

import com.salchichon.lavadero.models.Receipt;
import com.salchichon.lavadero.models.ReceiptDetail;
import com.salchichon.lavadero.repositories.ReceiptDetailRepository;
import com.salchichon.lavadero.repositories.ReceiptRepository;
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
 @RequestMapping("/api/receipt")
public class ReceiptController {
     
     @Autowired
     private  ReceiptRepository receiptrepo;
     
     @GetMapping("/all")
     public List<Receipt> getAllReceipt(){
         return receiptrepo.findAll();
     }
     @GetMapping("/{id}")
     public ResponseEntity<Receipt> getReceiptById(@PathVariable Long id){
         Optional<Receipt> receipt = receiptrepo.findById(id);
         return receipt.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
     }
     @PostMapping("/create")
     public ResponseEntity<Receipt>saveReceipt(@RequestBody Receipt receipt){
          Receipt saveReceipt = receiptrepo.save(receipt);
         return ResponseEntity.status(HttpStatus.CREATED).body(saveReceipt);
     }
     @DeleteMapping("/delete/{id}")
     public ResponseEntity<Void> deleteReceipt(@PathVariable Long id){
         if(!receiptrepo.existsById(id)){
             return ResponseEntity.notFound().build();
         }
         receiptrepo.deleteById(id);
         return ResponseEntity.ok().build();
     }
     @PutMapping("/edit/{id}")
     public ResponseEntity<Receipt> updateReceipt(@PathVariable Long id, @RequestBody Receipt updatedReceipt){
         if(receiptrepo.existsById(id)){
             return ResponseEntity.notFound().build();
         }
         updatedReceipt.setId(id);
         Receipt saveReceipt= receiptrepo.save(updatedReceipt);
         return ResponseEntity.ok(saveReceipt);
     }
}
