package com.company.registration_system.Controller;

import com.company.registration_system.Model.Customer;
import com.company.registration_system.Model.Specialist;
import com.company.registration_system.Model.Time;
import com.company.registration_system.Service.CustomerService;
import com.company.registration_system.Service.SpecialistService;
import com.company.registration_system.Service.TimeService;
import com.company.registration_system.Utilities.Methods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@Controller
@SessionAttributes({"customer"})
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
        return "customer/front";
    }

    @RequestMapping(value = "/booking", method = RequestMethod.GET)
    public String bookAppointment(Model model) {

        List<Specialist> specialists = specialistService.getList();
        Customer customer = new Customer();
        model.addAttribute("specialists", specialists);
        model.addAttribute("customer", customer);

        return "customer/booking";
    }

    @RequestMapping(value = "/specialist", method = RequestMethod.GET)
    public String searchSpecialist(@RequestParam("specialist") String name,
                                   Model model,
                                   @ModelAttribute("customer") Customer customer) {

        List<String> dates = Methods.getDatesList(20);
        customer.setSpecialist(name);
        model.addAttribute("dates", dates);
        model.addAttribute("customer", customer);

        return "customer/booking";
    }

    @RequestMapping(value = "/date", method = RequestMethod.GET)
    public String searchDate(@RequestParam("date") String date,
                             Model model,
                             @ModelAttribute("customer") Customer customer) throws ParseException {

        List<Time> allTimes;

        if (date.equals(Methods.getDate())) {
            allTimes = timeService.getTimesOfToday(Methods.getTime());
        } else {
            allTimes = timeService.getTimes();
        }
        List<String> occupTimes = customerService.getTimes(customer.getSpecialist(), date);
        if (occupTimes.size() > 0) {
            Methods.filterTimes(allTimes, occupTimes, date);
        }

        model.addAttribute("times", allTimes);
        model.addAttribute("customer", customer);

        return "customer/booking";
    }

    @RequestMapping(value = "/time", method = RequestMethod.GET)
    public String searchTime(@RequestParam("time") String time,
                             Model model,
                             @ModelAttribute("customer") Customer customer) {

        String timeLeft = Methods.timeLeft(customer.getDate(), customer.getTime());
        model.addAttribute("customer", customer);
        model.addAttribute("timeLeft", timeLeft);

        return "customer/booking";
    }

    @RequestMapping("/booking")
    public String book() {
        return "customer/booking";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String customerAccount(Model model,
                                  @ModelAttribute("customer") Customer customer) {

        String serial = Methods.genSerial();
        if (customerService.getCustomer(serial) == null) {
            customer.setSerial(serial);
            customerService.saveCustomer(customer);
            customer = customerService.getCustomer(serial);
        }
        String timeLeft = Methods.timeLeft(customer.getDate(), customer.getTime());
        model.addAttribute("timeLeft", timeLeft);
        model.addAttribute("customer", customer);

        return "customer/customer";
    }

    @RequestMapping(value = "/check/booking", method = RequestMethod.GET)
    public String checkBooking(@RequestParam("serial") String serial, Model model) {

        Customer customer = customerService.getCustomer(serial);
        if (customer != null) {
            String timeLeft = Methods.timeLeft(customer.getDate(), customer.getTime());
            model.addAttribute("timeLeft", timeLeft);
            model.addAttribute("customer", customer);
            return "customer/customer";
        } else {
            model.addAttribute("serial", serial);
            return "customer/check";
        }
    }

    @RequestMapping("/check")
    public String check() {

        return "customer/check";
    }

    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    public String specialistCancel(@RequestParam("serial") String serial, Model model) {

        customerService.cancelMeeting(serial);
        Customer customer = customerService.getCustomer(serial);
        model.addAttribute("customer", customer);

        return "/customer/customer";
    }
}
