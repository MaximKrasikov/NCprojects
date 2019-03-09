package com.entities.entity_rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name= "MODEL_FOR_REST")
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
