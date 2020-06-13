package ru.khmel.mpi.address.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.khmel.mpi.address.dto.AddressDto;
import ru.khmel.mpi.address.dto.CountryDto;
import ru.khmel.mpi.address.dto.DistrictDto;
import ru.khmel.mpi.address.dto.DistrictLocalityDto;
import ru.khmel.mpi.address.dto.LocalityDto;
import ru.khmel.mpi.address.dto.RegionDto;
import ru.khmel.mpi.address.dto.StreetDto;
import ru.khmel.mpi.address.dto.TypeLocalityDto;
import ru.khmel.mpi.exception.ValidationException;
import ru.khmel.mpi.address.service.AddressService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Khmel Anton
 */
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/api")
public class AddressController {
    @Autowired
    private AddressService addressService;

    /**
     * Метод возвращает список адресов для пользователя
     *
     * @return
     */
    @ApiOperation(value = "Список адресов", notes = "Метод для получения списка адресов", response = List.class)
    @RequestMapping(value = "/address/list", method= {GET})
    public List<AddressDto> getAddressList() {
        return this.addressService.getAddressList();
    }

    /**
     * Метод возвращает список стран
     *
     * @param countryName
     *
     * @return
     */
    @ApiOperation(value = "Список стран", notes = "Метод для получения списка стран", response = List.class)
    @RequestMapping(value = "/country/list/{countryName}", method= {GET})
    public List<CountryDto> getCountryList(@PathVariable("countryName") String countryName) {
        return this.addressService.getCountryList(countryName);
    }

    /**
     * Метод возвращает список областей для страны
     *
     * @param countryId
     * @param regionName
     *
     * @return
     */
    @ApiOperation(value = "Список областей", notes = "Метод для получения списка областей", response = List.class)
    @RequestMapping(value = "/region/list/{countryId}/{regionName}", method= {GET})
    public List<RegionDto> getRegionList(@PathVariable("countryId") long countryId,
                                         @PathVariable("regionName") String regionName) {
        return this.addressService.getRegionList(countryId, regionName);
    }

    /**
     * Метод возвращает список районов для области
     *
     * @param regionId
     * @param districtName
     *
     * @return
     */
    @ApiOperation(value = "Список районов", notes = "Метод для получения списка районов", response = List.class)
    @RequestMapping(value = "/district/list/{regionId}/{districtName}", method= {GET})
    public List<DistrictDto> getDistrictList(@PathVariable("regionId") long regionId,
                                             @PathVariable("districtName") String districtName) {
        return this.addressService.getDistrictList(regionId, districtName);
    }

    /**
     * Метод возвращает список населенных пунктов для области или району
     *
     * @param regionId
     * @param districtId
     * @param localityName
     *
     * @return
     */
    @ApiOperation(value = "Список населенных пунктов", notes = "Метод для получения списка населенных пунктов", response = List.class)
    @RequestMapping(value = "/locality/list/{regionId}/{districtId}/{localityName}", method= {GET})
    public List<LocalityDto> getLocalityList(@PathVariable("regionId") long regionId,
                                             @PathVariable("districtId") long districtId,
                                             @PathVariable("localityName") String localityName) {
        return this.addressService.getLocalityList(regionId, districtId, localityName);
    }

    /**
     * Метод возвращает список типов населенного пункта
     *
     * @return
     */
    @ApiOperation(value = "Список типов населенного пункта", notes = "Метод для получения списка типов населенного пункта", response = List.class)
    @RequestMapping(value = "/type/locality/list", method= {GET})
    public List<TypeLocalityDto> getTypeLocalityList() {
        return this.addressService.getTypeLocalityList();
    }

    /**
     * Метод возвращает список районов населенного пункта
     *
     * @param localityId
     * @param districtLocalityName
     *
     * @return
     */
    @ApiOperation(value = "Список районов населенного пункта", notes = "Метод для получения списка районов населенного пункта", response = List.class)
    @RequestMapping(value = "/district/locality/list/{localityId}/{districtLocalityName}", method= {GET})
    public List<DistrictLocalityDto> getDistrictLocalityList(@PathVariable("localityId") long localityId,
                                                             @PathVariable("districtLocalityName") String districtLocalityName) {
        return this.addressService.getDistrictLocalityList(localityId, districtLocalityName);
    }

