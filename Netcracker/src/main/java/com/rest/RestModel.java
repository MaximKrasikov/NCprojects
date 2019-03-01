package com.rest;

import com.entities.Model_Char;
import com.repository.ModelService.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by Admin on 01.03.2019.
 */
@RestController
public class RestModel {
    @Autowired
    ModelService modelService;
    //-------------------Retrieve All Model--------------------------------------------------------

    @RequestMapping(value = "/models/", method = RequestMethod.GET)
    public ResponseEntity<List<Model_Char>> listAllModels(){
        List<Model_Char> models = modelService.findAllModels();
        if (models.isEmpty()){
            return new ResponseEntity<List<Model_Char>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Model_Char>>(models, HttpStatus.OK);
    }

    //-------------------Retrieve Single Model--------------------------------------------------------

    @RequestMapping(value = "/model/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Model_Char> getModel(@PathVariable("id") long id){
        System.out.println("Fetching model with id :" + id);
        Model_Char model= modelService.findById(id);
        if (model == null){
            System.out.println("Model with id :" + id + "not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Model_Char>(model, HttpStatus.OK);
    }

    //-------------------Create a Model--------------------------------------------------------
    @RequestMapping(value = "/model/", method = RequestMethod.POST)
    public ResponseEntity<Void> createModel(@RequestBody Model_Char model, UriComponentsBuilder ucBuilder){
        System.out.println("Creating phone " + model.getName());

        if (modelService.isModelExist(model)){
            System.out.println("Model with name " + model.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        modelService.saveModel(model);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/model/{id}").buildAndExpand(model.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a Model --------------------------------------------------------

    @RequestMapping(value = "/model/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Model_Char> updateModel(@PathVariable("id") long id, @RequestBody Model_Char model){
        System.out.println("Updating model " + id);
        Model_Char currentModel = modelService.findById(id);
        if (currentModel == null){
            System.out.println("Model with id " + id + " not found");
            return new ResponseEntity<Model_Char>(HttpStatus.NOT_FOUND);
        }
        //String name,Double diagonal,Double size, String description
        currentModel.setName(model.getName());
        currentModel.setPictures(model.getPictures());
        currentModel.setDescription(model.getDescription());
        currentModel.setDiagonal(model.getDiagonal());
        currentModel.setSize(model.getSize());
        currentModel.setPhones(model.getPhones());

        modelService.updateModel(currentModel);
        return new ResponseEntity<Model_Char>(currentModel, HttpStatus.OK);
    }

    //------------------- Delete a Model --------------------------------------------------------

    @RequestMapping(value = "/model/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Model_Char> deleteModel(@PathVariable("id") long id){
        System.out.println("Fetching & deleting model with id " + id);

        Model_Char model = modelService.findById(id);

        if (model == null){
            System.out.println("Unable to delete. Model with id " + id + " not found");
            return new ResponseEntity<Model_Char>(HttpStatus.NOT_FOUND);
        }
        modelService.deleteModelById(id);
        return new ResponseEntity<Model_Char>(model, HttpStatus.NO_CONTENT);
    }
}
