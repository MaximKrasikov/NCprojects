package com.repository;

import com.entities.Pictures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 03.12.2018.
 */
@Repository
public interface PictureRepository extends JpaRepository<Pictures,Long> {
}

