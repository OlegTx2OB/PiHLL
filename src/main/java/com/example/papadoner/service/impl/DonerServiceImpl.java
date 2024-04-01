package com.example.papadoner.service.impl;

import com.example.papadoner.dto.DonerDto;
import com.example.papadoner.mapper.DonerMapper;
import com.example.papadoner.model.Doner;
import com.example.papadoner.model.Ingredient;
import com.example.papadoner.model.PriceByWeight;
import com.example.papadoner.repository.DonerRepository;
import com.example.papadoner.repository.IngredientRepository;
import com.example.papadoner.repository.PriceByWeightRepository;
import com.example.papadoner.service.DonerService;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DonerServiceImpl implements DonerService {

    private final DonerRepository mDonerRepository;
    private final DonerMapper mDonerMapper;
    private final IngredientRepository mIngredientRepository;

    private final PriceByWeightRepository mPriceByWeightRepository;

    @Autowired
    public DonerServiceImpl(DonerRepository donerRepository,
                            PriceByWeightRepository priceByWeightRepository,
                            DonerMapper donerMapper,
                            IngredientRepository ingredientRepository) {
        this.mDonerRepository = donerRepository;
        this.mPriceByWeightRepository = priceByWeightRepository;
        this.mDonerMapper = donerMapper;
        this.mIngredientRepository = ingredientRepository;
    }

    @Override
    public DonerDto createDoner(Doner doner,
                                @Nullable Set<String> ingredientNames,
                                @Nullable Set<Long> priceByWeightIds) {
        doner = setIngredients(doner, ingredientNames);
        doner = setPriceByWeights(doner, priceByWeightIds);
        return mDonerMapper.toDto(mDonerRepository.save(doner));
    }

    @Override
    public Set<DonerDto> getDonersByName(String name) {
        return mDonerMapper.toDtos(mDonerRepository
                .findDonersByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Doner with name " + name + " not found")));
    }

    @Override
    public DonerDto updateDoner(long id, Doner newDoner,
                                @Nullable Set<String> ingredientNames,
                                @Nullable Set<Long> priceByWeightIds) {
        newDoner = setIngredients(newDoner, ingredientNames);
        newDoner = setPriceByWeights(newDoner, priceByWeightIds);

        Optional<Doner> optionalOldDoner = mDonerRepository.findById(id);
        if (optionalOldDoner.isPresent()) {
            Doner oldDoner = optionalOldDoner.get();
            newDoner.setId(oldDoner.getId());
            return mDonerMapper.toDto(mDonerRepository.save(newDoner));
        } else {
            throw new EntityNotFoundException("Doner with id " + id + " not found");
        }
    }

    @Override
    public void deleteDoner(long id) {
        mDonerRepository.deleteById(id);
    }

    @Override
    public List<DonerDto> getAllDoners() {
        return mDonerMapper.toDtos(mDonerRepository.findAll());
    }

    private Doner setIngredients(Doner doner, Set<String> ingredientNames) {
        if (ingredientNames != null) {
            Set<Ingredient> ingredients = new HashSet<>();
            for (String name : ingredientNames) {
                ingredients.add(
                        mIngredientRepository.findByName(name)
                                .orElseThrow(() -> new EntityNotFoundException(
                                        "Ingredient with name " + name + " not found")));
            }
            doner.setIngredients(ingredients);
        }
        return doner;
    }

    private Doner setPriceByWeights(Doner doner, Set<Long> priceByWeightIds) {
        if (priceByWeightIds != null) {
            Set<PriceByWeight> priceByWeights = new HashSet<>();
            for (long id : priceByWeightIds) {
                priceByWeights.add(
                        mPriceByWeightRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(
                                        "priceByWeight with name " + id + " not found")));
            }
            doner.setPricesByWeight(priceByWeights);
        }
        return doner;
    }
}