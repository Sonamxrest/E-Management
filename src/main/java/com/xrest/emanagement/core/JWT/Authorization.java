package com.xrest.emanagement.core.JWT;

import antlr.TokenStreamException;
import com.xrest.emanagement.core.JWTCONSTANT;
import org.springframework.security.authentication.AuthenticationManager;
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
            String token  = request.getHeader(JWTCONSTANT.TYPE);
            if (null == token || !token.contains(JWTCONSTANT.PREFIX)) {
                chain.doFilter(request,response);
            }
        } catch (Exception e) {

        }
        super.doFilterInternal(request, response, chain);
    }
}
