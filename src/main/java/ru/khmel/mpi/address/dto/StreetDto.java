package ru.khmel.mpi.address.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Улица
 *
 * @author Khmel Anton
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class StreetDto {
  private Long id;
  private String name;
  private String description;
  private long localityId;
}
