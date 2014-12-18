package com.mycompany.control;

import com.mycompany.entity.Plant;
import com.mycompany.entity.PlantType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PlantServiceTest extends BaseTestClass {

    private PlantService plantService;

    private List<Plant> plantList;

    private Plant plant1;
    private Plant plant2;
    private Plant plant3;

    @Mock
    private EntityManager entityManager;

    @Before
    public void before() {
        plantService = new PlantService();

        plant1 = new Plant(PlantType.Daisy, "daisy.gif", 0,0, new Date());
        plant2 = new Plant(PlantType.Flower, "flower.gif", 1, 1, new Date());
        plant3 = new Plant(PlantType.Tulip, "tulip.gif", 2, 2, new Date());

        plantList = Arrays.asList(plant1, plant2, plant3);
    }

    @Test
    public void test() {
        /*assertTrue("3 plants inserted in List", plantList.size() == 3);

        for (int i = 0; i<plantList.size(); ++i) {
            plantService.savePlant(plantList.get(i));
        }

        assertTrue("Plant1 should be saved", entityManager.contains(plant1));
        assertTrue("Plant2 should be saved", entityManager.contains(plant2));
        assertTrue("Plant3 should be saved", entityManager.contains(plant3));*/
    }
}

