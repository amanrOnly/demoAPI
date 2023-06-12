package com.dtdl.demoAPI.services.impl;

import com.dtdl.demoAPI.exception.ResourceNotFoundException;
import com.dtdl.demoAPI.exception.SaveException;
import com.dtdl.demoAPI.exception.ServerException;
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

//        DateFormat order_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar cal = Calendar.getInstance();
//        String date_time = order_time.format(cal.getTime());
        User user = this.userRepo.findById(userID).orElseThrow(()->new ResourceNotFoundException("saveOrder", 58));
        Order order = this.modelMapper.map(orderDto, Order.class);
//        try {
            order.setUser(user);
            this.orderRepo.save(order);
//        } catch (Exception e) {
//            throw new SaveException("order");
//        }
    }

    @Override
    public List<UserDto> getUsers(){
        List<User> userList = null;
        try {
            userList = this.userRepo.findAll();
        } catch (Exception e) {
            throw new ResourceNotFoundException("getUsers", 71);
        }
        return userList.stream().map((user)-> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public void addRating(FoodReviewDto reviewDto, int userID, int restaurantID, int foodID){

        Food food = this.foodRepo.findById(foodID).orElseThrow(()->new ResourceNotFoundException("addRating", 81));
        FoodReview review = this.modelMapper.map(reviewDto, FoodReview.class);
        User user = this.userRepo.findById(userID).orElseThrow(()->new ResourceNotFoundException("addRating", 83));
        Set<FoodReview> reviewSet = food.getReviewSet();
        reviewSet.add(review);
        review.setUserReview(user);
        try {
//            review.setFoodReview(reviewDto.getFoodReview());
//            review.setFoodRating(reviewDto.getFoodRating());
//            review.setFood(food);
            food.setReviewSet(reviewSet);
            this.foodRepo.save(food);
        } catch (Exception e) {
            throw new SaveException("FoodReview");
        }

        //Calculating Average Rating of the Restaurant based on ratings of individual food rating

        Restaurant restaurant = this.resRepo.findById(restaurantID).orElseThrow(() -> new ResourceNotFoundException("addRating", 94));
        List<Food> foodList = restaurant.getFoodList();

        try {
            if(foodList.isEmpty()) restaurant.setRating(0);
            else{
                float sumRatingFood = (float) reviewSet.stream().mapToDouble(singleReview->singleReview.getFoodRating()).sum();
                float avgFoodRating = sumRatingFood/reviewSet.size();
                food.setRating(avgFoodRating);
                this.foodRepo.save(food);
                float sumRatingRestaurant = (float) foodList.stream().mapToDouble(singleFood->singleFood.getRating()).sum();
                float avgRestaurantRating = sumRatingRestaurant/foodList.size();
                restaurant.setRating(avgRestaurantRating);
                this.resRepo.save(restaurant);
            }
        } catch (Exception e) {
            throw new ServerException("Error while calculating average ratings for Food and Restaurant", System.getProperty("user.dir"));
        }
    }

    @Override
    public UserDto assignFoodToOrder(int userID, int orderID, int foodID) {

        Set<Food> foodSet = null;
        Order order = this.orderRepo.findById(orderID).orElseThrow(()->new ResourceNotFoundException("assignFoodToOrder", 111));
        Food food = this.foodRepo.findById(foodID).orElseThrow(()-> new ResourceNotFoundException("assignFoodToOrder", 112));
        foodSet = order.getFoodSet();
        foodSet.add(food);
        order.setFoodSet(foodSet);
        this.orderRepo.save(order);
        User user = this.userRepo.findById(userID).orElseThrow(()->new ResourceNotFoundException("assignFoodToOrder", 117));
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public List<Restaurant> search(int userID, float minRating, String foodName, String sortDir){

        User user = this.userRepo.findById(userID).orElseThrow(()->new ResourceNotFoundException("search", 138));
        String location = user.getLocation();
        System.out.println("/-----------LOCATION----------"+location);
        List<Restaurant> resList = this.resRepo.searchByRestaurantLocation("%"+foodName+"%","%"+location+"%", minRating);
        resList.forEach(System.out::println);
//        if(sortDir.equalsIgnoreCase("ASC")){
//            Collections.sort(resList);
//        }else{Collections.sort(resList, Collections.reverseOrder());}

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
