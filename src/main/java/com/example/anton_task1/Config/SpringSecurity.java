package com.example.anton_task1.Config;

import com.example.anton_task1.Repository.UserRepository;
import com.example.anton_task1.Service.MyUserDetailsService;
import com.example.anton_task1.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    private final MyUserDetailsService myUserDetailsService;

    public SpringSecurity(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(crsf -> crsf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/users/delete/*", "/users/update", "/users/find/*").hasAnyRole("ADMIN")
                        .requestMatchers("/users/hello").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/users/create").permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(authenticationProvider());
        return http.build();
    }
}
