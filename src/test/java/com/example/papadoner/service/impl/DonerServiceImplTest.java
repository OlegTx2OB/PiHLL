package com.example.papadoner.service.impl;

import com.example.papadoner.dto.DonerDto;
import com.example.papadoner.mapper.DonerMapper;
import com.example.papadoner.model.Doner;
import com.example.papadoner.model.Ingredient;
import com.example.papadoner.model.PriceByWeight;
import com.example.papadoner.repository.DonerRepository;
import com.example.papadoner.repository.IngredientRepository;
import com.example.papadoner.repository.PriceByWeightRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DonerServiceImplTest {

    @Mock
    private IngredientRepository mIngredientRepository;
    @Mock
    private PriceByWeightRepository mPriceByWeightRepository;
    @Mock
    private DonerRepository mDonerRepository;

    @InjectMocks
    private DonerServiceImpl mDonerService;

    @Test
    void correctConstructorTest() {
        DonerRepository donerRepositoryMock = mock(DonerRepository.class);
        IngredientRepository ingredientRepositoryMock = mock(IngredientRepository.class);
        PriceByWeightRepository priceByWeightRepositoryMock = mock(PriceByWeightRepository.class);

        DonerServiceImpl donerService = new DonerServiceImpl(donerRepositoryMock, priceByWeightRepositoryMock, ingredientRepositoryMock);

        assertEquals(donerRepositoryMock, donerService.getMDonerRepository());
        assertEquals(ingredientRepositoryMock, donerService.getMIngredientRepository());
        assertEquals(priceByWeightRepositoryMock, donerService.getMPriceByWeightRepository());
    }

    @Test
    void getDonersByName_DonersFoundTest() {
        //Setup
        String name = "TestDoner";
        List<Doner> doners = List.of(
                new Doner(1L, name, List.of(), List.of()),
                new Doner(2L, name, List.of(), List.of())
        );
        when(mDonerRepository.findDonersByName(name)).thenReturn(doners);

        // Act
        List<DonerDto> result = mDonerService.getDonersByName(name);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
        assertEquals(name, result.get(0).getName());
        assertEquals(name, result.get(1).getName());
    }

    @Test
    void getDonersByName_NoDonersFoundTest() {
        //Setup
        String name = "NonExistentDoner";
        when(mDonerRepository.findDonersByName(name)).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> mDonerService.getDonersByName(name));
    }

    @Test
    void saveDonerInIngredients_CorrectAddTest() {
        //Setup
        List<Ingredient> ingredients = new ArrayList<>(Collections.singleton(new Ingredient(1L, "ingr", new ArrayList<>())));
        Doner doner = new Doner(1L, "doner", new ArrayList<>(), new ArrayList<>());

        //Act
        mDonerService.saveDonerInIngredients(doner, ingredients);

        //Assert
        verify(mIngredientRepository, times(1)).save(any(Ingredient.class)); // Проверяем вызов save только один раз
        assertEquals(1, ingredients.get(0).getDoners().size()); // Проверяем, что ингредиент был добавлен к списку doners
        assertEquals(doner, ingredients.get(0).getDoners().get(0)); // Проверяем, что doner добавлен правильно
    }

    @Test
    void getIngredients_correctThrowExceptionTest() {
        Set<String> ingredientNames = Set.of("ingr1", "ingr2");

        assertThrows(EntityNotFoundException.class, () -> mDonerService.getIngredients(ingredientNames));
    }

    @Test
    void getPriceByWeights_correctThrowExceptionTest() {
        Set<Long> priceByWeightIds = Set.of(1L, 2L);

        assertThrows(EntityNotFoundException.class, () -> mDonerService.getPriceByWeights(priceByWeightIds));
    }

    @Test
    void getIngredients_ReturnEmptyArrayListIfArgIsNullTest() {
        Set<String> ingredientNames = null;
        List<Ingredient> result = mDonerService.getIngredients(ingredientNames);

        assertEquals(new ArrayList<Ingredient>(), result);
    }

    @Test
    void getPriceByWeights_ReturnEmptyArrayListIfArgIsNullTest() {
        Set<Long> priceByWeightIds = null;
        List<PriceByWeight> result = mDonerService.getPriceByWeights(priceByWeightIds);
        assertEquals(List.of(), result);
    }

    @Test
    void getIngredients_ReturnArrayListTest() {
        //Setup
        Set<String> ingredientNames = Set.of("ingr1", "ingr2");

        //Act
        when(mIngredientRepository.findByName("ingr1")).thenReturn(Optional.of(new Ingredient()));
        when(mIngredientRepository.findByName("ingr2")).thenReturn(Optional.of(new Ingredient()));

        List<Ingredient> result = mDonerService.getIngredients(ingredientNames);

        //Assert
        assertEquals(2, result.size());
    }

    @Test
    void getPriceByWeights_ReturnArrayListTest() {
        //Setup
        Set<Long> priceByWeightIds = Set.of(1L, 2L);

        //Act
        when(mPriceByWeightRepository.findById(1L)).thenReturn(Optional.of(new PriceByWeight()));
        when(mPriceByWeightRepository.findById(2L)).thenReturn(Optional.of(new PriceByWeight()));

        List<PriceByWeight> result = mDonerService.getPriceByWeights(priceByWeightIds);

        //Assert
        assertEquals(2, result.size());
    }

}
