package com.example.papadoner.mapper;

import com.example.papadoner.dto.IngredientDto;
import com.example.papadoner.model.Ingredient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientMapper {
    public IngredientDto toDto(Ingredient ingredient);
}