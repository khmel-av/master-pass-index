package ru.khmel.mpi.card.jpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.khmel.mpi.card.jpa.entity.PassCard;

import java.util.List;
import java.util.UUID;

/**
 * @author Khmel Anton
 */
public interface PassCardRepository extends JpaRepository<PassCard, Long>,
    QuerydslPredicateExecutor<PassCard> {
  Page<PassCard> findByOrderByIdDesc(Pageable pageable);

  List<PassCard> findByUserId(long userId);

  List<PassCard> findByNumber(UUID number);
}
