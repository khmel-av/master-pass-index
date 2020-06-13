package ru.khmel.mpi.address.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Населенный пункт
 *
 * @author Khmel Anton
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class LocalityDto {
    private Long id;
    private String name;
    private TypeLocalityDto typeLocality;
    private String description;
    private Long regionId;
    private Long districtId;
}
