package com.example.papadoner.controller;

import com.example.papadoner.dto.PriceByWeightDto;
import com.example.papadoner.model.PriceByWeight;
import com.example.papadoner.service.PriceByWeightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/priceByWeight")
public class PriceByWeightController {

    private final PriceByWeightService mPriceByWeightService;

    @Autowired
    public PriceByWeightController(PriceByWeightService priceByWeightService) {
        this.mPriceByWeightService = priceByWeightService;
    }

    @PostMapping
    public ResponseEntity<Void> createPriceByWeight(@RequestBody PriceByWeight priceByWeight) {
        mPriceByWeightService.createPriceByWeight(priceByWeight);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceByWeightDto> getPriceByWeightById(@PathVariable("id") long id) {
        PriceByWeightDto priceByWeight = mPriceByWeightService.getPriceByWeightById(id);
        return new ResponseEntity<>(priceByWeight, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PriceByWeightDto> updatePriceByWeight(@PathVariable("id") long id,
                                                             @RequestBody PriceByWeight updatedPriceByWeight) {
        PriceByWeightDto priceByWeight = mPriceByWeightService.updatePriceByWeight(id, updatedPriceByWeight);
        return new ResponseEntity<>(priceByWeight, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePriceByWeight(@PathVariable("id") long id) {
        mPriceByWeightService.deletePriceByWeight(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<PriceByWeightDto>> getAllPriceByWeights() {
        List<PriceByWeightDto> priceByWeights = mPriceByWeightService.getAllPriceByWeights();
        return new ResponseEntity<>(priceByWeights, HttpStatus.OK);
    }
}

