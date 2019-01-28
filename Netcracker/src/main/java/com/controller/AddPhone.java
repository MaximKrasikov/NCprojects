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
 * Created by Admin on 22.01.2019.
 */
/*добавление продукта*/
@Controller
public class AddPhone {
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private ModelRepository modelRepository;

    @RequestMapping(value = {"/addphone"}, method = RequestMethod.GET)
    public String addphone(Model model) {
        PhoneForm phoneForm = new PhoneForm();
        model.addAttribute("phoneForm", phoneForm);
        return "addphone";
    }
    @RequestMapping(value = {"/addphone"}, method = RequestMethod.POST)
    public String savePhone(Model model, @ModelAttribute("phoneForm") PhoneForm phoneForm) {
        String model_name = phoneForm.getModel_name();
        String color_name = phoneForm.getColor_name();
        Double price = phoneForm.getPrice();
        Double size = phoneForm.getSize();
        Double diagonal = phoneForm.getDiagonal();
        String description = phoneForm.getDescription();
        Pictures picture = phoneForm.getPictures();

        Model_Char m;
        Phones p;
        if (modelRepository.findByName(model_name).isEmpty() && modelRepository.findByDiagonal(diagonal).isEmpty()
                && modelRepository.findBySize(size).isEmpty() && modelRepository.findByDescription(description).isEmpty()) {
            m = new Model_Char(model_name,diagonal, size, description);
            p= new Phones(m, price, color_name, picture);
            modelRepository.save(m);
            phoneRepository.save(p);
        } else {
            m = modelRepository.findByName(model_name).get(0);
            phoneRepository.findByModel(m);
        }
        return "redirect:/phones";
    }
}
