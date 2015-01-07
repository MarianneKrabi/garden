package com.mycompany.control;

import com.mycompany.entity.Plant;
import com.mycompany.entity.PlantDAO;
import com.mycompany.entity.PlantType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Date;

import static org.mockito.Mockito.verify;

public class PlantServiceTest extends BaseTestClass {

    private PlantService plantService;

    @Mock
    private PlantDAO plantDAO;

    @Before
    public void before() {
        plantService = new PlantService();
        plantService.plantDAO = plantDAO;
    }

    @Test
    public void savePlantTest() {
        Plant plant = new Plant(PlantType.Daisy, "daisy.gif", 0, 0, new Date());
        plantService.savePlant(plant);

        verify(plantDAO).savePlant(plant);
    }

    @Test
    public void findPlantsTest() {
        plantService.findPlants("Daisy");

        verify(plantDAO).findPlants("Daisy");
    }

    @Test
    public void findPlantByIdTest() {
        plantService.findPlantById(1l);

        verify(plantDAO).findPlantById(1l);
    }

    @Test
    public void findAllPlantsTest() {
        plantService.findAllPlants();

        verify(plantDAO).findAllPlants();
    }

    @Test
    public void deletePlant() {
        plantService.deletePlant(1l);

        verify(plantDAO).deletePlant(1l);
    }
}

