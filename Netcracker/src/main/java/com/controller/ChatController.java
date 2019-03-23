package com.controller;

import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

/**
 * Created by Admin on 21.03.2019.
 */
@Controller
public class ChatController {
    @Autowired
    private UserRepository users;

    @RequestMapping("/chat")
    public String index( HttpServletRequest request, org.springframework.ui.Model model) throws URISyntaxException {
        String username = (String) request.getSession().getAttribute("username");
        //User user = users.findByUsername(principal.getName());
        //String username= user.getUsername();
        if (username == null || username.isEmpty()) {
            return "redirect:/loginchat";
        }
        //final ChatEndpoint clientEndPoint = new ChatEndpoint(new URI("ws://localhost:5030/chat"));
        model.addAttribute("username", username);
        return "chat.html";
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
    @RequestMapping(path = "/logout")
    public String logout(HttpServletRequest request) {
        request.getSession(true).invalidate();

        return "redirect:/loginchat";
    }
}
