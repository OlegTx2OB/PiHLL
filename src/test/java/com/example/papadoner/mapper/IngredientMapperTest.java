package com.example.papadoner.mapper;

import com.example.papadoner.dto.IngredientDto;
import com.example.papadoner.model.Ingredient;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IngredientMapperTest {

    @Test
    void toDtos_CorrectConvertation_IngredientMapperTest() {
        //Setup
        Ingredient ingredient = new Ingredient();
        ingredient.setId(1);
        ingredient.setName("name");

        //Act
        List<IngredientDto> ingredientDtos = IngredientMapper.toDtos(List.of(ingredient));

        //Assert
        assertNotNull(ingredientDtos);
        assertEquals(1, ingredientDtos.size());
        IngredientDto ingredientDto = ingredientDtos.get(0);
        assertEquals(ingredient.getId(), ingredientDto.getId());
        assertEquals(ingredient.getName(), ingredientDto.getName());
    }

    @Test
    void toDtos_GetEmptyListIfPassNullArg_IngredientMapperTest() {
        //Act
        List<IngredientDto> ingredientDtos = IngredientMapper.toDtos(null);

        //Assert
        assertNotNull(ingredientDtos);
        assertTrue(ingredientDtos.isEmpty());
    }

    @Test
    void toDtos_GetEmptyListIfPassEmptyList_IngredientMapperTest() {
        //Act
        List<IngredientDto> ingredientDtos = IngredientMapper.toDtos(List.of());

        //Assert
        assertNotNull(ingredientDtos);
        assertTrue(ingredientDtos.isEmpty());
    }
}
