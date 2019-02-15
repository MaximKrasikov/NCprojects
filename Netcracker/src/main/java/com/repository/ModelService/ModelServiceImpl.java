package com.repository.ModelService;

import com.entities.Model_Char;
import com.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void save(Model_Char model) {
        modelRep.save(model);
    }
}
