package com.dtdl.demoAPI.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "demoAPI_food")
public class Food {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int foodID;

    @Column
    private String foodName;

    @Column
    private int calorie;

    @Column
    private float price;

    @Column
    private float rating;

    @Column
    private Boolean availabilityStatus;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "food")
    private Set<FoodReview> reviewSet;

    @ManyToOne
    @JoinColumn(name = "restaurant_food")
    private Restaurant res;

    @ManyToMany(mappedBy = "foodSet", cascade = CascadeType.ALL)
    private Set<Order> orderSet = new HashSet<>();
}
