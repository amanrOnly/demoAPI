package com.dtdl.demoAPI.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FoodDto {

    private int foodID;
    private String foodName;
    private int calorie;
    private float price;
    private float rating;
    private Boolean availabilityStatus;
}
