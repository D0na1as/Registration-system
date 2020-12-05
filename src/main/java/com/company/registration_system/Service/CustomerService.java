package com.company.registration_system.Service;

import com.company.registration_system.Model.Customers;
import com.company.registration_system.Repository.CustomerRepoCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepoCRUD customerRepo;

    public Customers getCustomer(String serial) {
        return customerRepo.findBySerial(serial);
    }

    public List<String> getDates(String specialist) {
        return customerRepo.queryGetDates(specialist);
    }

    //Get Specialist customers
    public List<Customers> getCustomers(String name) {
        return customerRepo.queryGetCustomers(name);
    }
    public List<Customers> getCustomersByDate(String name, String date) {
        return customerRepo.queryGetCustomersByDate(name, date);
    }

    public void registerCustomer(Customers customer) {
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


}
