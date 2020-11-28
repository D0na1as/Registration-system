package com.company.registration_system.Controller;

import com.company.registration_system.Service.CustomerService;
import com.company.registration_system.Service.SpecialistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    SpecialistService specialistService;


    @GetMapping("/")
    public String index() {

        return customerService.getCustomer("123").getSpecialist();
    }

    @GetMapping("/specialist")
    public String getSpecialist() {

        return specialistService.getSpecialist("Hansas").getName();
    }

    @GetMapping("/customer")
    public String getCustomer() {

        return customerService.getBySpecialist("Vilius").getSerial();
    }
}
