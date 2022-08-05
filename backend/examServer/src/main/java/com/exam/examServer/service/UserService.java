package com.exam.examServer.service;

import java.util.Set;

import com.exam.examServer.entity.User;
import com.exam.examServer.entity.UserRole;

public interface UserService {
    public User createUser(User user,Set<UserRole> userRole)throws Exception;
    public User findByUsername(String username);
    public void deleteById(Long id);
}
