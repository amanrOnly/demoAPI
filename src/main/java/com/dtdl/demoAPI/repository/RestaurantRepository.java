package com.dtdl.demoAPI.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.dtdl.demoAPI.model.Restaurant;

import java.util.List;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query(value = "SELECT t FROM Restaurant t JOIN t.foodList f WHERE f.foodName LIKE :reqFood AND t.location LIKE :key AND t.rating >= :minRating")
    List<Restaurant> searchByRestaurantLocation(@Param("reqFood") String reqFood, @Param("key") String restaurantLocation, @Param("minRating") float minRating);

}
