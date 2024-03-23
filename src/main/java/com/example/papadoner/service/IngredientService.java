package com.example.papadoner.service;

import com.example.papadoner.model.Ingredient;

import java.util.List;

public interface IngredientService {


    public Ingredient createIngredient(Ingredient ingredient);
    public Ingredient getIngredientById(long id);
    public Ingredient updateIngredient(long id, Ingredient updatedIngredient);
    public void deleteIngredient(long id);
    public List<Ingredient> getAllIngredients();
}
