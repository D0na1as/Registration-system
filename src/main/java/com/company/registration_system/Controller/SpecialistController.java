package com.company.registration_system.Controller;

import com.company.registration_system.Model.Customer;
import com.company.registration_system.Model.Specialist;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/specialist")
public class SpecialistController {

    @Autowired
    CustomerService customerService;
    @Autowired
    SpecialistService specialistService;
    @Autowired
    TimeService timeService;

    @RequestMapping("/")
    public String login() {
        return "specialist/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginSpecialist(@RequestParam String name, @RequestParam String password, Model model) {

        String screen = "screen";

        if (name.equals(screen)) {

            List<Specialist> specialists = specialistService.getList();
            HashMap<String, String> currentCustomers = customerService.getCurrentCustomers(specialists);
            HashMap<String, List<Customer>> nextCustomers = customerService.getUpcomingCustomers(specialists);
            model.addAttribute("specialists", specialists);
            model.addAttribute("currentCustomer", currentCustomers);
            model.addAttribute("nextCustomers", nextCustomers);
            return "screen/screen";
        } else {

            Specialist specialist = specialistService.getSpecialist(name, password);
            List<String> dates = customerService.getDates(name);

            if (specialist != null) {
                if (!specialist.getStatus().equals("0")) {
                    String onGoing = customerService.getCustomer(specialist.getStatus()).getDate();
                    List<Customer> customers = customerService.getCustomersByDate(name, onGoing);
                    customers = Methods.sortTime(customers);
                    model.addAttribute("onGoing", onGoing);
                    model.addAttribute("customers", customers);
                } else {
                    List<Customer> customers = customerService.getCustomersByDate(name, Methods.getDate());
                    customers = Methods.sortTime(customers);
                    model.addAttribute("customers", customers);
                }
                model.addAttribute("specialist", specialist);
                model.addAttribute("dates", dates);

                return "specialist/specialist";
            } else {
                return "specialist/login";
            }
        }
    }

    @RequestMapping(value = "/{date}", method = RequestMethod.GET)
    public String specialistDate(@PathVariable("date") String date, Model model) {
        String name = "Hansas";
        String password = "123";
        Specialist specialist = specialistService.getSpecialist(name, password);
        List<String> dates = customerService.getDates(name);
        List<Customer> customers = customerService.getCustomersByDate(name, date);
        customers = Methods.sortTime(customers);
        model.addAttribute("specialist", specialist);
        model.addAttribute("dates", dates);
        model.addAttribute("customers", customers);

        return "specialist/specialist";
    }

    @RequestMapping(value = "/{serial}/start", method = RequestMethod.GET)
    public String specialistStart(@PathVariable("serial") String serial, Model model) {
        String name = "Hansas";
        String password = "123";

        customerService.startMeeting(Methods.getTime(), serial);
        specialistService.status(serial, name);
        String date = customerService.getCustomer(serial).getDate();
        Specialist specialist = specialistService.getSpecialist(name, password);
        List<String> dates = customerService.getDates(name);

        model.addAttribute("specialist", specialist);
        model.addAttribute("dates", dates);
        model.addAttribute("customers", customerService.getCustomersByDate(name, date));

        return "redirect:/specialist/" + date;
    }

    @RequestMapping(value = "/{serial}/end", method = RequestMethod.GET)
        public String specialistEnd(@PathVariable("serial") String serial, Model model) {
            String name = "Hansas";
            String password = "123";

            customerService.endMeeting(Methods.getTime(), serial);
            String date = customerService.getCustomer(serial).getDate();
            Specialist specialist = specialistService.getSpecialist(name, password);
            List<String> dates = customerService.getDates(name);
            specialistService.status("0", name);

            model.addAttribute("specialist", specialist);
            model.addAttribute("dates", dates);
            model.addAttribute("customers", customerService.getCustomersByDate(name, date));

            return "redirect:/specialist/" + date;
        }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public String specialistCancel(@RequestParam("serial") String serial, Model model) {
            String name = "Hansas";
            String password = "123";

            String date = customerService.getCustomer(serial).getDate();
            Specialist specialist = specialistService.getSpecialist(name, password);
            List<String> dates = customerService.getDates(name);
            customerService.cancelMeeting(serial);

            model.addAttribute("specialist", specialist);
            model.addAttribute("dates", dates);
            model.addAttribute("customers", customerService.getCustomersByDate(name, date));

            return "redirect:/specialist/" + date;
        }

}
