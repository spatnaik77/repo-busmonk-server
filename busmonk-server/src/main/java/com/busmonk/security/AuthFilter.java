package com.busmonk.security;

import com.busmonk.service.UserService;
import com.busmonk.service.bo.User;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by sr250345 on 10/31/16.
 */
public class AuthFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
                            throws IOException, ServletException {

        final HttpServletRequest request = (HttpServletRequest) servletRequest;

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer "))
        {
            throw new ServletException("Missing or invalid Authorization header");
        }

        final String token = authHeader.substring(7); // The part after "Bearer "

        User u = JWTUtil.parseJWT(token);

        ThreadLocal tLocal = new ThreadLocal();

        tLocal.set(u);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
