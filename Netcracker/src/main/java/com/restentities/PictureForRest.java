package com.restentities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Admin on 03.03.2019.
 */
@Setter
@Getter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "PICTUREFORREST")
public class PictureForRest {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COLOR")
    private String color;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "PHONE_ID", nullable = true)
    private PhoneForRest phone;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_ID", nullable = false)
    private ModelForRest model;

    @Lob
    @Column(name = "PICTURE", columnDefinition = "BLOB", nullable = false)
    private byte[] bytes;

    public PictureForRest(PhoneForRest phone, String color, String name, byte[] bytes) {
        this.color = color;
        this.phone = phone;
        this.name = name;
        this.bytes = bytes;
    }

    public PictureForRest(ModelForRest model, String color, String name, byte[] bytes) {
        this.color = color;
        this.model = model;
        this.name = name;
        this.bytes = bytes;
    }

    public PictureForRest() {
    }

}