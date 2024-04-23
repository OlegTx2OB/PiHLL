package com.example.papadoner.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.junit.jupiter.api.Test;

public class DonerTest {

    @Test
    void idField_correctGenerationTypeStrategy_DonerTest() throws NoSuchFieldException {
        var idField = Doner.class.getDeclaredField("id");
        var generatedValueAnnotation = idField.getAnnotation(GeneratedValue.class);
        assertNotNull(generatedValueAnnotation);
        assertEquals(GenerationType.IDENTITY, generatedValueAnnotation.strategy());
    }

    @Test
    public void allArgsConstructor_CorrectConstructorData_DonerTest() {
        // Setup
        long id = 1;
        String name = "Test Doner";
        List<Ingredient> ingredients = new ArrayList<>();
        List<PriceByWeight> pricesByWeight = new ArrayList<>();

        // Act
        Doner doner = new Doner(id, name, ingredients, pricesByWeight);

        // Assert
        assertEquals(id, doner.getId());
        assertEquals(name, doner.getName());
        assertEquals(ingredients, doner.getIngredients());
        assertEquals(pricesByWeight, doner.getPricesByWeight());
    }

    @Test
    public void gettersSetters_CorrectGettersSetters_DonerTest() {
        // Setup
        Doner doner = new Doner();

        long id = 1;
        String name = "Test Doner";
        List<Ingredient> ingredients = new ArrayList<>();
        List<PriceByWeight> pricesByWeight = new ArrayList<>();

        // Act
        doner.setId(id);
        doner.setName(name);
        doner.setIngredients(ingredients);
        doner.setPricesByWeight(pricesByWeight);

        // Assert
        assertEquals(id, doner.getId());
        assertEquals(name, doner.getName());
        assertEquals(ingredients, doner.getIngredients());
        assertEquals(pricesByWeight, doner.getPricesByWeight());
    }
}