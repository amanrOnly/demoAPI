package com.dtdl.demoAPI.services.impl;

import com.dtdl.demoAPI.exception.ResourceNotFoundException;
import com.dtdl.demoAPI.model.Food;
import com.dtdl.demoAPI.model.FoodReview;
import com.dtdl.demoAPI.model.Restaurant;
import com.dtdl.demoAPI.payload.FoodDto;
import com.dtdl.demoAPI.repository.FoodRepository;
import com.dtdl.demoAPI.repository.FoodReviewRepository;
import com.dtdl.demoAPI.repository.RestaurantRepository;
import com.dtdl.demoAPI.services.FoodService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepo;

    @Autowired
    private RestaurantRepository resRepo;

    @Autowired
    private FoodReviewRepository reviewRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<FoodDto> getFoods(){
        List<Food> foodList = null;
        try {
            foodList = this.foodRepo.getAvailableFoods();
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
        List<FoodDto> foodDtoList = foodList.stream().map((food)->this.modelMapper.map(food, FoodDto.class)).collect(Collectors.toList());
        return foodDtoList;
    }

    @Override
    public void addFood(FoodDto foodDto, int restaurantID, int reviewID){

        Food food = this.modelMapper.map(foodDto, Food.class);
        Restaurant restaurant = resRepo.findById(restaurantID).orElseThrow(()->new ResourceNotFoundException("Restaurant", "restaurantID", restaurantID));
        food.setRes(restaurant);
        FoodReview review = reviewRepo.findById(reviewID).get();
        food.setReview(review);
        // setOrder remaining
        foodRepo.save(food);
    }

    @Override
    public void deleteById(int id){
        Food food = this.foodRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException());
        foodRepo.delete(food);
    }

    @Override
    public void deactivateFoodById(int id){
        Food food = foodRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Food Table", "foodID", id));
        food.setAvailabilityStatus(false);
        foodRepo.save(food);
    }
}
