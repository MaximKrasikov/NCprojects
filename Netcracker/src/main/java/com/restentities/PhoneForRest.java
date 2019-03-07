package com.restentities;

import com.entities.Phones;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by Admin on 03.03.2019.
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneForRest {
    private Long phoneId;

    private ModelForRest model;

    private List<PictureForRest> pictures;

    private long price;

    String link= "http://localhost:8080/index";

    private String color=null;

    public PhoneForRest( ModelForRest model, long price, String color) {
        this.model = model;
        this.price = price;
        //this.color = color;
    }

    public PhoneForRest(ModelForRest model, String color) {
        this.model = model;
        this.color = color;
    }

    public PhoneForRest (){
    }

    public PhoneForRest(Phones p) {
        //this.phoneId = p.getPhone_id();
        this.color = p.getColor();
        this.model= new ModelForRest(p.getModelName(),p.getPrice(), p.getPrice());
        this.price = p.getPrice();
        //need to set model after
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
