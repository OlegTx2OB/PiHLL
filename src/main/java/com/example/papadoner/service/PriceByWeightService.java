package com.example.papadoner.service;

import com.example.papadoner.model.PriceByWeight;

import java.util.List;

public interface PriceByWeightService {

    public PriceByWeight createPriceByWeight(PriceByWeight priceByWeight);
    public PriceByWeight getPriceByWeightById(short id);
    public PriceByWeight updatePriceByWeight(short id, PriceByWeight updatedPriceByWeight);
    public void deletePriceByWeight(long id);
    public List<PriceByWeight> getAllPriceByWeights();
}
