package com.xrest.emanagement.core.JWT;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.xrest.emanagement.core.JWTCONSTANT;
import com.xrest.emanagement.entity.User;
import org.json.JSONObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Authentication extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private ObjectMapper objectMapper = new ObjectMapper();

    public Authentication(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public org.springframework.security.core.Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User loginDto = objectMapper.readValue(request.getInputStream(),User.class);
            if (loginDto.getUsername() == null || loginDto.getPassword() == null) {
                throw new UsernameNotFoundException("Not Valid");
            }
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword(), new ArrayList<>());
            return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, org.springframework.security.core.Authentication authResult) throws IOException, ServletException {
        User user = (User) authResult.getPrincipal();
        Gson gson = new Gson();
       String token = JWT.create()
               .withSubject(user.getUsername())
               .withExpiresAt(new Date(System.currentTimeMillis() + JWTCONSTANT.EXPIRATION))
               .sign(Algorithm.HMAC512(JWTCONSTANT.KEY.getBytes()));
        JSONObject map = new JSONObject();
        map.put("token", token);
        map.put("username", user.getUsername());
        map.put("id", user.getId().toString());
        response.getWriter().write(gson.toJson(map));
       response.getWriter().flush();
    }
}
