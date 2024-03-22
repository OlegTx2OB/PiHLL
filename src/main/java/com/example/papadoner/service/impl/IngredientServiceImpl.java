package com.example.papadoner.service.impl;

import com.example.papadoner.model.Ingredient;
import com.example.papadoner.repository.IngredientRepository;
import com.example.papadoner.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient getIngredientById(short id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient with id " + id + " not found"));
    }

    @Override
    public Ingredient updateIngredient(short id, Ingredient updatedIngredient) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);
        if (optionalIngredient.isPresent()) {
            Ingredient ingredient = optionalIngredient.get();
            ingredient.setName(updatedIngredient.getName());
            ingredient.setDoners(ingredient.getDoners());
            return ingredientRepository.save(ingredient);
        } else {
            throw new RuntimeException("Ingredient with id " + id + " not found");
        }
    }

    @Override
    public void deleteIngredient(short id) {
        ingredientRepository.deleteById(id);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}
