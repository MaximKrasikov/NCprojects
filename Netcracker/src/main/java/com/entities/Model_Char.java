package com.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Admin on 29.11.2018.
 */
@Getter
@Setter
@Entity
@Table(name= "MODEL_CHAR")
public class Model_Char {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 64, nullable = false)
    private String name;

    @OneToMany(mappedBy = "model", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Phones> phones;

    @OneToMany(mappedBy = "model", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<Pictures> pictures;

    @Column(name = "DIAGONAL")
    private Double diagonal;

    @Column(name = "SIZE")
    private Double size;

    @Column(name = "DESCRIPTION")
    private String description;

    public Model_Char(String name,Double diagonal,Double size, String description) {
        this.name=name;
        this.diagonal = diagonal;
        this.size = size;
         this.description = description;
    }
    public Model_Char(String name) {
        this.name = name;
    }

    public Model_Char() {
    }
}
