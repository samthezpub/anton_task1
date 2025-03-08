package com.example.rest.Config;

import com.example.rest.Config.MyUserPrincipal;
import com.example.db.Entity.UserEntity;
import com.example.db.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  public MyUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findByUsername(username);
    if (userEntity == null) {
      throw new UsernameNotFoundException(username);
    }
    return new MyUserPrincipal(userEntity);
  }
}
