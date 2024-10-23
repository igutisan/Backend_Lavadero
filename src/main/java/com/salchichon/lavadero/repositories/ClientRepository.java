/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.salchichon.lavadero.repositories;

import com.salchichon.lavadero.models.Client;
import com.salchichon.lavadero.models.UserM;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author igutisan
 */
public interface ClientRepository extends JpaRepository<Client, Long>{
    Optional<Client> findByDni(String dni);
}
