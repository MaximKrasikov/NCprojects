package com.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Admin on 29.11.2018.
 */
@Getter
@Setter
@Entity
@Table(name = "PICTURES")
public class Pictures implements Serializable{
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COLOR")
    private String color;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL", nullable = false)
    private Model_Char model;

    @Column(name = "NAME")
    private String name;

    @Lob
    @Column(name = "PICTURE",columnDefinition = "BLOB", nullable = false)
    private String bytes;

    public Pictures( Model_Char model,String color,String name, String bytes) {
        this.color = color;
        this.model = model;
        this.name=name;
        this.bytes =bytes;
    }
    public Pictures( Model_Char model,String color) {
        this.color = color;
        this.model = model;
    }
    public Pictures() {
    }
    public String getModelName(){
        return model.getName();
    }
}
