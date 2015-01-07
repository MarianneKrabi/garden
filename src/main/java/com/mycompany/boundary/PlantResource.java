package com.mycompany.boundary;

import com.mycompany.control.PlantService;
import com.mycompany.entity.Plant;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/plant")
@Stateless
public class PlantResource {

    @Inject
    PlantService plantService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findPlants(@QueryParam("searchString") String searchString) {
        List<Plant> plants;
        if (searchString != null) {
            plants = plantService.findPlants(searchString);
        } else {
            plants = plantService.findAllPlants();
        }
        return Response.ok(plants).build();
    }

    @POST
    public Response savePlant(@Context UriInfo uriInfo, Plant plant)
            throws URISyntaxException {
        plantService.savePlant(plant);
        return Response.created(
                new URI(uriInfo.getRequestUri() + "/" + plant.getId())).build();
    }

    @DELETE
    @Path("/{plantId}")
    public Response deletePlant(@PathParam("plantId") Long plantId) {
        plantService.deletePlant(plantId);
        return Response.ok().build();
    }

}

