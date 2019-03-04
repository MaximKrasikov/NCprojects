package com.rest;

import com.entities.Model_Char;
import com.repository.ModelRepository;
import com.repository.ModelRepositoryForRest;
import com.restentities.ModelForRest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 03.03.2019.
 */
@RestController
public class ModelRest {
    @Autowired
    ModelRepository models;
    @Autowired
    ModelRepositoryForRest modelRepositoryForRest;
    //изменил порты
    static final String URL_MODEL_POST = "http://localhost:5030/restart";//Cracker
    public static final String USER_NAME = "admin";
    public static final String PASSWORD = "admin";

    //главный магазин запрашивает модель
    @RequestMapping(value = "/models", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.ALL_VALUE )
    public ResponseEntity<List<ModelForRest>> listAllModels(){
        Iterable<Model_Char> allModels = models.findAll();

        //сущности для отправки
        List<ModelForRest> modelsForRest = new ArrayList<>();
        for (Model_Char e:allModels) {
            ModelForRest eForRest = new ModelForRest(e);
            modelsForRest.add(eForRest);
        }

        return new ResponseEntity<List<ModelForRest>>(modelsForRest, HttpStatus.OK);
    }

    public void postAllModels(ModelForRest modelForRest) {
        HttpHeaders headers = new HttpHeaders();
        String auth = USER_NAME + ":" + PASSWORD;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<ModelForRest> requestBody = new HttpEntity<>(modelForRest, headers);

        Set<String> urlSet = new HashSet<String>();
        urlSet.add(URL_MODEL_POST);
        for (String URL_MODEL : urlSet) {
            try {
                ModelForRest e = restTemplate.postForObject(URL_MODEL_POST, requestBody, ModelForRest.class);
                if (e != null)  {
                        modelRepositoryForRest.save(e);
                }
            } catch (Exception e) {
                System.out.println("I am falling!");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
