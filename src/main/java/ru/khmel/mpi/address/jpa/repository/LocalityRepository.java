package ru.khmel.mpi.address.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.khmel.mpi.address.jpa.entity.Locality;

import java.util.List;

/**
 * @author Khmel Anton
 */
public interface LocalityRepository extends JpaRepository<Locality, Long>,
    QuerydslPredicateExecutor<Locality> {
  List<Locality> findByRegionId(long regionId);

  List<Locality> findByDistrictId(long districtId);

  List<Locality> findByRegionIdAndName(long regionId, String name);

  List<Locality> findByDistrictIdAndName(long districtId, String name);
}
