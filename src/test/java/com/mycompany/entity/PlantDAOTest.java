package com.mycompany.entity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

public class PlantDAOTest {

    private PlantDAO plantDAO;

    /*@Mock
    private EntityManager entityManager;
*/
    EntityTransaction tx;
    Plant plant1 = new Plant(PlantType.Daisy, "daisy.gif", 0, 0, new Date());
    Plant plant2 = new Plant(PlantType.Flower, "flower.gif", 1, 1, new Date());
    Plant plant3 = new Plant(PlantType.Tulip, "tulip.gif", 2, 2, new Date());

    @Before
    public void initEM() {
        plantDAO = new PlantDAO();

        plantDAO.entityManager = Persistence.createEntityManagerFactory("integration-test").createEntityManager();
        tx = plantDAO.entityManager.getTransaction();

        TypedQuery<Plant> query = plantDAO.entityManager.createQuery("SELECT e FROM Plant e", Plant.class);
        List<Plant> plants = query.getResultList();
        for (Plant p : plants) {
            plantDAO.entityManager.remove(p);
        }
/*        tx.begin();
        plantDAO.entityManager.persist(plant1);
        plantDAO.entityManager.persist(plant2);
        plantDAO.entityManager.persist(plant3);
        tx.commit();*/
    }

    @Test
    public void testSavePlant() throws Exception {
        tx.begin();
        plantDAO.savePlant(plant1);
        plantDAO.savePlant(plant2);
        plantDAO.savePlant(plant3);
        tx.commit();

        assertTrue("Expect plant1 to be inserted", plantDAO.entityManager.contains(plant1));
        assertTrue("Expect plant2 to be inserted", plantDAO.entityManager.contains(plant2));
        assertTrue("Expect plant3 to be inserted", plantDAO.entityManager.contains(plant3));

        assertNotNull("Expect persisted plant to have an id", plant1.getId());
        assertNotNull("Expect persisted plant to have an id", plant2.getId());
        assertNotNull("Expect persisted plant to have an id", plant3.getId());
    }

    @Test
    public void testFindAllPlants() throws Exception {
        tx.begin();
        plantDAO.entityManager.persist(plant1);
        plantDAO.entityManager.persist(plant2);
        plantDAO.entityManager.persist(plant3);
        tx.commit();

        List<Plant> plants = plantDAO.findAllPlants();

        assertEquals("Expecting findAllPlants to return 3", 3, plants.size());

        assertTrue("Expect plant1 to be returned from query findAllPlants", plants.contains(plant1));
        assertTrue("Expect plant2 to be returned from query findAllPlants", plants.contains(plant2));
        assertTrue("Expect plant3 to be returned from query findAllPlants", plants.contains(plant3));
    }

    @Test
    public void testFindPlantById() throws Exception {
        tx.begin();
        plantDAO.entityManager.persist(plant1);
        tx.commit();

        assertEquals("Find plant by id should return plant1", plant1, plantDAO.findPlantById(plant1.getId()));
        assertTrue(plantDAO.findPlantById(plant1.getId()+1) == null);
    }

    @Test
    public void testFindPlants() throws Exception {
        Plant plant1double = new Plant(PlantType.Daisy, "daisy.gif", 0, 0, new Date());
        tx.begin();
        plantDAO.entityManager.persist(plant1);
        plantDAO.entityManager.persist(plant2);
        plantDAO.entityManager.persist(plant1double);
        tx.commit();

        assertEquals(2, plantDAO.findPlants("Daisy").size());
        assertEquals(1, plantDAO.findPlants("Flower").size());
        assertEquals(0, plantDAO.findPlants("Tulip").size());
    }

    @Test
    public void testDeletePlant() throws Exception {
        tx.begin();
        plantDAO.entityManager.persist(plant1);
        tx.commit();

        plantDAO.deletePlant(plant1.getId());
        assertFalse(plantDAO.entityManager.contains(plant1));
    }
}