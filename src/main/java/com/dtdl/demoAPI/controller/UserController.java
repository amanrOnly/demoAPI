package com.dtdl.demoAPI.controller;

import com.dtdl.demoAPI.model.*;
import com.dtdl.demoAPI.payload.FoodReviewDto;
import com.dtdl.demoAPI.payload.OrderDto;
import com.dtdl.demoAPI.payload.UserDto;
import com.dtdl.demoAPI.services.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Api(value = "User",  tags = "User API")
public class UserController {

    @Autowired
    private UserService userService;

    // GET REQUESTS
    @GetMapping("/getUsers")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> userList = userService.getUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    // POST REQUESTS
    @PostMapping("/register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody UserDto userDto){
        this.userService.registerUser(userDto);
        return new ResponseEntity<>("User Registered!", HttpStatus.CREATED);
    }

    @PostMapping("/order")
    public ResponseEntity<Object> order(@Valid @RequestBody OrderDto order, @RequestParam int userID){
        this.userService.saveOrder(order, userID);
        return new ResponseEntity<>("Food Ordered!", HttpStatus.CREATED);
    }

    @PostMapping("/addReview")
    public ResponseEntity<Object> addReview(@Valid FoodReviewDto review, @RequestParam("RestaurantID") int restaurantID, @RequestParam("FoodID") int foodID){
        this.userService.addRating(review, restaurantID, foodID);
        return new ResponseEntity<>("Feedback Submitted!", HttpStatus.CREATED);
    }

    @GetMapping("/user/{id}/search")
    public ResponseEntity<List<Restaurant>> searchRestaurants(
            @PathVariable int id,
            @RequestParam("minimumRating") float minRating,
            @RequestParam("foodName") String foodName,
            @RequestParam("sortDirection") String sortDir){
        List<Restaurant> filteredList =  userService.search(id, minRating, foodName, sortDir);
        return new ResponseEntity<>(filteredList, HttpStatus.OK);
    }

//    @GetMapping(  "/user/{id}/search")
//    public ResponseEntity<Object> search(
//            @PathVariable int id,
//            @RequestParam (value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
//            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
//            @RequestParam("foodName") String foodName,
//            @RequestParam("restaurantName") String restaurantName,
//            @RequestParam("sortBy") String sortBy,
//            @RequestParam(value = "SortDirection", defaultValue = "DESC",required = false) String sortDir){
//
//        return new ResponseEntity<>("DONE", HttpStatus.OK);
//    }

}
