package com.dtdl.demoAPI.services.impl;

import com.dtdl.demoAPI.exception.ResourceNotFoundException;
import com.dtdl.demoAPI.exception.SaveException;
import com.dtdl.demoAPI.model.Food;
import com.dtdl.demoAPI.model.Order;
import com.dtdl.demoAPI.model.Restaurant;
import com.dtdl.demoAPI.payload.RestaurantDto;
import com.dtdl.demoAPI.repository.FoodRepository;
import com.dtdl.demoAPI.repository.OrderRepository;
import com.dtdl.demoAPI.repository.RestaurantRepository;
import com.dtdl.demoAPI.services.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository resRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private FoodRepository foodRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void RegisterRestaurant(RestaurantDto restaurantDto){

        try {
            Restaurant restaurant = this.modelMapper.map(restaurantDto, Restaurant.class);
            resRepo.save(restaurant);
        } catch (Exception e) {
            throw new SaveException("Restaurant");
        }
    }

    @Override
    public void updateRestaurant(RestaurantDto restaurantDto, int id){

        Restaurant res = resRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("updateRestaurant",48));
        try {
            res.setRestaurantName(restaurantDto.getRestaurantName());
            res.setOwnerName(restaurantDto.getOwnerName());
            res.setLocation(restaurantDto.getLocation());
            res.setContactNumber(restaurantDto.getContactNumber());
            resRepo.save(res);
        } catch (Exception e) {
            throw new SaveException("Restaurant");
        }
    }

    public RestaurantDto assignOrderToFood(int restaurantID, int foodID, int orderID) {

        Set<Order> orderSet = null;
        Order order = this.orderRepo.findById(orderID).orElseThrow(()->new ResourceNotFoundException("assignOrderToFood",63));
        Food food = this.foodRepo.findById(foodID).orElseThrow(()->new ResourceNotFoundException("assignOrderToFood",64));
        orderSet = food.getOrderSet();
        orderSet.add(order);
        food.setOrderSet(orderSet);
        this.foodRepo.save(food);
        Restaurant restaurant = resRepo.findById(restaurantID).orElseThrow(()->new ResourceNotFoundException("assignOrderToFood",69));
        RestaurantDto restaurantDto = this.modelMapper.map(restaurant, RestaurantDto.class);
        return restaurantDto;
    }
}
