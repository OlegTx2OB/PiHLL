package com.example.papadoner.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IngredientDtoTest {

    @Test
    void allArgsConstructor_CorrectConstructorData_IngredientDtoTest() {
        // Setup
        long id = 1;
        String name = "name";

        // Act
        IngredientDto ingredientDto = new IngredientDto(id, name);

        // Assert
        assertEquals(id, ingredientDto.getId());
        assertEquals(name, ingredientDto.getName());
    }

    @Test
    void gettersSetters_CorrectGettersSetters_IngredientDtoTest() {
        // Setup
        IngredientDto ingredientDto = new IngredientDto();

        long id = 1;
        String name = "Test Ingredient";

        // Act
        ingredientDto.setId(id);
        ingredientDto.setName(name);

        // Assert
        assertEquals(id, ingredientDto.getId());
        assertEquals(name, ingredientDto.getName());
    }

    @Test
    void noArgsConstructor_DefaultValues_IngredientDtoTest() {
        // Act
        IngredientDto ingredientDto = new IngredientDto();

        // Assert
        assertEquals(0, ingredientDto.getId());
        assertNull(ingredientDto.getName());
    }
}