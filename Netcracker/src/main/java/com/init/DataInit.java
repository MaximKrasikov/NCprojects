package com.init;

import com.entities.*;
import com.repository.ModelService.ModelServiceImpl;
import com.repository.PhoneService.PhoneServiceImpl;
import com.repository.PictureService.PictureServiceImpl;
import com.repository.UserService.UserServiceImpl;
import com.websocket.GreetServer;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Admin on 07.12.2018.
 */
//класс инициализации
@Component
public class DataInit implements ApplicationRunner {
    @Autowired
    private PhoneServiceImpl servicePhone;
    @Autowired
    private PictureServiceImpl servicePicture;
    @Autowired
    private ModelServiceImpl serviceModel;
    @Autowired
    private UserServiceImpl serviceUser;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public void autentication() {
        User user = new User("admin", passwordEncoder.encode("admin123A"));
        user.setActive(true);
        Set<Role> roles = new HashSet<Role>();
        user.setRoles(roles);
        user.addRole(Role.ADMIN);

        User userCli = new User("cli", passwordEncoder.encode("cli123A"));
        userCli.setActive(true);
        Set<Role> rolesCli = new HashSet<Role>();
        userCli.setRoles(rolesCli);
        userCli.addRole(Role.USER);

        serviceUser.save(user);
        serviceUser.save(userCli);
    }

    public  List<Phones> getModelsAndPhones() throws ParseException, IOException, SQLException, URISyntaxException {
        List<Model_Char> models = new ArrayList<>();
        List<Phones> phones = new ArrayList<>();
        List<Pictures> pics = new ArrayList<>();

        Model_Char m_1 = new Model_Char("Alcatel", 170.0, 68.0, "Телефон хороший");// создаем отдельную модель телефона
        Model_Char m_2 = new Model_Char("Meizu", 120.0, 57.0, "Отличное качество, надо брать");
        Model_Char m_3 = new Model_Char("Xiaomi", 115.0, 59.0, "Скидочка 20 проц");
        Model_Char m_4 = new Model_Char("Nokia", 115.0, 59.0, "Скидочка 30 проц");


        models.add(m_1);
        models.add(m_2);
        models.add(m_3);
        models.add(m_4);

        for (Model_Char model : models) {
            serviceModel.addModel(model);
            serviceModel.save(model);
        }


        Pictures pic_1 = new Pictures(m_1, "black", m_1.getName(), servicePicture.loadImage("/images/alcatel.jpeg"));
        Pictures pic_2 = new Pictures(m_2, "white", m_2.getName(), servicePicture.loadImage("/images/meizu.jpeg"));
        Pictures pic_3 = new Pictures(m_3, "yellow", m_3.getName(), servicePicture.loadImage("/images/xiaomi.jpeg"));
        Pictures pic_4 = new Pictures(m_4, "white", m_4.getName(), servicePicture.loadImage("/images/nokia.jpg"));
        Pictures pic_5 = new Pictures(m_4, "black", m_4.getName(), servicePicture.loadImage("/images/nokia-black.jpg"));
        Pictures pic_6 = new Pictures(m_4, "black", m_4.getName(), servicePicture.loadImage("/images/iphoneX.jpg"));

        //pic_1.getPhones().getPhone_id();
        // добавление картинок в общий контейнер
        pics.add(pic_1);
        pics.add(pic_2);
        pics.add(pic_3);
        pics.add(pic_4);
        pics.add(pic_5);
        pics.add(pic_6);

        for (Pictures picture : pics) {
            servicePicture.addPictures(picture);
            servicePicture.save(picture);
        }

        Phones p1 = new Phones(m_1, 120000, "black","брать или не брать хз");
        Phones p2 = new Phones(m_2, 34000, "white","брать или не брать кто знает?");
        //добавление нескольких телефонов к одной модели
        Phones p3 = new Phones(m_3, 15000, "yellow", "Можно найти и лучше");
        Phones p4 = new Phones(m_4, 15000, "white","крэкер, так сказать");
        Phones p5 = new Phones(m_4, 15000, "black","дешевый, практичный");

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

    public void startChatServer() throws IOException {
        GreetServer server=new GreetServer();
        server.start(5555);
    }
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        autentication();
        getModelsAndPhones();
        startChatServer();
    }
}
