package com.controller;

import com.entities.Role;
import com.entities.User;
import com.entities.domains.password.PasswordConstraintValidator;
import com.error.InvalidPasswordException;
import com.forms.UserDto;
import com.repository.UserRepository;
import com.repository.UserService.IUserService;
import com.repository.UserService.UserService;
import com.repository.UserService.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Admin on 04.02.2019.
 */
@Controller
public class RegistrationController extends PasswordConstraintValidator {
    @Autowired
    private UserRepository users;
    @Autowired
    private UserServiceImpl service;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private IUserService iUserService;

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") @Valid UserDto userDto,
                          Map<String, Object> model, BindingResult result, WebRequest request, Errors errors)throws InvalidPasswordException{
        User userFromDb = users.findByUsername(userDto.getUsername());
        User user= new User();

        if (userFromDb != null) {
            model.put("checkDb", true);
            return "registration";
        }
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        users.save(user);

        return "redirect:/loginpage";
    }

    /*==========================================обработка ошибок===========================================================*/
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "IOException exception! check arguments!")
    @ExceptionHandler(value= {IOException.class, SQLException.class})
    public void handleIOException() {
        logger.error("IOException handler executed");
    }

    //перехват ошибки по паролю
    @ExceptionHandler(InvalidPasswordException.class)
    public ModelAndView handleBadPasswordException(Exception ex) {
        logger.error("IOException handler executed");
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", ex.getMessage());
        return modelAndView;
    }
}