package ru.khmel.mpi.address.jpa.filter;

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
public class CountryFilter {
  private Long id;
  private String name;
  private Boolean active = true;

  public void clear() {
    this.id = null;
    this.name = null;
  }
}
