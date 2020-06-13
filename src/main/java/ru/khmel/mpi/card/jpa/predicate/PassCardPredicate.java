package ru.khmel.mpi.card.jpa.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import lombok.AllArgsConstructor;
import ru.khmel.mpi.base.IPredicate;
import ru.khmel.mpi.card.jpa.filter.PassCardFilter;
import ru.khmel.mpi.card.jpa.entity.QPassCard;

import java.util.Objects;

/**
 * @author Khmel Anton
 */
@AllArgsConstructor
public class PassCardPredicate implements IPredicate {
  private static final QPassCard Q_PASS_CARD = QPassCard.passCard;

  private IPredicate predicate;
  private PassCardFilter filter;

  @Override
  public Predicate predicate() {
    BooleanBuilder builder = new BooleanBuilder(predicate.predicate());

    if (Objects.nonNull(filter.getId())) {
      builder.and(Q_PASS_CARD.id.eq(filter.getId()));
    } else {
      builder.and(Expressions.FALSE.ne(Expressions.FALSE));
    }

    return builder;
  }
}
