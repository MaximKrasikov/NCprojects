package com.repository.ModelService;

import com.entities.Model_Char;
import com.repository.ModelRepository;
import com.repository.ModelRepositoryForRest;
import com.restentities.ModelForRest;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 07.12.2018.
 */
@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    private ModelRepository modelRep;
    @Autowired
    ModelRepositoryForRest modelRepositoryForRest;

    static final String URL_MODEL_POST = "http://localhost:5030/";//Cracker
    public static final String USER_NAME = "admin";
    public static final String PASSWORD = "admin";

    /*
    Сторонний магазин будет посылать запросы на главный магазин с просьбой на создание/изменение/удаление телефона
    String modelName,long priceMin,long priceMax
    */

    @Override
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

    public ModelServiceImpl(ModelRepository modelRep) {
        this.modelRep = modelRep;
    }

    @Override
    public Model_Char addModel(Model_Char model) {
        Model_Char savedModel = modelRep.saveAndFlush(model);
        return savedModel;
    }

    @Override
    public List<Model_Char> findAllModels() {
        return modelRep.findAll();
    }

    @Override
    public Model_Char findById(long id) {
        for (Model_Char model :modelRep.findAll()) {
            if (model.getId() == id) {
                return model;
            }
        }
        return null;
    }

    @Override
    public boolean isModelExist(Model_Char model) {
        return findByName(model.getName()) != null;
    }
    @Override
    public Model_Char findByName(String name) {
        for (Model_Char model : modelRep.findAll()) {
            if (model.getName().equalsIgnoreCase(name)) {
                return model;
            }
        }
        return null;
    }

    @Override
    public void saveModel(Model_Char model) {
        modelRep.save(model);
    }

    @Override
    public void updateModel(Model_Char currentModel) {
        int index = modelRep.findAll().indexOf(currentModel);//находит индекс переданной модели
        modelRep.findAll().set(index, currentModel);
    }

    @Override
    public void deleteModelById(long id) {
        for (Iterator<Model_Char> iterator = modelRep.findAll().iterator(); iterator.hasNext(); ) {
            Model_Char model = iterator.next();
            if (model.getId() == id) {
                iterator.remove();
            }
        }
    }

    public void save(Model_Char model) {
        modelRep.save(model);
    }
}
