package ru.khmel.mpi.card.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.khmel.mpi.card.jpa.entity.ReasonCard;

import java.util.List;

/**
 * @author Khmel Anton
 */
public interface ReasonCardRepository extends JpaRepository<ReasonCard, Long>,
    QuerydslPredicateExecutor<ReasonCard> {
  List<ReasonCard> findByName(String name);
}
