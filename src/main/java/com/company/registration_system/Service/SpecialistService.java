package com.company.registration_system.Service;

import com.company.registration_system.Model.Specialist;
import com.company.registration_system.Repository.SpecialistRepoCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialistService {

    @Autowired
    SpecialistRepoCRUD specialistRepo;

    public List<Specialist> getList() {
        return specialistRepo.getList();
    }

    public Specialist getSpecialist(String name) {
        return specialistRepo.getSpecialist(name);
    }

    public void status(String serial, String name) {
        specialistRepo.queryUpStatus(serial, name);
    }
}
