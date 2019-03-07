package com.controller;

import com.entities.Model_Char;
import com.entities.Phones;
import com.entities.Pictures;
import com.forms.PhoneForm;
import com.repository.ModelRepository;
import com.repository.PhoneRepository;
import com.repository.PhoneService.PhoneService;
import com.repository.PictureService.PictureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 22.01.2019.
 */
/*добавление продукта*/
@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AddPhone {
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private PictureServiceImpl picturesRepository;
    @Autowired
    PhoneService phoneService;

    @Value("${upload.path}")
    private String uploadPath;


    @RequestMapping(value = {"/addphone"}, method = RequestMethod.GET)
    public String addphone(Model model) {
        PhoneForm phoneForm = new PhoneForm();
        model.addAttribute("phoneForm", phoneForm);
        return "addphone";
    }

    @RequestMapping(value = {"/addphone"}, method = RequestMethod.POST)
    public String savePhone(Model model, @ModelAttribute("phoneForm") PhoneForm phoneForm) throws IOException, URISyntaxException, SQLException {
        String model_name = phoneForm.getModel_name();
        String color_name = phoneForm.getColor_name();
        long price = phoneForm.getPrice();
        Double size = phoneForm.getSize();
        Double diagonal = phoneForm.getDiagonal();
        String description = phoneForm.getDescription();
        String comment= phoneForm.getComment();

        Model_Char m;
        Phones p;
        List<Pictures> picList = new ArrayList<>();
        if (phoneRepository.findPhoneById(phoneForm.getPhone_id()) == null) {//если телефон по id телефона не найден
            m = new Model_Char(model_name, diagonal, size, description);
            modelRepository.save(m);

           List<MultipartFile> files= phoneForm.getPictures();
            for(MultipartFile picture: files){
                byte[] b = picturesRepository.loadImage("/images/" + picture.getResource().getFilename());
                picList.add(new Pictures(m, color_name, model_name, b));
            }
            for (Pictures picture : picList) {
                picturesRepository.addPictures(picture);
            }
            p = new Phones(m, price, color_name,comment);
            picturesRepository.searchForPicturesList(picList, p);
            phoneRepository.save(p);
            phoneService.createPhone(p);
        } else {
            m = modelRepository.findByName(model_name).get(0);
            phoneRepository.findByModel(m);
            return "redirect:/addmistake";
        }
        return "redirect:/phones";
    }
}
