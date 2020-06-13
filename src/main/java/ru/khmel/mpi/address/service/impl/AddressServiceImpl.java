package ru.khmel.mpi.address.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khmel.mpi.address.dto.AddressDto;
import ru.khmel.mpi.address.dto.CountryDto;
import ru.khmel.mpi.address.dto.DistrictDto;
import ru.khmel.mpi.address.dto.DistrictLocalityDto;
import ru.khmel.mpi.address.dto.LocalityDto;
import ru.khmel.mpi.address.dto.RegionDto;
import ru.khmel.mpi.address.dto.StreetDto;
import ru.khmel.mpi.address.dto.TypeLocalityDto;
import ru.khmel.mpi.exception.ValidationException;
import ru.khmel.mpi.address.jpa.entity.Address;
import ru.khmel.mpi.address.jpa.entity.Country;
import ru.khmel.mpi.address.jpa.entity.District;
import ru.khmel.mpi.address.jpa.entity.DistrictLocality;
import ru.khmel.mpi.address.jpa.entity.Locality;
import ru.khmel.mpi.address.jpa.entity.Region;
import ru.khmel.mpi.address.jpa.entity.Street;
import ru.khmel.mpi.address.jpa.entity.TypeLocality;
import ru.khmel.mpi.address.jpa.repository.AddressRepository;
import ru.khmel.mpi.address.jpa.repository.CountryRepository;
import ru.khmel.mpi.address.jpa.repository.DistrictLocalityRepository;
import ru.khmel.mpi.address.jpa.repository.DistrictRepository;
import ru.khmel.mpi.address.jpa.repository.LocalityRepository;
import ru.khmel.mpi.address.jpa.repository.RegionRepository;
import ru.khmel.mpi.address.jpa.repository.StreetRepository;
//import ru.khmel.mpi.jpa.repository.address.TypeLocalityRepository;
import ru.khmel.mpi.address.service.AddressService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Сервис для работы с адресом
 *
 * @author Khmel Anton
 */
