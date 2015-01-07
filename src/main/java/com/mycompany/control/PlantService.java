package com.mycompany.control;

import com.mycompany.entity.Plant;
import com.mycompany.entity.PlantDAO;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by marianne on 10.12.14.
 */
public class PlantService {

    @Inject
    PlantDAO plantDAO;

    public void savePlant(Plant plant) {
        plantDAO.savePlant(plant);
    }

    public Plant findPlantById(Long id) {
        return plantDAO.findPlantById(id);
    }

    public List<Plant> findPlants(String searchString) {
        return plantDAO.findPlants(searchString);
    }

    public List<Plant> findAllPlants() {
        return plantDAO.findAllPlants();
    }

    public void deletePlant(Long id) {
        plantDAO.deletePlant(id);
    }

}
