package com.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Base64;

/**
 * Created by Admin on 29.11.2018.
 */
@Getter
@Setter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "PICTURES")
public class Pictures {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "COLOR")
    private String color;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "PHONES_ID", nullable = true)
    private Phones phones;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_ID", nullable = false)
    private Model_Char model;

    @Column(name = "NAME")
    private String name;

    @Lob
    @Column(name = "PICTURE", columnDefinition = "BLOB", nullable = false)
    private byte[] bytes;

    public Pictures(Phones phones, String color, String name, byte[] bytes) {
        this.color = color;
        this.phones = phones;
        this.name = name;
        this.bytes = bytes;
    }

    public Pictures(Model_Char model, String color, String name, byte[] bytes) {
        this.color = color;
        this.model = model;
        this.name = name;
        this.bytes = bytes;
    }
    public Pictures(Model_Char model, String color, String name) {
        this.color = color;
        this.model = model;
        this.name = name;
    }

    public Pictures() {
    }

    public String encodeImage() throws IOException, URISyntaxException {
        String file = Base64.getEncoder().encodeToString(bytes);
        return file;
    }
}
