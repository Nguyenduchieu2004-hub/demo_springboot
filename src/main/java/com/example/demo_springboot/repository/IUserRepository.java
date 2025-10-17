package com.example.demo_springboot.repository;

import com.example.demo_springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, String> {
    User findByEmailAndPassword(String email, String password);
}