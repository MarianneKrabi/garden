package com.mycompany.boundary;

import com.mycompany.control.PlantService;
import com.mycompany.entity.Plant;
import com.mycompany.entity.PlantType;
import org.jboss.resteasy.test.TestPortProvider;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class PlantResourceTest extends AbstractResourceTest {

    PlantService plantService;
    PlantResource plantResource;

    @Override
    public Object prepareTestResource() {
        plantService = mock(PlantService.class);
        plantResource = new PlantResource();
        plantResource.plantService = plantService;
        return plantResource;
    }

    @Test
    public void testFindPlantsWithSearchString() throws Exception {

        String url = TestPortProvider.generateURL("/rest/plant?searchString=hallo");
        Client client = ClientBuilder.newClient();

        List<Plant> plants = new ArrayList<Plant>();
        Plant plant = new Plant(PlantType.Tulip, "www.abc.de", 0, 0, new Date());
        when(plantService.findPlants(eq("hallo"))).thenReturn(plants);

        plants.add(plant);
        Response response = client.target(url).request().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).get();
        List<Plant> responsePlants = response.readEntity(new GenericType<List<Plant>>() {
        });

        assertEquals("Expecting find plants with searchstring to be 1", 1, responsePlants.size());
        Plant returnPlant = responsePlants.get(0);
        assertEquals(plant.getName(), returnPlant.getName());
        assertEquals(plant.getCreateDate(), returnPlant.getCreateDate());
        assertEquals(plant.getImagePath(), returnPlant.getImagePath());
        assertEquals(plant.getXPosition(), returnPlant.getXPosition());
        assertEquals(plant.getYPosition(), returnPlant.getYPosition());

        verify(plantService).findPlants(eq("hallo"));

        client.close();
    }

    @Test
    public void testFindPlantsWithoutSearchString() throws Exception {
        String url = TestPortProvider.generateURL("/rest/plant");
        Client client = ClientBuilder.newClient();

        List<Plant> plants = new ArrayList<Plant>();
        Plant plant = new Plant(PlantType.Tulip, "www.abc.de", 0, 0, new Date());
        when(plantService.findAllPlants()).thenReturn(plants);

        plants.add(plant);
        Response response = client.target(url).request().header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON).get();
        List<Plant> responsePlants = response.readEntity(new GenericType<List<Plant>>() {
        });

        assertEquals("Expecting find all plants to be 1", 1, responsePlants.size());
        Plant returnPlant = responsePlants.get(0);
        assertEquals(plant.getName(), returnPlant.getName());
        assertEquals(plant.getName(), returnPlant.getName());
        assertEquals(plant.getCreateDate(), returnPlant.getCreateDate());
        assertEquals(plant.getImagePath(), returnPlant.getImagePath());
        assertEquals(plant.getXPosition(), returnPlant.getXPosition());
        assertEquals(plant.getYPosition(), returnPlant.getYPosition());

        verify(plantService).findAllPlants();

        client.close();
    }

    @Test
    public void testSavePlant() throws Exception {
        Plant plant = new Plant(PlantType.Tulip, "www.abc.de", 0, 0, new Date());
        plant.setId(1l);

        String url = TestPortProvider.generateURL("/rest/plant");
        Client client = ClientBuilder.newClient();

        Response response = client.target(url).request().post(Entity.entity(plant, MediaType.APPLICATION_JSON));

        String expectedPath = url + "/1";
        assertEquals("Expecting save plant to return created path", expectedPath, response.getLocation().toString());

        verify(plantService).savePlant(plant);

        client.close();
    }

    @Test
    public void testDeletePlant() throws Exception {
        Client client = ClientBuilder.newClient();

        String url = TestPortProvider.generateURL("/rest/plant/1");
        Response response = client.target(url).request().delete();

        assertEquals("Expecting delete request to return ok status", 200, response.getStatus());

        verify(plantService).deletePlant((long) (1));
    }
}