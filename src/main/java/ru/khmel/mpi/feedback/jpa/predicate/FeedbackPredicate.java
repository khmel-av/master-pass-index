package ru.khmel.mpi.feedback.jpa.predicate;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import ru.khmel.mpi.base.IPredicate;
import ru.khmel.mpi.feedback.jpa.entity.QFeedback;
import ru.khmel.mpi.feedback.jpa.filter.FeedbackFilter;

import java.util.Objects;

/**
 * Предикат для обратной связи
 *
 * @author Khmel Anton
 */
@AllArgsConstructor
public class FeedbackPredicate implements IPredicate {
  private static final QFeedback Q_FEEDBACK = QFeedback.feedback;

  private IPredicate predicate;
  private FeedbackFilter filter;

  @Override
  public Predicate predicate() {
    BooleanBuilder builder = new BooleanBuilder(predicate.predicate());

    if (Objects.nonNull(filter.getId())) {
      builder.and(Q_FEEDBACK.id.eq(filter.getId()));
    }

    if (Objects.nonNull(filter.getEmail())) {
      builder.and(Q_FEEDBACK.email.eq(filter.getEmail()));
    }

    if (Objects.nonNull(filter.getMessage())) {
      builder.and(Q_FEEDBACK.message.eq(filter.getMessage()));
    }

    if (Objects.nonNull(filter.getUserId())) {
      builder.and(Q_FEEDBACK.userId.eq(filter.getUserId()));
    }

    if (Objects.nonNull(filter.getCreateDate())) {
      builder.and(Q_FEEDBACK.createDate.eq(filter.getCreateDate()));
    }

    if (Objects.nonNull(filter.getActive())) {
      builder.and(Q_FEEDBACK.active.eq(filter.getActive()));
    }

    return builder;
  }
}
