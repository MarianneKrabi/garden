package com.mycompany.entity;

import javax.persistence.*;
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
    @Temporal(TemporalType.DATE)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plant plant = (Plant) o;

        if (createDate != null ? !createDate.equals(plant.createDate) : plant.createDate != null) return false;
        //if (id != null ? !id.equals(plant.id) : plant.id != null) return false;
        if (imagePath != null ? !imagePath.equals(plant.imagePath) : plant.imagePath != null) return false;
        if (name != plant.name) return false;
        if (xposition != null ? !xposition.equals(plant.xposition) : plant.xposition != null) return false;
        if (yposition != null ? !yposition.equals(plant.yposition) : plant.yposition != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + (xposition != null ? xposition.hashCode() : 0);
        result = 31 * result + (yposition != null ? yposition.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        return result;
    }
}
