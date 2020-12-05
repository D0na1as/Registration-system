package com.company.registration_system.Controller;

import com.company.registration_system.Model.Specialist;
import com.company.registration_system.Model.Time;
import com.company.registration_system.Service.CustomerService;
import com.company.registration_system.Service.SpecialistService;
import com.company.registration_system.Service.TimeService;
import com.company.registration_system.Utilities.Methods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/customer")
public class CustomerController {

        @Autowired
        CustomerService customerService;
        @Autowired
        SpecialistService specialistService;
        @Autowired
        TimeService timeService;

        @RequestMapping("/")
        public String login() {
            return "Customer/front";
        }

        @RequestMapping(value = "/booking", method = RequestMethod.GET)
        public String bookAppointment(Model model) {
                if (model.getAttribute("specialists")==null) {
                        List<Specialist> specialists = specialistService.getList();
                        String name = "name";
                        //List<String> dates = Methods.getDatesList(20);
                        //List<Time> time = timeService.getTimes();

                        model.addAttribute("specialists", specialists);
                        model.addAttribute("name", name);
                }

                //model.addAttribute("dates", dates);
                //model.addAttribute("times", time);

                return "customer/booking";
        }

        @RequestMapping(value = "/{name}", method = RequestMethod.GET)
        public String searchSpecialist(@PathVariable("name") String name, Model model){
                List<Specialist> specialists = new ArrayList<Specialist>();
                specialists.add(specialistService.getSpecialist(name, "123"));
                List<String> dates = Methods.getDatesList(20);
                model.addAttribute("specialists", specialists);
                model.addAttribute("dates", dates);
                model.addAttribute("name", name);

                return "customer/booking";
        }

        @RequestMapping(value = "/{specialist.name}/{date}", method = RequestMethod.GET)
        public String searchDate(@PathVariable("specialist.name") String name, @PathVariable("date") String date,  Model model) {

                model.addAttribute("name", name);
                List<Specialist> specialists = new ArrayList<Specialist>();
                List<String> dates = new ArrayList<String>();
                List<Time> times = timeService.getTimes();
                specialists.add(specialistService.getSpecialist(name, "123"));
                dates.add(date);
                model.addAttribute("times", times);
                model.addAttribute("specialists", specialists);
                model.addAttribute("dates", dates);

                return "customer/booking";
        }

        @RequestMapping(value = "/{specialist}/{date}/{time}", method = RequestMethod.GET)
        public String searchTime(@PathVariable("time") String date, Model model) {

                return "customer/customer";
        }


        @RequestMapping("/booking")
        public String book() {
            return "Customer/booking";
        }


}
