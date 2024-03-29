package com.example.papadoner.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/*
 * http://localhost:8080/swagger-ui/index.html
 */

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

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Ingredient> ingredients;
}
