package com.repository.ModelService;

import com.entities.Model_Char;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repository.ModelRepository;

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
    public Model_Char findByName(String name) {
        return modelRep.findModelByName(name);
    }

    @Override
    public void deleteModel(Model_Char model) {
        modelRep.delete(model);
    }

    @Override
    public List<Model_Char> findAllModels() {
        List<Model_Char> res = modelRep.findAll();
        return res;
    }

    @Override
    public Model_Char findModelById(Long modelId) {
        return modelRep.findModelById(modelId);
    }

    public void save(Model_Char model) {
        modelRep.save(model);
    }
}
