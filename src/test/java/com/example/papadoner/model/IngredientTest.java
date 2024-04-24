package com.example.papadoner.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class IngredientTest {

    @Test
    void idField_correctGenerationTypeStrategy_IngredientTest() throws NoSuchFieldException {
        var idField = Ingredient.class.getDeclaredField("id");
        var generatedValueAnnotation = idField.getAnnotation(GeneratedValue.class);
        assertNotNull(generatedValueAnnotation);
        assertEquals(GenerationType.IDENTITY, generatedValueAnnotation.strategy());
    }

    @Test
    void allArgsConstructor_CorrectConstructorData_IngredientTest() {
        // Setup
        long id = 1;
        String name = "Test Ingredient";
        List<Doner> doners = new ArrayList<>();

        // Act
        Ingredient ingredient = new Ingredient(id, name, doners);

        // Assert
        assertEquals(id, ingredient.getId());
        assertEquals(name, ingredient.getName());
        assertEquals(doners, ingredient.getDoners());
    }

    @Test
    void gettersSetters_CorrectGettersSetters_IngredientTest() {
        // Setup
        Ingredient ingredient = new Ingredient();

        long id = 1;
        String name = "Test Ingredient";
        List<Doner> doners = new ArrayList<>();

        // Act
        ingredient.setId(id);
        ingredient.setName(name);
        ingredient.setDoners(doners);

        // Assert
        assertEquals(id, ingredient.getId());
        assertEquals(name, ingredient.getName());
        assertEquals(doners, ingredient.getDoners());
    }
}