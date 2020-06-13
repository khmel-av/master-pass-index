package ru.khmel.mpi.feedback.jpa.entity;

/**
 * Тип обратной связи
 *
 * @author Khmel Anton
 */
public enum TypeFeedback {
  ERROR(1, "Ошибка"),
  APPEAL(2, "Обращение"),
  WISH(3, "Пожелание");

  /**
   * Идентификатор типа
   */
  private int id;
  /**
   * Наименование типа
   */
  private String name;

  TypeFeedback(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
