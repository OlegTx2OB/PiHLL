package com.example.papadoner.mapper;

import com.example.papadoner.dto.IngredientDto;
import com.example.papadoner.model.Ingredient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IngredientMapper {
    public IngredientDto toDto(Ingredient ingredient) {
        return new IngredientDto(ingredient.getId(), ingredient.getName());
    }

    public List<IngredientDto> toDtos(List<Ingredient> ingredients) {
        List<IngredientDto> ingredientDtos = new ArrayList<>();
        if (ingredients != null) {
            for (Ingredient ingredient : ingredients) {
                ingredientDtos.add(toDto(ingredient));
            }
        }
        return ingredientDtos;
    }
}