package com.example.papadoner.mapper;

import com.example.papadoner.dto.DonerDto;
import com.example.papadoner.dto.IngredientDto;
import com.example.papadoner.dto.PriceByWeightDto;
import com.example.papadoner.model.Doner;
import com.example.papadoner.model.Ingredient;
import com.example.papadoner.model.PriceByWeight;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DonerMapperTest {

    @Test
    public void toDtos_CorrectConvertation_DonerMapperTest() {
        //Setup
        Doner doner = new Doner();
        doner.setId(1);
        doner.setName("name");

        Ingredient ingredient = new Ingredient();
        ingredient.setId(1);
        ingredient.setName("name");

        PriceByWeight priceByWeight = new PriceByWeight(1, 500, 0.5);

        doner.setIngredients(List.of(ingredient));
        doner.setPricesByWeight(List.of(priceByWeight));

        //Act
        List<DonerDto> donerDtos = DonerMapper.toDtos(List.of(doner));

        //Assert
        assertNotNull(donerDtos);
        assertEquals(1, donerDtos.size());
        DonerDto donerDto = donerDtos.get(0);
        assertEquals(doner.getId(), donerDto.getId());
        assertEquals(doner.getName(), donerDto.getName());

        IngredientDto ingredientDto = donerDto.getIngredientDtos().get(0);
        assertEquals(ingredient.getId(), ingredientDto.getId());
        assertEquals(ingredient.getName(), ingredientDto.getName());

        PriceByWeightDto priceByWeightDto = donerDto.getPriceByWeightDtos().get(0);
        assertEquals(priceByWeight.getId(), priceByWeightDto.getId());
        assertEquals(priceByWeight.getWeight(), priceByWeightDto.getWeight());
        assertEquals(priceByWeight.getPrice(), priceByWeightDto.getPrice());
    }

    @Test
    public void toDtos_GetEmptyListIfPassNullArg_DonerMapperTest() {
        //Act
        List<DonerDto> donerDtos = DonerMapper.toDtos(null);

        //Assert
        assertNotNull(donerDtos);
        assertTrue(donerDtos.isEmpty());
    }

    @Test
    public void toDtos_GetEmptyListIfPassEmptyList_DonerMapperTest() {
        //Act
        List<DonerDto> donerDtos = DonerMapper.toDtos(List.of());

        //Assert
        assertNotNull(donerDtos);
        assertTrue(donerDtos.isEmpty());
    }
}
