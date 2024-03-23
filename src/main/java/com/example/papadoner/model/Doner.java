package com.example.papadoner.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_doner")
public class Doner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "doner_ingredients",
            joinColumns = @JoinColumn(name = "doner_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id"))
    private List<Ingredient> ingredients;


    @ManyToMany
    @JoinTable(name = "doner_price_by_weights",
            joinColumns = @JoinColumn(name = "doner_id"),
            inverseJoinColumns = @JoinColumn(name = "price_by_weights_id"))
    private Set<PriceByWeight> priceByWeights;
}
