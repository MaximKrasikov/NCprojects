package com.repository.RepositoryForRest;

import com.entities.Model_Char;
import com.entities.entity_rest.ModelForRest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ModelRepositoryForRest extends JpaRepository<ModelForRest,Long> {

}
