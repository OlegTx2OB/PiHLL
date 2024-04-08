package com.example.papadoner.repository;

import com.example.papadoner.model.Doner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonerRepository extends JpaRepository<Doner, Long> {
    List<Doner> findDonersByName(String name);
}
