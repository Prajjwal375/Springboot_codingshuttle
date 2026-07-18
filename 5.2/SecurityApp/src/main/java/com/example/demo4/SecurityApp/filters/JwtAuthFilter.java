package com.example.demo4.SecurityApp.filters;

import com.example.demo4.SecurityApp.entities.User;
import com.example.demo4.SecurityApp.services.JwtService;
import com.example.demo4.SecurityApp.services.UserService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    //  this is used to pass the request from one context to servlet context
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            // fetching the token
            final String requestTokenHeader = request.getHeader("Authorization");
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer")) {
                filterChain.doFilter(request, response);
                return;
            }
            // got the token
            String token = requestTokenHeader.split("Bearer ")[1];   // seperating token
            // getting the userid from the token    see in jwtservice
            Long userId = jwtService.getUserIdFromToken(token);

            // if we dont have already securitycontext object
            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // validate userid if present in database if not the we gonna get exception
                User user = userService.getUserById(userId);
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // Add the user in spring security context Holder
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

            // After that we are calling chaindofilter
            filterChain.doFilter(request, response);  // geting response

            // now add this before UsernamePasswordAuthenticationFilter  checks authentication in the SecurityContextHolder and continues the chain

            // --> Dispatch Servlet & Controller
            // --> Get User from the SecurityContextHolder
        } catch (Exception ex) {
            handlerExceptionResolver.resolveException(request, response, null, ex);
        }
    }
}
