package com.example.papadoner.mapper;

import com.example.papadoner.dto.IngredientDto;
import com.example.papadoner.dto.PriceByWeightDto;
import com.example.papadoner.model.Ingredient;
import com.example.papadoner.model.PriceByWeight;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PriceByWeightMapper {

    public PriceByWeightDto toDto(PriceByWeight p) {
        return new PriceByWeightDto(p.getId(), p.getWeight(), p.getPrice());
    }

    public Set<PriceByWeightDto> toDtos(Set<PriceByWeight> ps) {
        Set<PriceByWeightDto> priceByWeightDtos = new HashSet<>();
        if (ps != null) {
            for (PriceByWeight p : ps) {
                priceByWeightDtos.add(toDto(p));
            }
        }
        return priceByWeightDtos;
    }

    public List<PriceByWeightDto> toDtos(List<PriceByWeight> ps) {
        List<PriceByWeightDto> priceByWeightDtos = new ArrayList<>();
        if (ps != null) {
            for (PriceByWeight p : ps) {
                priceByWeightDtos.add(toDto(p));
            }
        }
        return priceByWeightDtos;
    }
}
