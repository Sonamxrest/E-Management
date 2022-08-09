package com.xrest.emanagement.core.JWT;

import antlr.TokenStreamException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.xrest.emanagement.core.JWTCONSTANT;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Authorization extends BasicAuthenticationFilter {
    public Authorization(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String bearer  = request.getHeader(JWTCONSTANT.TYPE);
            if (null == bearer || !bearer.contains(JWTCONSTANT.PREFIX)) {
                chain.doFilter(request,response);
            }
            String token = bearer.substring(6);
            String username = JWT.require(Algorithm.HMAC512(JWTCONSTANT.KEY.getBytes())).build().verify(token).getSubject();
            //user repository
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username,null,null);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } catch (Exception e) {

        }
        super.doFilterInternal(request, response, chain);
    }
}
