package ru.khmel.mpi.card.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.khmel.mpi.card.dto.PassCardDto;
import ru.khmel.mpi.card.dto.ReasonCardDto;
import ru.khmel.mpi.card.dto.ScopeCardDto;
import ru.khmel.mpi.card.dto.StatusCardDto;
import ru.khmel.mpi.card.dto.TypeCardDto;
import ru.khmel.mpi.exception.ValidationException;
import ru.khmel.mpi.card.jpa.entity.PassCard;
import ru.khmel.mpi.card.jpa.entity.ReasonCard;
import ru.khmel.mpi.card.jpa.entity.ScopeCard;
import ru.khmel.mpi.card.jpa.entity.StatusCard;
import ru.khmel.mpi.card.jpa.entity.TypeCard;
import ru.khmel.mpi.card.jpa.repository.PassCardRepository;
import ru.khmel.mpi.card.jpa.repository.ReasonCardRepository;
import ru.khmel.mpi.card.jpa.repository.ScopeCardRepository;
import ru.khmel.mpi.card.jpa.repository.TypeCardRepository;
import ru.khmel.mpi.address.service.AddressService;
import ru.khmel.mpi.card.service.PassCardService;
import ru.khmel.mpi.user.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Сервис для работы с пропусками
 *
 * @author Khmel Anton
 */
@Service
@AllArgsConstructor
public class PassCardServiceImpl implements PassCardService {
  @Autowired
  private final PassCardRepository passCardRepository;
  @Autowired
  private final ReasonCardRepository reasonCardRepository;
  @Autowired
  private final ScopeCardRepository scopeCardRepository;
  @Autowired
  private final TypeCardRepository typeCardRepository;

  @Autowired
  private AddressService addressService;
  @Autowired
  private UserService userService;

  /**
   * @Inherited
   */
  @Override
  public List<PassCardDto> getPassCardList(long userId) {
    System.out.println("userId: " + userId);
//    long userId = 1 + (int) (Math.random() * 3);
//    PassCardFilter filter = new PassCardFilter();
//    filter.setUserId(userId);
//    Pageable pageable = Pageable.unpaged();
//    IPredicate iPredicate = new PassCardPredicate(new EmptyPredicate(), filter);
//    Page<PassCard> page = this.passCardRepository.findByOrderByIdDesc(pageable);
//    Page<PassCard> page = this.passCardRepository.findAll(iPredicate.predicate(), pageable);
//    this.totalSize = page.getTotalElements();
//    this.activePage = Math.min(this.activePage, (int) this.totalSize / this.pageSize);
//    List<PassCard> passCardList = page.getContent();
    List<PassCard> passCardList = this.passCardRepository.findByUserId(userId);
    Collections.reverse(passCardList);
    Function<PassCard, PassCardDto> mapPassCard = p -> {
      return getPassCardDto(p);
    };

    return passCardList.stream()
        .map(mapPassCard)
        .collect(Collectors.toList());
  }

  /**
   * @Inherited
   */
  @Override
  public PassCardDto getPassCardByNumber(String number) {
    UUID uuid = UUID.fromString(number);
    List<PassCard> passCardList = this.passCardRepository.findByNumber(uuid);
    if (passCardList != null && !passCardList.isEmpty()) {
      return getPassCardDto(passCardList.get(0));
    }

    return null;
  }

  /**
   * @Inherited
   */
  @Override
  public List<ReasonCardDto> getReasonCardList(String reasonCardName) {
    List<ReasonCard> reasonCardList = this.reasonCardRepository.findAll();
    Function<ReasonCard, ReasonCardDto> mapReasonCard = p -> {
      return getReasonCardDto(p);
    };

    return reasonCardList.stream()
        .map(mapReasonCard)
        .collect(Collectors.toList());
  }

  /**
   * @Inherited
   */
  @Override
  public List<ScopeCardDto> getScopeCardList() {
    List<ScopeCard> scopeCardList = this.scopeCardRepository.findAll();
    Function<ScopeCard, ScopeCardDto> mapScopeCard = p -> {
      return getScopeCardDto(p);
    };

    return scopeCardList.stream()
        .map(mapScopeCard)
        .collect(Collectors.toList());
  }

