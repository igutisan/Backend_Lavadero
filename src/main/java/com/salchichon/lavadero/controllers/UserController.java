/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salchichon.lavadero.controllers;

import com.salchichon.lavadero.models.UserM;
import com.salchichon.lavadero.repositories.UserRepository;
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
 @RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userrepo;
    
    @GetMapping("/all")
    public List<UserM> getAllUsers(){
        return userrepo.findAll();
    }
    @CrossOrigin
     @GetMapping("/v1/{dni}")
    public ResponseEntity<UserM> getUserById(@PathVariable String dni){
        Optional<UserM> user = userrepo.findByDni(dni);
        return user.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserM> getUserById(@PathVariable Long id){
        Optional<UserM> user = userrepo.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/create")
    public ResponseEntity<UserM>createUser(@RequestBody UserM user){
        
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        
        UserM saveUser = userrepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveUser);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>deleteUser(@PathVariable long id){
        if(!userrepo.existsById(id)){
            return ResponseEntity.notFound().build();
        }else{
            userrepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<UserM> updateUser(@PathVariable long id, @RequestBody UserM updatedUser){
   
        if(!userrepo.existsById(id)){
            return ResponseEntity.notFound().build();
        }else{
            updatedUser.setId(id);
            UserM saveUser = userrepo.save(updatedUser);
            return ResponseEntity.ok(saveUser);
        }
    }
}
