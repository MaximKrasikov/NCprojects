package com.entities;

/**
 * Created by Admin on 28.11.2018.
 */

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "PHONES")
public class Phones implements Serializable{

    @Id
    @Column(name = "ID",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phone_id;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_ID", nullable = false)
    private Model_Char model;

    @OneToMany(mappedBy = "phones", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Pictures> pictures;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "COLOR")
    private String color;

    @Column(name = "COMMENT", nullable = true)
    private String comment;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE")
    private Date creation_date;

    @ManyToMany
    private Set<User> users;

    public Phones( Model_Char model, Double price, String color,String comment,List<Pictures> pictures) {
        this.model = model;
        this.price = price;
        this.color = color;
        this.pictures=pictures;
        this.comment= comment;
        this.creation_date = new Date();
    }
    public Phones( Model_Char model, Double price, String color, String comment){
        this.model = model;
        this.price = price;
        this.color = color;
        this.comment= comment;
        this.creation_date = new Date();
    }

    public Phones(Model_Char model, String color) {
        this.model = model;
        this.color = color;
    }
    public Phones (){

    }
    public String getModelName() {
        return this.model.getName();
    }
    public Double getSize(){
        return this.model.getSize();
    }
    public Double getDiagonal(){
        return this.model.getDiagonal();
    }
    public String getDescription() {
        return this.model.getDescription();
    }
}
