package com.company.registration_system.Repository;

import com.company.registration_system.Model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepoCRUD extends CrudRepository<Customer, Integer> {

    String customersTable = "customers";
    String queryBySerial = "SELECT id, serial, status, specialist, time FROM " + customersTable + " WHERE serial = ?1";
    String queryGetCustomer = "SELECT * FROM " + customersTable + " WHERE specialist = ?1";

    @Query(nativeQuery = true, value = queryBySerial)
    Customer findBySerial(String serial);

    @Query(nativeQuery = true, value = queryGetCustomer)
    Customer queryGetCustomer(String specialist);
}
