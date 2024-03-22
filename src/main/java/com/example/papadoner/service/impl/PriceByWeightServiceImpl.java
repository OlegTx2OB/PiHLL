package com.example.papadoner.service.impl;

import com.example.papadoner.model.PriceByWeight;
import com.example.papadoner.repository.PriceByWeightRepository;
import com.example.papadoner.service.PriceByWeightService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceByWeightServiceImpl implements PriceByWeightService {
    private final PriceByWeightRepository priceByWeightRepository;

    @Autowired
    public PriceByWeightServiceImpl(PriceByWeightRepository priceByWeightRepository) {
        this.priceByWeightRepository = priceByWeightRepository;
    }

    @Override
    public PriceByWeight createPriceByWeight(PriceByWeight priceByWeight) {
        return priceByWeightRepository.save(priceByWeight);
    }

    @Override
    public PriceByWeight getPriceByWeightById(short id) {
        return priceByWeightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PriceByWeight with id " + id + " not found"));
    }

    @Override
    public PriceByWeight updatePriceByWeight(short id, PriceByWeight updatedPriceByWeight) {
        Optional<PriceByWeight> optionalPriceByWeight = priceByWeightRepository.findById(id);
        if (optionalPriceByWeight.isPresent()) {
            PriceByWeight priceByWeight = optionalPriceByWeight.get();
            priceByWeight.setWeight(updatedPriceByWeight.getWeight());
            priceByWeight.setPrice(updatedPriceByWeight.getPrice());
            return priceByWeightRepository.save(priceByWeight);
        } else {
            throw new EntityNotFoundException("PriceByWeight with id " + id + " not found");
        }
    }

    @Override
    public void deletePriceByWeight(short id) {
        priceByWeightRepository.deleteById(id);
    }

    @Override
    public List<PriceByWeight> getAllPriceByWeights() {
        return priceByWeightRepository.findAll();
    }
}
