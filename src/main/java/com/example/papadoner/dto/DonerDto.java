package com.example.papadoner.dto;

import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonerDto {

    private short id;
    private String name;
    private List<IngredientDto> ingredientDtos;
    private Set<PriceByWeightDto> priceByWeightDtos;
}
