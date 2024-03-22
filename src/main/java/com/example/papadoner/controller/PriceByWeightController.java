package com.example.papadoner.controller;

import com.example.papadoner.model.PriceByWeight;
import com.example.papadoner.service.PriceByWeightService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/priceByWeight")
public class PriceByWeightController {

    private final PriceByWeightService priceByWeightService;

    @Autowired
    public PriceByWeightController(PriceByWeightService priceByWeightService) {
        this.priceByWeightService = priceByWeightService;
    }

    @PostMapping
    public ResponseEntity<PriceByWeight> createPriceByWeight(@RequestBody PriceByWeight priceByWeight) {
        PriceByWeight createdPriceByWeight = priceByWeightService.createPriceByWeight(priceByWeight);
        return new ResponseEntity<>(createdPriceByWeight, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceByWeight> getPriceByWeightById(@PathVariable("id") short id) {
        PriceByWeight priceByWeight = priceByWeightService.getPriceByWeightById(id);
        return new ResponseEntity<>(priceByWeight, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PriceByWeight> updatePriceByWeight(@PathVariable("id") short id, @RequestBody PriceByWeight updatedPriceByWeight) {
        PriceByWeight priceByWeight = priceByWeightService.updatePriceByWeight(id, updatedPriceByWeight);
        return new ResponseEntity<>(priceByWeight, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePriceByWeight(@PathVariable("id") short id) {
        priceByWeightService.deletePriceByWeight(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<PriceByWeight>> getAllPriceByWeights() {
        List<PriceByWeight> priceByWeights = priceByWeightService.getAllPriceByWeights();
        return new ResponseEntity<>(priceByWeights, HttpStatus.OK);
    }
}
