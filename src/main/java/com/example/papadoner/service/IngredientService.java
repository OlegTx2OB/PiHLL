package com.example.papadoner.service;

import com.example.papadoner.model.Ingredient;

import java.util.List;

public interface IngredientService {


    public Ingredient createIngredient(Ingredient ingredient);
    public Ingredient getIngredientById(short id);
    public Ingredient updateIngredient(short id, Ingredient updatedIngredient);
    public void deleteIngredient(short id);
    public List<Ingredient> getAllIngredients();
}
