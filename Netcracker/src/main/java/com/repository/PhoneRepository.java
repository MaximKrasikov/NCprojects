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
    Phones findByModel(Model_Char model);

    @Query(value="SELECT * FROM Phones WHERE Phones.id like %?1%",nativeQuery = true)
    List<Phones> findListByPhone(Long phoneId);

    @Query(value="SELECT * FROM Phones WHERE Phones.id like %?1%",nativeQuery = true)
    List<Phones> findListByListId(List<Long> phoneId);

    List<Phones> findListByModel(Model_Char model);

    @Query(value="SELECT * FROM Phones WHERE Phones.id like %?1%",nativeQuery = true)
    Phones findPhoneById(Long phone);
}
