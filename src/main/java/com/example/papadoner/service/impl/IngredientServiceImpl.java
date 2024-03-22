package com.example.papadoner.service.impl;

import com.example.papadoner.model.Ingredient;
import com.example.papadoner.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    public IngredientServiceImpl() {

    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        return null;
    }

    @Override
    public Ingredient getIngredientById() {
        return null;
    }

    @Override
    public Ingredient updateIngredient(short id, Ingredient updatedIngredient) {
        return null;
    }

    @Override
    public void deleteIngredient(short id) {

    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return null;
    }
}
