package com.example.papadoner.service;

import com.example.papadoner.dto.PriceByWeightDto;
import com.example.papadoner.model.PriceByWeight;

import java.util.List;

public interface PriceByWeightService {

    void createPriceByWeight(PriceByWeight priceByWeight);

    PriceByWeightDto getPriceByWeightById(long id);

    PriceByWeightDto updatePriceByWeight(long id, PriceByWeight newPriceByWeight);

    void deletePriceByWeight(long id);

    List<PriceByWeightDto> getAllPriceByWeights();

    void createPriceByWeightBulk(List<PriceByWeight> priceByWeights);
}
