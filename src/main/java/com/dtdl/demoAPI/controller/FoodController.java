package com.dtdl.demoAPI.controller;

import com.dtdl.demoAPI.payload.FoodDto;
import com.dtdl.demoAPI.services.impl.FoodServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/food")
@CrossOrigin
@Api(value = "Food",  tags = "Food API")
public class FoodController {

    @Autowired
    private FoodServiceImpl foodService;

    //GET REQUESTS
    @GetMapping("/getFoods")
    public ResponseEntity<List<FoodDto>> getFoods(){
        List<FoodDto> foodList = this.foodService.getFoods();
        return new ResponseEntity<>(foodList, HttpStatus.ACCEPTED);
    }

    // POST REQUESTS
    @PostMapping("/add")
    public ResponseEntity<Object> addFood(@Valid @RequestBody FoodDto foodDto, @RequestParam("restaurantID") int restaurantID){

        this.foodService.addFood(foodDto, restaurantID);
        return new ResponseEntity<>("Food added!", HttpStatus.CREATED);
    }

    // DELETE REQUESTS
    @DeleteMapping("/remove")
    public ResponseEntity<Object> removeFood(@Valid @RequestParam("Food ID") int id){
        this.foodService.deleteById(id);
        return new ResponseEntity<>("Food Deleted!", HttpStatus.OK);
    }

    // PUT REQUESTS
    @PutMapping("/deactivate")
    public ResponseEntity<Object> deactivateFood(@Valid @RequestParam("Food ID") int id){
        this.foodService.deactivateFoodById(id);
        return new ResponseEntity<>("Food Deleted!", HttpStatus.OK);
    }
}