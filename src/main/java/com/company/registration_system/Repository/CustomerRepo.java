package com.company.registration_system.Repository;

import com.company.registration_system.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerRepo {

    @Autowired
    JdbcTemplate template;

    private String database = "heroku_47fd00a889de629.customers";
    private String query;

    public Customer getCustomer(String serial) {

        query = "SELECT * FROM " +database+ " WHERE serial=?";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return  template.queryForObject(query, rowMapper, serial);

    }
}
