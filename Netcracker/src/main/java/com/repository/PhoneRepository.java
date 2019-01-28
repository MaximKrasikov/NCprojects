package com.repository;

import com.entities.Model_Char;
import com.entities.Phones;
import com.entities.Pictures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 02.12.2018.
 */
@Repository
public interface PhoneRepository extends JpaRepository<Phones,Long>{

    Phones findByPrice(Double price);
    Phones findByColor(String color);
    Phones findByModel(Model_Char model);
    @Query(value="SELECT Pictures.picture FROM Pictures WHERE Pictures.model like %?1%",nativeQuery = true)
    Pictures findByPhone(Phones phone);
    List<Phones> findListByModel(Model_Char model);
}
