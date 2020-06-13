package ru.khmel.mpi.card.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.khmel.mpi.card.jpa.entity.ScopeCard;

import java.util.List;

/**
 * @author Khmel Anton
 */
public interface ScopeCardRepository extends JpaRepository<ScopeCard, Long>,
    QuerydslPredicateExecutor<ScopeCard> {
  List<ScopeCard> findByCountryIdAndRegionIdAndLocalityId(long countryId, long regionId, long localityId);

  List<ScopeCard> findByCountryIdAndRegionIdAndDistrictIdAndLocalityId(long countryId, long regionId, long districtId, long localityId);
}
