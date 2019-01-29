package com.repository;

import com.entities.Model_Char;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Admin on 07.12.2018.
 */
@Repository
public interface ModelRepository extends JpaRepository<Model_Char,Long> {
    List<Model_Char> findByName(String name);
    List<Model_Char> findByDiagonal(Double diagonal);
    List<Model_Char> findBySize(Double size);
    List<Model_Char> findByDescription(String descroptional);
    Model_Char findModelByName(String name);
    Model_Char findModelById(Long model_Id);
}
