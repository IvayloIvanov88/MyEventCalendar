package com.example.demo.home.users;

import com.example.demo.home.users.model.entity.UserEntity;
import com.example.demo.home.users.service.AppUserDetailsService;
import com.example.demo.home.users.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class Oauth2UserSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final UserService userService;
    private final UserDetailsService userDetailsService;

    public Oauth2UserSuccessHandler(UserService userService, @Lazy UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
        setDefaultTargetUrl("/home");

    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        if (authentication instanceof OAuth2AuthenticationToken) {
            OAuth2AuthenticationToken oAuth2AuthenticationToken =
                    (OAuth2AuthenticationToken) authentication;

            String email = oAuth2AuthenticationToken
                    .getPrincipal()
                    .getAttribute("email");

            UserEntity userEntity = userService.getOrCreateUser(email);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getEmail());

            authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
