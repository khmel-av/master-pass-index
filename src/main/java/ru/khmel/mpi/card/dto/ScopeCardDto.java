package ru.khmel.mpi.card.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.khmel.mpi.address.dto.CountryDto;
import ru.khmel.mpi.address.dto.DistrictDto;
import ru.khmel.mpi.address.dto.LocalityDto;
import ru.khmel.mpi.address.dto.RegionDto;

/**
 * Область передвижения
 *
 * @author Khmel Anton
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class ScopeCardDto {
    private Long id;
    private CountryDto country;
    private RegionDto region;
    private DistrictDto district;
    private LocalityDto locality;
    private String description;
}
