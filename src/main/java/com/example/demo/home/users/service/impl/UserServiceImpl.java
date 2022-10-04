package com.example.demo.home.users.service.impl;

import com.example.demo.home.users.model.entity.UserEntity;
import com.example.demo.home.users.model.entity.UserRoleEntity;
import com.example.demo.home.users.model.enums.UserRoleEnum;
import com.example.demo.home.users.repository.UserRepository;
import com.example.demo.home.users.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

//    private static final Logger LOGGER = LoggerFactory.getLogger(com.example.demo.home.users.service.impl.UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    public UserServiceImpl(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder, @Lazy UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean existsUser(String email) {
        Objects.requireNonNull(email);
        return userRepository.findOneByEmail(email).isPresent();
    }

    @Override
    public UserEntity getOrCreateUser(String email) {

        Objects.requireNonNull(email);

        Optional<UserEntity> userEntityOpt = userRepository.findOneByEmail(email);

        return userEntityOpt.orElseGet(() -> createUser(email));
    }

    @Override
    public void createAndLoginUser(String email, String password) {
        UserEntity newUser = createUser(email, password);

        UserDetails userDetails = userDetailsService.loadUserByUsername(newUser.getEmail());
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserEntity createUser(String email, String password) {
        log.info("Creating a new user with email [GDPR]");
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        if (password != null) {
            userEntity.setPassword(passwordEncoder.encode(password));
        }

        UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

        userEntity.setUserRoles(List.of(userRole));
        return userRepository.saveAndFlush(userEntity);
    }

    private UserEntity createUser(String email) {
        return createUser(email, null);
    }
}
