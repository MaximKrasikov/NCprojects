package com.controller;

import com.entities.Model_Char;
import com.entities.Phones;
import com.entities.Pictures;
import com.forms.PhoneForm;
import com.repository.ModelRepository;
import com.repository.PhoneRepository;
import com.repository.PictureService.PictureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Admin on 25.01.2019.
 */
@Controller
public class ModifyPhone {
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private PictureServiceImpl picturesRepository;

    @RequestMapping(value = {"/modifyphone"}, method = RequestMethod.GET)
    public String modifyPhone(Model model,@ModelAttribute("modify") Long modelId) {
        PhoneForm phoneForm = new PhoneForm();
        phoneForm.setPhone_id(modelId);
        model.addAttribute("phoneForm", phoneForm);
        return "modifyphone";
    }

    //,@ModelAttribute("phoneForm") PhoneForm phoneForm
    @RequestMapping(value = {"/modifyphone"}, method = RequestMethod.POST)
    public String modifyPhone(Model model,@ModelAttribute("phoneForm") PhoneForm phoneForm) throws IOException, URISyntaxException {
        Model_Char m;
        Phones p;
        if (modelRepository.findModelById(phoneForm.getPhone_id())!=null) {
            m = modelRepository.findModelById(phoneForm.getPhone_id());
            p= phoneRepository.findByModel(m);

            String model_name = phoneForm.getModel_name();
            String color_name = phoneForm.getColor_name();
            Double price = phoneForm.getPrice();
            Double size = phoneForm.getSize();
            Double diagonal = phoneForm.getDiagonal();
            String description = phoneForm.getDescription();

            m.setName(model_name);
            m.setDiagonal(diagonal);
            m.setSize(size);
            m.setDescription(description);

            Pictures pic = new Pictures(m,color_name,model_name,picturesRepository.useImageFromBase("/images/"+phoneForm.getPictures().getPath()));
            picturesRepository.addPictures(pic);
            p.setModel(m);
            p.setPrice(price);
            p.setColor(color_name);
            p.setPictures( pic);

            modelRepository.save(m);
            phoneRepository.save(p);
        }
        return "redirect:/phones";
    }
}