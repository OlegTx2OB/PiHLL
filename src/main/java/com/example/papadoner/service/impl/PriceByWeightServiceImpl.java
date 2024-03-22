package com.example.papadoner.service.impl;

import com.example.papadoner.model.PriceByWeight;
import com.example.papadoner.service.PriceByWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceByWeightServiceImpl implements PriceByWeightService {

    @Autowired
    public PriceByWeightServiceImpl() {

    }

    @Override
    public PriceByWeight createPriceByWeight(PriceByWeight priceByWeight) {
        return null;
    }

    @Override
    public PriceByWeight getPriceByWeightById(short id) {
        return null;
    }

    @Override
    public PriceByWeight updatePriceByWeight(short id, PriceByWeight updatedPriceByWeight) {
        return null;
    }

    @Override
    public void deletePriceByWeight(long id) {

    }

    @Override
    public List<PriceByWeight> getAllPriceByWeights() {
        return null;
    }
}
