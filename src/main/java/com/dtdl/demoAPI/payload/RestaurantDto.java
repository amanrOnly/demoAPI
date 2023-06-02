package com.dtdl.demoAPI.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RestaurantDto implements Comparable<RestaurantDto>{

    private int restaurantID;
    private String restaurantName;
    private String ownerName;
    private String location;
    private int contactNumber;
    private float rating;

    @Override
    public int compareTo(RestaurantDto o) {
        return Float.compare(this.rating, o.rating);
    }
}