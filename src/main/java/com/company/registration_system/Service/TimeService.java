package com.company.registration_system.Service;

import com.company.registration_system.Model.Time;
import com.company.registration_system.Repository.TimeRepoCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeService {

    @Autowired
    TimeRepoCRUD timeRepo;

    public List<Time> getTimes() {
        return timeRepo.getTimes();
    }
}
