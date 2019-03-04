package com.repository;

import com.restentities.ModelForRest;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Admin on 04.03.2019.
 */
public interface ModelRepositoryForRest extends JpaRepository<ModelForRest,Long> {
}
