package com.example.papadoner.service.impl;

import com.example.papadoner.cache.EntityCache;
import com.example.papadoner.dto.PriceByWeightDto;
import com.example.papadoner.exception.InvalidEnteredDataException;
import com.example.papadoner.mapper.PriceByWeightMapper;
import com.example.papadoner.model.PriceByWeight;
import com.example.papadoner.repository.PriceByWeightRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PriceByWeightServiceImplTest {

    @Mock
    private PriceByWeightRepository mPriceByWeightRepository;
    @Mock
    private EntityCache<Long, PriceByWeightDto> mCache;
    @InjectMocks
    private PriceByWeightServiceImpl mPriceByWeightService;

    @Test
    void correctConstructorTest() {
        PriceByWeightRepository priceByWeightRepository = mock(PriceByWeightRepository.class);
        EntityCache<Long, PriceByWeightDto> cache = mock(EntityCache.class);

        PriceByWeightServiceImpl priceByWeightService = new PriceByWeightServiceImpl(priceByWeightRepository, cache);

        assertEquals(priceByWeightRepository, priceByWeightService.getMPriceByWeightRepository());
        assertEquals(cache, priceByWeightService.getMCache());
    }

//    @Test
//    void createPriceByWeight_Success() {
//        // Arrange
//        PriceByWeight priceByWeight = new PriceByWeight(1L, 250, 10.0);
//
//        // Act
//        mPriceByWeightService.createPriceByWeight(priceByWeight);
//
//        // Assert
//        verify(mPriceByWeightRepository, times(1)).save(priceByWeight);
//        verify(mCache, times(1)).put(eq(priceByWeight.getId()), any(PriceByWeightDto.class));
//    }

    @Test
    void getPriceByWeightById_PriceByWeightFound_Success() {
        // Arrange
        long id = 1L;
        PriceByWeight priceByWeight = new PriceByWeight(id, 250, 10.0);
        when(mCache.get(id)).thenReturn(Optional.of(PriceByWeightMapper.toDto(priceByWeight)));

        // Act
        PriceByWeightDto result = mPriceByWeightService.getPriceByWeightById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void getPriceByWeightById_PriceByWeightNotFound_ExceptionThrown() {
        // Arrange
        long nonExistentId = 999L;
        when(mCache.get(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> mPriceByWeightService.getPriceByWeightById(nonExistentId));
    }

//    @Test
//    void updatePriceByWeight_Success() {
//        // Arrange
//        long id = 1L;
//        PriceByWeight oldPriceByWeight = new PriceByWeight(id, 250, 10.0);
//        PriceByWeight newPriceByWeight = new PriceByWeight(id, 300, 12.0);
//        when(mPriceByWeightRepository.findById(id)).thenReturn(Optional.of(oldPriceByWeight));
//
//        // Act
//        PriceByWeightDto result = mPriceByWeightService.updatePriceByWeight(id, newPriceByWeight);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals(newPriceByWeight.getWeight(), result.getWeight());
//        assertEquals(newPriceByWeight.getPrice(), result.getPrice());
//        verify(mPriceByWeightRepository, times(1)).save(newPriceByWeight);
//        verify(mCache, times(1)).put(eq(id), any(PriceByWeightDto.class));
//    }

    @Test
    void updatePriceByWeight_PriceByWeightNotFound_ExceptionThrown() {
        // Arrange
        long nonExistentId = 999L;
        PriceByWeight newPriceByWeight = new PriceByWeight(nonExistentId, 300, 12.0);
        when(mPriceByWeightRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> mPriceByWeightService.updatePriceByWeight(nonExistentId, newPriceByWeight));
    }

    @Test
    void deletePriceByWeight_Success() {
        // Arrange
        long id = 1L;

        // Act
        mPriceByWeightService.deletePriceByWeight(id);

        // Assert
        verify(mPriceByWeightRepository, times(1)).deleteById(id);
        verify(mCache, times(1)).remove(id);
    }

    @Test
    void getAllPriceByWeights_Success() {
        // Arrange
        List<PriceByWeight> priceByWeights = List.of(new PriceByWeight(), new PriceByWeight());
        when(mPriceByWeightRepository.findAll()).thenReturn(priceByWeights);

        // Act
        List<PriceByWeightDto> result = mPriceByWeightService.getAllPriceByWeights();

        // Assert
        assertNotNull(result);
        assertEquals(priceByWeights.size(), result.size());
    }

    @Test
    void createPriceByWeightBulk_NullList_ExceptionThrown() {
        // Act & Assert
        assertThrows(InvalidEnteredDataException.class, () -> mPriceByWeightService.createPriceByWeightBulk(null));
    }

//    @Test
//    void createPriceByWeightBulk_ListWithItems_Success() {
//        // Arrange
//        List<PriceByWeight> priceByWeights = List.of(new PriceByWeight(), new PriceByWeight());
//
//        // Act
//        mPriceByWeightService.createPriceByWeightBulk(priceByWeights);
//
//        // Assert
//        verify(mPriceByWeightRepository, times(priceByWeights.size())).save(any(PriceByWeight.class));
//    }
}
