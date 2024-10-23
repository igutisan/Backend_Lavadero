/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.salchichon.lavadero.controllers;

import com.salchichon.lavadero.models.Plan;
import com.salchichon.lavadero.repositories.PlanRepository;
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
@RequestMapping("/api/plans")
public class PlanController {
    @Autowired
    private PlanRepository planrepo;
    
    @GetMapping("/all")
    public List<Plan>getAllPlans(){
        return planrepo.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Long id){
        Optional<Plan>plan = planrepo.findById(id);
        return plan.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/create")
    public ResponseEntity<Plan>createPlan(@RequestBody Plan plan){
        Plan savePlan = planrepo.save(plan);
        return ResponseEntity.status(HttpStatus.CREATED).body(savePlan);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>deletePlan(@PathVariable Long id){
        if(!planrepo.existsById(id)){
            return ResponseEntity.notFound().build();
        }else{
            planrepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Plan> updatePlan(@PathVariable Long id, @RequestBody Plan updatedPlan){
        
        if(!planrepo.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        updatedPlan.setId(id);
        Plan savedPlan = planrepo.save(updatedPlan);
        return ResponseEntity.ok(savedPlan);
    }
}
