package com.repository.PictureService;

import com.entities.Phones;
import com.entities.Pictures;
import com.repository.ModelService.ModelServiceImpl;
import com.repository.PhoneService.PhoneServiceImpl;
import com.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    @Autowired
    private PhoneServiceImpl phoneRepository;
    @Autowired
    private ModelServiceImpl modelRepository;


    public PictureServiceImpl(PictureRepository repository) {
        this.pictureRepository = repository;
    }

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

    public byte[] loadImage(String filePath) throws URISyntaxException, IOException {
        return Files.readAllBytes(Paths.get(this.getClass().getResource(filePath).toURI()));
    }

    public String useImageFromBase(String filePath) throws IOException, URISyntaxException {
        byte[] image = loadImage(filePath);
        String file = Base64.getEncoder().encodeToString(image);
        return file;
    }

    public void getByteFromPicture(Phones phone) {
        phone.getPictures().forEach(Pictures::getBytes);
    }

    public void save(Pictures picture) {
        pictureRepository.save(picture);
    }

    public List<Pictures> findByCount(Long modelId) {//возврат листа картинок по id телефона
        return pictureRepository.findAllById(modelId);
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
