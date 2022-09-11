package com.example.porfoliotest.services;

import com.example.porfoliotest.model.User;
import com.example.porfoliotest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public void createUser (User user) {
        BCryptPasswordEncoder encodePwd = new BCryptPasswordEncoder();
        user.setPassword(encodePwd.encode(user.getPassword()));
        userRepository.save(user);
    }



}
