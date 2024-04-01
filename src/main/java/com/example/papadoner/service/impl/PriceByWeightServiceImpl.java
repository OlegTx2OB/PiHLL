package com.example.papadoner.service.impl;

import com.example.papadoner.dto.PriceByWeightDto;
import com.example.papadoner.mapper.PriceByWeightMapper;
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

    private final PriceByWeightRepository mPriceByWeightRepository;
    private final PriceByWeightMapper mPriceByWeightMapper;

    @Autowired
    public PriceByWeightServiceImpl(PriceByWeightRepository priceByWeightRepository,
                                    PriceByWeightMapper priceByWeightMapper) {
        this.mPriceByWeightRepository = priceByWeightRepository;
        this.mPriceByWeightMapper = priceByWeightMapper;
    }

    @Override
    public PriceByWeightDto createPriceByWeight(PriceByWeight priceByWeight) {
        return mPriceByWeightMapper.toDto(mPriceByWeightRepository.save(priceByWeight));
    }

    @Override
    public PriceByWeightDto getPriceByWeightById(long id) {
        return mPriceByWeightMapper.toDto(mPriceByWeightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PriceByWeight with id " + id + " not found")));
    }

    @Override
    public PriceByWeightDto updatePriceByWeight(long id, PriceByWeight newPriceByWeight) {

        Optional<PriceByWeight> optionalOldPriceByWeight = mPriceByWeightRepository.findById(id);
        if (optionalOldPriceByWeight.isPresent()) {
            PriceByWeight oldPriceByWeight = optionalOldPriceByWeight.get();
            newPriceByWeight.setId(oldPriceByWeight.getId());
            return mPriceByWeightMapper.toDto(mPriceByWeightRepository.save(newPriceByWeight));
        } else {
            throw new EntityNotFoundException("PriceByWeight with id " + id + " not found");
        }
    }

    @Override
    public void deletePriceByWeight(long id) {
        mPriceByWeightRepository.deleteById(id);
    }

    @Override
    public List<PriceByWeightDto> getAllPriceByWeights() {
        return mPriceByWeightMapper.toDtos(mPriceByWeightRepository.findAll());
    }

}