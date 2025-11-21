package com.product.api.security;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "JWTFilter", urlPatterns = "/*")
public class JwtFilter implements Filter {
      private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    private boolean isPublicRoute(String path) {
    return path.equals("/auth/login")
        || path.equals("/auth/register")
        || path.equals("/auth/refresh");
    }


   @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();
        String token = httpRequest.getHeader("Authorization");

        if (isPublicRoute(path)) {
            chain.doFilter(request, response);
            return;
        }

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            if (jwtUtil.isValid(token)) {
                httpRequest.setAttribute("jwt", token);
                chain.doFilter(request, response);
                return;
            }

            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
            return;
        }

        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is missing");
    }
}
