package com.example.papadoner.controller;

import com.example.papadoner.model.Doner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DonerController {

    @GetMapping("/order/{name}/{weight}/{price}")
    public Doner order(@PathVariable("name") String name,
                            @PathVariable("weight") int weight,
                            @PathVariable("price") double price) {
        return new Doner(name, price, weight);
    }
    @PostMapping("/create")
    public ResponseEntity<Doner> create(@RequestBody Doner doner) {
        return new ResponseEntity<>(doner, HttpStatus.CREATED);
    }
}
