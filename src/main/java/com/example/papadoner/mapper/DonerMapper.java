package com.example.papadoner.mapper;

import com.example.papadoner.dto.DonerDto;
import com.example.papadoner.model.Doner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DonerMapper {

    private final IngredientMapper ingredientMapper;

    @Autowired
    public DonerMapper(IngredientMapper ingredientMapper) {
        this.ingredientMapper = ingredientMapper;
    }

    public DonerDto toDto(Doner doner) {
        return new DonerDto(doner.getId(),
                doner.getName(),
                ingredientMapper.toDtos(doner.getIngredients()));
    }

    public List<DonerDto> toDtos(List<Doner> doners) {
        List<DonerDto> donerDtos = new ArrayList<>();
        for (Doner doner : doners) {
            donerDtos.add(toDto(doner));
        }
        return donerDtos;
    }
}