package com.example.anton_task1.Config;

import com.example.anton_task1.Filter.JwtAuthenticationFilter;
import com.example.anton_task1.Service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

  private final MyUserDetailsService myUserDetailsService;
  private final JwtAuthenticationFilter jwtAuthFilter;

  public SpringSecurity(
      MyUserDetailsService myUserDetailsService, JwtAuthenticationFilter jwtAuthFilter) {
    this.myUserDetailsService = myUserDetailsService;
    this.jwtAuthFilter = jwtAuthFilter;
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(myUserDetailsService);
    provider.setPasswordEncoder(passwordEncoder());
    return provider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
      throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws java.lang.Exception {
    http.csrf(crsf -> crsf.disable())
        .authorizeHttpRequests(
            auth -> {
              try {
                auth.requestMatchers(
                        "/users/delete/*",
                        "/users/update",
                        "/users/find/*",
                        "/users/deleteAll",
                        "/dogs/find/*",
                        "/course/delete/*",
                        "/course/update",
                        "/course/addUser",
                        "/course/deleteUser")
                    .hasAnyRole("ADMIN")
                    .requestMatchers(
                        "/users/hello",
                        "/users/createCar",
                        "/users/createDog",
                        "/dogs/create",
                        "/dogs/update",
                        "/dogs/delete/*",
                        "/course/create",
                        "/course/findByUser/*",
                        "/course",
                        "/course/*")
                    .hasAnyRole("USER", "ADMIN")
                    .requestMatchers("/auth/register", "/auth/login", "/error")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authenticationProvider(authenticationProvider())
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
              } catch (Exception e) {
                throw new RuntimeException(e);
              }
            })
        .httpBasic(Customizer.withDefaults());
    return http.build();
  }
}
