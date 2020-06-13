package ru.khmel.mpi.base;

import com.querydsl.core.types.Predicate;

/**
 * @author Khmel Anton
 */
public interface IPredicate {
  /**
   * Предиката для фильтрации.
   *
   * @return
   */
  public Predicate predicate();
}
