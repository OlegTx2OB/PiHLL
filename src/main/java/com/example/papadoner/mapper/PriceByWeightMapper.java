package com.example.papadoner.mapper;

import com.example.papadoner.dto.PriceByWeightDto;
import com.example.papadoner.model.PriceByWeight;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceByWeightMapper {
    public PriceByWeightDto toDto(PriceByWeight priceByWeight);
}
