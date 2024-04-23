package com.example.papadoner.mapper;

import com.example.papadoner.dto.DonerDto;
import com.example.papadoner.model.Doner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DonerMapper {

    public static DonerDto toDto(Doner doner) {
        return new DonerDto(
                doner.getId(),
                doner.getName(),
                IngredientMapper.toDtos(doner.getIngredients()),
                PriceByWeightMapper.toDtos(doner.getPricesByWeight()));
    }

    public static List<DonerDto> toDtos(List<Doner> doners) {
        List<DonerDto> donerDtos = new ArrayList<>();
        if (doners != null) {
            for (Doner doner : doners) {
                donerDtos.add(toDto(doner));
            }
        }
        return donerDtos;
    }
}