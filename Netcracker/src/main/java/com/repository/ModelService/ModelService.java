package com.repository.ModelService;

import com.entities.Model_Char;

import java.util.List;

/**
 * Created by Admin on 07.12.2018.
 */
public interface ModelService {

    //Rest
    //будем посылать модель, а на главном магазине уже разбирутся какие поля надо брать
    Model_Char createModel(Model_Char model);
    //мы формируем модель на стороннем магазине и отсылаем ее на главный магаз, он решает какие поля взять
    Model_Char updateModelFromRest(Model_Char updateInfo);
    void deleteModelFromRest(Model_Char model);


    Model_Char addModel(Model_Char model);

    List<Model_Char> findAllModels();

    Model_Char findById(long id);

    boolean isModelExist(Model_Char model);

    Model_Char findByName(String name);

    void saveModel(Model_Char model);

    void updateModel(Model_Char currentModel);

    void deleteModelById(long id);
}
