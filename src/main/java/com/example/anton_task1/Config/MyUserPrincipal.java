package com.example.anton_task1.Config;

import com.example.anton_task1.Entity.UserEntity;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserPrincipal implements UserDetails {
  private UserEntity user;

  public MyUserPrincipal(UserEntity user) {
    this.user = user;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {

    String[] authoritiesStringArray = user.getAuthorities().split(",");
    System.out.println(Arrays.toString(authoritiesStringArray));

    List<GrantedAuthority> grants =
        Arrays.stream(authoritiesStringArray)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

    return grants;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String toString() {
    return "MyUserPrincipal{" + "user=" + user + '}';
  }
}
