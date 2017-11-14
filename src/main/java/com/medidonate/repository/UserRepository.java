package com.medidonate.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medidonate.model.User;

public interface UserRepository extends JpaRepository<User, Integer>
{
   public List<User> findByNameIgnoreCaseContaining(String name);
   
}
