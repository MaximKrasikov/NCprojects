package com.repository.PictureService;

import com.entities.Model_Char;
import com.entities.Pictures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repository.PictureRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;


/**
 * Created by Admin on 03.12.2018.
 */
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureRepository repository;

    public PictureServiceImpl(PictureRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pictures addPictures(Pictures picture) {
        Pictures savedPicture = repository.saveAndFlush(picture);
        return savedPicture;
    }

    @Override
    public void deletePictureFromModel(Model_Char model) {
        List<Pictures> listOfPictures= repository.findListByModel(model);// находит телефоны по имени модели
        for(Pictures picture:listOfPictures){
            repository.delete(picture);
        }
    }

    @Override
    public List<Pictures> findAllPictures(){
        List<Pictures> pictures= repository.findAll();
        return  pictures;
    }

    public byte[] loadImage(String filePath) throws URISyntaxException, IOException {
        return Files.readAllBytes(Paths.get(this.getClass().getResource(filePath).toURI()));
    }

    public String useImageFromBase(String filePath) throws IOException, URISyntaxException {
        byte [] image= loadImage(filePath);
        String file= Base64.getEncoder().encodeToString(image);
        return file;
    }

    public void save(Pictures picture) {
        repository.save(picture);
    }
}
