package com.dtdl.demoAPI.services;

import com.dtdl.demoAPI.model.Food;
import com.dtdl.demoAPI.payload.FoodDto;

import java.util.List;

public interface FoodService {

    List<FoodDto> getFoods();

    void addFood(FoodDto food, int restaurantID);

    void deleteById(int id);

    void deactivateFoodById(int id);
}
