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

import java.util.ArrayList;
import java.util.List;
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
        //manually creating two-way link because of @ManyToMany

        List<Ingredient> ingredients = getIngredients(ingredientNames);
        List<PriceByWeight> priceByWeights = getPriceByWeights(priceByWeightIds);
        doner.getIngredients().addAll(ingredients);
        doner.getPricesByWeight().addAll(priceByWeights);
        DonerDto donerDto = mDonerMapper.toDto(mDonerRepository.save(doner));

        saveDonerInIngredients(doner, ingredients);

        return donerDto;
    }

    @Override
    public List<DonerDto> getDonersByName(String name) {
        return mDonerMapper.toDtos(mDonerRepository
                .findDonersByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Doner with name " + name + " not found")));
    }

    @Override
    public DonerDto updateDoner(long id, Doner newDoner,
                                @Nullable Set<String> ingredientNames,
                                @Nullable Set<Long> priceByWeightIds) {
        //manually creating two-way link

        List<Ingredient> ingredients = getIngredients(ingredientNames);
        List<PriceByWeight> priceByWeights = getPriceByWeights(priceByWeightIds);

        Doner oldDoner = mDonerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doner with id " + id + " not found"));
        newDoner.setId(oldDoner.getId());

        newDoner.getIngredients().addAll(ingredients);
        newDoner.getPricesByWeight().addAll(priceByWeights);
        DonerDto donerDto = mDonerMapper.toDto(mDonerRepository.save(newDoner));
        saveDonerInIngredients(newDoner, ingredients);

        return donerDto;
    }

    @Override
    public void deleteDoner(long id) {
        //deleting two-way link

        Doner doner = mDonerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Doner with id " + id + " not found"));
        List<Ingredient> ingredients = doner.getIngredients();

        for (Ingredient ingredient : ingredients) {
            ingredient.getDoners().remove(doner);
            mIngredientRepository.save(ingredient);
        }
        mDonerRepository.deleteById(id);
    }

    @Override
    public List<DonerDto> getAllDoners() {
        return mDonerMapper.toDtos(mDonerRepository.findAll());
    }

    private List<Ingredient> getIngredients(Set<String> ingredientNames) {
        if (ingredientNames != null) {
            List<Ingredient> ingredients = new ArrayList<>();
            for (String name : ingredientNames) {
                Ingredient ingredient = mIngredientRepository.findByName(name)
                        .orElseThrow(() -> new EntityNotFoundException(
                                "Ingredient with name " + name + " not found"));
                ingredients.add(ingredient);
            }
            return ingredients;
        }
        return new ArrayList<>();
    }

    private List<PriceByWeight> getPriceByWeights(Set<Long> priceByWeightIds) {
        if (priceByWeightIds != null) {
            List<PriceByWeight> priceByWeights = new ArrayList<>();
            for (long id : priceByWeightIds) {
                priceByWeights.add(
                        mPriceByWeightRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException(
                                        "priceByWeight with name " + id + " not found")));
            }
            return priceByWeights;
        }
        return new ArrayList<>();
    }

    private void saveDonerInIngredients(Doner doner, List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            ingredient.getDoners().add(doner);
            mIngredientRepository.save(ingredient);
        }
    }
}