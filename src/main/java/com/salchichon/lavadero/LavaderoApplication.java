package com.salchichon.lavadero;

import com.salchichon.lavadero.models.Service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LavaderoApplication {

    static ArrayList<Service> listService;

    public static void main(String[] args) {
        listService = new ArrayList<>();
        SpringApplication.run(LavaderoApplication.class, args);
    }

}
