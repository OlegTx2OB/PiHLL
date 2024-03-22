package com.example.papadoner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonerDto {

    private short id;
    private String name;
    private List<IngredientDto> ingredients;
    private Set<PriceByWeightDto> priceByWeights;
}
