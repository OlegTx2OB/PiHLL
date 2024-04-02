package com.example.papadoner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonerDto {

    private long id;
    private String name;
    private List<IngredientDto> ingredientDtos = new ArrayList<>();
    private List<PriceByWeightDto> priceByWeightDtos = new ArrayList<>();
}
