package com.example.papadoner.service.impl;

import com.example.papadoner.cache.EntityCache;
import com.example.papadoner.dto.PriceByWeightDto;
import com.example.papadoner.mapper.PriceByWeightMapper;
import com.example.papadoner.model.PriceByWeight;
import com.example.papadoner.repository.PriceByWeightRepository;
import com.example.papadoner.service.PriceByWeightService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceByWeightServiceImpl implements PriceByWeightService {

    private final PriceByWeightRepository mPriceByWeightRepository;
    private final PriceByWeightMapper mPriceByWeightMapper;

    private final EntityCache<Long, PriceByWeightDto> mCache;
    boolean isCacheInitialized = false;

    @Autowired
    public PriceByWeightServiceImpl(PriceByWeightRepository priceByWeightRepository,
                                    PriceByWeightMapper priceByWeightMapper,
                                    EntityCache<Long, PriceByWeightDto> cache) {
        this.mPriceByWeightRepository = priceByWeightRepository;
        this.mPriceByWeightMapper = priceByWeightMapper;
        this.mCache = cache;
    }

    @Override
    public PriceByWeightDto createPriceByWeight(PriceByWeight priceByWeight) {
        initializeCacheIfClear();

        PriceByWeightDto priceByWeightDto = mPriceByWeightMapper
                .toDto(mPriceByWeightRepository.save(priceByWeight));
        mCache.put(priceByWeight.getId(), mPriceByWeightMapper.toDto(priceByWeight));
        return priceByWeightDto;
    }

    @Override
    public PriceByWeightDto getPriceByWeightById(long id) {
        initializeCacheIfClear();

        return mCache.get(id)
                .orElseThrow(() -> new EntityNotFoundException("PriceByWeight with id " + id + " not found"));
    }

    @Override
    public PriceByWeightDto updatePriceByWeight(long id, PriceByWeight newPriceByWeight) {
        initializeCacheIfClear();

        PriceByWeight oldPriceByWeight = mPriceByWeightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PriceByWeight with id " + id + " not found"));
        newPriceByWeight.setId(oldPriceByWeight.getId());

        mCache.put(newPriceByWeight.getId(), mPriceByWeightMapper.toDto(newPriceByWeight));

        return mPriceByWeightMapper.toDto(mPriceByWeightRepository.save(newPriceByWeight));

    }

    @Override
    public void deletePriceByWeight(long id) {
        initializeCacheIfClear();

        mPriceByWeightRepository.deleteById(id);
        mCache.remove(id);
    }

    @Override
    public List<PriceByWeightDto> getAllPriceByWeights() {
        initializeCacheIfClear();
        return mCache.getAll();
    }

    private void fillCache() {
        List<PriceByWeight> priceByWeights = mPriceByWeightRepository.findAll();

        for (PriceByWeight priceByWeight : priceByWeights) {
            mCache.put(priceByWeight.getId(), mPriceByWeightMapper.toDto(priceByWeight));
        }
        isCacheInitialized = true;
    }

    void initializeCacheIfClear() {
        if (!isCacheInitialized) {
            fillCache();
        }
    }

}