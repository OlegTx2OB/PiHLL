package com.example.papadoner.mapper;

import com.example.papadoner.dto.PriceByWeightDto;
import com.example.papadoner.model.PriceByWeight;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PriceByWeightMapperTest {

    @Test
    void toDtos_CorrectConvertation_PriceByWeightMapperTest() {
        PriceByWeight entity1 = new PriceByWeight(1, 500, 0.5);
        PriceByWeight entity2 = new PriceByWeight(2, 600, 0.43);

        List<PriceByWeight> entityList = List.of(entity1, entity2);
        List<PriceByWeightDto> dtoList = PriceByWeightMapper.toDtos(entityList);

        assertEquals(2, dtoList.size());

        assertEquals(entity1.getId(), dtoList.get(0).getId());
        assertEquals(entity1.getWeight(), dtoList.get(0).getWeight());
        assertEquals(entity1.getPrice(), dtoList.get(0).getPrice());

        assertEquals(entity2.getId(), dtoList.get(1).getId());
        assertEquals(entity2.getWeight(), dtoList.get(1).getWeight());
        assertEquals(entity2.getPrice(), dtoList.get(1).getPrice());
    }

    @Test
    void toDtos_GetEmptyListIfPassNullArg_PriceByWeightMapperTest() {
        //Act
        List<PriceByWeightDto> priceByWeightDtos = PriceByWeightMapper.toDtos(null);

        //Assert
        assertNotNull(priceByWeightDtos);
        assertTrue(priceByWeightDtos.isEmpty());
    }

    @Test
    void toDtos_GetEmptyListIfPassEmptyList_PriceByWeightMapperTest() {
        //Act
        List<PriceByWeightDto> priceByWeightDtos = PriceByWeightMapper.toDtos(List.of());

        //Assert
        assertNotNull(priceByWeightDtos);
        assertTrue(priceByWeightDtos.isEmpty());
    }
}
