package com.example.papadoner.service.impl;

import com.example.papadoner.cache.EntityCache;
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
    private final EntityCache<String, Ingredient> cache;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository,
                                 EntityCache<String, Ingredient> cache) {
        this.ingredientRepository = ingredientRepository;
        this.cache = cache;
    }

    @Override
    public Ingredient createIngredient(Ingredient ingredient) {
        cache.put(ingredient.getName(), ingredient);
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient getIngredientById(long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient with id " + id + " not found"));
    }

    @Override
    public Ingredient updateIngredient(long id, Ingredient newIngredient) {
        if (newIngredient == null) {
            throw new IllegalArgumentException("fun updateIngredient cannot get null argument");
        }
        Optional<Ingredient> optionalOldIngredient = ingredientRepository.findById(id);
        if (optionalOldIngredient.isPresent()) {
            Ingredient oldIngredient = optionalOldIngredient.get();
            newIngredient.setId(oldIngredient.getId());

            cache.remove(oldIngredient.getName());
            cache.put(newIngredient.getName(), newIngredient);
            return ingredientRepository.save(newIngredient);
        } else {
            throw new EntityNotFoundException("Ingredient with id " + id + " not found");
        }
    }

    @Override
    public void deleteIngredient(long id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        ingredient.ifPresent(value -> cache.remove(value.getName()));
        ingredientRepository.deleteById(id);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        List<Ingredient> ingredients = cache.getAll();
        if (!ingredients.isEmpty()) {
            return ingredients;
        } else {
            return ingredientRepository.findAll();
        }
    }

    @Override
    public Ingredient findIngredientByName(String name) {
        Ingredient ingredient = cache.get(name);
        if (ingredient != null) {
            return ingredient;
        } else {
            throw new EntityNotFoundException("Ingredient with name " + name + " not found");
        }
    }
}
