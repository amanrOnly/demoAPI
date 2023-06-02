package com.dtdl.demoAPI.repository;

import com.dtdl.demoAPI.model.FoodReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodReviewRepository extends JpaRepository<FoodReview, Integer> {
}
