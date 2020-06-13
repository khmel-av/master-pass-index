package ru.khmel.mpi.address.service;

import ru.khmel.mpi.address.dto.AddressDto;
import ru.khmel.mpi.address.dto.CountryDto;
import ru.khmel.mpi.address.dto.DistrictDto;
import ru.khmel.mpi.address.dto.DistrictLocalityDto;
import ru.khmel.mpi.address.dto.LocalityDto;
import ru.khmel.mpi.address.dto.RegionDto;
import ru.khmel.mpi.address.dto.StreetDto;
import ru.khmel.mpi.address.dto.TypeLocalityDto;
import ru.khmel.mpi.exception.ValidationException;

import java.util.List;

/**
 * @author Khmel Anton
 */
public interface AddressService {
    /**
     * Метод возвращает список адресов для пользователя
     *
     * @return
     */
    List<AddressDto> getAddressList();

    /**
     * Метод возвращает список стран по части названия
     *
     * @param countryName
     *
     * @return
     */
    List<CountryDto> getCountryList(String countryName);

    /**
     * Метод возвращает список областей для страны по части названия
     *
     * @param countryId
     * @param regionName
     *
     * @return
     */
    List<RegionDto> getRegionList(long countryId, String regionName);

    /**
     * Метод возвращает список районов для области по части названия
     *
     * @param regionId
     * @param districtName
     *
     * @return
     */
    List<DistrictDto> getDistrictList(long regionId, String districtName);

    /**
     * Метод возвращает список населенных пунктов для области или району по части названия
     *
     * @param regionId
     * @param districtId
     * @param localityName
     *
     * @return
     */
    List<LocalityDto> getLocalityList(long regionId, long districtId, String localityName);

    /**
     * Метод возвращает список типов населенного пункта
     *
     * @return
     */
    List<TypeLocalityDto> getTypeLocalityList();

    /**
     * Метод возвращает список районов для населенного пункта по части названия
     *
     * @param localityId
     * @param districtLocalityName
     *
     * @return
     */
    List<DistrictLocalityDto> getDistrictLocalityList(long localityId, String districtLocalityName);

    /**
     * Метод возвращает список улиц для населенного пункта по части названия
     *
     * @param localityId
     * @param streetName
     *
     * @return
     */
    List<StreetDto> getStreetList(long localityId, String streetName);

    /**
     * Метод возвращает страну по ID
     *
     * @param countryId
     *
     * @return
     */
    CountryDto getCountry(long countryId);

    /**
     * Метод добавляет страну
     *
     * @param countryDto
     *
     * @return
     *
     * @throws ValidationException
     */
    CountryDto addCountry(CountryDto countryDto) throws ValidationException;

    /**
     * Метод обновляет страну
     *
     * @param countryDto
     *
     * @return
     *
     * @throws ValidationException
     */
    CountryDto updateCountry(CountryDto countryDto) throws ValidationException;

    /**
     * Метод проставляет флаг удаления для страны
     *
     * @param countryId
     *
     * @throws ValidationException
     */
    void deleteCountry(long countryId) throws ValidationException;

    /**
     * Метод возвращает область по ID
     *
     * @param regionId
     *
     * @return
     */
    RegionDto getRegion(long regionId);

    /**
     * Метод добавляет область
     *
     * @param regionDto
     *
     * @return
     *
     * @throws ValidationException
     */
    RegionDto addRegion(RegionDto regionDto) throws ValidationException;

    /**
     * Метод обновляет область
     *
     * @param regionDto
     *
     * @return
     *
     * @throws ValidationException
     */
    RegionDto updateRegion(RegionDto regionDto) throws ValidationException;

    /**
     * Метод проставляет флаг удаления для области
     *
     * @param regionId
     *
     * @throws ValidationException
     */
    void deleteRegion(long regionId) throws ValidationException;

    /**
     * Метод возвращает район по ID
     *
     * @param districtId
     *
     * @return
     */
    DistrictDto getDistrict(long districtId);

    /**
     * Метод добавляет район
     *
     * @param districtDto
     *
     * @return
     *
     * @throws ValidationException
     */
    DistrictDto addDistrict(DistrictDto districtDto) throws ValidationException;

    /**
     * Метод обновляет район
     *
     * @param districtDto
     *
     * @return
     *
     * @throws ValidationException
     */
    DistrictDto updateDistrict(DistrictDto districtDto) throws ValidationException;

    /**
     * Метод проставляет флаг удаления для района
     *
     * @param districtId
     *
     * @throws ValidationException
     */
    void deleteDistrict(long districtId) throws ValidationException;

    /**
     * Метод возвращает населенный пункт по ID
     *
     * @param localityId
     *
     * @return
     */
    LocalityDto getLocality(long localityId);

    /**
     * Метод добавляет населенный пункт
     *
     * @param localityDto
     *
     * @return
     *
     * @throws ValidationException
     */
    LocalityDto addLocality(LocalityDto localityDto) throws ValidationException;

    /**
     * Метод обновляет населенный пункт
     *
     * @param localityDto
     *
     * @return
     *
     * @throws ValidationException
     */
    LocalityDto updateLocality(LocalityDto localityDto) throws ValidationException;

    /**
     * Метод проставляет флаг удаления для населенного пункта
     *
     * @param localityId
     *
     * @throws ValidationException
     */
    void deleteLocality(long localityId) throws ValidationException;

    /**
     * Метод возвращает район населенного пункта по ID
     *
     * @param districtLocalityId
     *
     * @return
     */
    DistrictLocalityDto getDistrictLocality(long districtLocalityId);

    /**
     * Метод добавляет райое населенного пункт
     *
     * @param districtLocalityDto
     *
     * @return
     *
     * @throws ValidationException
     */
    DistrictLocalityDto addDistrictLocality(DistrictLocalityDto districtLocalityDto) throws ValidationException;

    /**
     * Метод обновляет район населенного пункта
     *
     * @param districtLocalityDto
     *
     * @return
     *
     * @throws ValidationException
     */
    DistrictLocalityDto updateDistrictLocality(DistrictLocalityDto districtLocalityDto) throws ValidationException;

    /**
     * Метод проставляет флаг удаления для района населенного пункта
     *
     * @param districtLocalityId
     *
     * @throws ValidationException
     */
    void deleteDistrictLocality(long districtLocalityId) throws ValidationException;

    /**
     * Метод возвращает улицу по ID
     *
     * @param streetId
     *
     * @return
     */
    StreetDto getStreet(long streetId);

    /**
     * Метод добавляет улицу
     *
     * @param streetDto
     *
     * @return
     *
     * @throws ValidationException
     */
    StreetDto addStreet(StreetDto streetDto) throws ValidationException;

    /**
     * Метод обновляет улицу
     *
     * @param streetDto
     *
     * @return
     *
     * @throws ValidationException
     */
    StreetDto updateStreet(StreetDto streetDto) throws ValidationException;

    /**
     * Метод проставляет флаг удаления для улицы
     *
     * @param streetId
     *
     * @throws ValidationException
     */
    void deleteStreet(long streetId) throws ValidationException;

    /**
     * Метод возвращает адрес по ID
     *
     * @param addressId
     *
     * @return
     *
     * @throws ValidationException
     */
    AddressDto getAddress(long addressId);

    /**
     * Метод добавляет адрес
     *
     * @param addressDto
     *
     * @return
     *
     * @throws ValidationException
     */
    AddressDto addAddress(AddressDto addressDto) throws ValidationException;

    /**
     * Метод проставляет флаг удаления для адреса
     *
     * @param addressId
     *
     * @throws ValidationException
     */
    void deleteAddress(long addressId) throws ValidationException;
}
