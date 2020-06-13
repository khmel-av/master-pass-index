package ru.khmel.mpi.feedback.jpa.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.khmel.mpi.feedback.dto.FeedbackDto;

import java.time.LocalDateTime;

/**
 * Фильтр для обратной связи
 *
 * @author Khmel Anton
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackFilter {
  /**
   * Идентификатор
   */
  private Long id;
  /**
   * Email
   */
  private String email;
  /**
   * Сообщение
   */
  private String message;
  /**
   * Идентификатор пользователя
   */
  private Long userId;
  /**
   * Дата и время создания
   */
  private LocalDateTime createDate;
  /**
   * Флаг удаления
   */
  private Boolean active = true;

  /**
   * Обнуляем фильтр
   */
  public void clear() {
    this.id = null;
    this.email = null;
    this.message = null;
    this.userId = null;
    this.createDate = null;
  }

  /**
   * Сеттим не пустые поля
   */
  public void check(FeedbackDto filter) {
    if (filter != null) {
      if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
        this.email = filter.getEmail();
      }
      if (filter.getMessage() != null && !filter.getMessage().isEmpty()) {
        this.message = filter.getMessage();
      }
      if (filter.getUserId() > 0) {
        this.userId = filter.getUserId();
      }
      if (filter.getCreateDate() != null) {
        this.createDate = filter.getCreateDate();
      }
    }
  }
}
