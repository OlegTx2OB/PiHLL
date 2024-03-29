package com.example.papadoner.mapper;

import com.example.papadoner.dto.IngredientDto;
import com.example.papadoner.model.Ingredient;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class IngredientMapper {
    public IngredientDto toDto(Ingredient ingredient) {
        return new IngredientDto(ingredient.getId(), ingredient.getName());
    }

    public Set<IngredientDto> toDtos(Set<Ingredient> ingredients) {
        Set<IngredientDto> ingredientDtos = new HashSet<>();
        for (Ingredient ingredient : ingredients) {
            ingredientDtos.add(toDto(ingredient));
        }
        return ingredientDtos;
    }
}