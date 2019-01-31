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
public class Pictures implements Serializable {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COLOR")
    private String color;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "PHONES_ID", nullable = true)
    private Phones phones;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_ID", nullable = false)
    private Model_Char model;

    @Column(name = "NAME")
    private String name;

    @Lob
    @Column(name = "PICTURE", columnDefinition = "BLOB", nullable = false)
    private String bytes;

    public Pictures(Phones phones, String color, String name, String bytes) {
        this.color = color;
        this.phones = phones;
        this.name = name;
        this.bytes = bytes;
    }

    public Pictures(Model_Char model, String color, String name, String bytes) {
        this.color = color;
        this.model = model;
        this.name = name;
        this.bytes = bytes;
    }

    public Pictures() {
    }

    public String getModelName() {
        return phones.getModelName();
    }
}
