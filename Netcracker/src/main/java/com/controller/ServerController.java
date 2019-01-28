package com.controller;

/**
 * Created by Admin on 03.12.2018.
 */

import com.entities.Model_Char;
import com.entities.Phones;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.repository.ModelRepository;
import com.repository.PhoneRepository;

import java.util.List;


/**
 * Created by Admin on 03.12.2018.
 */
@Controller
public class ServerController {
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private ModelRepository modelRepository;

    private static final Logger log = LoggerFactory.getLogger(ServerController.class);

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    //отображение всех телефонов
    @RequestMapping(value = {"/phones", "/phone"}, method = RequestMethod.GET)
    public String phones(Model model) {
        Iterable<Phones> phones = phoneRepository.findAll();
        List<Model_Char> models= modelRepository.findAll();
        model.addAttribute("phones", phones);
        return "phones";
    }
}