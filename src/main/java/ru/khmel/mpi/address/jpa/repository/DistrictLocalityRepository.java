package ru.khmel.mpi.address.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.khmel.mpi.address.jpa.entity.DistrictLocality;

import java.util.List;

/**
 * @author Khmel Anton
 */
public interface DistrictLocalityRepository extends JpaRepository<DistrictLocality, Long>,
    QuerydslPredicateExecutor<DistrictLocality> {
  List<DistrictLocality> findByLocalityId(long localityId);

  List<DistrictLocality> findByLocalityIdAndName(long localityId, String name);
}
