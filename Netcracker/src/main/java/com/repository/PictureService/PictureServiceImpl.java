package com.repository.PictureService;

import com.entities.Phones;
import com.entities.Pictures;
import com.repository.PictureRepository;
import com.util.ImageUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


/**
 * Created by Admin on 03.12.2018.
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public Pictures addPictures(Pictures picture) {
        Pictures savedPicture = pictureRepository.saveAndFlush(picture);
        return savedPicture;
    }

    @Override
    public List<Pictures> findAllPictures() {
        List<Pictures> pictures = pictureRepository.findAll();
        return pictures;
    }

    //loading image
    //URISyntaxException
    public byte[] loadImage(String filePath) throws IOException, URISyntaxException {
        try {
        //return Files.readAllBytes(Paths.get(this.getClass().getResource(filePath).toURI()));
            return IOUtils.toByteArray(ImageUtil.class.getResourceAsStream(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String useImageFromBase(String filePath) throws IOException, URISyntaxException {
        byte[] image = loadImage(filePath);
        String file = Base64.getEncoder().encodeToString(image);
        return file;
    }

    public void save(Pictures picture) {
        pictureRepository.save(picture);
    }

    public Phones searchForPicturesList(List<Pictures> pictures, Phones phone) {
        List<Pictures> picListForPhone = new ArrayList<>();
        for (Pictures picture : pictures) {
            if (picture.getModel().getId() == phone.getModel().getId() && picture.getColor() == phone.getColor()) {
                picListForPhone.add(picture);
            }
        }
        phone.setPictures(picListForPhone);
        return phone;
    }
}
