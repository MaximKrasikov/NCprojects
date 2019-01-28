package com.Init;

import com.entities.Model_Char;
import com.entities.Phones;
import com.entities.Pictures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.repository.ModelService.ModelServiceImpl;
import com.repository.PhoneService.PhoneServiceImpl;
import com.repository.PictureService.PictureServiceImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Admin on 07.12.2018.
 */
//тестовый класс
@Component
public class DataInit implements ApplicationRunner {

    public static PhoneServiceImpl servicePhone;
    public static PictureServiceImpl servicePicture;
    public static ModelServiceImpl serviceModel;

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public DataInit(PhoneServiceImpl servicePhone, PictureServiceImpl servicePicture, ModelServiceImpl serviceModel) {
        this.servicePhone = servicePhone;
        this.servicePicture = servicePicture;
        this.serviceModel=serviceModel;
    }

    public static List<Phones> getModelsAndPhones() throws ParseException, IOException, SQLException, URISyntaxException {
        List<Model_Char> models = new ArrayList<>();
        List<Phones> phones = new ArrayList<>();
        List<Pictures> pics= new ArrayList<>();

        Model_Char m_1 = new  Model_Char("Alcatel",170.0,68.0,"Телефон хороший");// создаем отдельную модель телефона
        Model_Char m_2 = new  Model_Char("Meizu",120.0,57.0,"Отличное качество, надо брать");
        Model_Char m_3 = new  Model_Char("Xiaomi",115.0,59.0,"Скидочка 20проц");

        models.add(m_1);
        models.add(m_2);
        models.add(m_3);

        for(Model_Char model:models){
            serviceModel.addModel(model);
            serviceModel.save(model);
        }

        Pictures pic_1= new Pictures(m_1,"black",m_1.getName(),servicePicture.useImageFromBase("/images/alcatel.jpeg"));
        Pictures pic_2= new Pictures(m_2,"white",m_2.getName(),servicePicture.useImageFromBase("/images/meizu.jpeg"));
        Pictures pic_3= new Pictures(m_3,"yellow",m_3.getName(),servicePicture.useImageFromBase("/images/xiaomi.jpeg"));

        pics.add(pic_1);
        pics.add(pic_2);
        pics.add(pic_3);

        for(Pictures picture:pics){
            servicePicture.addPictures(picture);
            servicePicture.save(picture);
        }

        Phones p1 = new Phones(m_1,120000.0,"black",pic_1);
        Phones p2 = new Phones(m_2,34000.0,"white",pic_2);
        Phones p3 = new Phones(m_3,15000.0,"yellow",pic_3);

        phones.add(p1);
        phones.add(p2);
        phones.add(p3);

        for (Phones p : phones) {
            servicePhone.addPhone(p);
            servicePhone.save(p);
        }
        return phones;
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        getModelsAndPhones();
    }

}
