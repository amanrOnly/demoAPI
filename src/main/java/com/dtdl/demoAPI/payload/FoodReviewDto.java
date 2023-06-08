package com.dtdl.demoAPI.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Validated
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class FoodReviewDto implements Serializable {

    private static final long serialVersionUID = 1L;
    @JsonProperty("id")
    private int id = 0;

    @JsonProperty("foodReview")
    private String foodReview = null;

    @JsonProperty("foodRating")
    private float foodRating = 0;
}
