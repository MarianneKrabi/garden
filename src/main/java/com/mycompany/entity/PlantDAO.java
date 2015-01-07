package com.mycompany.entity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by marianne on 29.12.14.
*/

public class PlantDAO {
    @PersistenceContext
    protected EntityManager entityManager;

    public void savePlant(Plant plant) {
        entityManager.persist(plant);
    }

    public List<Plant> findAllPlants() {
        TypedQuery<Plant> query = entityManager.createQuery(
                "SELECT e FROM Plant e", Plant.class);
        List<Plant> plants = (List<Plant>) query.getResultList();

        return plants;
    }

    public Plant findPlantById(Long id) {
        return entityManager.find(Plant.class, id);
    }

    public List<Plant> findPlants(String searchString) {

        String[] searchTerms = new String[]{searchString};
        if (searchString.contains(", ")) {
            searchTerms = searchString.split(", ");
        } else if (searchString.contains("; ")) {
            searchTerms = searchString.split("; ");
        } else if (searchString.contains(",")) {
            searchTerms = searchString.split(",");
        } else if (searchString.contains(";")) {
            searchTerms = searchString.split(";");
        } else if (searchString.contains(" ")) {
            searchTerms = searchString.split(" ");
        }

        Map<String, String> paramMapping = new HashMap<String, String>();


        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT e FROM Plant e where e.name = ");

        for(int i=0; i<searchTerms.length; i++) {
            String paramName = "searchParam" + i;
            String paramValue = searchTerms[i];
            paramMapping.put(paramName, paramValue);

            queryBuilder.append(":").append(paramName);
            if (i != searchTerms.length - 1)
                queryBuilder.append(" OR");
        }

        TypedQuery<Plant> query = entityManager.createQuery(queryBuilder.toString(),
                Plant.class);


        for(String paramName : paramMapping.keySet()) {
            String paramValue = paramMapping.get(paramName);
            PlantType paramType = PlantType.valueOf(paramValue);
            query.setParameter(paramName, paramType);
        }

        return query.getResultList();
    }

    public void deletePlant(Long id) {
        Plant plant = findPlantById(id);
        if (plant != null) {
            entityManager.remove(plant);
        }
    }
}
