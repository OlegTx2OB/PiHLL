package com.example.papadoner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonerDto {

    private long id;
    private String name;
    private Set<IngredientDto> ingredientDtos;
    private Set<PriceByWeightDto> priceByWeightDtos;
}