  /**
   * @Inherited
   */
  @Override
  public List<StatusCardDto> getStatusCardList() {
    List<StatusCard> statusCardList = Arrays.asList(StatusCard.values());
    Function<StatusCard, StatusCardDto> mapStatusCard = p -> {
      return getStatusCardDto(p);
    };

    return statusCardList.stream()
        .map(mapStatusCard)
        .collect(Collectors.toList());
  }

  /**
   * @Inherited
   */
  @Override
  public List<TypeCardDto> getTypeCardList(String typeCardName) {
    List<TypeCard> typeCardList = this.typeCardRepository.findAll();
    Function<TypeCard, TypeCardDto> mapTypeCard = p -> {
      return getTypeCardDto(p);
    };

    return typeCardList.stream()
        .map(mapTypeCard)
        .collect(Collectors.toList());
  }

  /**
   * @Inherited
   */
  @Override
  public ReasonCardDto getReasonCard(long reasonCardId) {
    Optional<ReasonCard> reasonCardOpt = this.reasonCardRepository.findById(reasonCardId);
    if (reasonCardOpt.isPresent()) {
      return getReasonCardDto(reasonCardOpt.get());
    }

    return null;
  }

  /**
   * @Inherited
   */
  @Override
  public ReasonCardDto addReasonCard(ReasonCardDto reasonCardDto) throws ValidationException {
    List<ReasonCard> reasonCardList = this.reasonCardRepository.findByName(reasonCardDto.getName());
    if (!reasonCardList.isEmpty()) {
      throw new ValidationException("Причина для выдачи пропуска: " + reasonCardDto.getName() +
          " уже существует");
    }
    ReasonCard reasonCard = getReasonCard(reasonCardDto);
    reasonCard.setId(null);
    reasonCard.setCreateDate(LocalDateTime.now());

    this.reasonCardRepository.save(reasonCard);

    return getReasonCardDto(reasonCard);
  }

  /**
   * @Inherited
   */
  @Override
  public ReasonCardDto updateReasonCard(ReasonCardDto reasonCardDto) throws ValidationException {
    Optional<ReasonCard> reasonCardOpt = this.reasonCardRepository.findById(reasonCardDto.getId());
    if (!reasonCardOpt.isPresent()) {
      throw new ValidationException("Причина для выдачи пропуска: " + reasonCardDto.getName() +
          " для обновления не найдена");
    }
    ReasonCard reasonCard = getReasonCard(reasonCardDto);

    this.reasonCardRepository.save(reasonCard);

    return getReasonCardDto(reasonCard);
  }

  /**
   * @Inherited
   */
  @Override
  public void deleteReasonCard(long reasonCardId) throws ValidationException {
    Optional<ReasonCard> reasonCardOpt = this.reasonCardRepository.findById(reasonCardId);
    if (!reasonCardOpt.isPresent()) {
      throw new ValidationException("Причина для выдачи пропуска, по ID: " + reasonCardId +
          " для удаления не найдена");
    }
    ReasonCard reasonCard = reasonCardOpt.get();
    reasonCard.setActive(false);
    reasonCard.setLastEditDate(LocalDateTime.now());

    this.reasonCardRepository.save(reasonCard);
  }

  /**
   * @Inherited
   */
  @Override
  public ScopeCardDto getScopeCard(long scopeCardId) {
    Optional<ScopeCard> scopeCardOpt = this.scopeCardRepository.findById(scopeCardId);
    if (scopeCardOpt.isPresent()) {
      return getScopeCardDto(scopeCardOpt.get());
    }

    return null;
  }

