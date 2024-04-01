package com.example.papadoner.service;

import com.example.papadoner.dto.IngredientDto;
import com.example.papadoner.model.Ingredient;

import java.util.Set;

public interface IngredientService {

    IngredientDto createIngredient(Ingredient ingredient, Set<String> donerNames);

    IngredientDto getIngredientByName(String name);

    IngredientDto updateIngredient(long id, Ingredient newIngredient, Set<String> donerNames);

    void deleteIngredient(long id);

    Set<IngredientDto> getAllIngredients();
}
