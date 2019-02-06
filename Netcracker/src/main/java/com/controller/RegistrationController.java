package com.controller;

import com.entities.Role;
import com.entities.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

/**
 * Created by Admin on 04.02.2019.
 */
@Controller
public class RegistrationController {
    @Autowired
    private UserRepository users;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = users.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("checkDb", true);
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        users.save(user);

        return "redirect:/login";
    }
}