  /**
   * @Inherited
   */
  @Override
  public ScopeCardDto addScopeCard(ScopeCardDto scopeCardDto) throws ValidationException {
    List<ScopeCard> scopeCardList = Collections.EMPTY_LIST;
    if (scopeCardDto.getCountry() != null &&
        scopeCardDto.getCountry().getId() != null &&
        scopeCardDto.getCountry().getId() != 0L &&
        scopeCardDto.getRegion() != null &&
        scopeCardDto.getRegion().getId() != null &&
        scopeCardDto.getRegion().getId() != 0L &&
        scopeCardDto.getDistrict() != null &&
        scopeCardDto.getDistrict().getId() != null &&
        scopeCardDto.getDistrict().getId() != 0L &&
        scopeCardDto.getLocality() != null &&
        scopeCardDto.getLocality().getId() != null &&
        scopeCardDto.getLocality().getId() != 0L) {
      scopeCardList = this.scopeCardRepository
          .findByCountryIdAndRegionIdAndDistrictIdAndLocalityId(scopeCardDto.getCountry().getId(),
              scopeCardDto.getRegion().getId(), scopeCardDto.getDistrict().getId(), scopeCardDto.getLocality().getId());
    } else if (scopeCardDto.getCountry() != null &&
        scopeCardDto.getCountry().getId() != null &&
        scopeCardDto.getCountry().getId() != 0L &&
        scopeCardDto.getRegion() != null &&
        scopeCardDto.getRegion().getId() != null &&
        scopeCardDto.getRegion().getId() != 0L &&
        scopeCardDto.getLocality() != null &&
        scopeCardDto.getLocality().getId() != null &&
        scopeCardDto.getLocality().getId() != 0L) {
      scopeCardList = this.scopeCardRepository
          .findByCountryIdAndRegionIdAndLocalityId(scopeCardDto.getCountry().getId(),
              scopeCardDto.getRegion().getId(), scopeCardDto.getLocality().getId());
    } else {
      throw new ValidationException("Для области передвижения, не заданы обязательные поля");
    }
    if (!scopeCardList.isEmpty()) {
      throw new ValidationException("Область передвижения уже существует");
    }
    ScopeCard scopeCard = getScopeCard(scopeCardDto);
    scopeCard.setId(null);
    scopeCard.setCreateDate(LocalDateTime.now());

    this.scopeCardRepository.save(scopeCard);

    return getScopeCardDto(scopeCard);
  }

  /**
   * @Inherited
   */
  @Override
  public ScopeCardDto updateScopeCard(ScopeCardDto scopeCardDto) throws ValidationException {
    Optional<ScopeCard> scopeCardOpt = this.scopeCardRepository.findById(scopeCardDto.getId());
    if (!scopeCardOpt.isPresent()) {
      throw new ValidationException("Область передвижения, по ID: " + scopeCardDto.getId() +
          " для обновления не найдена");
    }
    ScopeCard scopeCard = getScopeCard(scopeCardDto);

    this.scopeCardRepository.save(scopeCard);

    return getScopeCardDto(scopeCard);
  }

  /**
   * @Inherited
   */
  @Override
  public void deleteScopeCard(long scopeCardId) throws ValidationException {
    Optional<ScopeCard> scopeCardOpt = this.scopeCardRepository.findById(scopeCardId);
    if (!scopeCardOpt.isPresent()) {
      throw new ValidationException("Область передвижения, по ID: " + scopeCardId +
          " для удаления не найдена");
    }
    ScopeCard scopeCard = scopeCardOpt.get();
    scopeCard.setActive(false);
    scopeCard.setLastEditDate(LocalDateTime.now());

    this.scopeCardRepository.save(scopeCard);
  }

  /**
   * @Inherited
   */
  @Override
  public TypeCardDto getTypeCard(long typeCardId) {
    Optional<TypeCard> typeCardOpt = this.typeCardRepository.findById(typeCardId);
    if (typeCardOpt.isPresent()) {
      return getTypeCardDto(typeCardOpt.get());
    }

    return null;
  }

  /**
   * @Inherited
   */
  @Override
  public TypeCardDto addTypeCard(TypeCardDto typeCardDto) throws ValidationException {
    List<TypeCard> typeCardList = this.typeCardRepository.findByName(typeCardDto.getName());
    if (!typeCardList.isEmpty()) {
      throw new ValidationException("Вид передвижения: " + typeCardDto.getName() +
          " уже существует");
    }
    TypeCard typeCard = getTypeCard(typeCardDto);
    typeCard.setId(null);
    typeCard.setCreateDate(LocalDateTime.now());

    this.typeCardRepository.save(typeCard);

    return getTypeCardDto(typeCard);
  }

  /**
   * @Inherited
   */
  @Override
  public TypeCardDto updateTypeCard(TypeCardDto typeCardDto) throws ValidationException {
    Optional<TypeCard> typeCardOpt = this.typeCardRepository.findById(typeCardDto.getId());
    if (!typeCardOpt.isPresent()) {
      throw new ValidationException("Вид передвижения: " + typeCardDto.getName() +
          " для обновления не найден");
    }
    TypeCard typeCard = getTypeCard(typeCardDto);

    this.typeCardRepository.save(typeCard);

    return getTypeCardDto(typeCard);
  }

