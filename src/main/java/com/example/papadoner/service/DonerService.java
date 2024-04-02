package com.example.papadoner.service;

import com.example.papadoner.dto.DonerDto;
import com.example.papadoner.model.Doner;

import java.util.List;
import java.util.Set;

public interface DonerService {

    DonerDto createDoner(Doner doner, Set<String> ingredientNames, Set<Long> priceByWeightIds);

    List<DonerDto> getDonersByName(String name);

    DonerDto updateDoner(long id, Doner newDoner, Set<String> ingredientNames, Set<Long> priceByWeightIds);

    void deleteDoner(long id);

    List<DonerDto> getAllDoners();
}
