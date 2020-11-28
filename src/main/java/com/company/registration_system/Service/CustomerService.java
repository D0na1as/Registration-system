package com.company.registration_system.Service;

import com.company.registration_system.Model.Customer;
import com.company.registration_system.Repository.CustomerRepoCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepoCRUD customerRepo;

    public Customer getCustomer(String serial) {

        return customerRepo.findBySerial(serial);
    }
    public Customer getBySpecialist(String name) {

        return customerRepo.queryGetCustomer(name);
    }

}
