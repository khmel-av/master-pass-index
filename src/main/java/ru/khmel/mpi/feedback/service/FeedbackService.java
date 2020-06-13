package ru.khmel.mpi.feedback.service;

import ru.khmel.mpi.exception.ValidationException;
import ru.khmel.mpi.feedback.dto.FeedbackDto;
import ru.khmel.mpi.feedback.dto.TypeFeedbackDto;

import java.util.List;

/**
 * @author Khmel Anton
 */
public interface FeedbackService {
  /**
   * Метод возвращает список сообщений обратной связи по фильтру
   *
   * @return
   *
   * @throws ValidationException
   */
  List<FeedbackDto> getFeedbackList(FeedbackDto filter) throws ValidationException;

  /**
   * Метод возвращает список типов обратной связи
   *
   * @return
   */
  List<TypeFeedbackDto> getTypeFeedbackList();

  /**
   * Метод сохраняет данные обратной связи
   *
   * @param feedbackDto
   *
   * @throws ValidationException
   */
  void saveFeedback(FeedbackDto feedbackDto) throws ValidationException;

  /**
   * Метод получает объект обратной связи по ID
   *
   * @param feedbackId
   *
   * @return
   *
   * @throws ValidationException
   */
  FeedbackDto getFeedback(long feedbackId) throws ValidationException;

  /**
   * Метод проставляет флаг удаления для обратной связи
   *
   * @param feedbackId
   *
   * @throws ValidationException
   */
  void deleteFeedback(long feedbackId) throws ValidationException;
}
