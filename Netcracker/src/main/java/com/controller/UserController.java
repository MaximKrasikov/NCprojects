package com.controller;

import com.entities.User;
import com.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Admin on 06.02.2019.
 */
@Controller
public class UserController {
    @Autowired
    private UserRepository users;

    private static final Logger log = LoggerFactory.getLogger(ServerController.class);

    @RequestMapping(value = { "/users" }, method = RequestMethod.GET)
    public String users(Model model) {
        Iterable<User> allusers = users.findAll();
        model.addAttribute("users", allusers);
        return "users";
    }
}