package com.controller;


import com.entities.Role;
import com.entities.User;
import com.repository.UserRepository;
import com.repository.UserService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserServiceImpl users;

    @GetMapping("/registration")
    public String registration() {
        return "registration";//форма регистрации
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = users.findUserByName(user.getUsername());

        if (userFromDb != null) {// если пользователь нашелся
            model.put("message", "User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        users.save(user);

        return "redirect:/login";
    }

}