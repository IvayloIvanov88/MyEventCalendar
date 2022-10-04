package com.example.demo.configuration;


import com.example.demo.home.users.Oauth2UserSuccessHandler;
import com.example.demo.home.users.model.enums.UserRoleEnum;
import com.example.demo.home.users.repository.UserRepository;
import com.example.demo.home.users.service.AppUserDetailsService;
import lombok.Data;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Data
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    public final Oauth2UserSuccessHandler oauth2UserSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return new AppUserDetailsService(userRepository);
    }


//    @Bean
//    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
//        return new MySimpleUrlAuthenticationSuccessHandler();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().
                requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                antMatchers("/login-error**", "/login**", "/registration").permitAll().
                antMatchers("/pages/moderators").hasRole(UserRoleEnum.MODERATOR.name()).
                antMatchers("/pages/admins").hasRole(UserRoleEnum.ADMIN.name()).
                anyRequest().
                authenticated().
                and().
                formLogin().
                loginPage("/login").permitAll().
//                loginProcessingUrl("/login").
        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                defaultSuccessUrl("/", true).
                // where to go in case that the login failed
                        failureForwardUrl("/login-error").
                and().
                logout().
                logoutUrl("/logout").
                invalidateHttpSession(true).
                deleteCookies("JSESSIONID")
                //for login with Facebook
                .and()
                .oauth2Login()
                .loginPage("/login")
                .successHandler(oauth2UserSuccessHandler);

        return http.build();
    }


}
