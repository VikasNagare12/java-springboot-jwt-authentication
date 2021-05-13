package com.vidnyan.javaspringbootjwtautontication.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vidnyan.javaspringbootjwtautontication.service.UserService;
import com.vidnyan.javaspringbootjwtautontication.utility.JWTUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain;
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;
    @Autowired JWTUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorisationHeader =request.getHeader("Authorization");
        String userName = null;
        String jwt= null;
        if(authorisationHeader != null && authorisationHeader.startsWith("Bearer ")){
            jwt = authorisationHeader.substring(7);
            userName = jwtUtil.getUsernameFromToken(jwt);
        }        
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() ==null){
            
            UserDetails userDetails = this.userService.loadUserByUsername(userName);
            if(jwtUtil.validateToken(jwt, userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken
                (userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                filterChain.doFilter(request, response);
            }
        }
    }
    

}