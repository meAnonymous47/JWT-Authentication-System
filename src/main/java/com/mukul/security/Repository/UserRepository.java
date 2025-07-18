package com.mukul.security.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mukul.security.Model.MyUsers;

@Repository
public interface UserRepository extends JpaRepository<MyUsers,Integer>{
    public MyUsers findByUsername(String username);
    public boolean existsByUsername(String username);
}
