package com.company.registration_system.Repository;

import com.company.registration_system.Model.Specialist;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SpecialistRepoCRUD extends CrudRepository<Specialist, Integer> {

    String table = "employees";
    String queryGetNames = "SELECT id, name, status FROM " + table;
    String queryGetAccount = "SELECT id, name, status FROM " + table + " WHERE name=? and password=?";
    //Update queries
    String queryUpStart = "UPDATE " + table + " SET status = ? WHERE name =? ";

    @Query(nativeQuery = true, value = queryGetNames)
        List<Specialist> getList();

    @Query(nativeQuery = true, value = queryGetAccount)
        Specialist getSpecialist(String name, String password);

    //Update
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = queryUpStart)
    void queryUpStatus(String status, String name);




}
