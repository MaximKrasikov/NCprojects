package com.repository;

import com.restentities.ModelForRest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Admin on 04.03.2019.
 */
@RepositoryRestResource
public interface ModelRepositoryForRest extends JpaRepository<ModelForRest,Long> {
}
