package ru.khmel.mpi.card.jpa.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Khmel Anton
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassCardFilter {
  private Long id;
  private String number;
  private Long userId;
  private Boolean active = true;

  public void clear() {
    this.id = null;
    this.number = null;
    this.userId = null;
  }
}
