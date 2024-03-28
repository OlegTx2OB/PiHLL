package com.example.papadoner.repository;

import com.example.papadoner.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    @Query("SELECT ingredient FROM Ingredient ingredient WHERE ingredient.name=?1")
    Ingredient findIngredientByName(String name);
}
