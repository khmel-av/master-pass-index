package ru.khmel.mpi.user.service;

import ru.khmel.mpi.user.dto.AuthDto;
import ru.khmel.mpi.user.dto.UserDto;
import ru.khmel.mpi.exception.ValidationException;
import ru.khmel.mpi.user.jpa.entity.User;

import java.util.List;

/**
 * @author Khmel Anton
 */
public interface UserService {
  /**
   * Метод возвращает список пользователей
   *
   * @return
   */
  List<UserDto> getUserList();

  /**
   * Метод возвращает текущего пользователя
   *
   * @return
   */
  UserDto getUser(User user);

  /**
   * Метод возвращает пользователя по ID
   *
   * @param userId
   *
   * @return
   */
  User getUser(long userId);

  /**
   * Метод добавляет пользователя
   *
   * @param userDto
   *
   * @return
   *
   * @throws ValidationException
   */
  UserDto addUser(UserDto userDto) throws ValidationException;

  /**
   * Метод обновляет пользователя
   *
   * @param userDto
   *
   * @return
   *
   * @throws ValidationException
   */
  UserDto updateUser(UserDto userDto) throws ValidationException;

  /**
   * Метод проставляет флаг удаления для пользователя
   *
   * @throws ValidationException
   */
  void deleteUser() throws ValidationException;

  /**
   * Метод проставляет флаг удаления для пользователя
   *
   * @param userId
   *
   * @return
   *
   * @throws ValidationException
   */
  void deleteUser(long userId) throws ValidationException;

  /**
   * Метод возвращает токен при успешной аутентификации
   *
   * @param authDto
   *
   * @return
   *
   * @throws ValidationException
   */
  String getToken(AuthDto authDto) throws ValidationException;

  /**
   * Метод возвращает пользователя при успешной аутентификации
   *
   * @param authDto
   *
   * @return
   *
   * @throws ValidationException
   */
  UserDto getUser(AuthDto authDto) throws ValidationException;

  /**
   * Выход из системы
   */
  void logout();
}
