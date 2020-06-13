package ru.khmel.mpi.feedback.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * DTO обратная связь
 *
 * @author Khmel Anton
 */
@Data
@EqualsAndHashCode(of = "message")
@NoArgsConstructor
public class FeedbackDto {
  /**
   * Email
   */
  @NotBlank(message = "Не заполнено поле email")
  @Email(message = "Некорректный адрес электронной почты")
  @Size(min = 6, max = 30, message = "Поле email должно быть длиной не менее 6 и не более 30 символов")
  private String email;
  /**
   * Идентификатор типа
   */
  @NotBlank(message = "Не заполнено поле typeId")
  private int typeId;
  /**
   * Сообщение
   */
  @NotBlank(message = "Не заполнено поле message")
  @Size(min = 10, max = 1000, message = "Поле message должно быть длиной не менее 10 и не более 1000 символов")
  private String message;
  /**
   * Идентификатор пользователя
   */
  private long userId;
  /**
   * Дата и время создания
   */
  private LocalDateTime createDate;
}
