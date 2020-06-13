package ru.khmel.mpi.address.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.khmel.mpi.address.jpa.entity.District;

import java.util.List;

/**
 * @author Khmel Anton
 */
public interface DistrictRepository extends JpaRepository<District, Long>,
    QuerydslPredicateExecutor<District> {
  List<District> findByRegionId(long districtId);

  List<District> findByName(String name);
}
