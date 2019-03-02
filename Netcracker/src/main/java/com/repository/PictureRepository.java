package com.repository;

import com.entities.Pictures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Admin on 03.12.2018.
 */
@RepositoryRestResource
public interface PictureRepository extends JpaRepository<Pictures,Long> {
}

