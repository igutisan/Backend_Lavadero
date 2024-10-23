/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salchichon.lavadero.controllers;


import com.salchichon.lavadero.models.Plan;
import com.salchichon.lavadero.models.Receipt;
import com.salchichon.lavadero.models.ReceiptDetail;
import com.salchichon.lavadero.models.Service;
import com.salchichon.lavadero.repositories.PlanRepository;
import com.salchichon.lavadero.repositories.ReceiptDetailRepository;
import com.salchichon.lavadero.repositories.ReceiptRepository;
import com.salchichon.lavadero.repositories.ServiceRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
@RequestMapping("/api/service")
public class ServiceController {
    @Autowired
    private ServiceRepository servicerepo;
     @Autowired
    private PlanRepository planrepo;
     @Autowired
     private ReceiptRepository receiptRepo;
     @Autowired
     private ReceiptDetailRepository receiptDetailRepo;
    
    
    @GetMapping("/all")
    public List<Service>getAllService(){
        return servicerepo.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable Long id){
        Optional<Service>service = servicerepo.findById(id);
        return service.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
    @CrossOrigin
    @PostMapping("/create")
    @Transactional 
    public ResponseEntity<List<Service>> createService(@RequestBody List<Service> services) {
        List<Service> listServices  = new ArrayList<>();
    try {
        System.out.println("Ventas recibidas: " + services);
        
        BigDecimal total = BigDecimal.ZERO; // Inicializa total

        for (Service service : services) {
            // Guarda el servicio
            Service savedService = servicerepo.save(service);
            listServices.add(savedService);

            // Busca el plan relacionado
            Plan plan = planrepo.findById(savedService.getIdPlan().getId()).orElse(null);
            System.out.println("V recibidas: " + plan);
        
            if (plan != null) {
                total = total.add(plan.getPrice());
            } else {
                throw new RuntimeException("Plan no encontrado");
            }
        }

        // Crear y guardar la factura
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaFormateada = fechaActual.format(formato);

        Receipt receipt = new Receipt();
        receipt.setTotal(total);
        receipt.setDate(fechaFormateada);
        Receipt savedReceipt = receiptRepo.save(receipt);

        // Guardar detalles de la factura
        for (Service service : services) {
            ReceiptDetail detail = new ReceiptDetail();
            detail.setIdReceipt(savedReceipt);
            detail.setIdService(service); // Aquí es correcto usar el servicio guardado

            receiptDetailRepo.save(detail);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(listServices);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>deleteService(@PathVariable Long id){
        if(!servicerepo.existsById(id)){
            return ResponseEntity.notFound().build();
        }else{
            servicerepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Service> updateService(@PathVariable Long id, @RequestBody Service updatedService){
        
        if(!servicerepo.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        updatedService.setId(id);
        Service savedService = servicerepo.save(updatedService);
        return ResponseEntity.ok(savedService);
    }
}