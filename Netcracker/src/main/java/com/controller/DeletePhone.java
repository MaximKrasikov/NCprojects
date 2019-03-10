package com.controller;

import com.entities.Phones;
import com.repository.ModelService.ModelService;
import com.repository.PhoneRepository;
import com.repository.PhoneService.PhoneService;
import com.repository.PhoneService.PhoneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Created by Admin on 22.01.2019.
 */
/*Удаление продукта*/
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class DeletePhone {
    @Autowired
    private PhoneServiceImpl phoneRepository;
    @Autowired
    private PhoneRepository phones;
    @Autowired
    private PhoneService phoneService;
    @Autowired
    private ModelService modelService;

    @RequestMapping(value = {"/deletephone"}, method = RequestMethod.POST)
    public String deletePhone(@ModelAttribute("delete") Long phoneId) {
        //удаление из рест
        phoneService.deletePhone(phoneId);
        phoneRepository.deletePhoneFromPhone(phoneId);
        return "redirect:/phones";
    }

    @RequestMapping(value = {"/deletephonefrompage"}, method = RequestMethod.GET)
    public String deletePhoneFromPhonePage(@RequestParam(name="phoneId")long phoneId) {
        Optional<Phones> prePhone = phones.findById(phoneId);
        Phones phone = prePhone.get();
        //удаление из рест
        phoneService.deletePhone(phoneId);
        phoneRepository.deletePhone(phone);
        return "redirect:/phones";
    }

    @RequestMapping(value = {"/deletesomephone"}, method = RequestMethod.POST)
    public String deleteSomePhone(HttpServletRequest request, ModelMap model) {
        try {
            if(request.getParameterValues("phoneId")!=null) {
                for (String phone : request.getParameterValues("phoneId")) {
                    //удаление из рест
                    phoneService.deletePhone(Long.valueOf(phone));
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
