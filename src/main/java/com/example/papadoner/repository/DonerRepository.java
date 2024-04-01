package com.example.papadoner.repository;

import com.example.papadoner.model.Doner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface DonerRepository extends JpaRepository<Doner, Long> {
    Optional<Set<Doner>> findDonersByName(String name);

}
