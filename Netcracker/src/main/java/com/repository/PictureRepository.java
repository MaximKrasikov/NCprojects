package com.repository;

import com.entities.Pictures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 03.12.2018.
 */
@Repository
public interface PictureRepository extends JpaRepository<Pictures,Long> {
    List<Pictures> findAllById(Long modelId);
    @Query(value="SELECT * FROM PICTURES p where p.name like %?1%",nativeQuery = true)//%?1%
    List<Pictures> findByModel(String modelName);
    //List<Pictures> findListByPhone(Long phoneId);
}

