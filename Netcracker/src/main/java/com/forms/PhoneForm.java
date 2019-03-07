package com.forms;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Admin on 20.12.2018.
 */
@Getter
@Setter
public class PhoneForm {
    private Long phone_id;
    private String model_name;
    private String color_name;
    private long price;
    private List<MultipartFile> pictures;
    private Double size;
    private Double diagonal;
    private String description;
    private String comment;
}