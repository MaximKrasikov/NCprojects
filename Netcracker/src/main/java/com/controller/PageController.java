package com.controller;

import com.entities.Phones;
import com.entities.User;
import com.repository.PhoneRepository;
import com.repository.PhoneService.PhoneServiceImpl;
import com.repository.PictureService.PictureServiceImpl;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

/**
 * Created by Admin on 23.02.2019.
 */
@Controller
@RequestMapping("/phonepage")
public class PageController {
    @Autowired
    private PhoneRepository phones;
    @Autowired
    private UserRepository users;
    @Autowired
    private PhoneServiceImpl phoneRepository;
    @Autowired
    private PictureServiceImpl pictureService;

    @RequestMapping(value = {"/infphone"}, method = RequestMethod.GET)
    public String phonepage(Model model, @RequestParam(name="phoneId")long phoneId, Principal principal) {
        Optional<Phones> prePhone = phones.findById(phoneId);
        Phones phone = prePhone.get();
        phoneRepository.save(pictureService.searchForPicturesList(pictureService.findAllPictures(), phone));
        model.addAttribute("phone", phone);
        User user = users.findByUsername(principal.getName());
        model.addAttribute("isAdmin", user.isAdmin());
        model.addAttribute("isUser", user.isUser());
        return "phonepage";
    }
}
