package com.restentities;

import com.entities.Phones;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Admin on 03.03.2019.
 */
@Setter
@Getter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "PHONEFORREST")
public class PhoneForRest {

    @Id
    @Column(name = "ID",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId;


    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_ID", nullable = false)
    private ModelForRest model;

    @OneToMany(mappedBy = "phone", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<PictureForRest> pictures;

    @Column(name = "PRICE")
    private long price;

    @Column(name = "LINK", nullable = false)
    String link= "http://192.168.99.100:8080/index";

    @Column(name = "COLOR")
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
