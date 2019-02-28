package com.repository.ModelService;

import com.entities.Model_Char;
import com.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Admin on 07.12.2018.
 */
@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    private ModelRepository modelRep;

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
