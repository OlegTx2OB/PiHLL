package com.example.papadoner.service;

import com.example.papadoner.model.PriceByWeight;

import java.util.List;

public interface PriceByWeightService {

    public PriceByWeight createPriceByWeight(PriceByWeight priceByWeight);
    public PriceByWeight getPriceByWeightById(long id);
    public PriceByWeight updatePriceByWeight(long id, PriceByWeight updatedPriceByWeight);
    public void deletePriceByWeight(long id);
    public List<PriceByWeight> getAllPriceByWeights();
}
