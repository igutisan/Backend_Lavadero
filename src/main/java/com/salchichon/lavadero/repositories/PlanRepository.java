/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.salchichon.lavadero.repositories;

import com.salchichon.lavadero.models.Plan;
import com.salchichon.lavadero.models.UserM;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author igutisan
 */
public interface PlanRepository extends JpaRepository<Plan, Long> {
    
}
