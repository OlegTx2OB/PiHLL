package com.example.papadoner.service.impl;

import com.example.papadoner.model.Doner;
import com.example.papadoner.service.DonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonerServiceImpl implements DonerService {


    @Autowired
    public DonerServiceImpl() {

    }

    @Override
    public Doner createDoner(Doner doner) {
        return null;
    }

    @Override
    public Doner getDonerById() {
        return null;
    }

    @Override
    public Doner updateDoner(short id, Doner updatedDoner) {
        return null;
    }

    @Override
    public void deleteDoner(short id) {

    }

    @Override
    public List<Doner> getAllDoners() {
        return null;
    }
}