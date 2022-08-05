package com.exam.examServer.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examServer.entity.Role;
import com.exam.examServer.entity.User;
import com.exam.examServer.entity.UserRole;
import com.exam.examServer.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User createUser(@RequestBody User user){
        if(user.getProfile()==null)user.setProfile("default.png");
        Role role=new Role();
        role.setRoleName("NORMAL");
        Set<UserRole> ur=new HashSet<UserRole>();
        ur.add(new UserRole(user,role));
        try{
            user=this.userService.createUser(user, ur);
            return user;
        }catch(Exception e){}
        return null;
        
    }

    @GetMapping("/{username}")
    public User getUserByName(@PathVariable String username){
        User u=userService.findByUsername(username);
        return u;
    }

    @DeleteMapping("/{idvar}")
    public void deleteUserById(@PathVariable("idvar") Long id){
        userService.deleteById(id);
    }

    
}
