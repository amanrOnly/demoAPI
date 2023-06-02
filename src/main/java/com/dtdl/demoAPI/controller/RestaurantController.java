package com.dtdl.demoAPI.controller;

import com.dtdl.demoAPI.payload.RestaurantDto;
import com.dtdl.demoAPI.services.impl.RestaurantServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin
@Api(value = "Restaurants",  tags = "Restaurant API")
public class RestaurantController {

    @Autowired
    private RestaurantServiceImpl service;

    // POST REQUESTS
    @PostMapping("/register")
    public ResponseEntity<Object> registerRestaurant(@Valid @RequestBody RestaurantDto restaurant) {

        service.RegisterRestaurant(restaurant);
        return new ResponseEntity<Object>("Restaurant Registered!", HttpStatus.CREATED);
    }

    // PUT REQUESTS
    @PutMapping("/update")
    public ResponseEntity<Object> updateRestaurant(@Valid @RequestBody RestaurantDto restaurant, @RequestParam("restaurantID") int id){

        service.updateRestaurant(restaurant, id);
        return new ResponseEntity<Object>("Restaurant Updated!", HttpStatus.OK);
    }
}
