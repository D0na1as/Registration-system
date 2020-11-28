package com.company.registration_system.Service;

import com.company.registration_system.Model.Specialist;
import com.company.registration_system.Repository.SpecialistRepoCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialistService {

    @Autowired
    SpecialistRepoCRUD specialistRepo;

    public Specialist getSpecialist(String name) {

        return specialistRepo.findByName(name);
    }
}
