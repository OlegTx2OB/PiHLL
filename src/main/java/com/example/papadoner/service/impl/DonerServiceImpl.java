package com.example.papadoner.service.impl;

import com.example.papadoner.model.Doner;
import com.example.papadoner.repository.DonerRepository;
import com.example.papadoner.service.DonerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonerServiceImpl implements DonerService {

    private final DonerRepository donerRepository;

    @Autowired
    public DonerServiceImpl(DonerRepository donerRepository) {
        this.donerRepository = donerRepository;
    }

    @Override
    public Doner createDoner(Doner doner) {
        return donerRepository.save(doner);
    }

    @Override
    public Doner getDonerById(long id) {
        return donerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doner with id " + id + " not found"));
    }

    @Override
    public Doner updateDoner(long id, Doner newDoner) {
        if (newDoner == null) {
            throw new IllegalArgumentException("fun updateDoner cannot get null argument");
        }
        Optional<Doner> optionalOldDoner = donerRepository.findById(id);
        if (optionalOldDoner.isPresent()) {
            Doner oldDoner = optionalOldDoner.get();
            newDoner.setId(oldDoner.getId());
            return donerRepository.save(newDoner);
        } else {
            throw new EntityNotFoundException("Doner with id " + id + " not found");
        }
    }

    @Override
    public void deleteDoner(long id) {
        donerRepository.deleteById(id);
    }

    @Override
    public List<Doner> getAllDoners() {
        return donerRepository.findAll();
    }
}