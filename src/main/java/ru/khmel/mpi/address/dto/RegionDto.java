package ru.khmel.mpi.address.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Область
 *
 * @author Khmel Anton
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class RegionDto {
    private Long id;
    private String name;
    private String description;
    private Long countryId;
}
