package com.mukul.security.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager; 
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mukul.security.Model.MyUsers;
import com.mukul.security.Repository.UserRepository;

@Service
public class UserService {

    private final JWTService jwtService;
    private final AuthenticationManager authManager;
    private final UserRepository repository;
    UserService(UserRepository repository,AuthenticationManager authManager,JWTService jwtService){
        this.repository = repository;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public ResponseEntity<String> register(MyUsers user) {
        if(repository.existsByUsername(user.getUsername())){
            return new ResponseEntity<>("Username Already Taken",HttpStatus.CONFLICT);
        }else{
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            repository.save(user);
            return new ResponseEntity<>("User Registered Successfully",HttpStatus.OK);
        }
    }

    public ResponseEntity<String> verify(MyUsers user) {
        Authentication authentication = authManager
            .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        return new ResponseEntity<>("Login Failed",HttpStatus.BAD_REQUEST);
    }
}
