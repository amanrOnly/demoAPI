package com.dtdl.demoAPI.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "food")
    private FoodReview review;

    @ManyToOne
    @JoinColumn(name = "RestaurantID")
    private Restaurant res;

    @ManyToMany(mappedBy = "foods", cascade = CascadeType.ALL)
    private List<Order> orderList = new ArrayList<>();
}
