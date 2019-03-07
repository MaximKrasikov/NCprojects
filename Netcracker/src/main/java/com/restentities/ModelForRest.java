package com.restentities;

import com.entities.Model_Char;
import com.entities.Phones;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 03.03.2019.
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModelForRest {
    private Long modelId= null;

    private String  modelName;

    private Set<PhoneForRest> phones;

    private List<PictureForRest> pictures;

    private long priceMin;

    private long priceMax;

    public ModelForRest(String modelName,long priceMin,long priceMax) {
        this.modelName=modelName;
        this.priceMax=priceMax;
        this.priceMin=priceMin;
    }
    public ModelForRest() {
    }

    public ModelForRest(Model_Char e) {
        //this.modelId = e.getId();
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