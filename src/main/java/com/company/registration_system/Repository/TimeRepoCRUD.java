package com.company.registration_system.Repository;

import com.company.registration_system.Model.Time;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeRepoCRUD extends CrudRepository<Time, Integer> {

    String table = "times";
    String queryGetTimesToday = "SELECT * FROM " + table + " where time>=?";
    String queryGetTimes = "SELECT * FROM " + table;

    @Query(nativeQuery = true, value = queryGetTimesToday)
    List<Time> getTimesOfToday(String currentTime);

    @Query(nativeQuery = true, value = queryGetTimes)
    List<Time> getTimes();

}
