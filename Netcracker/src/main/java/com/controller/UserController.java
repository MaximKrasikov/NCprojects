package com.controller;

import com.entities.Role;
import com.entities.User;
import com.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

/**
 * Created by Admin on 06.02.2019.
 */
@Controller
@CrossOrigin(origins = "http://localhost:8080")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository users;

    private boolean deletingAdmin = false;

    private static final Logger log = LoggerFactory.getLogger(ServerController.class);

    @RequestMapping(value = { "/users" }, method = RequestMethod.GET)
    public String users(Model model) {
        Iterable<User> allusers = users.findAll();
        model.addAttribute("users", allusers);
        model.addAttribute("deletingAdmin", deletingAdmin);
        deletingAdmin = false;
        return "users";
    }
    @GetMapping("/setAdmin")
    public String setAdmin(Model model, @RequestParam(name="userId")long userId) {
        User user = users.findById(userId).get();
        user.addRole(Role.ADMIN);
        users.save(user);
        return "redirect:/users";
    }

    @GetMapping("/setUser")
    public String setUser(Model model, @RequestParam(name="userId")long userId) {
        User user = users.findById(userId).get();
        user.addRole(Role.USER);
        users.save(user);
        return "redirect:/users";
    }
    @GetMapping("/rmAdmin")
    public String rmAdmin(Model model, @RequestParam(name="userId")long userId) {
        User user = users.findById(userId).get();
        //если админ остался не один, то удаляем роль
        if (user.getId() != 1) {
            user.removeRole(Role.ADMIN);
        } else {
            // нельзя удалять админа
            deletingAdmin = true;
        }
        users.save(user);
        return "redirect:/users";
    }

    @GetMapping("/rmUser")
    public String rmUser(Model model, @RequestParam(name="userId")long userId) {
        User user = users.findById(userId).get();
        user.removeRole(Role.USER);
        users.save(user);
        return "redirect:/users";
    }
    //@CrossOrigin
    @RequestMapping("/chat")
    public String chat(HttpServletRequest request, org.springframework.ui.Model model) throws URISyntaxException {
        String username = (String) request.getSession().getAttribute("username");
        if (username == null || username.isEmpty()) {
            return "redirect:/loginchat";
        }
        model.addAttribute("username", username);
        return "chat.html";
    }
    //@CrossOrigin
    @RequestMapping(path = "/loginchat", method = RequestMethod.GET)
    public String showLoginPage() {
        return "loginchat";
    }

    //@CrossOrigin
    @RequestMapping(path = "/loginchat", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, @RequestParam(defaultValue = "") String username) {
        username = username.trim();

        if (username.isEmpty()) {
            return "loginchat";
        }
        request.getSession().setAttribute("username", username);

        return "redirect:/chat";
    }
  //  @CrossOrigin
    @RequestMapping(path = "/logoutchat")
    public String logoutchat(HttpServletRequest request) {
        request.getSession(true).invalidate();

        return "redirect:/loginchat";
    }
}