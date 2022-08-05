package com.exam.examServer;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.examServer.entity.Role;
import com.exam.examServer.entity.User;
import com.exam.examServer.entity.UserRole;
import com.exam.examServer.service.imple.UserServiceImple;

@SpringBootApplication
public class ExamServerApplication implements CommandLineRunner{
	@Autowired
	UserServiceImple userService;

	public static void main(String[] args) {
		SpringApplication.run(ExamServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("starting code");

		 
		User user= new User();
		user.setUsername("DEbangwan");
		user.setFirstName("deb");
		user.setLastName("Ray");
		user.setPassword("password");
		user.setEmail("abc@gmail.com");
		user.setPhone("9876567890");
		user.setProfile("userM.jpg");
		
		System.out.println("\n111111111111111111111111111111111\n");
		Role role=new Role(1L, "ADMIN");
		
		UserRole userRoles=new UserRole();
		userRoles.setUser(user);
		userRoles.setRole(role);

		System.out.println("\n22222222222222222222222222222222\n");

		Set<UserRole> ur=new HashSet<UserRole>();
		ur.add(userRoles);

		System.out.println("\n133333333333333333333333333333333331\n");


		try{
			User u1=this.userService.createUser(user,ur);
		}catch(Exception e){}
		System.out.println(userService.allUser());
	}

}
