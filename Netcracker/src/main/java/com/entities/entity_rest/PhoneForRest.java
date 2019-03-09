package com.entities.entity_rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name= "PHONE_FOR_REST")
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

    @Column(name = "COLOR")
    private String color;

    public PhoneForRest( ModelForRest model, long price, String color) {
        this.model = model;
        this.price = price;
        this.color = color;
    }
    public PhoneForRest(ModelForRest model, String color) {
        this.model = model;
        this.color = color;
    }
    public PhoneForRest (){
    }
    @Override
    public String toString() {
        return "Phone{" +
                "color='" + color + '\'' +
                ", price=" + price +
                ", model=" + model +
                ", phoneId=" + phoneId +
                '}';
    }
}
