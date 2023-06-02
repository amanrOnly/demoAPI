package com.dtdl.demoAPI.services.impl;

import com.dtdl.demoAPI.exception.ResourceNotFoundException;
import com.dtdl.demoAPI.exception.SaveException;
import com.dtdl.demoAPI.model.Restaurant;
import com.dtdl.demoAPI.payload.RestaurantDto;
import com.dtdl.demoAPI.repository.RestaurantRepository;
import com.dtdl.demoAPI.services.RestaurantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository resRepo;

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

        Restaurant res = resRepo.findById(id).orElseThrow(()->new ResourceNotFoundException());
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
}
