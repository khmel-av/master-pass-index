package ru.khmel.mpi.address.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.khmel.mpi.address.jpa.entity.Region;

import java.util.List;

/**
 * @author Khmel Anton
 */
public interface RegionRepository extends JpaRepository<Region, Long>,
    QuerydslPredicateExecutor<Region> {
  List<Region> findByCountryId(long countryId);

  List<Region> findByName(String name);
}
