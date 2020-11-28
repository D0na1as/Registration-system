package com.company.registration_system.Repository;

import com.company.registration_system.Model.Specialist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialistRepoCRUD   extends CrudRepository<Specialist, Integer> {

    String specialistsTable = "employees";
    String queryByName = "SELECT id, status, name FROM " + specialistsTable + " WHERE name = ?1";

    @Query(nativeQuery = true, value = queryByName)
    Specialist findByName(String name);


}
