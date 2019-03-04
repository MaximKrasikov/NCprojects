package com.repository.ModelService;

import com.entities.Model_Char;
import com.entities.Phones;

import java.util.List;

/**
 * Created by Admin on 07.12.2018.
 */
public interface ModelService {

    //Rest
    //будем посылать модель, а на главном магазине уже разбирутся какие поля надо брать
    public void postAllModels(Phones phones);


    Model_Char addModel(Model_Char model);

    List<Model_Char> findAllModels();

    Model_Char findById(long id);

    boolean isModelExist(Model_Char model);

    Model_Char findByName(String name);

    void saveModel(Model_Char model);

    void updateModel(Model_Char currentModel);

    void deleteModelById(long id);
}
