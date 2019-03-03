package com.restentities;

import com.entities.Model_Char;
import com.entities.Phones;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 03.03.2019.
 */
@Setter
@Getter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name= "MODELFORREST")
public class ModelForRest {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long modelId;

    @Column(name = "NAME", length = 64, nullable = false)
    private String  modelName;

    @OneToMany(mappedBy = "model", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<PhoneForRest> phones;

    @OneToMany(mappedBy = "model", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<PictureForRest> pictures;

    @Column(name = "PRICE_MIN", nullable = false)
    private long priceMin;

    @Column(name = "PRICE_MAX", nullable = false)
    private long priceMax;

    public ModelForRest(String modelName,long priceMin,long priceMax) {
        this.modelName=modelName;
        this.priceMax=priceMax;
        this.priceMin=priceMin;
    }
    public ModelForRest() {
    }

    public ModelForRest(Model_Char e) {
        this.modelId = e.getId();
        this.modelName = e.getName();
        Long maxprice = (long) 0;
        for (Phones p:e.getPhones()) {
            if (p.getPrice() > maxprice) {
                maxprice = p.getPrice();
            }
        }
        Long minprice = maxprice;
        for (Phones p:e.getPhones()) {
            if (p.getPrice() < minprice) {
                minprice = p.getPrice();
            }
        }
        this.priceMax = maxprice;
        this.priceMin = minprice;
        this.phones = new HashSet<PhoneForRest>();
        for (Phones p:e.getPhones())  {
            PhoneForRest pfr = new PhoneForRest(p);
            //	pfr.setModel(this);
            this.phones.add(pfr);
        }
    }

    @Override
    public String toString() {
        return "Model{" +
                "priceMax=" + priceMax +
                ", priceMin=" + priceMin +
                ", phones=" + phones +
                ", modelName='" + modelName + '\'' +
                ", modelId=" + modelId +
                '}';
    }

}