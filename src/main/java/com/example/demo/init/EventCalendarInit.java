package com.example.demo.init;

import com.example.demo.announcement.model.entity.AnnouncementEntity;
import com.example.demo.announcement.repository.AnnouncementRepository;
import com.example.demo.home.users.model.entity.UserEntity;
import com.example.demo.home.users.model.entity.UserRoleEntity;
import com.example.demo.home.users.model.enums.UserRoleEnum;
import com.example.demo.home.users.repository.RoleRepository;
import com.example.demo.home.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@Component
public class EventCalendarInit implements CommandLineRunner {

    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {
        if (announcementRepository.count() == 0) {
            AnnouncementEntity announcementEntity = new AnnouncementEntity();
            announcementEntity.setTitle("Hello to Spring Boot");
            announcementEntity.setDescription("Spring advanced course!");
            announcementEntity.setCreatedOn(Instant.now());
            announcementEntity.setUpdatedOn(Instant.now());
            announcementRepository.saveAndFlush(announcementEntity);
        }

        if (userRepository.count() == 0) {
            UserRoleEntity adminRole = new UserRoleEntity().setRole(UserRoleEnum.ADMIN);
            UserRoleEntity userRole = new UserRoleEntity().setRole(UserRoleEnum.USER);
            UserRoleEntity moderatorRole = new UserRoleEntity().setRole(UserRoleEnum.MODERATOR);

            //admin
            roleRepository.saveAll(List.of(adminRole, userRole, moderatorRole));
            UserEntity admin = new UserEntity();
            admin
                    .setEmail("admin@example.com")
                    .setUsername("Admin")
                    .setFirstName("Ivaylo")
                    .setLastName("Ivanov")
                    .setPassword(passwordEncoder.encode("topsecret"))
                    .setImageUrl("https://previews.123rf.com/images/drizzd/drizzd1608/drizzd160800001/60596893-the-word-admin-and-gear-wheel-3d-rendering.jpg")

                    .setUserRoles(List.of(adminRole, userRole, moderatorRole));

            //normal user
            UserEntity user = new UserEntity();
            user
                    .setEmail("user@example.com")
                    .setUsername("User")
                    .setFirstName("Tedi")
                    .setLastName("Ivanova")
                    .setPassword(passwordEncoder.encode("1234"))
                    .setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQuIbv-7JSgC23hcGq8qDRBpFzdMBEw8urHdQ&usqp=CAU")

                    .setUserRoles(List.of(userRole));

            //moderator
            UserEntity moderator = new UserEntity();
            moderator
                    .setEmail("moderator@example.com")
                    .setUsername("Moderator")
                    .setFirstName("Moderator")
                    .setLastName("Moderatov")
                    .setPassword(passwordEncoder.encode("topsecret"))
                    .setUserRoles(List.of(userRole, moderatorRole));

            userRepository.saveAll(List.of(admin,user,moderator));
        }

    }
}