  /**
   * @Inherited
   */
  @Override
  public void deleteTypeCard(long typeCardId) throws ValidationException {
    Optional<TypeCard> typeCardOpt = this.typeCardRepository.findById(typeCardId);
    if (!typeCardOpt.isPresent()) {
      throw new ValidationException("Вид передвижения, по ID: " + typeCardId +
          " для удаления не найден");
    }
    TypeCard typeCard = typeCardOpt.get();
    typeCard.setActive(false);
    typeCard.setLastEditDate(LocalDateTime.now());

    this.typeCardRepository.save(typeCard);
  }

  /**
   * @Inherited
   */
  @Override
  public PassCardDto getPassCard(long passCardId) {
    Optional<PassCard> passCardOpt = this.passCardRepository.findById(passCardId);
    if (passCardOpt.isPresent()) {
      return getPassCardDto(passCardOpt.get());
    }

    return null;
  }

  /**
   * @Inherited
   */
  @Override
  public PassCardDto addPassCard(PassCardDto passCardDto) throws ValidationException {
    List<PassCard> passCardList = this.passCardRepository.findByNumber(passCardDto.getNumber());
    if (!passCardList.isEmpty()) {
      throw new ValidationException("Пропуск по номеру: " + passCardDto.getNumber() +
          " уже существует");
    }
    PassCard passCard = getPassCard(passCardDto);
    passCard.setId(null);
    passCard.setCreateDate(LocalDateTime.now());

    this.passCardRepository.save(passCard);

    return getPassCardDto(passCard);
  }

  /**
   * @Inherited
   */
  @Override
  public PassCardDto updatePassCard(PassCardDto passCardDto) throws ValidationException {
    Optional<PassCard> passCardOpt = this.passCardRepository.findById(passCardDto.getId());
    if (!passCardOpt.isPresent()) {
      throw new ValidationException("Пропуск, по ID: " + passCardDto.getId() +
          " для обновления не найден");
    }
    PassCard passCard = getPassCard(passCardDto);

    this.passCardRepository.save(passCard);

    return getPassCardDto(passCard);
  }

  /**
   * @Inherited
   */
  @Override
  public void deletePassCard(long passCardId) throws ValidationException {
    Optional<PassCard> passCardOpt = this.passCardRepository.findById(passCardId);
    if (!passCardOpt.isPresent()) {
      throw new ValidationException("Пропуск, по ID: " + passCardId +
          " для удаления не найден");
    }
    PassCard passCard = passCardOpt.get();
    passCard.setActive(false);
    passCard.setLastEditDate(LocalDateTime.now());

    this.passCardRepository.save(passCard);
  }

  /**
   * Метод заполняет DTO объект пропуска
   *
   * @param passCard
   *
   * @return
   */
  private PassCardDto getPassCardDto(PassCard passCard) {
    PassCardDto passCardDto = new PassCardDto();
    passCardDto.setId(passCard.getId());
    passCardDto.setNumber(passCard.getNumber());
    if (passCard.getResons() != null && !passCard.getResons().isEmpty()) {
      List<ReasonCardDto> reasonCardDtoList = new ArrayList<>();
      for (ReasonCard item : passCard.getResons()) {
        if (item != null) {
          reasonCardDtoList.add(getReasonCardDto(item));
        }
      }
      passCardDto.setReasons(reasonCardDtoList);
    }
    if (passCard.getScopes() != null && !passCard.getScopes().isEmpty()) {
      List<ScopeCardDto> scopeCardDtoList = new ArrayList<>();
      for (ScopeCard item : passCard.getScopes()) {
        if (item != null) {
          scopeCardDtoList.add(getScopeCardDto(item));
        }
      }
      passCardDto.setScopes(scopeCardDtoList);
    }
    if (passCard.getTypes() != null && !passCard.getTypes().isEmpty()) {
      List<TypeCardDto> typeCardDtoList = new ArrayList<>();
      for (TypeCard item : passCard.getTypes()) {
        if (item != null) {
          typeCardDtoList.add(getTypeCardDto(item));
        }
      }
      passCardDto.setTypes(typeCardDtoList);
    }
    if (passCard.getStatusCard() != null) {
      passCardDto.setStatus(getStatusCardDto(passCard.getStatusCard()));
    }
    passCardDto.setStartDate(passCard.getStartDate());
    passCardDto.setExpirationDate(passCard.getExpirationDate());
    System.out.println("userId: " + passCard.getUserId());
    System.out.println("userDto: " + this.userService.getUser(passCard.getUserId()));
    passCardDto.setUser(this.userService.getUser(passCard.getUserId()));
    passCardDto.setDescription(passCard.getDescription());
    passCardDto.setCreateDate(passCard.getCreateDate());

    return passCardDto;
  }

