package com.restentities;

import com.entities.Phones;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.repository.ModelRepository;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by Admin on 03.03.2019.
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneForRest {
    ModelRepository modelRepository;

    private Long phoneId;

    private ModelForRest model;

    private long price;

    String link;

    private String color;

    private String picturelink;

    public PhoneForRest( ModelForRest model, long price, String color) {
        this.model = model;
        this.price = price;
        this.color= color;
    }

    public PhoneForRest(ModelForRest model, String color) {
        this.model = model;
        this.color = color;
    }

    public PhoneForRest (){
    }

    public PhoneForRest(Phones p) {
        this.color = p.getColor();
        this.model= new ModelForRest(p.getModelName(),p.getPrice(), p.getPrice());
        this.price = p.getPrice();
        this.link = "http://localhost:8080/phonepage/infphone?phoneId="+p.getPhone_id();
        //картинка
        String picturelink = "";
        try {
            picturelink = "data:image/jpeg;base64,"+p.getModel().getPictures().iterator().next().encodeImage();;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.picturelink = picturelink;
    }
    @Override
    public String toString() {
        return "Phone{" +
                "color='" + color + '\'' +
                ", price=" + price +
                ", model=" + model +
                //", phoneId=" + phoneId +
                '}';
    }
}
