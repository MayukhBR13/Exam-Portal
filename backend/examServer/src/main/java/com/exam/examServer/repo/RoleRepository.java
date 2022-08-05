package com.exam.examServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.examServer.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
    
}
