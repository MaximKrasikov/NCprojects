package com.init;

import com.entities.*;
import com.repository.ModelService.ModelServiceImpl;
import com.repository.PhoneService.PhoneServiceImpl;
import com.repository.PictureService.PictureServiceImpl;
import com.repository.UserService.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Admin on 07.12.2018.
 */
//класс инициализации
@Component
public class DataInit implements ApplicationRunner {
    @Autowired
    public static PhoneServiceImpl servicePhone;
    @Autowired
    public static PictureServiceImpl servicePicture;
    @Autowired
    public static ModelServiceImpl serviceModel;
    @Autowired
    public static UserServiceImpl serviceUser;
    @Autowired
    public  static PasswordEncoder passwordEncoder;

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public DataInit(PhoneServiceImpl servicePhone, PictureServiceImpl servicePicture, ModelServiceImpl serviceModel, UserServiceImpl serviceUser,PasswordEncoder passwordEncoder) {
        this.servicePhone = servicePhone;
        this.servicePicture = servicePicture;
        this.serviceModel = serviceModel;
        this.serviceUser = serviceUser;
        this.passwordEncoder= passwordEncoder;
    }

    public static void autentication() {
        String hashedPasswordAdmin =passwordEncoder.encode("admin");

        User user = new User("admin", hashedPasswordAdmin);
        user.setActive(true);
        Set<Role> roles = new HashSet<Role>();
        user.setRoles(roles);
        user.addRole(Role.ADMIN);

        serviceUser.save(user);
    }

    public static List<Phones> getModelsAndPhones() throws ParseException, IOException, SQLException, URISyntaxException {
        List<Model_Char> models = new ArrayList<>();
        List<Phones> phones = new ArrayList<>();
        List<Pictures> pics = new ArrayList<>();

        Model_Char m_1 = new Model_Char("Alcatel", 170.0, 68.0, "Телефон хороший");// создаем отдельную модель телефона
        Model_Char m_2 = new Model_Char("Meizu", 120.0, 57.0, "Отличное качество, надо брать");
        Model_Char m_3 = new Model_Char("Xiaomi", 115.0, 59.0, "Скидочка 20проц");
        Model_Char m_4 = new Model_Char("Nokia", 115.0, 59.0, "Скидочка 20проц");

        models.add(m_1);
        models.add(m_2);
        models.add(m_3);
        models.add(m_4);

        for (Model_Char model : models) {
            serviceModel.addModel(model);
            serviceModel.save(model);
        }

        Pictures pic_1 = new Pictures(m_1, "black", m_1.getName(), servicePicture.useImageFromBase("/images/alcatel.jpeg"));
        Pictures pic_2 = new Pictures(m_2, "white", m_2.getName(), servicePicture.useImageFromBase("/images/meizu.jpeg"));
        Pictures pic_3 = new Pictures(m_3, "yellow", m_3.getName(), servicePicture.useImageFromBase("/images/xiaomi.jpeg"));
        Pictures pic_4 = new Pictures(m_4, "white", m_4.getName(), servicePicture.useImageFromBase("/images/nokia.jpg"));
        Pictures pic_5 = new Pictures(m_4, "black", m_4.getName(), servicePicture.useImageFromBase("/images/nokia-black.jpg"));

        // добавление картинок в общий контейнер
        pics.add(pic_1);
        pics.add(pic_2);
        pics.add(pic_3);
        pics.add(pic_4);
        pics.add(pic_5);

        for (Pictures picture : pics) {
            servicePicture.addPictures(picture);
            servicePicture.save(picture);
        }

        Phones p1 = new Phones(m_1, 120000.0, "black");
        Phones p2 = new Phones(m_2, 34000.0, "white");
        //добавление нескольких телефонов к одной модели
        Phones p3 = new Phones(m_3, 15000.0, "yellow");
        Phones p4 = new Phones(m_4, 15000.0, "white");
        Phones p5 = new Phones(m_4, 15000.0, "black");

        phones.add(p1);
        phones.add(p2);
        phones.add(p3);
        phones.add(p4);
        phones.add(p5);

        for (Phones p : phones) {
            servicePhone.addPhone(p);
            servicePhone.save(p);
        }
        return phones;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        autentication();
        getModelsAndPhones();
    }

}
