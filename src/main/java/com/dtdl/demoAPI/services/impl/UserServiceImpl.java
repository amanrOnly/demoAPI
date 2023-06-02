package com.dtdl.demoAPI.services.impl;

import com.dtdl.demoAPI.exception.ResourceNotFoundException;
import com.dtdl.demoAPI.exception.SaveException;
import com.dtdl.demoAPI.model.*;
import com.dtdl.demoAPI.payload.FoodReviewDto;
import com.dtdl.demoAPI.payload.OrderDto;
import com.dtdl.demoAPI.payload.UserDto;
import com.dtdl.demoAPI.repository.*;
import com.dtdl.demoAPI.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private FoodRepository foodRepo;

    @Autowired
    private RestaurantRepository resRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private FoodReviewRepository reviewRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void registerUser(UserDto userDto){
        //mising set<Order>
        User user = this.modelMapper.map(userDto, User.class);
        try {
            this.userRepo.save(user);
        } catch (Exception e) {
            throw new SaveException("User");
        }
    }

    @Override
    public void saveOrder(OrderDto orderDto, int userID){

        DateFormat order_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        String date_time = order_time.format(cal.getTime());
        User user = userRepo.findById(userID).orElseThrow(()->new ResourceNotFoundException());
        Order order = this.modelMapper.map(orderDto, Order.class);
        try {
            order.setUser(user);
            order.setDateTime(date_time);
            this.orderRepo.save(order);
        } catch (Exception e) {
            throw new SaveException("order");
        }
    }

    @Override
    public List<UserDto> getUsers(){
        List<User> userList = null;
        try {
            userList = userRepo.findAll();
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
        return userList.stream().map((user)-> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void addRating(FoodReviewDto reviewDto, int foodID, int restaurantID){

        Food food = foodRepo.findById(foodID).orElseThrow(()->new ResourceNotFoundException());
        FoodReview review = this.modelMapper.map(reviewDto, FoodReview.class);
        try {
            review.setFood(food);
            reviewRepo.save(review);
        } catch (Exception e) {
            throw new SaveException("FoodReview");
        }

        //Calculating Average Rating of the Restaurant based on ratings of individual food rating

        Restaurant restaurant = resRepo.findById(restaurantID).orElseThrow(() -> new ResourceNotFoundException("Restaurant", "restaurantID", restaurantID));
        List<Food> foodList = restaurant.getFoodList();

        if(foodList.isEmpty()){
            restaurant.setRating(0);
        }else{
            float sum = (float) foodList.stream().mapToDouble(item->item.getReview().getFoodRating()).sum();
            float avgRating = sum/foodList.size();
            restaurant.setRating(avgRating);
        }
        resRepo.save(restaurant);
    }

    @Override
    public List<Restaurant> search(int userID, float minRating, String foodName, String sortDir){

        User user = userRepo.findById(userID).orElseThrow(()->new ResourceNotFoundException("User", "userID", userID));
        String location = user.getLocation();
        List<Restaurant> resList = resRepo.searchByRestaurantLocation("%"+foodName+"%","%"+location+"%", minRating);

        if(sortDir.equalsIgnoreCase("ASC")){
            Collections.sort(resList);
        }else{Collections.sort(resList, Collections.reverseOrder());}

//        ---------Sorting using Pagination----------
//        List<Food> filteredFoods = foodRepo.searchByFoodName("%"+foodName+"%");
//        List<Food> filteredAndSortedFoods = filteredFoods.stream().sorted(Comparator.comparing(Food::getFoodName).reversed()).collect(Collectors.toList());
//        Sort sort = (sortDir.equalsIgnoreCase("ASC")) ? (Sort.by(sortBy).ascending()):(Sort.by(sortBy).descending());
//        Pageable filter = PageRequest.of(pageNumber, pageSize, sort);
//        Page<Food> allFoods = food_repo.findAll(filter);
//        List<Food> filteredFoods = allFoods.getContent();

        return resList;
    }

}
