/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salchichon.lavadero.services;

import com.salchichon.lavadero.controllers.CustomerUserDetails;
import com.salchichon.lavadero.models.Client;
import com.salchichon.lavadero.models.UserM;
import com.salchichon.lavadero.repositories.ClientRepository;
import com.salchichon.lavadero.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepo;
    private final ClientRepository clientRepo;

    public UserDetailsServiceImpl(UserRepository userRepo, ClientRepository clientRepo) {
        this.userRepo = userRepo;
        this.clientRepo = clientRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String dni) throws UsernameNotFoundException {
        Optional<UserM> userOptional = userRepo.findByDni(dni);
        
        if (userOptional.isPresent()) {
            UserM user = userOptional.orElseThrow(() -> 
                    new UsernameNotFoundException("Usuario no encontrado con DNI: " + dni));
            return new CustomerUserDetails(user); 
        }

        Optional<Client> clientOptional = clientRepo.findByDni(dni);
        
        Client client = clientOptional.orElseThrow(() -> 
                new UsernameNotFoundException("Cliente no encontrado con DNI: " + dni));
        
        return new CustomerUserDetails(client); 
    }

 
    
}
