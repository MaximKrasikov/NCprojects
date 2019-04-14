package com.controller;

import com.entities.User;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.security.Principal;

/**
 * Created by Admin on 09.04.2019.
 */
@Controller
//@PreAuthorize("hasAuthority('ADMIN')")
public class ChatController {
    @Autowired
    private UserRepository users;

    @RequestMapping("/chat")
    public String chat(Principal principal, org.springframework.ui.Model model) throws URISyntaxException {
        User user = users.findByUsername(principal.getName());
        String username= user.getUsername();
        model.addAttribute("username", username);
        return "chat";
    }

    @RequestMapping(path = "/loginchat", method = RequestMethod.GET)
    public String showLoginPage() {
        return "loginchat";
    }


    @RequestMapping(path = "/loginchat", method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, @RequestParam(defaultValue = "") String username) {
        username = username.trim();

        if (username.isEmpty()) {
            return "loginchat";
        }
        request.getSession().setAttribute("username", username);

        return "redirect:/chat";
    }
}
