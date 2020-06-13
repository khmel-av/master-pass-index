package ru.khmel.mpi.address.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Район населенного пункта
 *
 * @author Khmel Anton
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class DistrictLocalityDto {
  private Long id;
  private String name;
  private String description;
  private long localityId;
}
