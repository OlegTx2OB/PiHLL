package com.example.papadoner.controller;

import com.example.papadoner.model.Doner;
import com.example.papadoner.service.DonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//public class DonerController {
//
//    private final DonerService donerService;
//
//    @Autowired
//    public DonerController(DonerService donerService) {
//        this.donerService = donerService;
//    }
//
//    @GetMapping("/getDoner/{name}")
//    public Doner getDonerByName(@PathVariable("name") String name) {
//        return donerService.getDonerByName(name);
//    }
//
//}
