package com.example.springarchtest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springarchtest.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
