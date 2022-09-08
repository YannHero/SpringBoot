package com.example.porfoliotest.repository;

import com.example.porfoliotest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);


}
