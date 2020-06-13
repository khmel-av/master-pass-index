package ru.khmel.mpi.user.jpa.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import ru.khmel.mpi.base.IPredicate;
import ru.khmel.mpi.user.jpa.filter.UserFilter;
import ru.khmel.mpi.user.jpa.entity.QUser;

import java.util.Objects;

/**
 * @author Khmel Anton
 */
@AllArgsConstructor
public class UserPredicate implements IPredicate {
  private static final QUser Q_USER = QUser.user;

  private IPredicate predicate;
  private UserFilter filter;

  @Override
  public Predicate predicate() {
    BooleanBuilder builder = new BooleanBuilder(predicate.predicate());

    if (Objects.nonNull(filter.getId())) {
      builder.and(Q_USER.id.eq(filter.getId()));
    }

    return builder;
  }
}
