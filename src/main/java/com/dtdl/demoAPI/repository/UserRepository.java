package com.dtdl.demoAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.dtdl.demoAPI.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
