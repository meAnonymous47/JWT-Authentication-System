package com.mukul.security.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mukul.security.Model.DukaanItem;

@Repository
public interface DukaanRepository extends JpaRepository<DukaanItem,Integer>{

}
