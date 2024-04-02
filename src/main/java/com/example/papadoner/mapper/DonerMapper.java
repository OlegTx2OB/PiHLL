package com.example.papadoner.mapper;

import com.example.papadoner.dto.DonerDto;
import com.example.papadoner.model.Doner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DonerMapper {

    private final IngredientMapper mIngredientMapper;
    private final PriceByWeightMapper mPriceByWeightMapper;

    @Autowired
    public DonerMapper(IngredientMapper ingredientMapper,
                       PriceByWeightMapper priceByWeightMapper) {
        this.mIngredientMapper = ingredientMapper;
        this.mPriceByWeightMapper = priceByWeightMapper;
    }

    public DonerDto toDto(Doner doner) {
        return new DonerDto(
                doner.getId(),
                doner.getName(),
                mIngredientMapper.toDtos(doner.getIngredients()),
                mPriceByWeightMapper.toDtos(doner.getPricesByWeight()));
    }

    public List<DonerDto> toDtos(List<Doner> doners) {
        List<DonerDto> donerDtos = new ArrayList<>();
        if (doners != null) {
            for (Doner doner : doners) {
                donerDtos.add(toDto(doner));
            }
        }
        return donerDtos;
    }
}