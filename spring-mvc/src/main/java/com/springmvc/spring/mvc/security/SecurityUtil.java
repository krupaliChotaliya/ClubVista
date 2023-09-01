package com.springmvc.spring.mvc.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    //check whether user is only visitor or creater
    //null means actually a visitor
    public static String getSessionUser(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername=authentication.getName();
            return currentUsername;
        }
        return null;
    }
}
