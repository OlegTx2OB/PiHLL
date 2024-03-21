package com.example.papadoner.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "price_by_weight")
public class PriceByWeight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;

    private short name;

    private double price;
}