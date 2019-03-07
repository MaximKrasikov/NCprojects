package com.rest;

import com.entities.Model_Char;
import com.repository.ModelRepository;
import com.repository.PhoneService.PhoneService;
import com.restentities.ModelForRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 03.03.2019.
 */
@RestController
public class ModelRest {
    @Autowired
    ModelRepository modelRepository;
    @Autowired
    PhoneService phoneService;

    //главный магазин запрашивает модель
    @RequestMapping(value = "/models", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.ALL_VALUE )
    public ResponseEntity<List<ModelForRest>> listAllModels(){
        Iterable<Model_Char> allModels = modelRepository.findAll();
        //сущности для отправки
        List<ModelForRest> modelsForRest = new ArrayList<>();
        for (Model_Char e:allModels) {
            ModelForRest eForRest = new ModelForRest(e);
            modelsForRest.add(eForRest);
        }
        return new ResponseEntity<List<ModelForRest>>(modelsForRest, HttpStatus.OK);
    }

}
