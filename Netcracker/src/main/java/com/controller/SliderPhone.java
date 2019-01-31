package com.controller;

import com.repository.PictureService.PictureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Admin on 30.01.2019.
 */
@Controller
public class SliderPhone {
    @Autowired
    private PictureServiceImpl picturesRepository;

    @RequestMapping(value = {"/sliderphoto"}, method = RequestMethod.GET)
    public String sliderPhoto(Model model, @ModelAttribute("slider") Long  modelId) {
        picturesRepository.findByCount(modelId);//возврат листа картинок по id телефона
        return "phones";
    }
}
