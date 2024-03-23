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
@Table(name = "t_price_by_weight")
public class PriceByWeight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private short weight;
    private double price;

//    @ManyToMany(mappedBy = "priceByWeights")
//    private Set<Doner> doners;
}