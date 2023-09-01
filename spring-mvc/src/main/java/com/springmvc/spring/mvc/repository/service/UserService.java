package com.springmvc.spring.mvc.repository.service;

import com.springmvc.spring.mvc.dto.RegistrationDto;
import com.springmvc.spring.mvc.models.User;
import jakarta.servlet.Registration;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);


    User findByEmail(String email);

    User findByUsername(String username);
}
