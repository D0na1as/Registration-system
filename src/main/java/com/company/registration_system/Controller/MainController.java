package com.company.registration_system.Controller;

import com.company.registration_system.Service.CustomerService;
import com.company.registration_system.Service.SpecialistService;
import com.company.registration_system.Service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    CustomerService customerService;
    @Autowired
    SpecialistService specialistService;
    @Autowired
    TimeService timeService;

    @GetMapping("/")
    public String index() {

        //return customerService.getCustomer("123").getSpecialist();
        return "index";
    }
//
//    @GetMapping("/customer")
//    public String getSpecialist() {
//
//        //return specialistService.getSpecialist("Hansas").getName();
//        return "Customer/front";
//    }

//    @GetMapping("/booking")
//        public String book() {
//            return "Customer/booking";
//        }

    @PostMapping(value="/register")
    public String printUserGreeting(Model model) {

        return "index";
    }

//    @GetMapping("/customer")
//    public String getCustomer() {
//
//        return customerService.getBySpecialist("Vilius").getSerial();
//    }
}
