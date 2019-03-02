package com.repository;

import com.entities.Model_Char;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by Admin on 07.12.2018.
 */
@RepositoryRestResource
public interface ModelRepository extends JpaRepository<Model_Char,Long> {
    List<Model_Char> findByName(String name);
}
