package com.exam.examServer.service.imple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.examServer.entity.User;
import com.exam.examServer.repo.UserRepository;

@Service
public class UserDetailsServiceImple implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        if(user==null){
            System.out.println("username not found!!");
            throw new UsernameNotFoundException("No User Found");
        }
        return user;
    }

    
}
