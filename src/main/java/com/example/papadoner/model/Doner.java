package com.example.papadoner.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Doner {
    private String name;
    private double price;
    private int weight;
}
