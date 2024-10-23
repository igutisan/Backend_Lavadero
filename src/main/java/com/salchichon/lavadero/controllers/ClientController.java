/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salchichon.lavadero.controllers;

import com.salchichon.lavadero.models.Client;
import com.salchichon.lavadero.repositories.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
 @RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    private ClientRepository clientrepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/all")
    public List<Client> getAllClients(){
        return clientrepo.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id){
        Optional<Client> user = clientrepo.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/create")
    public ResponseEntity<Client>createClient(@RequestBody Client client){
        String encryptedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encryptedPassword);
        
        Client saveClient = clientrepo.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveClient);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>deleteClient(@PathVariable long id){
        if(!clientrepo.existsById(id)){
            return ResponseEntity.notFound().build();
        }else{
            clientrepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
    @PutMapping("update/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable long id, @RequestBody Client updatedClient){
   
        if(!clientrepo.existsById(id)){
            return ResponseEntity.notFound().build();
        }else{
            updatedClient.setId(id);
            Client saveUser = clientrepo.save(updatedClient);
            return ResponseEntity.ok(saveUser);
        }
    }
}
