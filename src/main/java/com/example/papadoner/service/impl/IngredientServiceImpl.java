package com.example.papadoner.service.impl;

import com.example.papadoner.model.Ingredient;
import com.example.papadoner.repository.IngredientRepository;
import com.example.papadoner.service.IngredientService;
import jakarta.persistence.EntityNotFoundException;
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
    public Ingredient getIngredientById(long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient with id " + id + " not found"));
    }

    @Override
    public Ingredient updateIngredient(long id, Ingredient updatedIngredient) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);
        if (optionalIngredient.isPresent()) {
            Ingredient ingredient = optionalIngredient.get();
//            ingredient.setName(updatedIngredient.getName());
//            ingredient.setDoners(updatedIngredient.getDoners());
            return ingredientRepository.save(ingredient);
        } else {
            throw new EntityNotFoundException("Ingredient with id " + id + " not found");
        }
    }

    @Override
    public void deleteIngredient(long id) {
        ingredientRepository.deleteById(id);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }
}
