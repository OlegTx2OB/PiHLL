package com.example.papadoner.service.impl;

import com.example.papadoner.cache.EntityCache;
import com.example.papadoner.dto.IngredientDto;
import com.example.papadoner.mapper.IngredientMapper;
import com.example.papadoner.model.Doner;
import com.example.papadoner.model.Ingredient;
import com.example.papadoner.repository.DonerRepository;
import com.example.papadoner.repository.IngredientRepository;
import com.example.papadoner.service.IngredientService;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository mIngredientRepository;
    private final DonerRepository mDonerRepository;
    private final IngredientMapper mIngredientMapper;
    private final EntityCache<String, IngredientDto> mCache;

    boolean isCacheInitialized = false;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository,
                                 DonerRepository donerRepository,
                                 IngredientMapper ingredientMapper,
                                 EntityCache<String, IngredientDto> cache) {
        this.mIngredientRepository = ingredientRepository;
        this.mDonerRepository = donerRepository;
        this.mIngredientMapper = ingredientMapper;
        this.mCache = cache;
    }

    @Override
    public IngredientDto createIngredient(Ingredient ingredient, @Nullable Set<String> donerNames) {
        if (!isCacheInitialized) {
            fillCache();
        }
        ingredient = setDoners(ingredient, donerNames);
        mCache.put(ingredient.getName(), mIngredientMapper.toDto(ingredient));
        return mIngredientMapper.toDto(mIngredientRepository.save(ingredient));
    }

    @Override
    public IngredientDto getIngredientByName(String name) {
        IngredientDto ingredientDto = mCache.get(name);
        if (ingredientDto != null) {
            return ingredientDto;
        } else {
            Optional<Ingredient> ingredient = mIngredientRepository.findByName(name);
            return mIngredientMapper.toDto(ingredient
                    .orElseThrow(() -> new EntityNotFoundException("Ingredient with name " + name + " not found")));
        }
    }

    @Override
    public IngredientDto updateIngredient(long id, Ingredient newIngredient, @Nullable Set<String> donerNames) {
        newIngredient = setDoners(newIngredient, donerNames);

        Optional<Ingredient> optionalOldIngredient = mIngredientRepository.findById(id);
        if (optionalOldIngredient.isPresent()) {
            Ingredient oldIngredient = optionalOldIngredient.get();
            newIngredient.setId(oldIngredient.getId());

            mCache.remove(oldIngredient.getName());
            mCache.put(newIngredient.getName(), mIngredientMapper.toDto(newIngredient));
            return mIngredientMapper.toDto(mIngredientRepository.save(newIngredient));
        } else {
            throw new EntityNotFoundException("Ingredient with id " + id + " not found");
        }
    }

    @Override
    public void deleteIngredient(long id) {
        if (!isCacheInitialized) {
            fillCache();
        }
        Optional<Ingredient> ingredient = mIngredientRepository.findById(id);
        ingredient.ifPresent(value -> mCache.remove(value.getName()));
        mIngredientRepository.deleteById(id);
    }

    @Override
    public Set<IngredientDto> getAllIngredients() {
        if (!isCacheInitialized) {
            fillCache();
        }
        return new HashSet<>(mCache.getAll());
    }

    private Ingredient setDoners(Ingredient ingredient, Set<String> donerNames) {
        if (donerNames != null) {
            Set<Doner> doners = new HashSet<>();
            for (String name : donerNames) {
                doners.addAll(
                        mDonerRepository.findDonersByName(name)
                                .orElseThrow(() -> new EntityNotFoundException(
                                        "Doner with name " + name + " not found")));
            }
            ingredient.setDoners(doners);
        }
        return ingredient;
    }

    private void fillCache() {
        List<Ingredient> ingredients = mIngredientRepository.findAll();
        for (Ingredient ingredient : ingredients) {
            mCache.put(ingredient.getName(), mIngredientMapper.toDto(ingredient));
        }
        isCacheInitialized = true;
    }
}
