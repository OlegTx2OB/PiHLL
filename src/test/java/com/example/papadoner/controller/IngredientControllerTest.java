package com.example.papadoner.controller;

import com.example.papadoner.dto.IngredientDto;
import com.example.papadoner.model.Ingredient;
import com.example.papadoner.service.IngredientService;
import com.example.papadoner.service.impl.IngredientServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {
    @Mock
    private IngredientService mIngredientService;

    @InjectMocks
    private IngredientController mIngredientController;

    @Test
    void createIngredientTest() {
        // Setup
        Ingredient ingredient = new Ingredient();
        Set<String> donerNames = Set.of("doner1", "doner2");

        // Act
        ResponseEntity<Void> response = mIngredientController.createIngredient(ingredient, donerNames);

        // Assert
        verify(mIngredientService).createIngredient(ingredient, donerNames);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void getIngredientByNameTest() {
        // Setup
        String ingredientName = "ingredient1";
        IngredientDto expectedIngredientDto = new IngredientDto(1L, ingredientName);

        when(mIngredientService.getIngredientByName(ingredientName)).thenReturn(expectedIngredientDto);

        // Act
        ResponseEntity<IngredientDto> response = mIngredientController.getIngredientByName(ingredientName);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedIngredientDto, response.getBody());
    }

    @Test
    void updateIngredientTest() {
        // Setup
        long id = 1L;
        Ingredient updatedIngredient = new Ingredient();
        Set<String> donerNames = Set.of();
        IngredientDto expectedIngredientDto = new IngredientDto(id, "UpdatedIngredient");

        when(mIngredientService.updateIngredient(id, updatedIngredient, donerNames)).thenReturn(expectedIngredientDto);

        // Act
        ResponseEntity<IngredientDto> response = mIngredientController.updateIngredient(id, updatedIngredient, donerNames);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedIngredientDto, response.getBody());
    }

    @Test
    void deleteIngredientTest() {
        // Setup
        long id = 1L;

        // Act
        ResponseEntity<Void> response = mIngredientController.deleteIngredient(id);

        // Assert
        verify(mIngredientService).deleteIngredient(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getAllIngredientsTest() {
        // Setup
        List<IngredientDto> expectedIngredientDtos = List.of(new IngredientDto(1L, "Ingredient1"), new IngredientDto(2L, "Ingredient2"));

        when(mIngredientService.getAllIngredients()).thenReturn(expectedIngredientDtos);

        // Act
        ResponseEntity<List<IngredientDto>> response = mIngredientController.getAllIngredients();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedIngredientDtos.size(), response.getBody().size());
        assertEquals(expectedIngredientDtos, response.getBody());
    }

    @Test
    void correctConstructorTest() {
        IngredientService ingredientServiceMock = mock(IngredientServiceImpl.class);

        IngredientController donerController = new IngredientController(ingredientServiceMock);

        assertEquals(ingredientServiceMock, donerController.getMIngredientService());
    }
}
