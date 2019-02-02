package com.controller;

import com.forms.PhoneForm;
import com.repository.ModelService.ModelServiceImpl;
import com.repository.PhoneService.PhoneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Admin on 22.01.2019.
 */
/*Удаление продукта*/
@Controller
public class DeletePhone {
    @Autowired
    private PhoneServiceImpl phoneRepository;
    @Autowired
    private ModelServiceImpl modelRepository;

    @RequestMapping(value = {"/deletephone"}, method = RequestMethod.GET)
    public String deletePhone(Model model) {
        PhoneForm phoneForm = new PhoneForm();
        model.addAttribute("phoneForm", phoneForm);
        return "deletephone";
    }

    @RequestMapping(value = {"/deletephone"}, method = RequestMethod.POST)
    public String deletePhone(@ModelAttribute("delete") Long phoneId) {
        phoneRepository.deletePhoneFromPhone(phoneId);
        return "redirect:/phones";
    }

    @RequestMapping(value = {"/deletesomephone"}, method = RequestMethod.POST)
    public String deleteSomePhone(HttpServletRequest request, ModelMap model) {
        try {
            if(request.getParameterValues("phoneId")!=null) {
                for (String phone : request.getParameterValues("phoneId")) {
                    phoneRepository.deletePhoneFromPhone(Long.valueOf(phone));
                }
            }
            return "redirect:/phones";
        } catch (Exception e) {
            model.put("error",e.getMessage());
            return "phones";
        }
    }
}
