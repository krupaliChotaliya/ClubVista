package com.springmvc.spring.mvc.controller;

import com.springmvc.spring.mvc.dto.RegistrationDto;
import com.springmvc.spring.mvc.models.User;
import com.springmvc.spring.mvc.repository.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        RegistrationDto user=new RegistrationDto();
        model.addAttribute("user",user);
        return "Register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user")RegistrationDto user, BindingResult result,Model model){
       User existingUserEmail= userService.findByEmail(user.getEmail());
       if(existingUserEmail!=null && existingUserEmail.getEmail() !=null && !existingUserEmail.getEmail().isEmpty()){
          return "redirect:/register?fail";
       }
       User existinguser=userService.findByUsername(user.getUsername());
        if(existinguser!=null && existinguser.getUsername() !=null && !existinguser.getUsername().isEmpty()){
            return "redirect:/register?fail";
        }
        if(result.hasErrors()){
            model.addAttribute("user",user);
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/clubs?success";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
}
