package ru.khmel.mpi.user.jpa.filter;

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
public class UserFilter {
  private Long id;
  private String firstName;
  private Long lastName;
  private Boolean active = true;

  public void clear() {
    this.id = null;
    this.firstName = null;
    this.lastName = null;
  }
}
