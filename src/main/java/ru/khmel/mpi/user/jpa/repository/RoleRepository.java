package ru.khmel.mpi.user.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.khmel.mpi.user.jpa.entity.ERole;
import ru.khmel.mpi.user.jpa.entity.Role;

import java.util.Optional;

/**
 * @author Khmel Anton
 */
public interface RoleRepository extends JpaRepository<Role, Long>,
    QuerydslPredicateExecutor<Role> {
  Optional<Role> findByName(ERole name);
}
