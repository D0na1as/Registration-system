package com.company.registration_system.Controller;

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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginSpecialist(@RequestParam String name, @RequestParam String password, Model model) {

        Specialist specialist = specialistService.getSpecialist(name, password);
        if (specialist != null) {
            List<String> dates = customerService.getDates(name);
            System.out.println(specialist.getStatus());
            model.addAttribute("specialist", specialist);
            model.addAttribute("dates", dates);
            model.addAttribute("customers", customerService.getCustomersByDate(name, dates.get(0)));
            return "specialist/specialist";
        } else {
            return "specialist/login";
        }
    }

    @RequestMapping(value = "/{date}", method = RequestMethod.GET)
    public String specialistDate(@PathVariable("date") String date, Model model) {
        String name = "Hansas";
        String password = "123";
        Specialist specialist = specialistService.getSpecialist(name, password);
        List<String> dates = customerService.getDates(name);
        model.addAttribute("specialist", specialist);
        model.addAttribute("dates", dates);
        model.addAttribute("customers", customerService.getCustomersByDate(name, date));

        return "specialist/specialist";
    }

    @RequestMapping(value = "/{date}/start", method = RequestMethod.GET)
    public String specialistStart(@PathVariable("date") String date, Model model) {
        String name = "Hansas";
        String password = "123";
        Specialist specialist = specialistService.getSpecialist(name, password);
        List<String> dates = customerService.getDates(name);
        customerService.startMeeting(Methods.getTime(), customerService.getCustomersByDate(name, date).get(0).getSerial());
        specialistService.status(customerService.getCustomersByDate(name, date).get(0).getSerial(), name);
        model.addAttribute("specialist", specialist);
        model.addAttribute("dates", dates);
        model.addAttribute("customers", customerService.getCustomersByDate(name, date));

        return "redirect:/specialist/" + date;
    }

    @RequestMapping(value = "/{date}/end", method = RequestMethod.GET)
        public String specialistEnd(@PathVariable("date") String date, Model model) {
            String name = "Hansas";
            String password = "123";
            Specialist specialist = specialistService.getSpecialist(name, password);
            List<String> dates = customerService.getDates(name);
            customerService.endMeeting(Methods.getTime(), customerService.getCustomersByDate(name, date).get(0).getSerial());
            specialistService.status("0", name);
            model.addAttribute("specialist", specialist);
            model.addAttribute("dates", dates);
            model.addAttribute("customers", customerService.getCustomersByDate(name, date));

            return "redirect:/specialist/" + date;
        }

    @RequestMapping(value = "/{date}/cancel", method = RequestMethod.GET)
        public String specialistCancel(@PathVariable("date") String date, Model model) {
            String name = "Hansas";
            String password = "123";
            Specialist specialist = specialistService.getSpecialist(name, password);
            List<String> dates = customerService.getDates(name);
            customerService.cancelMeeting(customerService.getCustomersByDate(name, date).get(0).getSerial());
            model.addAttribute("specialist", specialist);
            model.addAttribute("dates", dates);
            model.addAttribute("customers", customerService.getCustomersByDate(name, date));

            return "redirect:/specialist/" + date;
        }

}
