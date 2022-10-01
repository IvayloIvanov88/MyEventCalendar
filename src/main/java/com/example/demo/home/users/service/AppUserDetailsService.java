package com.example.demo.home.users.service;


import com.example.demo.home.users.model.entity.UserEntity;
import com.example.demo.home.users.model.entity.UserRoleEntity;
import com.example.demo.home.users.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

// NOTE: This is not annotated as @Service, because we will return it as a bean.
@Slf4j
public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userOpt = userRepository.findOneByEmail(username);

        log.debug("Trying to load user {}.  Successful? {}", username, userOpt.isPresent());
        return userOpt.
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User with email " + username + " not found!"));
    }

    private UserDetails map(UserEntity userEntity) {
        return
                User.builder().
                        username(userEntity.getEmail()).
                        password(userEntity.getPassword() != null ? userEntity.getPassword() : "").
                        authorities(userEntity.
                                getUserRoles().
                                stream().
                                map(this::map).
                                collect(Collectors.toList())).
                        build();
    }

    private GrantedAuthority map(UserRoleEntity userRole) {
        return new SimpleGrantedAuthority("ROLE_" + userRole.getRole().name());
    }
}