  /**
   * Метод заполняет объект пропуска
   *
   * @param passCardDto
   *
   * @return
   */
  private PassCard getPassCard(PassCardDto passCardDto) {
    PassCard passCard = new PassCard();
    passCard.setId(passCardDto.getId());
    passCard.setNumber(passCardDto.getNumber());
    if (passCardDto.getReasons() != null && !passCardDto.getReasons().isEmpty()) {
      Set<ReasonCard> reasonCardSet = new HashSet<>();
      for (ReasonCardDto item : passCardDto.getReasons()) {
        Optional<ReasonCard> reasonCardOpt = this.reasonCardRepository.findById(item.getId());
        if (reasonCardOpt.isPresent()) {
          reasonCardSet.add(reasonCardOpt.get());
        }
      }

      passCard.setResons(reasonCardSet);
    }
    if (passCardDto.getScopes() != null && !passCardDto.getScopes().isEmpty()) {
      Set<ScopeCard> scopeCardSet = new HashSet<>();
      for (ScopeCardDto item : passCardDto.getScopes()) {
        Optional<ScopeCard> scopeCardOpt = this.scopeCardRepository.findById(item.getId());
        if (scopeCardOpt.isPresent()) {
          scopeCardSet.add(scopeCardOpt.get());
        }
      }

      passCard.setScopes(scopeCardSet);
    }
    if (passCardDto.getTypes() != null && !passCardDto.getTypes().isEmpty()) {
      Set<TypeCard> typeCardSet = new HashSet<>();
      for (TypeCardDto item : passCardDto.getTypes()) {
        Optional<TypeCard> typeCardOpt = this.typeCardRepository.findById(item.getId());
        if (typeCardOpt.isPresent()) {
          typeCardSet.add(typeCardOpt.get());
        }
      }

      passCard.setTypes(typeCardSet);
    }
//    if (passCardDto.getStatus() != null && passCardDto.getStatus().getName() != null) {
//      passCard.setStatusCard(StatusCard.valueOf(passCardDto.getStatus().getName()));
//    }
    passCard.setStatusCard(StatusCard.ACTIVE);
    passCard.setNumber(UUID.randomUUID());
    passCard.setUserId(passCardDto.getUser().getId());
    passCard.setExpirationDate(passCardDto.getExpDate());
    passCard.setStartDate(passCardDto.getStDate());
    passCard.setDescription(passCardDto.getDescription());
    passCard.setLastEditDate(LocalDateTime.now());
    passCard.setActive(true);

    return passCard;
  }

  /**
   * Метод заполняет DTO объект причины для выдачи пропуска
   *
   * @param reasonCard
   *
   * @return
   */
  private ReasonCardDto getReasonCardDto(ReasonCard reasonCard) {
    ReasonCardDto reasonCardDto = new ReasonCardDto();
    reasonCardDto.setId(reasonCard.getId());
    reasonCardDto.setName(reasonCard.getName());
    reasonCardDto.setDescription(reasonCard.getDescription());

    return reasonCardDto;
  }

  /**
   * Метод заполняет объект причины для выдачи пропуска
   *
   * @param reasonCardDto
   *
   * @return
   */
  private ReasonCard getReasonCard(ReasonCardDto reasonCardDto) {
    ReasonCard reasonCard = new ReasonCard();
    reasonCard.setId(reasonCardDto.getId());
    reasonCard.setName(reasonCardDto.getName());
    reasonCard.setDescription(reasonCardDto.getDescription());
    reasonCard.setLastEditDate(LocalDateTime.now());
    reasonCard.setActive(true);

    return reasonCard;
  }

