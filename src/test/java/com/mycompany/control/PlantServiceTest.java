package com.mycompany.control;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;

public class PlantServiceTest extends BaseTestClass {

    private PlantService plantService;

    @Mock
    private EntityManager entityManager;

    @Before
    public void before() {
        plantService = new PlantService();
    }


    @Test
    public void test() {
        int i = 1;
        assertEquals(1, i);
    }
}

