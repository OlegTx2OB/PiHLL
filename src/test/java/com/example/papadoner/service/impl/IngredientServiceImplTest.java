package com.example.papadoner.service.impl;

import com.example.papadoner.dto.IngredientDto;
import com.example.papadoner.model.Doner;
import com.example.papadoner.model.Ingredient;
import com.example.papadoner.repository.DonerRepository;
import com.example.papadoner.repository.IngredientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @Mock
    private IngredientRepository mIngredientRepository;

    @Mock
    private DonerRepository mDonerRepository;

    @InjectMocks
    private IngredientServiceImpl mIngredientService;

    @Test
    void correctConstructorTest() {
        DonerRepository donerRepositoryMock = mock(DonerRepository.class);
        IngredientRepository ingredientRepositoryMock = mock(IngredientRepository.class);

        IngredientServiceImpl ingredientService = new IngredientServiceImpl(ingredientRepositoryMock, donerRepositoryMock);

        assertEquals(donerRepositoryMock, ingredientService.getMDonerRepository());
        assertEquals(ingredientRepositoryMock, ingredientService.getMIngredientRepository());
    }

    @Test
    void createIngredient_Success() {
        // Arrange
        Ingredient ingredient = new Ingredient(1L, "Ingredient", new ArrayList<>());
        Set<String> donerNames = Set.of("Doner1", "Doner2");
        when(mIngredientRepository.save(ingredient)).thenReturn(ingredient);
        when(mDonerRepository.findDonersByName("Doner1")).thenReturn(List.of(new Doner()));
        when(mDonerRepository.findDonersByName("Doner2")).thenReturn(List.of(new Doner()));

        // Act
        mIngredientService.createIngredient(ingredient, donerNames);

        // Assert
        verify(mIngredientRepository, times(1)).save(ingredient);
        verify(mDonerRepository, times(2)).save(any());
    }

    @Test
    void getIngredientByName_ExistingIngredient_Success() {
        // Arrange
        String name = "Ingredient";
        Ingredient ingredient = new Ingredient(1L, name, List.of());
        when(mIngredientRepository.findByName(name)).thenReturn(Optional.of(ingredient));

        // Act
        IngredientDto result = mIngredientService.getIngredientByName(name);

        // Assert
        assertEquals(name, result.getName());
    }

    @Test
    void getIngredientByName_NonExistingIngredient_ThrowsException() {
        // Arrange
        String name = "NonExistingIngredient";
        when(mIngredientRepository.findByName(name)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> mIngredientService.getIngredientByName(name));
    }

    @Test
    void updateIngredient_Success() {
        // Arrange
        long id = 1L;
        Ingredient oldIngredient = new Ingredient(id, "Old Ingredient", new ArrayList<>());
        Ingredient newIngredient = new Ingredient(id, "New Ingredient", new ArrayList<>());

        Set<String> donerNames = new HashSet<>();
        donerNames.add("Doner1");
        donerNames.add("Doner2");

        when(mIngredientRepository.findById(id)).thenReturn(Optional.of(oldIngredient));
        when(mIngredientRepository.save(newIngredient)).thenReturn(newIngredient);
        when(mDonerRepository.findDonersByName("Doner1")).thenReturn(List.of(new Doner()));
        when(mDonerRepository.findDonersByName("Doner2")).thenReturn(List.of(new Doner()));

        // Act
        IngredientDto result = mIngredientService.updateIngredient(id, newIngredient, donerNames);

        // Assert
        assertEquals("New Ingredient", result.getName());
        verify(mIngredientRepository, times(1)).save(newIngredient);
        verify(mDonerRepository, times(2)).save(any());
    }

    @Test
    void deleteIngredient_Success() {
        // Arrange
        long id = 1L;
        Ingredient ingredient = new Ingredient(id, "Ingredient", List.of(new Doner()));
        when(mIngredientRepository.findById(id)).thenReturn(Optional.of(ingredient));

        // Act
        mIngredientService.deleteIngredient(id);

        // Assert
        verify(mIngredientRepository, times(1)).deleteById(id);
        verify(mDonerRepository, times(1)).save(any());
    }

    @Test
    void deleteIngredient_IngredientNotFound() {
        // Arrange
        long id = 1L;
        when(mIngredientRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> mIngredientService.deleteIngredient(id));
        verify(mIngredientRepository, never()).deleteById(id);
        verify(mDonerRepository, never()).save(any());
    }

    @Test
    void getAllIngredients_Success() {
        // Arrange
        List<Ingredient> ingredientList = List.of(new Ingredient(), new Ingredient());
        when(mIngredientRepository.findAll()).thenReturn(ingredientList);

        // Act
        List<IngredientDto> result = mIngredientService.getAllIngredients();

        // Assert
        assertEquals(ingredientList.size(), result.size());
    }

    @Test
    void getDoners_ReturnEmptyArrayListIfArgIsNullTest() {
        Set<String> donerNames = null;
        List<Doner> result = mIngredientService.getDoners(donerNames);

        assertEquals(new ArrayList<Doner>(), result);
    }

    @Test
    void getDoners_ReturnArrayListTest() {
        // Setup
        Set<String> donerNames = Set.of("doner1", "doner2");
        List<Doner> doners1 = List.of(new Doner());
        List<Doner> doners2 = List.of(new Doner());

        // Stubbing the method call for both names
        when(mDonerRepository.findDonersByName("doner1")).thenReturn(doners1);
        when(mDonerRepository.findDonersByName("doner2")).thenReturn(doners2);

        // Act
        List<Doner> result = mIngredientService.getDoners(donerNames);

        // Assert
        assertEquals(2, result.size());
    }

}
