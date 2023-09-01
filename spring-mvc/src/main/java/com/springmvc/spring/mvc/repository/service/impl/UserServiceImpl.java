package com.springmvc.spring.mvc.repository.service.impl;

import com.springmvc.spring.mvc.dto.RegistrationDto;
import com.springmvc.spring.mvc.models.Role;
import com.springmvc.spring.mvc.models.User;
import com.springmvc.spring.mvc.repository.RoleRepository;
import com.springmvc.spring.mvc.repository.UserRepository;
import com.springmvc.spring.mvc.repository.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.lang.reflect.Array;
import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository,RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository=roleRepository;
        this.passwordEncoder=passwordEncoder;
    }

    @Override
    public void saveUser(RegistrationDto registrationDto) {
            User user=new User();
            user.setEmail(registrationDto.getEmail());
            user.setUsername(registrationDto.getUsername());
            user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
            Role role=roleRepository.findByName("USER");
            user.setRoles(Arrays.asList(role));
            userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
