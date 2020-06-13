package ru.khmel.mpi.address.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Тип населенного пункта
 *
 * @author Khmel Anton
 */
@Getter
@Setter
@EqualsAndHashCode(of = "name")
@NoArgsConstructor
public class TypeLocalityDto {
    private String name;
}
