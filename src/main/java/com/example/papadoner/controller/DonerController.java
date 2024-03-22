package com.example.papadoner.controller;

import com.example.papadoner.model.Doner;
import com.example.papadoner.service.DonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doner")
public class DonerController {

    private final DonerService donerService;

    @Autowired
    public DonerController(DonerService donerService) {
        this.donerService = donerService;
    }

    @PostMapping
    public ResponseEntity<Doner> createDoner(@RequestBody Doner doner) {
        Doner createdDoner = donerService.createDoner(doner);
        return new ResponseEntity<>(createdDoner, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doner> getDonerById(@PathVariable("id") short id) {
        Doner doner = donerService.getDonerById(id);
        return new ResponseEntity<>(doner, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doner> updateDoner(@PathVariable("id") short id, @RequestBody Doner updatedDoner) {
        Doner doner = donerService.updateDoner(id, updatedDoner);
        return new ResponseEntity<>(doner, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoner(@PathVariable("id") short id) {
        donerService.deleteDoner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<Doner>> getAllDoners() {
        List<Doner> doners = donerService.getAllDoners();
        return new ResponseEntity<>(doners, HttpStatus.OK);
    }
}
