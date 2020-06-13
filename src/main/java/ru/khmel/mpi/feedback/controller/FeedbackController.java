package ru.khmel.mpi.feedback.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.khmel.mpi.exception.ValidationException;
import ru.khmel.mpi.feedback.dto.FeedbackDto;
import ru.khmel.mpi.feedback.dto.TypeFeedbackDto;
import ru.khmel.mpi.feedback.service.FeedbackService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Khmel Anton
 */
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
  @Autowired
  private FeedbackService feedbackService;

  /**
   * Метод возвращает список статусов пропуска
   *
   * @return
   */
  @ApiOperation(value = "Список объектов обратной связи", notes = "Метод для получения списка объектов обратной связи", response = List.class)
  @RequestMapping(value = "/list", method= {POST})
  public List<FeedbackDto> getFeedbackList(@RequestBody FeedbackDto feedbackDto) {
    return this.feedbackService.getFeedbackList(feedbackDto);
  }

  /**
   * Метод возвращает список типов обратной связи
   *
   * @return
   */
  @ApiOperation(value = "Список типов обратной связи", notes = "Метод для получения списка типов обратной связи", response = List.class)
  @RequestMapping(value = "/public/type/list", method= {GET})
  public List<TypeFeedbackDto> getTypeFeedbackList() {
    return this.feedbackService.getTypeFeedbackList();
  }

  /**
   * Метод добавляет пропуск для пользователя
   *
   * @param feedbackDto
   *
   * @return
   *
   * @throws ValidationException
   */
  @ApiOperation(value = "Сохранение объекта обратной связи", notes = "Метод для сохранения объекта обратной связи", response = ResponseEntity.class)
  @RequestMapping(value = "/public/send", method= {POST})
  public ResponseEntity<?> sendMessage(@Valid @RequestBody FeedbackDto feedbackDto) throws ValidationException {
    this.feedbackService.saveFeedback(feedbackDto);

    return ResponseEntity.ok().build();
  }

  /**
   * Метод возвращает объект обратной связи по ID
   *
   * @param feedbackId
   *
   * @return
   */
  @ApiOperation(value = "Получить объект обратной связи по ID", notes = "Метод для получения объекта обратной связи по ID", response = FeedbackDto.class)
  @RequestMapping(value = "/get/{feedbackId}", method= {GET})
  public FeedbackDto getFeedback(@PathVariable("feedbackId") long feedbackId) {
    return this.feedbackService.getFeedback(feedbackId);
  }

  /**
   * Метод проставляет флаг удаления обратной связи
   *
   * @param feedbackId
   *
   * @return
   *
   * @throws ValidationException
   */
  @ApiOperation(value = "Удалить объект обратной связи", notes = "Метод для удаления объекта обратной связи", response = ResponseEntity.class)
  @RequestMapping(value = "/delete/{feedbackId}", method= {GET})
  public ResponseEntity<?> deleteFeedback(@PathVariable("feedbackId") long feedbackId) throws ValidationException {
    this.feedbackService.deleteFeedback(feedbackId);

    return ResponseEntity.ok().build();
  }

  /**
   * @param exception
   * @return ResponseEntity<String>
   */
  @ExceptionHandler
  public ResponseEntity<String> handleException(Exception exception) {
    log.error("exception: {}", exception);

    return new ResponseEntity<String>(exception.getMessage(), HttpStatus.FORBIDDEN);
  }
}
