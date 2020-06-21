package ru.khmel.mpi.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.khmel.mpi.user.jpa.entity.User;
import ru.khmel.mpi.user.jpa.repository.UserRepository;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private final UserRepository repository;

  public UserDetailsServiceImpl(final UserRepository repository) {
    super();
    this.repository = repository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> userOpt = this.repository.findByUsername(username);
    if (userOpt.isPresent()) {
      return userOpt.get();
    }

    return null;
  }
}
