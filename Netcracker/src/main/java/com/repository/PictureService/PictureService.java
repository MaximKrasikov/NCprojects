package com.repository.PictureService;

import com.entities.Pictures;

import java.util.List;

/**
 * Created by Admin on 03.12.2018.
 */
// для реализации своих методов
public interface PictureService {
    public List<Pictures> findAllPictures();

    public Pictures addPictures(Pictures picture);

    //void deletePictureFromPhones(Phones picture);

}
