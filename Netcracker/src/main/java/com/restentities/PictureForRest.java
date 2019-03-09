package com.restentities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Admin on 03.03.2019.
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PictureForRest {
    private Long id= null;

    private String name;

    private String color=null;

    private PhoneForRest phone;

    private ModelForRest model;

    private byte[] bytes;

    public PictureForRest(PhoneForRest phone, String color, String name, byte[] bytes) {
       // this.color = color;
        this.phone = phone;
        this.name = name;
        this.bytes = bytes;
    }

    public PictureForRest(ModelForRest model, String color, String name, byte[] bytes) {
        //this.color = color;
        this.model = model;
        this.name = name;
        this.bytes = bytes;
    }

    public PictureForRest() {
    }

}