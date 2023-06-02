package com.dtdl.demoAPI.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FoodReviewDto {

    private int reviewID;
    private String foodReview;
    private float foodRating;
}
