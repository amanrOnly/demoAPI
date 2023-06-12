package com.dtdl.demoAPI.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "food", fetch = FetchType.LAZY)
    private Set<FoodReview> reviewSet;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_food")
    private Restaurant res;

    @ManyToMany(mappedBy = "foodSet", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<Order> orderSet = new HashSet<>();
}