  /**
   * Метод заполняет DTO объект области передвижения
   *
   * @param scopeCard
   *
   * @return
   */
  private ScopeCardDto getScopeCardDto(ScopeCard scopeCard) throws ValidationException {
    ScopeCardDto scopeCardDto = new ScopeCardDto();
    scopeCardDto.setId(scopeCard.getId());
    if (scopeCard.getCountryId() != null && scopeCard.getCountryId() != 0L) {
      scopeCardDto.setCountry(this.addressService.getCountry(scopeCard.getCountryId()));
    } else {
      throw new ValidationException("Для области передвижения, не задана страна");
    }
    if (scopeCard.getRegionId() != null && scopeCard.getRegionId() != 0L) {
      scopeCardDto.setRegion(this.addressService.getRegion(scopeCard.getRegionId()));
    } else {
      throw new ValidationException("Для области передвижения, не задана область");
    }
    if (scopeCard.getDistrictId() != null && scopeCard.getDistrictId() != 0L) {
      scopeCardDto.setDistrict(this.addressService.getDistrict(scopeCard.getDistrictId()));
    }
    if (scopeCard.getLocalityId() != null && scopeCard.getLocalityId() != 0L) {
      scopeCardDto.setLocality(this.addressService.getLocality(scopeCard.getLocalityId()));
    } else {
      throw new ValidationException("Для области передвижения, не задан населенный пункт");
    }
    scopeCardDto.setDescription(scopeCard.getDescription());

    return scopeCardDto;
  }

  /**
   * Метод заполняет объект области передвижения
   *
   * @param scopeCardDto
   *
   * @return
   */
  private ScopeCard getScopeCard(ScopeCardDto scopeCardDto) {
    ScopeCard scopeCard = new ScopeCard();
    scopeCard.setId(scopeCardDto.getId());
    if (scopeCardDto.getCountry() != null &&
        scopeCardDto.getCountry().getId() != null &&
        scopeCardDto.getCountry().getId() != 0L) {
      scopeCard.setCountryId(scopeCardDto.getCountry().getId());
    }
    if (scopeCardDto.getRegion() != null &&
        scopeCardDto.getRegion().getId() != null &&
        scopeCardDto.getRegion().getId() != 0L) {
      scopeCard.setRegionId(scopeCardDto.getRegion().getId());
    }
    if (scopeCardDto.getDistrict() != null &&
        scopeCardDto.getDistrict().getId() != null &&
        scopeCardDto.getDistrict().getId() != 0L) {
      scopeCard.setDistrictId(scopeCardDto.getDistrict().getId());
    }
    if (scopeCardDto.getLocality() != null &&
        scopeCardDto.getLocality().getId() != null &&
        scopeCardDto.getLocality().getId() != 0L) {
      scopeCard.setLocalityId(scopeCardDto.getLocality().getId());
    }
    scopeCard.setDescription(scopeCardDto.getDescription());
    scopeCard.setLastEditDate(LocalDateTime.now());
    scopeCard.setActive(true);

    return scopeCard;
  }


  /**
   * Метод заполняет DTO объект вида передвижения
   *
   * @param typeCard
   *
   * @return
   */
  private TypeCardDto getTypeCardDto(TypeCard typeCard) {
    TypeCardDto typeCardDto = new TypeCardDto();
    typeCardDto.setId(typeCard.getId());
    typeCardDto.setName(typeCard.getName());
    typeCardDto.setDescription(typeCard.getDescription());

    return typeCardDto;
  }

  /**
   * Метод заполняет объект вида передвижения
   *
   * @param typeCardDto
   *
   * @return
   */
  private TypeCard getTypeCard(TypeCardDto typeCardDto) {
    TypeCard typeCard = new TypeCard();
    typeCard.setId(typeCardDto.getId());
    typeCard.setName(typeCardDto.getName());
    typeCard.setDescription(typeCardDto.getDescription());
    typeCard.setLastEditDate(LocalDateTime.now());
    typeCard.setActive(true);

    return typeCard;
  }

  /**
   * Метод заполняет DTO объект статуса пропуска
   *
   * @param statusCard
   *
   * @return
   */
  private StatusCardDto getStatusCardDto(StatusCard statusCard) {
    StatusCardDto statusCardDto = new StatusCardDto();
    statusCardDto.setName(statusCard.getName());

    return statusCardDto;
  }
}
