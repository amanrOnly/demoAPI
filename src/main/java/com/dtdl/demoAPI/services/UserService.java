package com.dtdl.demoAPI.services;

import com.dtdl.demoAPI.model.FoodReview;
import com.dtdl.demoAPI.model.Order;
import com.dtdl.demoAPI.model.Restaurant;
import com.dtdl.demoAPI.model.User;
import com.dtdl.demoAPI.payload.FoodReviewDto;
import com.dtdl.demoAPI.payload.OrderDto;
import com.dtdl.demoAPI.payload.UserDto;

import java.util.List;

public interface UserService {
    void registerUser(UserDto user);

    void saveOrder(OrderDto order, int userID);

    List<UserDto> getUsers();

    List<Restaurant> search(int userID, float minRating, String foodName, String sortDir);

    void addRating(FoodReviewDto review, int userID, int restaurantID, int foodID);

    UserDto assignFoodToOrder(int userID, int orderID, int foodID);
}
