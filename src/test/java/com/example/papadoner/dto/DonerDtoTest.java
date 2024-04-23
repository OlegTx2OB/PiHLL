package com.example.papadoner.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DonerDtoTest {

    @Test
    public void allArgsConstructor_CorrectConstructorData_DonerDtoTest() {
        //Setup
        long id = 1;
        String name = "name";
        List<IngredientDto> ingredientDtos = List.of();
        List<PriceByWeightDto> priceByWeightDtos = List.of();

        //Act
        DonerDto donerDto = new DonerDto(id, name, ingredientDtos, priceByWeightDtos);

        //Assert
        assertEquals(id, donerDto.getId());
        assertEquals(name, donerDto.getName());
        assertEquals(ingredientDtos, donerDto.getIngredientDtos());
        assertEquals(priceByWeightDtos, donerDto.getPriceByWeightDtos());
    }

    @Test
    public void gettersSetters_CorrectGettersSetters_DonerDtoTest() {
        //Setup
        DonerDto donerDto = new DonerDto();

        long id = 1;
        String name = "Test Doner";
        List<IngredientDto> ingredientDtos = List.of();
        List<PriceByWeightDto> priceByWeightDtos = List.of();

        //Act
        donerDto.setId(id);
        donerDto.setName(name);
        donerDto.setIngredientDtos(ingredientDtos);
        donerDto.setPriceByWeightDtos(priceByWeightDtos);

        //Assert
        assertEquals(id, donerDto.getId());
        assertEquals(name, donerDto.getName());
        assertEquals(ingredientDtos, donerDto.getIngredientDtos());
        assertEquals(priceByWeightDtos, donerDto.getPriceByWeightDtos());
    }
}
