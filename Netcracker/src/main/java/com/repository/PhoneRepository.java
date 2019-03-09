package com.repository;

import com.entities.Model_Char;
import com.entities.Phones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 02.12.2018.
 */
@Repository
public interface PhoneRepository extends JpaRepository<Phones,Long>{

    Phones findByModel(Model_Char model);

    @Query(value="SELECT * FROM Phones WHERE Phones.id like %?1%",nativeQuery = true)
    Phones findPhoneById(Long phone);
}
