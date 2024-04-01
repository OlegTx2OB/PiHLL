package com.example.papadoner.controller;

import com.example.papadoner.dto.DonerDto;
import com.example.papadoner.model.Doner;
import com.example.papadoner.service.DonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/doner")
public class DonerController {

    private final DonerService mDonerService;
    @Autowired
    public DonerController(DonerService donerService) {
        this.mDonerService = donerService;
    }

    @PostMapping
    public ResponseEntity<Void> createDoner(@RequestBody Doner doner,
                                            @RequestParam (required = false) Set<String> ingredientNames,
                                            @RequestParam (required = false) Set<Long> priceByWeightIds) {
        mDonerService.createDoner(doner, ingredientNames, priceByWeightIds);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Set<DonerDto>> getDonersByName(@PathVariable("name") String name) {
        Set<DonerDto> donerDtos = mDonerService.getDonersByName(name);
        return new ResponseEntity<>(donerDtos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DonerDto> updateDoner(@PathVariable("id") long id,
                                                @RequestBody Doner updatedDoner,
                                                @RequestParam (required = false) Set<String> ingredientNames,
                                                @RequestParam (required = false) Set<Long> priceByWeightIds) {
        DonerDto donerDto = mDonerService.updateDoner(id, updatedDoner, ingredientNames, priceByWeightIds);
        return new ResponseEntity<>(donerDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoner(@PathVariable("id") long id) {
        mDonerService.deleteDoner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<DonerDto>> getAllDoners() {
        List<DonerDto> donerDtos = mDonerService.getAllDoners();
        return new ResponseEntity<>(donerDtos, HttpStatus.OK);
    }
}
