package ru.khmel.mpi.feedback.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * DTO тип обратной связи
 *
 * @author Khmel Anton
 */
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class TypeFeedbackDto {
  /**
   * Идентификатор
   */
  private int id;
  /**
   * Наименование
   */
  private String name;
}
