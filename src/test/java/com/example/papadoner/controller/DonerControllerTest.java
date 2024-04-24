package com.example.papadoner.controller;

import com.example.papadoner.dto.DonerDto;
import com.example.papadoner.model.Doner;
import com.example.papadoner.service.DonerService;
import com.example.papadoner.service.impl.DonerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DonerControllerTest {

    @Mock
    private DonerService mDonerService;

    @InjectMocks
    private DonerController mDonerController;

    @Test
    void createDonerTest() {
        //Setup
        Doner doner = new Doner();
        Set<String> ingredientNames = Set.of("s1", "s2");
        Set<Long> priceByWeightIds = null;

        //Act
        ResponseEntity<Void> response = mDonerController.createDoner(doner, ingredientNames, priceByWeightIds);

        //Assert
        verify(mDonerService).createDoner(doner, ingredientNames, priceByWeightIds);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void getDonersByNameTest() {
        //Setup
        String donerName = "SomeDoner";
        List<DonerDto> expectedDoners = List.of(new DonerDto(1L, "SomeDoner", List.of(), null));

        when(mDonerService.getDonersByName(donerName)).thenReturn(expectedDoners);

        //Act
        ResponseEntity<List<DonerDto>> result = mDonerController.getDonersByName(donerName);

        //Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
        DonerDto resultDonerDto = result.getBody().get(0);
        assertEquals(1L, resultDonerDto.getId());
        assertEquals("SomeDoner", resultDonerDto.getName());
        assertEquals(List.of(), resultDonerDto.getIngredientDtos());
        assertNull(resultDonerDto.getPriceByWeightDtos());
    }

    @Test
    void updateDonerTest() {
        //Setup
        Doner newDoner = new Doner(2L, "name", List.of(), null);
        DonerDto expectedDonerDto = new DonerDto(1L, "name", List.of(), null);

        when(mDonerService.updateDoner(1L, newDoner, null, null))
                .thenReturn(expectedDonerDto);

        //Act
        ResponseEntity<DonerDto> result = mDonerController
                .updateDoner(1L, newDoner, null, null);

        //Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(expectedDonerDto.getId(), result.getBody().getId());
        assertEquals(newDoner.getName(), result.getBody().getName());
        assertEquals(newDoner.getIngredients(), result.getBody().getIngredientDtos());
        assertNull(result.getBody().getPriceByWeightDtos());
    }

    @Test
    void deleteDonerTest() {
        //Setup
        long id = 1;

        //Act
        ResponseEntity<Void> response = mDonerController.deleteDoner(id);

        //Assert
        verify(mDonerService).deleteDoner(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllDonersTest() {
        //Setup
        List<DonerDto> expectedDoners = List.of(new DonerDto(1L, "SomeDoner", List.of(), null));

        when(mDonerService.getAllDoners()).thenReturn(expectedDoners);

        //Act
        ResponseEntity<List<DonerDto>> result = mDonerController.getAllDoners();

        //Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
        DonerDto resultDonerDto = result.getBody().get(0);
        assertEquals(1L, resultDonerDto.getId());
        assertEquals("SomeDoner", resultDonerDto.getName());
        assertEquals(List.of(), resultDonerDto.getIngredientDtos());
        assertNull(resultDonerDto.getPriceByWeightDtos());
    }

    @Test
    void correctConstructorTest() {
        DonerService donerServiceMock = mock(DonerServiceImpl.class);

        DonerController donerController = new DonerController(donerServiceMock);

        assertEquals(donerServiceMock, donerController.getMDonerService());
    }
}
