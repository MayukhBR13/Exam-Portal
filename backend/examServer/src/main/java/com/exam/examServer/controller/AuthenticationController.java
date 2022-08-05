package com.exam.examServer.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examServer.config.JwtUtils;
import com.exam.examServer.entity.JwtRequest;
import com.exam.examServer.entity.JwtResponse;
import com.exam.examServer.entity.User;
import com.exam.examServer.helper.UserNotFoundException;
import com.exam.examServer.service.imple.UserDetailsServiceImple;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImple userDetailsServiceImple;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        System.out.println(jwtRequest.getUsername()+"------"+ jwtRequest.getPassword());
        try{
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        }catch(UserNotFoundException e){
            e.printStackTrace();
            throw new Exception("User not Found!!");
        }

        UserDetails userDetails=this.userDetailsServiceImple.loadUserByUsername(jwtRequest.getUsername());
        String token=this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token)); 
    }

    private void authenticate(String username,String password) throws Exception{
        try{

            
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch(DisabledException e){
            throw new Exception("USER DISABLED "+e.getMessage());
        }catch(BadCredentialsException e){
            throw new Exception("Invalid Credentials "+e.getMessage());
        }
    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        return ((User)this.userDetailsServiceImple.loadUserByUsername(principal.getName()));
    }
}
