package ru.khmel.mpi.card.service;

import ru.khmel.mpi.card.dto.PassCardDto;
import ru.khmel.mpi.card.dto.ReasonCardDto;
import ru.khmel.mpi.card.dto.ScopeCardDto;
import ru.khmel.mpi.card.dto.StatusCardDto;
import ru.khmel.mpi.card.dto.TypeCardDto;
import ru.khmel.mpi.exception.ValidationException;

import java.util.List;

/**
 * @author Khmel Anton
 */
public interface PassCardService {
  /**
   * Метод возвращает список пропусков для пользователя
   *
   * @return
   */
  List<PassCardDto> getPassCardList(long userId);

  /**
   * Метод возвращает список пропусков для пользователя
   *
   * @return
   */
  PassCardDto getPassCardByNumber(String number);

  /**
   * Метод возвращает список причин для выдачи пропуска по части названия
   *
   * @param reasonCardName
   *
   * @return
   */
  List<ReasonCardDto> getReasonCardList(String reasonCardName);

  /**
   * Метод возвращает список областей передвижения для пользователя
   *
   * @return
   */
  List<ScopeCardDto> getScopeCardList();

  /**
   * Метод возвращает список статусов пропуска
   *
   * @return
   */
  List<StatusCardDto> getStatusCardList();

  /**
   * Метод возвращает список видов передвижения по части названия
   *
   * @param typeCardName
   *
   * @return
   */
  List<TypeCardDto> getTypeCardList(String typeCardName);

  /**
   * Метод возвращает причину для выдачи пропуска по ID
   *
   * @param reasonCardId
   *
   * @return
   */
  ReasonCardDto getReasonCard(long reasonCardId);

  /**
   * Метод добавляет причину для выдачи пропуска
   *
   * @param reasonCardDto
   *
   * @return
   *
   * @throws ValidationException
   */
  ReasonCardDto addReasonCard(ReasonCardDto reasonCardDto) throws ValidationException;

  /**
   * Метод обновляет причину для выдачи пропуска
   *
   * @param reasonCardDto
   *
   * @return
   *
   * @throws ValidationException
   */
  ReasonCardDto updateReasonCard(ReasonCardDto reasonCardDto) throws ValidationException;

  /**
   * Метод проставляет флаг удаления для причину для выдачи пропуска
   *
   * @param reasonCardId
   *
   * @throws ValidationException
   */
  void deleteReasonCard(long reasonCardId) throws ValidationException;

  /**
   * Метод возвращает область передвижения для пользователя по ID
   *
   * @param scopeCardId
   *
   * @return
   */
  ScopeCardDto getScopeCard(long scopeCardId);

  /**
   * Метод добавляет область передвижения для пользователя
   *
   * @param scopeCardDto
   *
   * @return
   *
   * @throws ValidationException
   */
  ScopeCardDto addScopeCard(ScopeCardDto scopeCardDto) throws ValidationException;

  /**
   * Метод обновляет область передвижения для пользователя
   *
   * @param scopeCardDto
   *
   * @return
   *
   * @throws ValidationException
   */
  ScopeCardDto updateScopeCard(ScopeCardDto scopeCardDto) throws ValidationException;

  /**
   * Метод проставляет флаг удаления для области передвижения для пользователя
   *
   * @param scopeCardId
   *
   * @throws ValidationException
   */
  void deleteScopeCard(long scopeCardId) throws ValidationException;

  /**
   * Метод возвращает вид передвижения по ID
   *
   * @param typeCardId
   *
   * @return
   */
  TypeCardDto getTypeCard(long typeCardId);

  /**
   * Метод добавляет вид передвижения
   *
   * @param typeCardDto
   *
   * @return
   *
   * @throws ValidationException
   */
  TypeCardDto addTypeCard(TypeCardDto typeCardDto) throws ValidationException;

  /**
   * Метод обновляет вид передвижения
   *
   * @param typeCardDto
   *
   * @return
   *
   * @throws ValidationException
   */
  TypeCardDto updateTypeCard(TypeCardDto typeCardDto) throws ValidationException;

  /**
   * Метод проставляет флаг удаления для вида передвижения
   *
   * @param typeCardId
   *
   * @throws ValidationException
   */
  void deleteTypeCard(long typeCardId) throws ValidationException;

  /**
   * Метод возвращает пропуск по ID
   *
   * @param passCardId
   *
   * @return
   */
  PassCardDto getPassCard(long passCardId);

  /**
   * Метод добавляет пропуск для пользователя
   *
   * @param passCardDto
   *
   * @return
   *
   * @throws ValidationException
   */
  PassCardDto addPassCard(PassCardDto passCardDto) throws ValidationException;

  /**
   * Метод обновляет пропуск для пользователя
   *
   * @param passCardDto
   *
   * @return
   *
   * @throws ValidationException
   */
  PassCardDto updatePassCard(PassCardDto passCardDto) throws ValidationException;

  /**
   * Метод проставляет флаг удаления для пропуска для пользователя
   *
   * @param passCardId
   *
   * @throws ValidationException
   */
  void deletePassCard(long passCardId) throws ValidationException;
}
