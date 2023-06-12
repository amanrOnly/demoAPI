package com.dtdl.demoAPI.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "food_reviews", referencedColumnName = "foodID")
    private Food food;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_review")
    private User userReview;
}
