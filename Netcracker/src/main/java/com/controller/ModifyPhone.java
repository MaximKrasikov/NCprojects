package com.controller;

import com.entities.Model_Char;
import com.entities.Phones;
import com.entities.Pictures;
import com.forms.PhoneForm;
import com.repository.ModelRepository;
import com.repository.PhoneRepository;
import com.repository.PictureService.PictureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 25.01.2019.
 */
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class ModifyPhone {
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private PictureServiceImpl picturesRepository;

    Phones phone;

    @RequestMapping(value = {"/modifyphone"}, method = RequestMethod.GET)
    public String modifyPhone(Model model, @ModelAttribute("modify") Long modelId) {
        Optional<Phones> pre_phone = phoneRepository.findById(modelId);
        phone = pre_phone.get();

        PhoneForm phoneForm = new PhoneForm();
        phoneForm.setPhone_id(phone.getPhone_id());
        phoneForm.setModel_name(phone.getModelName());
        phoneForm.setColor_name(phone.getColor());
        phoneForm.setDescription(phone.getDescription());
        phoneForm.setDiagonal(phone.getDiagonal());
        phoneForm.setPrice(phone.getPrice());
        phoneForm.setSize(phone.getSize());

        model.addAttribute("phoneForm", phoneForm);
        return "modifyphone";
    }

    @RequestMapping(value = {"/modifyphonefrompage"}, method = RequestMethod.GET)
    public String modifyPhoneByPhone(Model model, @RequestParam(name="phoneId")long phoneId) {
        Optional<Phones> pre_phone = phoneRepository.findById(phoneId);
        phone = pre_phone.get();

        PhoneForm phoneForm = new PhoneForm();
        phoneForm.setPhone_id(phone.getPhone_id());
        phoneForm.setModel_name(phone.getModelName());
        phoneForm.setColor_name(phone.getColor());
        phoneForm.setDescription(phone.getDescription());
        phoneForm.setDiagonal(phone.getDiagonal());
        phoneForm.setPrice(phone.getPrice());
        phoneForm.setSize(phone.getSize());
        phoneForm.setComment(phone.getComment());

        model.addAttribute("phoneForm", phoneForm);
        return "modifyphone";
    }

    @RequestMapping(value = {"/modifyphone"}, method = RequestMethod.POST)
    public String modifyPhone(Model model, @ModelAttribute("phoneForm") PhoneForm phoneForm) throws IOException, URISyntaxException {

        Optional<Phones> pre_phones = phoneRepository.findById(phoneForm.getPhone_id());
        Phones p = pre_phones.get();

        String model_name = phoneForm.getModel_name();
        String color_name = phoneForm.getColor_name();
        Double price = phoneForm.getPrice();
        Double size = phoneForm.getSize();
        Double diagonal = phoneForm.getDiagonal();
        String description = phoneForm.getDescription();

        Model_Char m = new Model_Char(model_name, diagonal, size, description);
        modelRepository.save(m);

        List<Pictures> picList = new ArrayList<>();
        List<MultipartFile> files= phoneForm.getPictures();
        for(MultipartFile picture: files){
            byte[] b = picturesRepository.loadImage("/images/" + picture.getResource().getFilename());
            picList.add(new Pictures(m, color_name, model_name, b));
        }
        for (Pictures picture : picList) {
            picturesRepository.addPictures(picture);
        }
        picturesRepository.searchForPicturesList(picList, p);
        p.setModel(m);
        p.setPrice(price);
        p.setColor(color_name);
        p.setPictures(picList);

        phoneRepository.save(p);
        return "redirect:/phones";
    }
}