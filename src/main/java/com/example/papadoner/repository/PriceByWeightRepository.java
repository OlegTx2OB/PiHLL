package com.example.papadoner.repository;

import com.example.papadoner.model.PriceByWeight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceByWeightRepository extends JpaRepository<PriceByWeight, Short> {
}
