package com.company.registration_system.Controller;

import com.company.registration_system.Model.Customer;
import com.company.registration_system.Model.Specialist;
import com.company.registration_system.Service.CustomerService;
import com.company.registration_system.Service.SpecialistService;
import com.company.registration_system.Service.TimeService;
import com.company.registration_system.Utilities.Methods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    //Screen username
    private final String screen = "screen";

    @GetMapping("/login")
    public String login() {
        return "specialist/login";
    }
    @GetMapping("/logout")
    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "specialist/login";
    }

    @RequestMapping(value = "/specialist", method = RequestMethod.GET)
    public String loginSpecialist(Model model) {

        String name = getName();

        if (name.equals(screen)) {

            List<Specialist> specialists = specialistService.getList();
            HashMap<String, String> currentCustomers = customerService.getCurrentCustomers(specialists);
            HashMap<String, List<Customer>> nextCustomers = customerService.getUpcomingCustomers(specialists);
            model.addAttribute("specialists", specialists);
            model.addAttribute("currentCustomer", currentCustomers);
            model.addAttribute("nextCustomers", nextCustomers);
            return "screen/screen";

        } else {

            Specialist specialist = specialistService.getSpecialist(name);
            List<String> dates = customerService.getDates(name);

            if (specialist != null) {
                if (!specialist.getStatus().equals("0")) {
                    String onGoing = customerService.getCustomer(specialist.getStatus()).getDate();
                    List<Customer> customers = customerService.getCustomersByDate(name, onGoing);
                    Methods.sortTime(customers);
                    model.addAttribute("onGoing", onGoing);
                    model.addAttribute("customers", customers);
                } else {
                    List<Customer> customers = customerService.getCustomersByDate(name, Methods.getDate());
                    Methods.sortTime(customers);
                    model.addAttribute("customers", customers);
                }
                model.addAttribute("specialist", specialist);
                model.addAttribute("date", Methods.getDate());

                return "specialist/specialist";
            } else {
                return "specialist/login";
            }
        }
    }

    @RequestMapping(value = "/{date}", method = RequestMethod.GET)
    public String specialistDate(@PathVariable("date") String date, Model model) {

        String name = getName();

        Specialist specialist = specialistService.getSpecialist(name);
        List<Customer> customers = customerService.getCustomersByDate(name, date);
        customers = Methods.sortTime(customers);
        model.addAttribute("specialist", specialist);
        model.addAttribute("date", date);
        model.addAttribute("customers", customers);

        return "specialist/specialist";
    }

    @RequestMapping(value = "/{serial}/start", method = RequestMethod.GET)
    public String specialistStart(@PathVariable("serial") String serial, Model model) {

        String name = getName();

        customerService.startMeeting(Methods.getTime(), serial);
        specialistService.status(serial, name);
        String date = customerService.getCustomer(serial).getDate();
        Specialist specialist = specialistService.getSpecialist(name);
        List<String> dates = customerService.getDates(name);

        model.addAttribute("specialist", specialist);
        model.addAttribute("dates", dates);
        model.addAttribute("customers", customerService.getCustomersByDate(name, date));

        return "redirect:/specialist/" + date;
    }

    @RequestMapping(value = "/{serial}/end", method = RequestMethod.GET)
    public String specialistEnd(@PathVariable("serial") String serial, Model model) {

        String name = getName();

        customerService.endMeeting(Methods.getTime(), serial);
        String date = customerService.getCustomer(serial).getDate();
        Specialist specialist = specialistService.getSpecialist(name);
        List<String> dates = customerService.getDates(name);
        specialistService.status("0", name);

        model.addAttribute("specialist", specialist);
        model.addAttribute("dates", dates);
        model.addAttribute("customers", customerService.getCustomersByDate(name, date));

        return "redirect:/specialist/" + date;
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public String specialistCancel(@RequestParam("serial") String serial, Model model) {

        String name = getName();

        String date = customerService.getCustomer(serial).getDate();
        Specialist specialist = specialistService.getSpecialist(name);
        List<String> dates = customerService.getDates(name);
        customerService.cancelMeeting(serial);

        model.addAttribute("specialist", specialist);
        model.addAttribute("dates", dates);
        model.addAttribute("customers", customerService.getCustomersByDate(name, date));

        return "redirect:/specialist/" + date;
    }

    private static String getName() {
        final String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return name;
    }

}
