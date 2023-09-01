package com.springmvc.spring.mvc.repository;

import com.springmvc.spring.mvc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
   User findByEmail(String email);
   User findByUsername(String username);
   User findFirstByUsername(String username);
}
