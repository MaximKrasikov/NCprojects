package com.forms;

import com.entities.Pictures;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Admin on 20.12.2018.
 */
@Getter
@Setter
public class PhoneForm {
    private String model_name;
    private Long phone_id;
    private String color_name;
    private Double price;
    private Pictures pictures;
    private Double size;
    private Double diagonal;
    private String description;
}