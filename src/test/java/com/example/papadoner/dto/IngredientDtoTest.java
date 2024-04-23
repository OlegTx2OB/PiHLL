package com.example.papadoner.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientDtoTest {

    @Test
    public void allArgsConstructor_CorrectConstructorData_IngredientDtoTest() {
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
    public void gettersSetters_CorrectGettersSetters_IngredientDtoTest() {
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
}