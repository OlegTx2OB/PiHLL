package com.example.papadoner.controller;

import com.example.papadoner.dto.PriceByWeightDto;
import com.example.papadoner.model.PriceByWeight;
import com.example.papadoner.service.PriceByWeightService;
import com.example.papadoner.service.RequestCounterService;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@Log4j2
@RestController
@RequestMapping("/priceByWeight")
public class PriceByWeightController {

    private final PriceByWeightService mPriceByWeightService;
    private final RequestCounterService mRequestCounterService;

    @Autowired
    public PriceByWeightController(
            PriceByWeightService priceByWeightService,
            RequestCounterService requestCounterService
    ) {
        this.mPriceByWeightService = priceByWeightService;
        this.mRequestCounterService = requestCounterService;
    }

    @PostMapping
    public ResponseEntity<Void> createPriceByWeight(@RequestBody PriceByWeight priceByWeight) {
        mPriceByWeightService.createPriceByWeight(priceByWeight);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/bulk")
    public void createPriceByWeightBulk(@RequestBody List<PriceByWeight> priceByWeights) {
        mPriceByWeightService.createPriceByWeightBulk(priceByWeights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceByWeightDto> getPriceByWeightById(@PathVariable("id") long id) {
        log.info("{}", mRequestCounterService.requestIncrement());
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

