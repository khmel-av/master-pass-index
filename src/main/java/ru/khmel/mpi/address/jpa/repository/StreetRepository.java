package ru.khmel.mpi.address.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.khmel.mpi.address.jpa.entity.Street;

import java.util.List;

/**
 * @author Khmel Anton
 */
public interface StreetRepository extends JpaRepository<Street, Long>,
    QuerydslPredicateExecutor<Street> {
  List<Street> findByLocalityId(long localityId);

  List<Street> findByLocalityIdAndName(long localityId, String name);
}
