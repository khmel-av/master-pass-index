package ru.khmel.mpi.feedback.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;

/**
 * Обратная связь
 *
 * @author Khmel Anton
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "feedback", schema = "mpi")
public class Feedback {
  private static final long serialVersionUID = 1L;

  /**
   * Идентификатор
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  /**
   * Email
   */
  @Column(name = "email")
  private String email;
  /**
   * Тип
   */
  @Column(name = "type_feedback_id")
  private TypeFeedback typeFeedback;
  /**
   * Сообщение
   */
  @Column(name = "message")
  private String message;
  /**
   * Идентификатор пользователя
   */
  @Column(name = "user_id")
  private Long userId;
  /**
   * Дата и время последнего изменения
   */
  @Column(name = "last_edit_date")
  private LocalDateTime LastEditDate;
  /**
   * Дата и время создания
   */
  @Column(name = "create_date")
  private LocalDateTime createDate;
  /**
   * Флаг удаления
   */
  @Column(name = "active")
  private Boolean active;
}
