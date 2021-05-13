package com.vidnyan.javaspringbootjwtautontication.controller;

import com.vidnyan.javaspringbootjwtautontication.entiry.User;
import com.vidnyan.javaspringbootjwtautontication.utility.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;


    @GetMapping("/")
    public String getWelcomeString(){
        return "welcome";
    }

    @PostMapping(value="authonticate")
    public ResponseEntity createAuthonticationToken(@RequestBody User user)throws Exception {

        try{ 
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        }catch(Exception e){
            System.out.println(e);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
        
        final String jwtTokenString = jwtUtil.generateToken(userDetails);
        
        return new ResponseEntity<>(jwtTokenString, HttpStatus.OK);
    }

    

}
