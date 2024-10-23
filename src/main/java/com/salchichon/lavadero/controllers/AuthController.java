/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salchichon.lavadero.controllers;

import com.salchichon.lavadero.models.AuthRequestDTO;
import com.salchichon.lavadero.services.JwtUtilService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/api")
@RestController
public class AuthController {
    @Autowired 
    private AuthenticationManager  authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtilService jwtUtilService;
    
   @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<?>auth(@RequestBody AuthRequestDTO authRequestDTO){
        
       try {
        // 1. Autenticar usuario
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authRequestDTO.getDni(), authRequestDTO.getPassword()
            )
        );

        // 2. Cargar los detalles del usuario
        CustomerUserDetails userDetails =
            (CustomerUserDetails) userDetailsService.loadUserByUsername(authRequestDTO.getDni());

        // 3. Generar el token
        String jwt = jwtUtilService.generateToken(userDetails);

          Map<String, String> response = new HashMap<>();
        response.put("token", jwt);
        return ResponseEntity.ok(response);

    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Error Authentication: " + e.getMessage());
    }
       
    }
}