@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {
  @Autowired
  private final AddressRepository addressRepository;
  @Autowired
  private final CountryRepository countryRepository;
  @Autowired
  private final RegionRepository regionRepository;
  @Autowired
  private final DistrictRepository districtRepository;
  @Autowired
  private final LocalityRepository localityRepository;
//  @Autowired
//  private final TypeLocalityRepository typeLocalityRepository;
  @Autowired
  private final DistrictLocalityRepository districtLocalityRepository;
  @Autowired
  private final StreetRepository streetRepository;


  @Override
  public List<AddressDto> getAddressList() {
    List<Address> addressList = this.addressRepository.findAll();
    Function<Address, AddressDto> mapAddress = p -> {
      return getAddressDto(p);
    };

    return addressList.stream()
        .map(mapAddress)
        .collect(Collectors.toList());
  }

  /**
   * @Inherited
   */
  @Override
  public List<CountryDto> getCountryList(String countryName) {
    List<Country> countryList = this.countryRepository.findAll();
    Function<Country, CountryDto> mapCountry = p -> {
      return getCountryDto(p);
    };

    return countryList.stream()
        .map(mapCountry)
        .collect(Collectors.toList());
  }

  /**
   * @Inherited
   */
  @Override
  public List<RegionDto> getRegionList(long countryId, String regionName) {
    List<Region> regionList = this.regionRepository.findByCountryId(countryId);
    Function<Region, RegionDto> mapRegion = p -> {
      return getRegionDto(p);
    };

    return regionList.stream()
        .map(mapRegion)
        .collect(Collectors.toList());
  }

  /**
   * @Inherited
   */
  @Override
  public List<DistrictDto> getDistrictList(long regionId, String districtName) {
    List<District> districtList = this.districtRepository.findByRegionId(regionId);
    Function<District, DistrictDto> mapDistrict = p -> {
      return getDistrictDto(p);
    };

    return districtList.stream()
        .map(mapDistrict)
        .collect(Collectors.toList());
  }

  /**
   * @Inherited
   */
  @Override
  public List<LocalityDto> getLocalityList(long regionId, long districtId, String localityName) {
    List<Locality> localityList = Collections.EMPTY_LIST;
    if (regionId != 0) {
      localityList = this.localityRepository.findByRegionId(regionId);
    } else if (regionId != 0) {
      localityList = this.localityRepository.findByDistrictId(districtId);
    } else {

    }
    Function<Locality, LocalityDto> mapLocality = p -> {
      return getLocalityDto(p);
    };

    return localityList.stream()
        .map(mapLocality)
        .collect(Collectors.toList());
  }

  /**
   * @Inherited
   */
  @Override
  public List<TypeLocalityDto> getTypeLocalityList() {
//    List<TypeLocality> typeLocalityList = this.typeLocalityRepository.findAll();
    List<TypeLocality> typeLocalityList = Arrays.asList(TypeLocality.values());
    Function<TypeLocality, TypeLocalityDto> mapTypeLocality = p -> {
      return getTypeLocalityDto(p);
    };

    return typeLocalityList.stream()
        .map(mapTypeLocality)
        .collect(Collectors.toList());
  }

  /**
   * @Inherited
   */
  @Override
  public List<DistrictLocalityDto> getDistrictLocalityList(long localityId, String districtLocalityName) {
    List<DistrictLocality> districtLocalityList = this.districtLocalityRepository.findByLocalityId(localityId);
    Function<DistrictLocality, DistrictLocalityDto> mapDistrictLocality = p -> {
      return getDistrictLocalityDto(p);
    };

    return districtLocalityList.stream()
        .map(mapDistrictLocality)
        .collect(Collectors.toList());
  }

  /**
   * @Inherited
   */
  @Override
  public List<StreetDto> getStreetList(long localityId, String streetName) {
    List<Street> streetList = this.streetRepository.findByLocalityId(localityId);
    Function<Street, StreetDto> mapStreet = p -> {
      return getStreetDto(p);
    };

    return streetList.stream()
        .map(mapStreet)
        .collect(Collectors.toList());
  }

  /**
   * @Inherited
   */
  @Override
  public CountryDto getCountry(long countryId) {
    Optional<Country> countryOpt = this.countryRepository.findById(countryId);
    if (countryOpt.isPresent()) {
      return getCountryDto(countryOpt.get());
    }

    return null;
  }

  /**
   * @Inherited
   */
  @Override
  public CountryDto addCountry(CountryDto countryDto) throws ValidationException {
    List<Country> countryList = this.countryRepository.findByName(countryDto.getName());
    if (!countryList.isEmpty()) {
      throw new ValidationException("Страна: " + countryDto.getName() +
          " уже существует");
    }
    Country country = getCountry(countryDto);
    country.setId(null);
    country.setCreateDate(LocalDateTime.now());

    this.countryRepository.save(country);

    return getCountryDto(country);
  }

  /**
   * @Inherited
   */
  @Override
  public CountryDto updateCountry(CountryDto countryDto) throws ValidationException {
    Optional<Country> countryOpt = this.countryRepository.findById(countryDto.getId());
    if (!countryOpt.isPresent()) {
      throw new ValidationException("Страна: " + countryDto.getName() +
          " для обновления не найдена");
    }
    Country country = getCountry(countryDto);

    this.countryRepository.save(country);

    return getCountryDto(country);
  }

  /**
   * @Inherited
   */
  @Override
  public void deleteCountry(long countryId) throws ValidationException {
    Optional<Country> countryOpt = this.countryRepository.findById(countryId);
    if (!countryOpt.isPresent()) {
      throw new ValidationException("Страна, по ID: " + countryId +
          " для удаления не найдена");
    }
    Country country = countryOpt.get();
    country.setActive(false);
    country.setLastEditDate(LocalDateTime.now());

    this.countryRepository.save(country);
  }

  /**
   * @Inherited
   */
  @Override
  public RegionDto getRegion(long regionId) {
    Optional<Region> regionOpt = this.regionRepository.findById(regionId);
    if (regionOpt.isPresent()) {
      return getRegionDto(regionOpt.get());
    }

    return null;
  }

  /**
   * @Inherited
   */
  @Override
  public RegionDto addRegion(RegionDto regionDto) throws ValidationException {
    List<Region> regionList = this.regionRepository.findByName(regionDto.getName());
    if (!regionList.isEmpty()) {
      throw new ValidationException("Область: " + regionDto.getName() +
          " уже существует");
    }
    Region region = getRegion(regionDto);
    region.setId(null);
    region.setCreateDate(LocalDateTime.now());

    this.regionRepository.save(region);

    return getRegionDto(region);
  }

  /**
   * @Inherited
   */
  @Override
  public RegionDto updateRegion(RegionDto regionDto) throws ValidationException {
    Optional<Region> regionOpt = this.regionRepository.findById(regionDto.getId());
    if (!regionOpt.isPresent()) {
      throw new ValidationException("Область: " + regionDto.getName() +
          " для обновления не найдена");
    }
    Region region = getRegion(regionDto);

    this.regionRepository.save(region);

    return getRegionDto(region);
  }

  /**
   * @Inherited
   */
  @Override
  public void deleteRegion(long regionId) throws ValidationException {
    Optional<Region> regionOpt = this.regionRepository.findById(regionId);
    if (!regionOpt.isPresent()) {
      throw new ValidationException("Область, по ID: " + regionId +
          " для удаления не найдена");
    }
    Region region = regionOpt.get();
    region.setActive(false);
    region.setLastEditDate(LocalDateTime.now());

    this.regionRepository.save(region);
  }

  /**
   * @Inherited
   */
  @Override
  public DistrictDto getDistrict(long districtId) {
    Optional<District> districtOpt = this.districtRepository.findById(districtId);
    if (districtOpt.isPresent()) {
      return getDistrictDto(districtOpt.get());
    }

    return null;
  }

  /**
   * @Inherited
   */
  @Override
  public DistrictDto addDistrict(DistrictDto districtDto) throws ValidationException {
    List<District> districtList = this.districtRepository.findByName(districtDto.getName());
    if (!districtList.isEmpty()) {
      throw new ValidationException("Район: " + districtDto.getName() +
          " уже существует");
    }
    District district = getDistrict(districtDto);
    district.setId(null);
    district.setCreateDate(LocalDateTime.now());

    this.districtRepository.save(district);

    return getDistrictDto(district);
  }

  /**
   * @Inherited
   */
  @Override
  public DistrictDto updateDistrict(DistrictDto districtDto) throws ValidationException {
    Optional<District> districtOpt = this.districtRepository.findById(districtDto.getId());
    if (!districtOpt.isPresent()) {
      throw new ValidationException("Район: " + districtDto.getName() +
          " для обновления не найден");
    }
    District district = getDistrict(districtDto);

    this.districtRepository.save(district);

    return getDistrictDto(district);
  }

  /**
   * @Inherited
   */
  @Override
  public void deleteDistrict(long districtId) throws ValidationException {
    Optional<District> districtOpt = this.districtRepository.findById(districtId);
    if (!districtOpt.isPresent()) {
      throw new ValidationException("Район, по ID: " + districtId +
          " для удаления не найден");
    }
    District district = districtOpt.get();
    district.setActive(false);
    district.setLastEditDate(LocalDateTime.now());

    this.districtRepository.save(district);
  }

  /**
   * @Inherited
   */
  @Override
  public LocalityDto getLocality(long localityId) {
    Optional<Locality> localityOpt = this.localityRepository.findById(localityId);
    if (localityOpt.isPresent()) {
      return getLocalityDto(localityOpt.get());
    }

    return null;
  }

  /**
   * @Inherited
   */
  @Override
  public LocalityDto addLocality(LocalityDto localityDto) throws ValidationException {
    Locality locality = getLocality(localityDto);
    locality.setId(null);
    locality.setCreateDate(LocalDateTime.now());
    if (localityDto.getRegionId() != null) {
      List<Locality> localityList = this.localityRepository
          .findByRegionIdAndName(localityDto.getRegionId(), localityDto.getName());
      if (!localityList.isEmpty()) {
        Optional<Region> regionOpt =this.regionRepository.findById(localityDto.getRegionId());
        throw new ValidationException("Населенный пункт: " + localityDto.getName() +
            ", для области: " + (regionOpt.isPresent() ? regionOpt.get().getName() : "") +
            " уже существует");
      }
      locality.setRegionId(localityDto.getRegionId());
    } else if (localityDto.getDistrictId() != null) {
      List<Locality> localityList = this.localityRepository
          .findByDistrictIdAndName(localityDto.getDistrictId(), localityDto.getName());
      if (!localityList.isEmpty()) {
        Optional<District> districtOpt =this.districtRepository.findById(localityDto.getDistrictId());
        throw new ValidationException("Населенный пункт: " + localityDto.getName() +
            ", для района: " + (districtOpt.isPresent() ? districtOpt.get().getName() : "") +
            " уже существует");
      }
      locality.setDistrictId(localityDto.getDistrictId());
    } else {
      throw new ValidationException("Для населенного пункта: " + localityDto.getName() +
          " не указана область или район");
    }

    this.localityRepository.save(locality);

    return getLocalityDto(locality);
  }

  /**
   * @Inherited
   */
  @Override
  public LocalityDto updateLocality(LocalityDto localityDto) throws ValidationException {
    Optional<Locality> localityOpt = this.localityRepository.findById(localityDto.getId());
    if (!localityOpt.isPresent()) {
      throw new ValidationException("Населенный пункт, по ID: " + localityDto.getId() +
          " для обновления не найден");
    }
    Locality locality = getLocality(localityDto);
    locality.setId(null);
    if (localityDto.getRegionId() != null) {
      List<Locality> localityList = this.localityRepository
          .findByRegionIdAndName(localityDto.getRegionId(), localityDto.getName());
      Optional<Region> regionOpt =this.regionRepository.findById(localityDto.getRegionId());
      if (!regionOpt.isPresent()) {
        throw new ValidationException("Область, по ID: " + localityDto.getRegionId() +
            ", для населенного пункта: " + localityDto.getName() + " для обновления не найдена");
      }
      if (localityList.isEmpty()) {
        throw new ValidationException("Населенный пункт: " + localityDto.getName() +
            ", для области: " + regionOpt.get().getName() +
            " для обновления не найден");
      }
      locality.setRegionId(localityDto.getRegionId());
    } else if (localityDto.getDistrictId() != null) {
      List<Locality> localityList = this.localityRepository
          .findByDistrictIdAndName(localityDto.getDistrictId(), localityDto.getName());
      Optional<District> districtOpt =this.districtRepository.findById(localityDto.getDistrictId());
      if (!districtOpt.isPresent()) {
        throw new ValidationException("Район, по ID: " + localityDto.getDistrictId() +
            ", для населенного пункта: " + localityDto.getName() + " для обновления не найден");
      }
      if (localityList.isEmpty()) {
        throw new ValidationException("Населенный пункт: " + localityDto.getName() +
            ", для района: " + districtOpt.get().getName() +
            " для обновления не найден");
      }
      locality.setDistrictId(localityDto.getDistrictId());
    } else {
      throw new ValidationException("Для населенного пункта: " + localityDto.getName() +
          " не указана область или район");
    }

    this.localityRepository.save(locality);

    return getLocalityDto(locality);
  }

  /**
   * @Inherited
   */
  @Override
  public void deleteLocality(long localityId) throws ValidationException {
    Optional<Locality> localityOpt = this.localityRepository.findById(localityId);
    if (!localityOpt.isPresent()) {
      throw new ValidationException("Населенный пункт, по ID: " + localityId +
          " для удаления не найден");
    }
    Locality locality = localityOpt.get();
    locality.setActive(false);
    locality.setLastEditDate(LocalDateTime.now());

    this.localityRepository.save(locality);
  }

  /**
   * @Inherited
   */
  @Override
  public DistrictLocalityDto getDistrictLocality(long districtLocalityId) {
    Optional<DistrictLocality> districtLocalityOpt = this.districtLocalityRepository.findById(districtLocalityId);
    if (districtLocalityOpt.isPresent()) {
      return getDistrictLocalityDto(districtLocalityOpt.get());
    }

    return null;
  }

  /**
   * @Inherited
   */
  @Override
  public DistrictLocalityDto addDistrictLocality(DistrictLocalityDto districtLocalityDto) throws ValidationException {
    List<DistrictLocality> districtLocalityList = this.districtLocalityRepository
        .findByLocalityIdAndName(districtLocalityDto.getLocalityId(), districtLocalityDto.getName());
    if (!districtLocalityList.isEmpty()) {
      throw new ValidationException("Район населенного пункта: " + districtLocalityDto.getName() +
          " уже существует");
    }
    DistrictLocality districtLocality = getDistrictLocality(districtLocalityDto);
    districtLocality.setId(null);
    districtLocality.setCreateDate(LocalDateTime.now());

    this.districtLocalityRepository.save(districtLocality);

    return getDistrictLocalityDto(districtLocality);
  }

  /**
   * @Inherited
   */
  @Override
  public DistrictLocalityDto updateDistrictLocality(DistrictLocalityDto districtLocalityDto) throws ValidationException {
    Optional<DistrictLocality> districtLocalityOpt = this.districtLocalityRepository.findById(districtLocalityDto.getId());
    if (!districtLocalityOpt.isPresent()) {
      throw new ValidationException("Район населенного пункта: " + districtLocalityDto.getName() +
          " для обновления не найден");
    }
    DistrictLocality districtLocality = getDistrictLocality(districtLocalityDto);

    this.districtLocalityRepository.save(districtLocality);

    return getDistrictLocalityDto(districtLocality);
  }

  /**
   * @Inherited
   */
  @Override
  public void deleteDistrictLocality(long districtLocalityId) throws ValidationException {
    Optional<DistrictLocality> districtLocalityOpt = this.districtLocalityRepository.findById(districtLocalityId);
    if (!districtLocalityOpt.isPresent()) {
      throw new ValidationException("Район населенного пункта, по ID: " + districtLocalityId +
          " для удаления не найден");
    }
    DistrictLocality districtLocality = districtLocalityOpt.get();
    districtLocality.setActive(false);
    districtLocality.setLastEditDate(LocalDateTime.now());

    this.districtLocalityRepository.save(districtLocality);
  }

  /**
   * @Inherited
   */
  @Override
  public StreetDto getStreet(long streetId) {
    Optional<Street> streetOpt = this.streetRepository.findById(streetId);
    if (streetOpt.isPresent()) {
      return getStreetDto(streetOpt.get());
    }

    return null;
  }

  /**
   * @Inherited
   */
  @Override
  public StreetDto addStreet(StreetDto streetDto) throws ValidationException {
    List<Street> streetList = this.streetRepository
        .findByLocalityIdAndName(streetDto.getLocalityId(), streetDto.getName());
    if (!streetList.isEmpty()) {
      throw new ValidationException("Улица: " + streetDto.getName() +
          " уже существует");
    }
    Street street = getStreet(streetDto);
    street.setId(null);
    street.setCreateDate(LocalDateTime.now());

    this.streetRepository.save(street);

    return getStreetDto(street);
  }

  /**
   * @Inherited
   */
  @Override
  public StreetDto updateStreet(StreetDto streetDto) throws ValidationException {
    Optional<Street> streetOpt = this.streetRepository.findById(streetDto.getId());
    if (!streetOpt.isPresent()) {
      throw new ValidationException("Улица: " + streetDto.getName() +
          " для обновления не найдена");
    }
    Street street = getStreet(streetDto);

    this.streetRepository.save(street);

    return getStreetDto(street);
  }

  /**
   * @Inherited
   */
  @Override
  public void deleteStreet(long streetId) throws ValidationException {
    Optional<Street> streetOpt = this.streetRepository.findById(streetId);
    if (!streetOpt.isPresent()) {
      throw new ValidationException("Улица, по ID: " + streetId +
          " для удаления не найдена");
    }
    Street street = streetOpt.get();
    street.setActive(false);
    street.setLastEditDate(LocalDateTime.now());

    this.streetRepository.save(street);
  }

  /**
   * @Inherited
   */
  @Override
  public AddressDto getAddress(long addressId) {
    Optional<Address> addressOpt = this.addressRepository.findById(addressId);
    if (addressOpt.isPresent()) {
      return getAddressDto(addressOpt.get());
    }

    return null;
  }

  /**
   * @Inherited
   */
  @Override
  public AddressDto addAddress(AddressDto addressDto) throws ValidationException {
    Address address = getAddress(addressDto);
    address.setId(null);
    address.setCreateDate(LocalDateTime.now());

    this.addressRepository.save(address);

    return getAddressDto(address);
  }

  /**
   * @Inherited
   */
  @Override
  public void deleteAddress(long addressId) throws ValidationException {
    Optional<Address> addressOpt = this.addressRepository.findById(addressId);
    if (!addressOpt.isPresent()) {
      throw new ValidationException("Адрес, по ID: " + addressId +
          " для удаления не найден");
    }
    Address address = addressOpt.get();
    address.setActive(false);
    address.setLastEditDate(LocalDateTime.now());

    this.addressRepository.save(address);
  }



  /**
   * Метод заполняет DTO объект страны
   *
   * @param country
   *
   * @return
   */
  private CountryDto getCountryDto(Country country) {
    CountryDto countryDto = new CountryDto();
    countryDto.setId(country.getId());
    countryDto.setName(country.getName());
    countryDto.setDescription(country.getDescription());

    return countryDto;
  }

  /**
   * Метод заполняет объект страны
   *
   * @param countryDto
   *
   * @return
   */
  private Country getCountry(CountryDto countryDto) {
    Country country = new Country();
    country.setId(countryDto.getId());
    country.setName(countryDto.getName());
    country.setDescription(countryDto.getDescription());
    country.setLastEditDate(LocalDateTime.now());
    country.setActive(true);

    return country;
  }

  /**
   * Метод заполняет DTO объект области
   *
   * @param region
   *
   * @return
   */
  private RegionDto getRegionDto(Region region) {
    RegionDto regionDto = new RegionDto();
    regionDto.setId(region.getId());
    regionDto.setName(region.getName());
    regionDto.setDescription(region.getDescription());

    return regionDto;
  }

  /**
   * Метод заполняет объект области
   *
   * @param regionDto
   *
   * @return
   */
  private Region getRegion(RegionDto regionDto) {
    Region region = new Region();
    region.setId(regionDto.getId());
    region.setName(regionDto.getName());
    region.setDescription(regionDto.getDescription());
    region.setLastEditDate(LocalDateTime.now());
    region.setActive(true);

    return region;
  }

  /**
   * Метод заполняет DTO объект района
   *
   * @param district
   *
   * @return
   */
  private DistrictDto getDistrictDto(District district) {
    DistrictDto districtDto = new DistrictDto();
    districtDto.setId(district.getId());
    districtDto.setName(district.getName());
    districtDto.setDescription(district.getDescription());

    return districtDto;
  }

  /**
   * Метод заполняет объект района
   *
   * @param districtDto
   *
   * @return
   */
  private District getDistrict(DistrictDto districtDto) {
    District district = new District();
    district.setId(districtDto.getId());
    district.setName(districtDto.getName());
    district.setDescription(districtDto.getDescription());
    district.setLastEditDate(LocalDateTime.now());
    district.setActive(true);

    return district;
  }

  /**
   * Метод заполняет DTO объект населенного пункта
   *
   * @param locality
   *
   * @return
   */
  private LocalityDto getLocalityDto(Locality locality) {
    LocalityDto localityDto = new LocalityDto();
    localityDto.setId(locality.getId());
    localityDto.setName(locality.getName());
    localityDto.setDescription(locality.getDescription());
    if (locality.getTypeLocality() != null) {
      TypeLocalityDto typeLocalityDto = new TypeLocalityDto();
      typeLocalityDto.setName(locality.getTypeLocality().getName());
      localityDto.setTypeLocality(typeLocalityDto);
    }

    return localityDto;
  }

  /**
   * Метод заполняет объект населенного пункта
   *
   * @param localityDto
   *
   * @return
   */
  private Locality getLocality(LocalityDto localityDto) {
    Locality locality = new Locality();
    locality.setId(localityDto.getId());
    locality.setName(localityDto.getName());
    locality.setDescription(localityDto.getDescription());
    if (localityDto.getTypeLocality() != null) {
      locality.setTypeLocality(TypeLocality.valueOf(localityDto.getTypeLocality().getName()));
    }
    locality.setLastEditDate(LocalDateTime.now());
    locality.setActive(true);

    return locality;
  }

  /**
   * Метод заполняет DTO объект тип населенного пункта
   *
   * @param typeLocality
   *
   * @return
   */
  private TypeLocalityDto getTypeLocalityDto(TypeLocality typeLocality) {
    TypeLocalityDto typeLocalityDto = new TypeLocalityDto();
    typeLocalityDto.setName(typeLocality.getName());

    return typeLocalityDto;
  }

  /**
   * Метод заполняет DTO объект района населенного пункта
   *
   * @param districtLocality
   *
   * @return
   */
  private DistrictLocalityDto getDistrictLocalityDto(DistrictLocality districtLocality) {
    DistrictLocalityDto districtLocalityDto = new DistrictLocalityDto();
    districtLocalityDto.setId(districtLocality.getId());
    districtLocalityDto.setName(districtLocality.getName());
    districtLocalityDto.setId(districtLocality.getLocalityId());
    districtLocalityDto.setDescription(districtLocality.getDescription());

    return districtLocalityDto;
  }

  /**
   * Метод заполняет объект района населенного пункта
   *
   * @param districtLocalityDto
   *
   * @return
   */
  private DistrictLocality getDistrictLocality(DistrictLocalityDto districtLocalityDto) {
    DistrictLocality districtLocality = new DistrictLocality();
    districtLocality.setId(districtLocalityDto.getId());
    districtLocality.setName(districtLocalityDto.getName());
    districtLocality.setId(districtLocalityDto.getLocalityId());
    districtLocality.setDescription(districtLocalityDto.getDescription());
    districtLocality.setLastEditDate(LocalDateTime.now());
    districtLocality.setActive(true);

    return districtLocality;
  }

  /**
   * Метод заполняет DTO объект улицы
   *
   * @param street
   *
   * @return
   */
  private StreetDto getStreetDto(Street street) {
    StreetDto streetDto = new StreetDto();
    streetDto.setId(street.getId());
    streetDto.setName(street.getName());
    streetDto.setDescription(street.getDescription());

    return streetDto;
  }

  /**
   * Метод заполняет объект улицы
   *
   * @param streetDto
   *
   * @return
   */
  private Street getStreet(StreetDto streetDto) {
    Street street = new Street();
    street.setId(streetDto.getId());
    street.setName(streetDto.getName());
    street.setDescription(streetDto.getDescription());
    street.setLastEditDate(LocalDateTime.now());
    street.setActive(true);

    return street;
  }

  /**
   * Метод заполняет DTO объект адреса
   *
   * @param address
   *
   * @return
   */
  private AddressDto getAddressDto(Address address) {
    AddressDto addressDto = new AddressDto();
    addressDto.setId(address.getId());
    addressDto.setCountry(getCountry(address.getCountryId()));
    addressDto.setRegion(getRegion(address.getRegionId()));
    if (address.getDistrictId() != null) {
      addressDto.setDistrict(getDistrict(address.getDistrictId()));
    }
    addressDto.setLocality(getLocality(address.getLocalityId()));
    if (address.getDistrictLocalityId() != null) {
      addressDto.setDistrictLocality(getDistrictLocality(address.getDistrictLocalityId()));
    }
    addressDto.setStreet(getStreet(address.getStreetId()));
    addressDto.setFlat(address.getFlat());
    addressDto.setHome(address.getHome());
    addressDto.setBild(address.getBild());
    addressDto.setFlat(address.getFlat());
    addressDto.setRoom(address.getRoom());
    addressDto.setZipcode(address.getZipcode());
    addressDto.setDescription(address.getDescription());

    return addressDto;
  }

  /**
   * Метод заполняет объект адреса
   *
   * @param addressDto
   *
   * @return
   */
  private Address getAddress(AddressDto addressDto) {
    Address address = new Address();
    address.setId(addressDto.getId());
    address.setCountryId(addressDto.getCountry().getId());
    address.setRegionId(addressDto.getRegion().getId());
    if (addressDto.getDistrict() != null) {
      address.setDistrictId(addressDto.getDistrict().getId());
    }
    address.setLocalityId(addressDto.getLocality().getId());
    if (addressDto.getDistrictLocality() != null) {
      address.setDistrictLocalityId(addressDto.getDistrictLocality().getId());
    }
    address.setStreetId(addressDto.getStreet().getId());
    address.setFlat(addressDto.getFlat());
    address.setHome(addressDto.getHome());
    address.setBild(addressDto.getBild());
    address.setFlat(addressDto.getFlat());
    address.setRoom(addressDto.getRoom());
    address.setZipcode(addressDto.getZipcode());
    address.setDescription(addressDto.getDescription());
    address.setLastEditDate(LocalDateTime.now());
    address.setActive(true);

    return address;
  }
}
