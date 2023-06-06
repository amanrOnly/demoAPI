package com.dtdl.demoAPI.services;

import com.dtdl.demoAPI.model.Restaurant;
import com.dtdl.demoAPI.payload.RestaurantDto;

public interface RestaurantService {
    void RegisterRestaurant(RestaurantDto restaurant);

    void updateRestaurant(RestaurantDto restaurant, int id);
    RestaurantDto assignOrderToFood(int restaurantID, int foodID, int orderID);
}
