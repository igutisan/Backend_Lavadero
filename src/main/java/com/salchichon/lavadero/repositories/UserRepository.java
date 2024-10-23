/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.salchichon.lavadero.repositories;

import com.salchichon.lavadero.models.UserM;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

/**
 *
 * @author igutisan
 */
public interface UserRepository extends JpaRepository<UserM, Long> {

     Optional<UserM> findByDni(String dni);
    
    
    //List<Note> findByUserId(Long userId);{
    
}