    /**
     * Метод возвращает список улиц для населенного пункта
     *
     * @param localityId
     * @param streetName
     *
     * @return
     */
    @ApiOperation(value = "Список улиц", notes = "Метод для получения списка улиц", response = List.class)
    @RequestMapping(value = "/street/list/{localityId}/{streetName}", method= {GET})
    public List<StreetDto> getStreetList(@PathVariable("localityId") long localityId,
                                         @PathVariable("streetName") String streetName) {
        return this.addressService.getStreetList(localityId, streetName);
    }

    /**
     * Метод возвращает страну по ID
     *
     * @param countryId
     *
     * @return
     */
    @ApiOperation(value = "Получить страну по ID", notes = "Метод для получения страны по ID", response = CountryDto.class)
    @RequestMapping(value = "/country/get/{countryId}", method= {GET})
    public CountryDto getCountry(@PathVariable("countryId") long countryId) {
        return this.addressService.getCountry(countryId);
    }

    /**
     * Метод добавляет страну
     *
     * @param countryDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Добавить страну", notes = "Метод для добавления страны", response = CountryDto.class)
    @RequestMapping(value = "/country/add", method= {POST})
    public CountryDto addCountry(@RequestBody CountryDto countryDto) throws ValidationException {
        return this.addressService.addCountry(countryDto);
    }

    /**
     * Метод обновляет страну
     *
     * @param countryDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Обновить страну", notes = "Метод для обновления страны", response = CountryDto.class)
    @RequestMapping(value = "/country/update", method= {POST})
    public CountryDto updateCountry(@RequestBody CountryDto countryDto) throws ValidationException {
        return this.addressService.updateCountry(countryDto);
    }

    /**
     * Метод проставляет флаг удаления для страны
     *
     * @param countryId
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Удалить страну", notes = "Метод для удаления страны", response = ResponseEntity.class)
    @RequestMapping(value = "/country/delete/{countryId}", method= {GET})
    public ResponseEntity<?> deleteCountry(@PathVariable("countryId") long countryId) throws ValidationException {
        this.addressService.deleteCountry(countryId);

        return ResponseEntity.ok().build();
    }

    /**
     * Метод возвращает область по ID
     *
     * @param regionId
     *
     * @return
     */
    @ApiOperation(value = "Получить область по ID", notes = "Метод для получения области по ID", response = RegionDto.class)
    @RequestMapping(value = "/region/get/{regionId}", method= {GET})
    public RegionDto getRegion(@PathVariable("regionId") long regionId) {
        return this.addressService.getRegion(regionId);
    }

    /**
     * Метод добавляет область
     *
     * @param regionDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Добавить область", notes = "Метод для добавления области", response = RegionDto.class)
    @RequestMapping(value = "/region/add", method= {POST})
    public RegionDto addRegion(@RequestBody RegionDto regionDto) throws ValidationException {
        return this.addressService.addRegion(regionDto);
    }

    /**
     * Метод обновляет область
     *
     * @param regionDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Обновить область", notes = "Метод для обновления области", response = RegionDto.class)
    @RequestMapping(value = "/region/update", method= {POST})
    public RegionDto updateRegion(@RequestBody RegionDto regionDto) throws ValidationException {
        return this.addressService.updateRegion(regionDto);
    }

    /**
     * Метод проставляет флаг удаления для области
     *
     * @param regionId
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Удалить область", notes = "Метод для удаления области", response = ResponseEntity.class)
    @RequestMapping(value = "/region/delete/{regionId}", method= {GET})
    public ResponseEntity<?> deleteRegion(@PathVariable("regionId") long regionId) throws ValidationException {
        this.addressService.deleteRegion(regionId);

        return ResponseEntity.ok().build();
    }

    /**
     * Метод возвращает район по ID
     *
     * @param districtId
     *
     * @return
     */
    @ApiOperation(value = "Получить район по ID", notes = "Метод для получения района по ID", response = DistrictDto.class)
    @RequestMapping(value = "/district/get/{districtId}", method= {GET})
    public DistrictDto getDistrict(@PathVariable("districtId") long districtId) {
        return this.addressService.getDistrict(districtId);
    }

