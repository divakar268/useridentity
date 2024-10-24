package com.example.useridentity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.useridentity.entity.Users;

public interface UserRepository  extends JpaRepository<Users,Long  >{

}
