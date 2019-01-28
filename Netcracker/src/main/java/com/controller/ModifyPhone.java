package com.controller;

import com.entities.Model_Char;
import com.entities.Phones;
import com.entities.Pictures;
import com.forms.PhoneForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.repository.ModelRepository;
import com.repository.PhoneRepository;

/**
 * Created by Admin on 25.01.2019.
 */
@Controller
public class ModifyPhone {
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private ModelRepository modelRepository;

    @RequestMapping(value = {"/modifyphone"}, method = RequestMethod.GET)
    public String modifyPhone(Model model) {
        PhoneForm phoneForm = new PhoneForm();
        model.addAttribute("phoneForm", phoneForm);
        return "modifyphone";
    }

    @RequestMapping(value = {"/modifyphone"}, method = RequestMethod.POST)
    public String modifyPhone(@ModelAttribute("modify") Long modelId,@ModelAttribute("phoneForm") PhoneForm phoneForm) {
        Model_Char m;
        Phones p;
        if (modelRepository.findModelById(modelId)!=null) {
            m = modelRepository.findModelById(modelId);
            p= phoneRepository.findByModel(m);

            String model_name = phoneForm.getModel_name();
            String color_name = phoneForm.getColor_name();
            Double price = phoneForm.getPrice();
            Double size = phoneForm.getSize();
            Double diagonal = phoneForm.getDiagonal();
            String description = phoneForm.getDescription();
            Pictures picture = phoneForm.getPictures();

            m.setName(model_name);
            m.setDiagonal(diagonal);
            m.setSize(size);
            m.setDescription(description);

            p.setModel(m);
            p.setPrice(price);
            p.setColor(color_name);
            p.setPictures(picture);
        }
        return "redirect:/modifyphone";
    }
}