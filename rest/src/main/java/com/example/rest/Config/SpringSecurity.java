package com.example.rest.Config;

import com.example.rest.Filter.JwtAuthenticationFilter;
import com.example.rest.Config.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SpringSecurity {

  private final MyUserDetailsService myUserDetailsService;
  private final JwtAuthenticationFilter jwtAuthFilter;
  private final CustomAuthenticationEntryPoint entryPoint;
  private final CustomAccessDeniedHandler accessDeniedHandler;

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
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests()
        .requestMatchers(
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
            "/course/*",
            "/project/create",
            "/project/update",
            "/project/deleteProject",
            "/project/find/*",
            "/project/delete/*")
        .hasAnyRole("ADMIN", "USER")
        .requestMatchers("/auth/register", "/auth/login", "/error")
        .permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider())
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling()
        .authenticationEntryPoint(entryPoint)
        .accessDeniedHandler(accessDeniedHandler);

    return http.build();
  }
}
