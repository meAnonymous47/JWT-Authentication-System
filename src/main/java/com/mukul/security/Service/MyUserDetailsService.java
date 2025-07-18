package com.mukul.security.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mukul.security.Model.MyUsers;
import com.mukul.security.Repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUsers user = repo.findByUsername(username);
        if(user.getUsername().equals(username)){
            UserDetails userfound = User.builder()
                                        .username(user.getUsername())
                                        .password(user.getPassword())
                                        .authorities("ROLE_USER")
                                        .build();
            return userfound;
        }
        else{
            // System.out.println("User Not Found");
            throw new UsernameNotFoundException("User not found");
        }
    }
        
}
