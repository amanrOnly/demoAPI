package com.dtdl.demoAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.dtdl.demoAPI.model.Food;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    @Query(value = "SELECT t FROM Food t WHERE t.foodName LIKE :key AND t.availabilityStatus=true")
    List<Food> searchByFoodName(@Param("key") String food_name);

    @Query(value = "SELECT t FROM Food t WHERE t.availabilityStatus=true")
    List<Food> getAvailableFoods();

}
