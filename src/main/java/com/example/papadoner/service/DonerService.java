package com.example.papadoner.service;

import com.example.papadoner.model.Doner;

import java.util.List;

public interface DonerService {

    public Doner createDoner(Doner doner);
    public Doner getDonerById(long id);
    public Doner updateDoner(long id, Doner updatedDoner);
    public void deleteDoner(long id);
    public List<Doner> getAllDoners();
}
