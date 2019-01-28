package com.controller;

import com.entities.Model_Char;
import com.forms.PhoneForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.repository.ModelService.ModelServiceImpl;
import com.repository.PhoneService.PhoneServiceImpl;

/**
 * Created by Admin on 22.01.2019.
 */
/*Удаление продукта*/
@Controller
public class DeletePhone {
    @Autowired
    private PhoneServiceImpl phoneRepository;
    @Autowired
    private ModelServiceImpl modelRepository;

    @RequestMapping(value = {"/deletephone"}, method = RequestMethod.GET)
    public String deletePhone(Model model) {
        PhoneForm phoneForm = new PhoneForm();
        model.addAttribute("phoneForm", phoneForm);
        return "deletephone";
    }

    @RequestMapping(value = {"/deletephone"}, method = RequestMethod.POST)
    public String deletePhone(@ModelAttribute("delete") Long phone) {
        Model_Char model_char = modelRepository.findModelById(phone);
        phoneRepository.deletePhoneFromModel(model_char);
        return "redirect:/phones";
    }
}
