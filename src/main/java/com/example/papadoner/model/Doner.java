package com.example.papadoner.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_doners")
public class Doner {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String name;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "t_doners_ingredients", joinColumns = {@JoinColumn(name = "doner_id")}, inverseJoinColumns = {@JoinColumn(name = "ingredient_id")})
    private List<Ingredient> ingredients = new ArrayList<>();

    @JsonIgnore
    @OneToMany
    private List<PriceByWeight> pricesByWeight = new ArrayList<>();
}
