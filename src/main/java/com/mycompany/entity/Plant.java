package com.mycompany.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Plant entity
 */

@Entity
public class Plant {

    @Id
    @GeneratedValue
    private Long id;
    private PlantType name;
    private String imagePath;
    private Integer xposition;
    private Integer yposition;
    private Date createDate;

    /**
     * Default constructor for JAX-RS (object <> JSON serialization)
     */
    public Plant() {
    }

    public Plant(PlantType name, String imagePath, Integer xposition, Integer yposition,  Date createDate) {
        this.name = name;
        this.imagePath = imagePath;
        this.xposition = xposition;
        this.yposition = yposition;
        this.createDate = createDate;
    }

    // -------- getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlantType getName() {
        return name;
    }

    public void setName(PlantType name) {
        this.name = name;
    }

    public String getImagePath() { return imagePath; }

    public void setImagePath(String imagePath) {this.imagePath = imagePath; }

    public Integer getXPosition () { return xposition; }

    public void setXPosition (Integer xposition) { this.xposition = xposition; }

    public Integer getYPosition () { return yposition; }

    public void setYPosition (Integer yposition) { this.yposition = yposition; }

    public Date getCreateDate() { return createDate; }

    public void setCreateDate(Date date) { this.createDate = date; }
}
