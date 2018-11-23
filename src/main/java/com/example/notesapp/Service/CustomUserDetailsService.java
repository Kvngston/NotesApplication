package com.example.notesapp.Service;

import com.example.notesapp.Domain.User;
import com.example.notesapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  = userRepo.findByUserName(username);
        CustomUserDetails userDetails = null;

        if(user != null){
            userDetails = new CustomUserDetails();
            //System.out.println(user);
            userDetails.setUser(user);
        }else {
            throw  new UsernameNotFoundException("user with Username " + username + "does not exist");
        }

        return userDetails;
    }
}
