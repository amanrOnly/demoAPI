package com.dtdl.demoAPI.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "demoAPI_FoodReviews")
public class FoodReview {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reviewID;

    @Column
    private String foodReview;

    @Column
    private float foodRating;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "food_ID", referencedColumnName = "foodID")
    private Food food;

}
