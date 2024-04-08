package com.example.papadoner.controller;

import com.example.papadoner.dto.PriceByWeightDto;
import com.example.papadoner.model.PriceByWeight;
import com.example.papadoner.service.PriceByWeightService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
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
        log.info("POST endpoint createPriceByWeight [class PriceByWeightController] was called");
        mPriceByWeightService.createPriceByWeight(priceByWeight);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/bulk")
    public void createPriceByWeightBulk(@RequestBody List<PriceByWeight> priceByWeights) {
        log.info("POST endpoint createPriceByWeightBulk [class PriceByWeightController] was called");
        mPriceByWeightService.createPriceByWeightBulk(priceByWeights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceByWeightDto> getPriceByWeightById(@PathVariable("id") long id) {
        log.info("GET endpoint getPriceByWeightById [class PriceByWeightController] was called");
        PriceByWeightDto priceByWeight = mPriceByWeightService.getPriceByWeightById(id);
        return new ResponseEntity<>(priceByWeight, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PriceByWeightDto> updatePriceByWeight(@PathVariable("id") long id,
                                                             @RequestBody PriceByWeight updatedPriceByWeight) {
        log.info("PUT endpoint updatePriceByWeight [class PriceByWeightController] was called");
        PriceByWeightDto priceByWeight = mPriceByWeightService.updatePriceByWeight(id, updatedPriceByWeight);
        return new ResponseEntity<>(priceByWeight, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePriceByWeight(@PathVariable("id") long id) {
        log.info("DELETE endpoint deletePriceByWeight [class PriceByWeightController] was called");
        mPriceByWeightService.deletePriceByWeight(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<PriceByWeightDto>> getAllPriceByWeights() {
        log.info("GET endpoint getAllPriceByWeights [class PriceByWeightController] was called");
        List<PriceByWeightDto> priceByWeights = mPriceByWeightService.getAllPriceByWeights();
        return new ResponseEntity<>(priceByWeights, HttpStatus.OK);
    }
}

