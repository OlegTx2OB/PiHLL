package com.example.papadoner.mapper;

import com.example.papadoner.dto.DonerDto;
import com.example.papadoner.model.Doner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DonerMapper {
    public DonerDto toDto(Doner doner);
}






//    public DonerDto toDto(Doner doner) {
//        List<IngredientDto> ingredientDtos = doner.getIngredients().stream()
//                .map(ingredient -> IngredientDto.builder()
//                        .name(ingredient.getName())
//                        .quantity(ingredient.getQuantity())
//                        .build())
//                .collect(Collectors.toList());
//
//        Set<PriceByWeightDto> priceByWeightDtos = doner.getPriceByWeights().stream()
//                .map(priceByWeight -> PriceByWeightDto.builder()
//                        .weight(priceByWeight.getWeight())
//                        .price(priceByWeight.getPrice())
//                        .build())
//                .collect(Collectors.toSet());
//
//        return DonerDto.builder()
//                .id(doner.getId())
//                .name(doner.getName())
//                .ingredientDtos(ingredientDtos)
//                .priceByWeightDtos(priceByWeightDtos)
//                .build();
//    }
