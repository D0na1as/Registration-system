package com.company.registration_system.Repository;

import com.company.registration_system.Model.Customers;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CustomerRepoCRUD extends CrudRepository<Customers, Integer> {

    String table = "customers";

    //Select queries
    String queryBySerial = "SELECT id, serial, status, specialist, time FROM " + table + " WHERE serial = ?";
    String queryGetCustomers = "SELECT * FROM " + table + " WHERE specialist = ? AND date>=CAST( now() AS Date );";
    String queryGetCustomersByDate = "SELECT * FROM " + table + " WHERE specialist = ? AND date=? ;";
    String queryGetDates = "SELECT DISTINCT date FROM " + table + " WHERE specialist=? ORDER BY date;";

    //Update queries
    String queryUpStart = "UPDATE " + table + " SET start = ?, status = 1 WHERE serial=?";
    String queryUpEnd = "UPDATE " + table + " SET end = ?, status = 2 WHERE serial=? ";
    String queryUpCancel = "UPDATE " + table + " SET status = 3 WHERE serial=? ";

    //Select queries
    @Query(nativeQuery = true, value = queryBySerial)
    Customers findBySerial(String serial);

    @Query(nativeQuery = true, value = queryGetCustomers)
    List<Customers> queryGetCustomers(String specialist);

    @Query(nativeQuery = true, value = queryGetCustomersByDate)
    List<Customers> queryGetCustomersByDate(String specialist, String date);

    @Query(nativeQuery = true, value = queryGetDates)
    List<String> queryGetDates(String specialist);

    //Update queries
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = queryUpStart)
    void queryUpStart(String time, String serial);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = queryUpEnd)
    void queryUpEnd(String time, String serial);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = queryUpCancel)
    void queryUpCancel(String serial);

}
