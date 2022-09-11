package com.example.porfoliotest.services;

import com.example.porfoliotest.model.User;
import com.example.porfoliotest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserLoginDetailsService  implements UserDetailsService {
    @Autowired
   private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        System.out.printf("User Trouvé!!!");
        return new UserLogin(user);

    }
}
