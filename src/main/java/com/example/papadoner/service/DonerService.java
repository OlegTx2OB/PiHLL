package com.example.papadoner.service;

import com.example.papadoner.model.Doner;

import java.util.List;

public interface DonerService {

    public Doner createDoner(Doner doner);
    public Doner getDonerById();
    public Doner updateDoner(short id, Doner updatedDoner);
    public void deleteDoner(short id);
    public List<Doner> getAllDoners();
}
