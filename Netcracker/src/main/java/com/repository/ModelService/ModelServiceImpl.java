package com.repository.ModelService;

import com.entities.Model_Char;
import com.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Admin on 07.12.2018.
 */
@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    private ModelRepository modelRep;

    static final String URL_CREATE_MODEL = "http://localhost:8080/model/";
    static final String URL_UPDATE_MODEL = "http://localhost:8080/model/";
    static final String URL_MODEL_PREFIX = "http://localhost:8080/model/";

    /*
    Сторонний магазин будет посылать запросы на главный магазин с просьбой на создание/изменение/удаление телефона
    String modelName,long priceMin,long priceMax
    */

    //будем посылать запрос на создание модели, а на главном магазине уже разберутся какие поля надо брать
    @Override
    public Model_Char createModel(Model_Char model) {
        //String modelName,long priceMin,long priceMax
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_XML_VALUE);
        headers.setContentType(MediaType.APPLICATION_XML);

        RestTemplate restTemplate = new RestTemplate();
        // Data attached to the request.
        HttpEntity<Model_Char> requestBody = new HttpEntity<>(model, headers);
        // Send request with POST method.
        ResponseEntity<Model_Char> result = restTemplate.postForEntity(URL_CREATE_MODEL, requestBody, Model_Char.class);
        //подтверждение, что модель создалась
        if (result.getStatusCode() == HttpStatus.CREATED) {
            Model_Char e = result.getBody();
            System.out.println("(Client Side) Model Created: "+ e.getId()+" Name: "+e.getName());
            return e;
        }
        return  null;
    }
    //мы формируем модель на стороннем магазине и отсылаем ее на главный магаз, он решает какие поля взять
    @Override
    public Model_Char updateModelFromRest(Model_Char updateInfo) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

        RestTemplate restTemplate = new RestTemplate();
        // Data attached to the request.
        HttpEntity<Model_Char> requestBody = new HttpEntity<>(updateInfo, headers);
        // Send request with PUT method.
        restTemplate.exchange(URL_UPDATE_MODEL, HttpMethod.PUT,  requestBody, Void.class);
        String resourceUrl = URL_MODEL_PREFIX + "/" + updateInfo.getId().toString();
        Model_Char e = restTemplate.getForObject(resourceUrl, Model_Char.class);

        if (e != null) {
            System.out.println("(Client side) Model after update: ");
            System.out.println(e.toString());
            return  e;
        }
        return null;
    }
    @Override
    public void deleteModelFromRest(Model_Char model) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:8080/model/{id}";
        Object[] uriValues = new Object[] { model.getId().toString() };
        restTemplate.delete(resourceUrl, uriValues);
        Model_Char e = restTemplate.getForObject(resourceUrl, Model_Char.class);
        if (e != null) {
            System.out.println("(Client side) Model after delete: ");
            System.out.println("Model: " + e.getId() + " - " + e.getName());
        } else {
            System.out.println("Model not found!");
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
