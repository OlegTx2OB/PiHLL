package com.example.papadoner.controller;

import com.example.papadoner.dto.IngredientDto;
import com.example.papadoner.model.Ingredient;
import com.example.papadoner.service.IngredientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Log4j2
@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    private final IngredientService mIngredientService;

    @Autowired
    public IngredientController(IngredientService ingredientService) {
        this.mIngredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<Void> createIngredient(@RequestBody Ingredient ingredient,
                                                 @RequestParam (required = false) Set<String> donerNames) {
        log.info("POST endpoint createIngredient [class IngredientController] was called");
        mIngredientService.createIngredient(ingredient, donerNames);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<IngredientDto> getIngredientByName(@PathVariable("name") String name) {
        log.info("GET endpoint getIngredientById [class IngredientController] was called");
        IngredientDto ingredientDto = mIngredientService.getIngredientByName(name);
        return new ResponseEntity<>(ingredientDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientDto> updateIngredient(
            @PathVariable("id") long id,
            @RequestBody Ingredient updatedIngredient,
            @RequestParam (required = false) Set<String> donerNames) {
        log.info("PUT endpoint updateIngredient [class IngredientController] was called");
        IngredientDto ingredientDto = mIngredientService.updateIngredient(id, updatedIngredient, donerNames);
        return new ResponseEntity<>(ingredientDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable("id") long id) {
        log.info("DELETE endpoint deleteIngredient [class IngredientController] was called");
        mIngredientService.deleteIngredient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<IngredientDto>> getAllIngredients() {
        log.info("GET endpoint getAllIngredients [class IngredientController] was called");
        List<IngredientDto> ingredientDtos = mIngredientService.getAllIngredients();
        return new ResponseEntity<>(ingredientDtos, HttpStatus.OK);
    }
}
