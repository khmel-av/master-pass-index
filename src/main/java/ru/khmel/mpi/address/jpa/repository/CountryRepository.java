package ru.khmel.mpi.address.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.khmel.mpi.address.jpa.entity.Country;

import java.util.List;

/**
 * @author Khmel Anton
 */
public interface CountryRepository extends JpaRepository<Country, Long>,
    QuerydslPredicateExecutor<Country> {
  List<Country> findByName(String name);
}
