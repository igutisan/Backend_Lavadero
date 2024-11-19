/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salchichon.lavadero.controllers;

import com.salchichon.lavadero.models.Appointment;
import com.salchichon.lavadero.repositories.AppointmentRepository;
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
 @RequestMapping("/api/appoint")
public class AppointmentController {

     @Autowired
     private AppointmentRepository appointrepo;
     @GetMapping("/all/{id}")
     public List<Appointment> getAllAppointmentsByClient(@PathVariable Long id){
         return appointrepo.findByClient_Id(id);
     }

     @GetMapping("/all")
     public List<Appointment> getAllAppointments(){
         return appointrepo.findAll();
     }
     @GetMapping("/{id}")
     public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id){
         Optional<Appointment> appointment = appointrepo.findById(id);
         return appointment.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
     }
     @PostMapping("/create")
     public ResponseEntity<Appointment>saveAppointment(@RequestBody Appointment appoint){
         Appointment saveAppointment = appointrepo.save(appoint);
         return ResponseEntity.status(HttpStatus.CREATED).body(saveAppointment);
     }
     @DeleteMapping("/delete/{id}")
     public ResponseEntity<Void> deleteAppointment(@PathVariable Long id){
         if(!appointrepo.existsById(id)){
             return ResponseEntity.notFound().build();
         }
         appointrepo.deleteById(id);
         return ResponseEntity.ok().build();
     }

     @PutMapping("/edit/{id}")
     public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment updatedAppoint) {

         return (ResponseEntity<Appointment>) appointrepo.findById(id).map(existingAppoint -> {
             // Validación para idCliente
             if (updatedAppoint.getClient() == null) {
                 return ResponseEntity.badRequest().body(null); // Responder con un error si idCliente es null
             }

             if (updatedAppoint.getDate() != null) {
                 existingAppoint.setDate(updatedAppoint.getDate());
             }
             if (updatedAppoint.getStatus() != null) {
                 existingAppoint.setStatus(updatedAppoint.getStatus());
             }

             existingAppoint.setClient(updatedAppoint.getClient());  // Asegurarse de que idCliente no sea null

             Appointment savedAppoint = appointrepo.save(existingAppoint);
             return ResponseEntity.ok(savedAppoint);
         }).orElseGet(() -> ResponseEntity.notFound().build());

}
}
