package com.repository.RepositoryForRest;

import com.entities.entity_rest.ModelForRest;
import com.entities.entity_rest.PhoneForRest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PhoneRepositoryForRest extends JpaRepository<PhoneForRest,Long> {
}
