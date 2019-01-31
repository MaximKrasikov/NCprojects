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
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private PictureServiceImpl picturesRepository;

    @RequestMapping(value = {"/addphone"}, method = RequestMethod.GET)
    public String addphone(Model model) {
        PhoneForm phoneForm = new PhoneForm();
        model.addAttribute("phoneForm", phoneForm);
        return "addphone";
    }

    @RequestMapping(value = {"/addphone"}, method = RequestMethod.POST)
    public String savePhone(Model model, @ModelAttribute("phoneForm") PhoneForm phoneForm) throws IOException, URISyntaxException {
        String model_name = phoneForm.getModel_name();
        String color_name = phoneForm.getColor_name();
        Double price = phoneForm.getPrice();
        Double size = phoneForm.getSize();
        Double diagonal = phoneForm.getDiagonal();
        String description = phoneForm.getDescription();
        Model_Char m;
        Phones p;
        List<Pictures> picList = new ArrayList<>();
        if (modelRepository.findModelById(phoneForm.getPhone_id()) == null) {
            m = new Model_Char(model_name, diagonal, size, description);
            modelRepository.save(m);
            Pictures pic = new Pictures(m, color_name, model_name, picturesRepository.useImageFromBase("/images/" + phoneForm.getPictures().getPath()));
            picList.add(pic);
            for (Pictures picture : picList) {
                picturesRepository.addPictures(picture);
            }
            p = new Phones(m, price, color_name);
            picturesRepository.searchForPicturesList(picList, p);
            phoneRepository.save(p);
        } else {
            m = modelRepository.findByName(model_name).get(0);
            phoneRepository.findByModel(m);
            return "redirect:/addmistake";
        }

        return "redirect:/phones";
    }

}
