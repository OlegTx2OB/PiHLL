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
    public PriceByWeight getPriceByWeightById(long id) {
        return priceByWeightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PriceByWeight with id " + id + " not found"));
    }

    @Override
    public PriceByWeight updatePriceByWeight(long id, PriceByWeight newPriceByWeight) {
        if (newPriceByWeight == null) {
            throw new IllegalArgumentException("fun updatePriceByWeight cannot get null argument");
        }
        Optional<PriceByWeight> optionalOldPriceByWeight = priceByWeightRepository.findById(id);
        if (optionalOldPriceByWeight.isPresent()) {
            PriceByWeight oldPriceByWeight = optionalOldPriceByWeight.get();
            newPriceByWeight.setId(oldPriceByWeight.getId());
            return priceByWeightRepository.save(newPriceByWeight);
        } else {
            throw new EntityNotFoundException("PriceByWeight with id " + id + " not found");
        }
    }

    @Override
    public void deletePriceByWeight(long id) {
        priceByWeightRepository.deleteById(id);
    }

    @Override
    public List<PriceByWeight> getAllPriceByWeights() {
        return priceByWeightRepository.findAll();
    }
}
