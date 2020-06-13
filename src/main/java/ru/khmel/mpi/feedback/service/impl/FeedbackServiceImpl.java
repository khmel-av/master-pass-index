package ru.khmel.mpi.feedback.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.khmel.mpi.base.EmptyPredicate;
import ru.khmel.mpi.base.IPredicate;
import ru.khmel.mpi.exception.ValidationException;
import ru.khmel.mpi.feedback.dto.FeedbackDto;
import ru.khmel.mpi.feedback.dto.TypeFeedbackDto;
import ru.khmel.mpi.feedback.jpa.entity.Feedback;
import ru.khmel.mpi.feedback.jpa.entity.TypeFeedback;
import ru.khmel.mpi.feedback.jpa.filter.FeedbackFilter;
import ru.khmel.mpi.feedback.jpa.predicate.FeedbackPredicate;
import ru.khmel.mpi.feedback.jpa.repository.FeedbackRepository;
import ru.khmel.mpi.feedback.service.FeedbackService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Сервис для работы с обратной связью
 *
 * @author Khmel Anton
 */
@Service
@AllArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

  private final FeedbackRepository feedbackRepository;

  /**
   * @Inherited
   */
  @Override
  public List<FeedbackDto> getFeedbackList(FeedbackDto filter) throws ValidationException {
    FeedbackFilter ff = new FeedbackFilter();
    ff.check(filter);
    Pageable pageable = Pageable.unpaged();
    IPredicate iPredicate = new FeedbackPredicate(new EmptyPredicate(), ff);
    Page<Feedback> page = this.feedbackRepository.findAll(iPredicate.predicate(), pageable);

    List<Feedback> feedbackList = page.getContent();
    Function<Feedback, FeedbackDto> mapFeedback = p -> {
      return getFeedbackDto(p);
    };

    return feedbackList.stream()
        .map(mapFeedback)
        .collect(Collectors.toList());
  }

  /**
   * @Inherited
   */
  @Override
  public List<TypeFeedbackDto> getTypeFeedbackList() {
    List<TypeFeedback> typeFeedbackList = Arrays.asList(TypeFeedback.values());
    Function<TypeFeedback, TypeFeedbackDto> mapTypeFeedback = p -> {
      return getTypeFeedbackDto(p);
    };

    return typeFeedbackList.stream()
        .map(mapTypeFeedback)
        .collect(Collectors.toList());
  }

  /**
   * @Inherited
   */
  @Override
  public void saveFeedback(FeedbackDto feedbackDto) throws ValidationException {
    Feedback feedback = getFeedback(feedbackDto);
    feedback.setCreateDate(LocalDateTime.now());
    feedback.setLastEditDate(LocalDateTime.now());
    feedback.setActive(true);

    this.feedbackRepository.save(feedback);
  }

  /**
   * @Inherited
   */
  @Override
  public FeedbackDto getFeedback(long feedbackId) throws ValidationException {
    Optional<Feedback> feedbackOpt = this.feedbackRepository.findById(feedbackId);
    if (feedbackOpt.isPresent()) {
      return getFeedbackDto(feedbackOpt.get());
    }

    return null;
  }

  /**
   * @Inherited
   */
  @Override
  public void deleteFeedback(long feedbackId) throws ValidationException {
    Optional<Feedback> feedbackOpt = this.feedbackRepository.findById(feedbackId);
    if (!feedbackOpt.isPresent()) {
      throw new ValidationException("Объект обратной связи, по ID: " + feedbackId +
          " для удаления не найдена");
    }
    Feedback feedback = feedbackOpt.get();
    feedback.setLastEditDate(LocalDateTime.now());
    feedback.setActive(false);

    this.feedbackRepository.save(feedback);
  }

  /**
   * Метод заполняет DTO объект обратной связи
   *
   * @param feedback
   *
   * @return
   */
  private FeedbackDto getFeedbackDto(Feedback feedback) {
    FeedbackDto feedbackDto = new FeedbackDto();
    feedbackDto.setEmail(feedback.getEmail());
    feedbackDto.setTypeId(feedback.getTypeFeedback().getId());
    feedbackDto.setMessage(feedback.getMessage());
    feedbackDto.setUserId(feedback.getUserId());

    return feedbackDto;
  }

  /**
   * Метод заполняет объект обратной связи
   *
   * @param feedbackDto
   *
   * @return
   */
  private Feedback getFeedback(FeedbackDto feedbackDto) {
    Feedback feedback = new Feedback();
    feedback.setId(null);
    feedback.setEmail(feedbackDto.getEmail());
    feedback.setTypeFeedback(getTypeFeedbackById(feedbackDto.getTypeId()));
    feedback.setMessage(feedbackDto.getMessage());
    if (feedbackDto.getUserId() > 0) {
      feedback.setUserId(feedbackDto.getUserId());
    } else {
      feedback.setUserId(null);
    }

    return feedback;
  }

  /**
   * Метод заполняет DTO объект типа обратной связи
   *
   * @param typeFeedback
   *
   * @return
   */
  private TypeFeedbackDto getTypeFeedbackDto(TypeFeedback typeFeedback) {
    TypeFeedbackDto typeFeedbackDto = new TypeFeedbackDto();
    typeFeedbackDto.setId(typeFeedback.getId());
    typeFeedbackDto.setName(typeFeedback.getName());

    return typeFeedbackDto;
  }

  /**
   * Метод возврщает тип по ID
   *
   * @param typeFeedbackId
   *
   * @return
   */
  private TypeFeedback getTypeFeedbackById(int typeFeedbackId) {
    switch (typeFeedbackId) {
      case 1:
        return TypeFeedback.ERROR;
      case 2:
        return TypeFeedback.APPEAL;
      case 3:
        return TypeFeedback.WISH;
      default:
        return null;
    }
  }
}
