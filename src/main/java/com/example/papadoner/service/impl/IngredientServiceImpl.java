package com.example.papadoner.service.impl;

import com.example.papadoner.dto.IngredientDto;
import com.example.papadoner.mapper.IngredientMapper;
import com.example.papadoner.model.Doner;
import com.example.papadoner.model.Ingredient;
import com.example.papadoner.repository.DonerRepository;
import com.example.papadoner.repository.IngredientRepository;
import com.example.papadoner.service.IngredientService;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
@Log4j2
@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository mIngredientRepository;
    private final DonerRepository mDonerRepository;

    @Autowired
    public IngredientServiceImpl(IngredientRepository ingredientRepository,
                                 DonerRepository donerRepository) {
        this.mIngredientRepository = ingredientRepository;
        this.mDonerRepository = donerRepository;
    }

    @Override
    public void createIngredient(Ingredient ingredient, @Nullable Set<String> donerNames) {
        List<Doner> doners = getDoners(donerNames);
        ingredient.getDoners().addAll(doners);
        mIngredientRepository.save(ingredient);
        saveIngredientInDoners(ingredient, doners);
    }

    private void saveIngredientInDoners(Ingredient ingredient, List<Doner> doners) {
        for (Doner doner : doners) {
            doner.getIngredients().add(ingredient);
            mDonerRepository.save(doner);
        }
    }

    @Override
    public IngredientDto getIngredientByName(String name) {
        Optional<Ingredient> ingredient = mIngredientRepository.findByName(name);
        return IngredientMapper.toDto(ingredient
                .orElseThrow(() -> new EntityNotFoundException("Ingredient with name " + name + " not found")));
    }

    @Override
    public IngredientDto updateIngredient(long id, Ingredient newIngredient, @Nullable Set<String> donerNames) {
        List<Doner> doners = getDoners(donerNames);

        Ingredient oldIngredient = mIngredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient with id " + id + " not found"));
        newIngredient.setId(oldIngredient.getId());

        newIngredient.getDoners().addAll(doners);
        IngredientDto ingredientDto = IngredientMapper.toDto(mIngredientRepository.save(newIngredient));
        saveIngredientInDoners(newIngredient, doners);
        return ingredientDto;
    }

    @Override
    public void deleteIngredient(long id) {
        Ingredient ingredient = mIngredientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Ingredient with id " + id + " not found"));
        List<Doner> doners = ingredient.getDoners();

        for (Doner doner : doners) {
            doner.getIngredients().remove(ingredient);
            mDonerRepository.save(doner);
        }
        mIngredientRepository.deleteById(id);
    }

    @Override
    public List<IngredientDto> getAllIngredients() {
        return IngredientMapper.toDtos(mIngredientRepository.findAll());
    }

    List<Doner> getDoners(Set<String> donerNames) {
        if (donerNames != null) {
            List<Doner> doners = new ArrayList<>();
            for (String name : donerNames) {
                doners.addAll(mDonerRepository.findDonersByName(name));
            }
            return doners;
        }
        return new ArrayList<>();
    }
}
