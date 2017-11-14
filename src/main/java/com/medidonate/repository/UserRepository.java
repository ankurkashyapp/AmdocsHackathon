package com.medidonate.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.medidonate.model.User;

public interface UserRepository extends JpaRepository<User, Integer>
{
   public List<User> findByNameIgnoreCaseContaining(String name);
   
   @Modifying
   @Transactional(readOnly = false)
   @Query("insert into public."+"'"+"USER_DETAILS"+"'"+"(NAME,EMAIL,GENDER,CITY,ADDRESS,PASSWORD,TYPE,PIC,MOBILE) VALUES ('Ankur', 'aanchal@gmail.com', 'F', 'Pune', 'Sinhgad', 'User123', 'NGO', 'AAA', '808888')")
   public void saveData() ;
   
   
   
}
