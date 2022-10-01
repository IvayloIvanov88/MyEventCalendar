package com.example.demo.home.users.service.impl;

import com.example.demo.home.users.model.entity.UserEntity;
import com.example.demo.home.users.model.entity.UserRoleEntity;
import com.example.demo.home.users.model.enums.UserRoleEnum;
import com.example.demo.home.users.repository.UserRepository;
import com.example.demo.home.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

//    private static final Logger LOGGER = LoggerFactory.getLogger(com.example.demo.home.users.service.impl.UserServiceImpl.class);

    private final UserRepository userRepository;

    @Override
    public UserEntity getOrCreateUser(String email) {

        Objects.requireNonNull(email);

        Optional<UserEntity> userEntityOpt = userRepository.findOneByEmail(email);

        return userEntityOpt.orElseGet(()-> createUser(email));
    }


    private UserEntity createUser (String email){
        log.info("Creating a new user with email [GDPR]");
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);

        UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);

        userEntity.setUserRoles(List.of(userRole));
        return userRepository.save(userEntity);
    }
}
