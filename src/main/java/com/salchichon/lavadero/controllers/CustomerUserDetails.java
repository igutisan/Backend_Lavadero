/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salchichon.lavadero.controllers;

import com.salchichon.lavadero.models.Client;
import com.salchichon.lavadero.models.UserM;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author igutisan
 */
public class CustomerUserDetails  implements UserDetails {

  
    private UserM userM;
    private Client client;

    // Constructor para UserM
    public CustomerUserDetails(UserM userM) {
        this.userM = userM;
    }

    // Constructor para Client
    public CustomerUserDetails(Client client) {
        this.client = client;
    }

    public UserM getUserM() {
        return userM;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = userM != null ? userM.getRole() : "CLIENT";
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        if (userM != null) {
            return userM.getPassword();
        } else if (client != null) {
            // Si tienes una contraseña para los clientes, devuélvela aquí
            return client.getPassword(); // Asegúrate de tener un campo para la contraseña en Client
        }
        return null; // Si no tienes contraseñas para clientes, manejarlo adecuadamente
    }

    @Override
    public String getUsername() {
        if (userM != null) {
            return userM.getDni();
        } else if (client != null) {
            return client.getDni();
        }
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
