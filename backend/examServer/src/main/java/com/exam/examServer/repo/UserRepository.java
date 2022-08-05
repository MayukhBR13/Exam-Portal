package com.exam.examServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.examServer.entity.User;

public interface UserRepository extends JpaRepository<User,Long>{
    public User findByUsername(String username);
    
}
