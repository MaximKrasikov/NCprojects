package com.repository;

import com.entities.Model_Char;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 07.12.2018.
 */
@Repository
public interface ModelRepository extends JpaRepository<Model_Char,Long> {
    List<Model_Char> findByName(String name);

    @Query(value="SELECT * FROM MODEL_CHAR WHERE Model_Char.id like %?1%",nativeQuery = true)
    Model_Char findModelById(Long modelId);
}
