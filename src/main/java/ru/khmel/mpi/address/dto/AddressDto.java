package ru.khmel.mpi.address.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Справочник передвижения
 *
 * @author Khmel Anton
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class AddressDto {
    private Long id;
    private CountryDto country;
    private RegionDto region;
    private DistrictDto district;
    private LocalityDto locality;
    private DistrictLocalityDto districtLocality;
    private StreetDto street;
    private String home;
    private String bild;
    private String flat;
    private String room;
    private String zipcode;
    private String description;
}
