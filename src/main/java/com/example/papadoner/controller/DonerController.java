package com.example.papadoner.controller;

import com.example.papadoner.dto.DonerDto;
import com.example.papadoner.mapper.DonerMapper;
import com.example.papadoner.model.Doner;
import com.example.papadoner.service.DonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/doner")
public class DonerController {

    private final DonerService donerService;

    private final DonerMapper donerMapper;

    @Autowired
    public DonerController(DonerService donerService,
                           DonerMapper donerMapper) {
        this.donerService = donerService;
        this.donerMapper = donerMapper;

    }

    @PostMapping
    public ResponseEntity<Doner> createDoner(@RequestBody Doner doner) {
        Doner createdDoner = donerService.createDoner(doner);
        return new ResponseEntity<>(createdDoner, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonerDto> getDonerById(@PathVariable("id") long id) {
        Doner doner = donerService.getDonerById(id);
        return new ResponseEntity<>(donerMapper.toDto(doner), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doner> updateDoner(@PathVariable("id") long id, @RequestBody Doner updatedDoner) {
        Doner doner = donerService.updateDoner(id, updatedDoner);
        return new ResponseEntity<>(doner, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoner(@PathVariable("id") long id) {
        donerService.deleteDoner(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<DonerDto>> getAllDoners() {
        List<Doner> doners = donerService.getAllDoners();
        List<DonerDto> donersDto = new ArrayList<>();
        for (Doner doner : doners) {
            donersDto.add(donerMapper.toDto(doner));
        }
        return new ResponseEntity<>(donersDto, HttpStatus.OK);
    }
}
