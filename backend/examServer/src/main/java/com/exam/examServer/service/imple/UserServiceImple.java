package com.exam.examServer.service.imple;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.examServer.entity.User;
import com.exam.examServer.entity.UserRole;
import com.exam.examServer.repo.RoleRepository;
import com.exam.examServer.repo.UserRepository;
import com.exam.examServer.service.UserService;

@Service
public class UserServiceImple implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRole)throws Exception {
        User local=userRepository.findByUsername(user.getUsername());
        if(local!=null){
            throw new Exception("User already present!!!\n\n\n******************");
        }else{
            for(UserRole ur: userRole){
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRole);
            local=userRepository.save(user);
        }
        return local;
    }

    @Override
    public User findByUsername(String username) {
        User u=userRepository.findByUsername(username);
        return u;
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    
    public List<User> allUser(){
        return userRepository.findAll();
    }
}
