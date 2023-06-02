package com.dtdl.demoAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dtdl.demoAPI.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