    /**
     * Метод добавляет район
     *
     * @param districtDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Добавить район", notes = "Метод для добавления района", response = DistrictDto.class)
    @RequestMapping(value = "/district/add", method= {POST})
    public DistrictDto addDistrict(@RequestBody DistrictDto districtDto) throws ValidationException {
        return this.addressService.addDistrict(districtDto);
    }

    /**
     * Метод обновляет район
     *
     * @param districtDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Обновить район", notes = "Метод для обновления района", response = DistrictDto.class)
    @RequestMapping(value = "/district/update", method= {POST})
    public DistrictDto updateDistrict(@RequestBody DistrictDto districtDto) throws ValidationException {
        return this.addressService.updateDistrict(districtDto);
    }

    /**
     * Метод проставляет флаг удаления для района
     *
     * @param districtId
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Удалить район", notes = "Метод для удаления района", response = ResponseEntity.class)
    @RequestMapping(value = "/district/delete/{districtId}", method= {GET})
    public ResponseEntity<?> deleteDistrict(@PathVariable("districtId") long districtId) throws ValidationException {
        this.addressService.deleteDistrict(districtId);

        return ResponseEntity.ok().build();
    }

    /**
     * Метод возвращает населенный пункт по ID
     *
     * @param localityId
     *
     * @return
     */
    @ApiOperation(value = "Получить населенный пункт по ID", notes = "Метод для получения населенного пункта по ID", response = LocalityDto.class)
    @RequestMapping(value = "/locality/get/{localityId}", method= {GET})
    public LocalityDto getLocality(@PathVariable("localityId") long localityId) {
        return this.addressService.getLocality(localityId);
    }

    /**
     * Метод добавляет населенный пункт
     *
     * @param localityDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Добавить населенный пункт", notes = "Метод для добавления населенного пункта", response = LocalityDto.class)
    @RequestMapping(value = "/locality/add", method= {POST})
    public LocalityDto addLocality(@RequestBody LocalityDto localityDto) throws ValidationException {
        return this.addressService.addLocality(localityDto);
    }

    /**
     * Метод обновляет населенный пункт
     *
     * @param localityDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Обновить населенный пункт", notes = "Метод для обновления населенного пункта", response = LocalityDto.class)
    @RequestMapping(value = "/locality/update", method= {POST})
    public LocalityDto updateLocality(@RequestBody LocalityDto localityDto) throws ValidationException {
        return this.addressService.updateLocality(localityDto);
    }

    /**
     * Метод проставляет флаг удаления для населенного пункта
     *
     * @param localityId
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Удалить населенный пункт", notes = "Метод для удаления населенного пункта", response = ResponseEntity.class)
    @RequestMapping(value = "/locality/delete/{localityId}", method= {GET})
    public ResponseEntity<?> deleteLocality(@PathVariable("localityId") long localityId) throws ValidationException {
        this.addressService.deleteLocality(localityId);

        return ResponseEntity.ok().build();
    }

    /**
     * Метод возвращает район населенного пункта по ID
     *
     * @param districtLocalityId
     *
     * @return
     */
    @ApiOperation(value = "район населенного пункта", notes = "Метод для получения район населенного пункта по ID", response = DistrictLocalityDto.class)
    @RequestMapping(value = "/district/locality/get/{districtLocalityId}", method= {GET})
    public DistrictLocalityDto getDistrictLocality(@PathVariable("districtLocalityId") long districtLocalityId) {
        return this.addressService.getDistrictLocality(districtLocalityId);
    }

