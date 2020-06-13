package ru.khmel.mpi.card.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.khmel.mpi.card.jpa.entity.TypeCard;

import java.util.List;

/**
 * @author Khmel Anton
 */
public interface TypeCardRepository extends JpaRepository<TypeCard, Long>,
    QuerydslPredicateExecutor<TypeCard> {
  List<TypeCard> findByName(String name);
}
