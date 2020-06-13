package ru.khmel.mpi.base;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

/**
 * @author Khmel Anton
 */
public class EmptyPredicate implements IPredicate {
  @Override
  public Predicate predicate() {
    return new BooleanBuilder();
  }
}
