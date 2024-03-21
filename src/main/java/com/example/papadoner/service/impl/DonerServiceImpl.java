package com.example.papadoner.service.impl;

import com.example.papadoner.dao.DonerDAO;
import com.example.papadoner.model.Doner;
import com.example.papadoner.service.DonerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonerServiceImpl implements DonerService {

    private final DonerDAO donerDAO;

    @Autowired
    public DonerServiceImpl(DonerDAO donerDAO) {
        this.donerDAO = donerDAO;
    }
    public Doner getDonerByName(String name) {
        return donerDAO.findByName(name);
    }

}
