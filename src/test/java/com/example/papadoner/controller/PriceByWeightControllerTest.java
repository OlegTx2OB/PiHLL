package com.example.papadoner.controller;

import com.example.papadoner.dto.PriceByWeightDto;
import com.example.papadoner.model.PriceByWeight;
import com.example.papadoner.service.OrderService;
import com.example.papadoner.service.PriceByWeightService;
import com.example.papadoner.service.RequestCounterService;
import com.example.papadoner.service.impl.OrderServiceImpl;
import com.example.papadoner.service.impl.PriceByWeightServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class PriceByWeightControllerTest {

    @Mock
    private PriceByWeightService mPriceByWeightService;

    @Mock
    private RequestCounterService mRequestCounterService;

    @InjectMocks
    private PriceByWeightController mPriceByWeightController;

    @Test
    void createPriceByWeightTest() {
        // Setup
        PriceByWeight priceByWeight = new PriceByWeight();

        // Act
        ResponseEntity<Void> response = mPriceByWeightController.createPriceByWeight(priceByWeight);

        // Assert
        verify(mPriceByWeightService).createPriceByWeight(priceByWeight);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void createPriceByWeightBulkTest() {
        // Setup
        List<PriceByWeight> priceByWeights = List.of(new PriceByWeight(), new PriceByWeight());

        // Act
        mPriceByWeightController.createPriceByWeightBulk(priceByWeights);

        // Assert
        verify(mPriceByWeightService).createPriceByWeightBulk(priceByWeights);
    }

    @Test
    void getPriceByWeightByIdTest() {
        // Setup
        long priceByWeightId = 1L;
        PriceByWeightDto expectedPriceByWeightDto = new PriceByWeightDto(priceByWeightId, 500, 0.5);

        when(mPriceByWeightService.getPriceByWeightById(priceByWeightId)).thenReturn(expectedPriceByWeightDto);

        // Act
        ResponseEntity<PriceByWeightDto> response = mPriceByWeightController.getPriceByWeightById(priceByWeightId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedPriceByWeightDto, response.getBody());
    }

    @Test
    void updatePriceByWeightTest() {
        // Setup
        long priceByWeightId = 1L;
        PriceByWeight updatedPriceByWeight = new PriceByWeight();
        PriceByWeightDto expectedPriceByWeightDto = new PriceByWeightDto(priceByWeightId, 500, 0.5);

        when(mPriceByWeightService.updatePriceByWeight(priceByWeightId, updatedPriceByWeight))
                .thenReturn(expectedPriceByWeightDto);

        // Act
        ResponseEntity<PriceByWeightDto> response = mPriceByWeightController
                .updatePriceByWeight(priceByWeightId, updatedPriceByWeight);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedPriceByWeightDto, response.getBody());
    }

    @Test
    void deletePriceByWeightTest() {
        // Setup
        long priceByWeightId = 1L;

        // Act
        ResponseEntity<Void> response = mPriceByWeightController.deletePriceByWeight(priceByWeightId);

        // Assert
        verify(mPriceByWeightService).deletePriceByWeight(priceByWeightId);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getAllPriceByWeightsTest() {
        // Setup
        List<PriceByWeightDto> expectedPriceByWeightDtos = List.of(
                new PriceByWeightDto(1L, 500, 0.5),
                new PriceByWeightDto(2L, 600, 0.6));

        when(mPriceByWeightService.getAllPriceByWeights()).thenReturn(expectedPriceByWeightDtos);

        // Act
        ResponseEntity<List<PriceByWeightDto>> response = mPriceByWeightController.getAllPriceByWeights();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedPriceByWeightDtos.size(), response.getBody().size());
        assertEquals(expectedPriceByWeightDtos, response.getBody());
    }

    @Test
    void correctConstructorTest() {
        PriceByWeightService priceByWeightServiceMock = mock(PriceByWeightServiceImpl.class);
        RequestCounterService requestCounterServiceMock = mock(RequestCounterService.class);

        PriceByWeightController orderController = new PriceByWeightController(priceByWeightServiceMock, requestCounterServiceMock);

        assertEquals(priceByWeightServiceMock, orderController.getMPriceByWeightService());
        assertEquals(requestCounterServiceMock, orderController.getMRequestCounterService());
    }
}