    /**
     * Метод добавляет район населенного пункта
     *
     * @param districtLocalityDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Добавить район населенного пункта", notes = "Метод для добавления район населенного пункта", response = DistrictLocalityDto.class)
    @RequestMapping(value = "/district/locality/add", method= {POST})
    public DistrictLocalityDto addDistrictLocality(@RequestBody DistrictLocalityDto districtLocalityDto) throws ValidationException {
        return this.addressService.addDistrictLocality(districtLocalityDto);
    }

    /**
     * Метод обновляет район населенного пункта
     *
     * @param districtLocalityDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Обновить район населенного пункта", notes = "Метод для обновления район населенного пункта", response = DistrictLocalityDto.class)
    @RequestMapping(value = "/district/locality/update", method= {POST})
    public DistrictLocalityDto updateDistrictLocality(@RequestBody DistrictLocalityDto districtLocalityDto) throws ValidationException {
        return this.addressService.updateDistrictLocality(districtLocalityDto);
    }

    /**
     * Метод проставляет флаг удаления для район населенного пункта
     *
     * @param districtLocalityId
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Удалить район населенного пункта", notes = "Метод для удаления район населенного пункта", response = ResponseEntity.class)
    @RequestMapping(value = "/district/locality/delete/{districtLocalityId}", method= {GET})
    public ResponseEntity<?> deleteDistrictLocality(@PathVariable("districtLocalityId") long districtLocalityId) throws ValidationException {
        this.addressService.deleteDistrictLocality(districtLocalityId);

        return ResponseEntity.ok().build();
    }

    /**
     * Метод возвращает улицу по ID
     *
     * @param streetId
     *
     * @return
     */
    @ApiOperation(value = "Получить улицу по ID", notes = "Метод для получения улицы по ID", response = StreetDto.class)
    @RequestMapping(value = "/street/get/{streetId}", method= {GET})
    public StreetDto getStreet(@PathVariable("streetId") long streetId) {
        return this.addressService.getStreet(streetId);
    }

    /**
     * Метод добавляет улицу
     *
     * @param streetDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Добавить улицу", notes = "Метод для добавления улицы", response = StreetDto.class)
    @RequestMapping(value = "/street/add", method= {POST})
    public StreetDto addStreet(@RequestBody StreetDto streetDto) throws ValidationException {
        return this.addressService.addStreet(streetDto);
    }

    /**
     * Метод обновляет улицу
     *
     * @param streetDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Обновить улицу", notes = "Метод для обновления улицы", response = StreetDto.class)
    @RequestMapping(value = "/street/update", method= {POST})
    public StreetDto updateStreet(@RequestBody StreetDto streetDto) throws ValidationException {
        return this.addressService.updateStreet(streetDto);
    }

    /**
     * Метод проставляет флаг удаления для улицы
     *
     * @param streetId
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Удалить улицу", notes = "Метод для удаления улицы", response = ResponseEntity.class)
    @RequestMapping(value = "/street/delete/{streetId}", method= {GET})
    public ResponseEntity<?> deleteStreet(@PathVariable("streetId") long streetId) throws ValidationException {
        this.addressService.deleteStreet(streetId);

        return ResponseEntity.ok().build();
    }

    /**
     * Метод возвращает адрес по ID
     *
     * @param addressId
     *
     * @return
     */
    @ApiOperation(value = "Получить адрес по ID", notes = "Метод для получения адреса по ID", response = AddressDto.class)
    @RequestMapping(value = "/address/get/{addressId}", method= {GET})
    public AddressDto getAddress(@PathVariable("addressId") long addressId) {
        return this.addressService.getAddress(addressId);
    }

    /**
     * Метод добавляет адрес
     *
     * @param addressDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Добавить адрес", notes = "Метод для добавления адреса", response = AddressDto.class)
    @RequestMapping(value = "/address/add", method= {POST})
    public AddressDto addAddress(@RequestBody AddressDto addressDto) throws ValidationException {
        return this.addressService.addAddress(addressDto);
    }

    /**
     * Метод проставляет флаг удаления для адреса
     *
     * @param addressId
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Удалить адрес", notes = "Метод для удаления адреса", response = ResponseEntity.class)
    @RequestMapping(value = "/address/delete/{addressId}", method= {GET})
    public ResponseEntity<?> deleteAddress(@PathVariable("addressId") long addressId) throws ValidationException {
        this.addressService.deleteAddress(addressId);

        return ResponseEntity.ok().build();
    }

    /**
     * @param exception
     * @return ResponseEntity<String>
     */
    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception exception) {
        log.error("exception: {}", exception);

        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.FORBIDDEN);
    }
}
