package ru.khmel.mpi.user.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.khmel.mpi.user.jpa.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Khmel Anton
 */
public interface UserRepository extends JpaRepository<User, Long>,
    QuerydslPredicateExecutor<User> {
  List<User> findByUsernameAndPassword(String username, String password);

  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
