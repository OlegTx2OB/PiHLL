package com.example.papadoner.controller;

import com.example.papadoner.dto.DonerDto;
import com.example.papadoner.dto.IngredientDto;
import com.example.papadoner.mapper.IngredientMapper;
import com.example.papadoner.model.Doner;
import com.example.papadoner.model.Ingredient;
import com.example.papadoner.service.DonerService;
import com.example.papadoner.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService ingredientService;
    private final DonerService donerService;
    private final IngredientMapper ingredientMapper;

    @Autowired
    public IngredientController(IngredientService ingredientService,
                                DonerService donerService,
                                IngredientMapper ingredientMapper) {
        this.ingredientService = ingredientService;
        this.donerService = donerService;
        this.ingredientMapper = ingredientMapper;
    }

    @PostMapping
    public ResponseEntity<Ingredient> createIngredient(@RequestBody Ingredient ingredient,
                                                       @RequestParam Set<Long> donerIds) {

        Set<Doner> donerSet = new HashSet<>();
        for (Long id : donerIds) {
            donerSet.add(donerService.getDonerById(id));
        }
        ingredient.setDoners(donerSet);

        Ingredient createdIngredient = ingredientService.createIngredient(ingredient);
        return new ResponseEntity<>(createdIngredient, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDto> getIngredientById(@PathVariable("id") short id) {
        Ingredient ingredient = ingredientService.getIngredientById(id);
        return new ResponseEntity<>(ingredientMapper.toDto(ingredient), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(
            @PathVariable("id") short id,
            @RequestBody Ingredient updatedIngredient
    ) {
        Ingredient ingredient = ingredientService.updateIngredient(id, updatedIngredient);
        return new ResponseEntity<>(ingredient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable("id") short id) {
        ingredientService.deleteIngredient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<IngredientDto>> getAllIngredients() {
        List<Ingredient> ingredients = ingredientService.getAllIngredients();
        List<IngredientDto> ingredientsDto = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            ingredientsDto.add(ingredientMapper.toDto(ingredient));
        }
        return new ResponseEntity<>(ingredientsDto, HttpStatus.OK);
    }
}
