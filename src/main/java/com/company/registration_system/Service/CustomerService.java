package com.company.registration_system.Service;

import com.company.registration_system.Model.Customer;
import com.company.registration_system.Model.Specialist;
import com.company.registration_system.Repository.CustomerRepoCRUD;
import com.company.registration_system.Utilities.Methods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepoCRUD customerRepo;

    public Customer getCustomer(String serial) {
        return customerRepo.findBySerial(serial);
    }

    public List<String> getDates(String specialist) {
        return customerRepo.queryGetDates(specialist);
    }

    //Get Specialist customers
    public List<Customer> getCustomers(String name) {
        return customerRepo.queryGetCustomers(name);
    }

    public List<Customer> getCustomersByDate(String name, String date) {
        return customerRepo.queryGetCustomersByDate(name, date);
    }
    public HashMap<String, List<Customer>> getUpcomingCustomers(List<Specialist> specialists) {
        HashMap<String, List<Customer>> upcomingCustomers = new HashMap<>();
        for (Specialist specialist:specialists) {
            String a = specialist.getName();
            String b = Methods.getDate();
            List<Customer> customers = customerRepo.queryGetCustomersByDate(specialist.getName(), Methods.getDate());
            List<Customer> customersId = new ArrayList<Customer>();
            int i = 0;
            do {
                if (customers!=null && customers.size()>i) {
                    if (customers.get(i).getStatus()==0){
                        customersId.add(customers.get(i));
                    }
//                    else {
//                        customersId.add(new Customer(0));
//                    }
//                } else {
//                    customersId.add(new Customer(0));
                } else {
                    customersId.add(new Customer(0));
                }
                i++;
            } while (customersId.size()<5);
            upcomingCustomers.put(specialist.getName(), customersId);
        }
        return upcomingCustomers;
    }


    public HashMap<String, String> getCurrentCustomers(List<Specialist> specialists) {
        HashMap<String, String> customers = new HashMap<>();
        for (Specialist specialist:specialists) {
            if (!specialist.getStatus().equals("0")) {
                customers.put(specialist.getName(), customerRepo.queryGetID(specialist.getStatus()));
            } else {
                customers.put(specialist.getName(), "--");
            }
        }
        return customers;
    }


    //Save Customer
    public void registerCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    public void startMeeting(String time, String serial) {
        customerRepo.queryUpStart(time, serial);
    }

    public void endMeeting(String time, String serial) {
        customerRepo.queryUpEnd(time, serial);
    }

    public void cancelMeeting(String serial) {
        customerRepo.queryUpCancel(serial);
    }

    public void saveCustomer(Customer customer){
        customerRepo.save(customer);
    }

    public List<String> getTimes(String specialist, String date){
        return customerRepo.queryGetTimes(specialist, date);
    }
}
