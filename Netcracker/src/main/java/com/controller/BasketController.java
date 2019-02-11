package com.controller;

import com.entities.Phones;
import com.entities.User;
import com.repository.PhoneRepository;
import com.repository.PhoneService.PhoneServiceImpl;
import com.repository.PictureService.PictureServiceImpl;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

/**
 * Created by Admin on 06.02.2019.
 */
@Controller
@RequestMapping("/basket")
public class BasketController {
    @Autowired
    private UserRepository users;
    @Autowired
    private PhoneRepository phones;
    @Autowired
    private PictureServiceImpl pictureService;
    @Autowired
    private PhoneServiceImpl phoneRepository;

    private boolean alreadyThere = false;

    @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public String basket(Model model, Principal principal) {
        User user = users.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("isAdmin", user.isAdmin());
        model.addAttribute("isUser", user.isUser());
        return "redirect:/basket/user?userId="+user.getId();
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = { "/deletefrombasket" }, method = RequestMethod.GET)
    public String deletephone(Model model, @RequestParam(name="phoneId")long phoneId, @RequestParam(name="userId")long userId) {
        User user = users.findById(userId).get();
        Optional<Phones> pre_phone = phones.findById(phoneId);
        Phones phone = pre_phone.get();
        user.deletePhone(phone);
        users.save(user);
        return "redirect:/basket/user?userId="+userId;
    }

    @GetMapping("/user")
    public String basket(Model model, @RequestParam(name="userId")long userId, Principal principal) {
        User user = users.findById(userId).get();
        model.addAttribute("user", user);

        User whatUser = users.findByUsername(principal.getName());
        model.addAttribute("isAdmin", whatUser.isAdmin());
        model.addAttribute("isUser", whatUser.isUser());
        return "basket";
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = { "/addtobasket" }, method = RequestMethod.GET)
    public String addtobasket(Model model, @RequestParam(name="phoneId")long phoneId, Principal principal) {
        User user = users.findByUsername(principal.getName());
        Optional<Phones> pre_phone = phones.findById(phoneId);
        Phones phone = pre_phone.get();
        phoneRepository.save(pictureService.searchForPicturesList(pictureService.findAllPictures(), phone));
        if (user.getPhones().contains(phone)) {
            alreadyThere = true;
            return "redirect:/phones";
        } else {
            alreadyThere = false;
            user.addPhone(phone);
        }
        users.save(user);
        return "redirect:/basket";
    }
}