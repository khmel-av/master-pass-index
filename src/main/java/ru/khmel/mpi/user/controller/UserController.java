package ru.khmel.mpi.user.controller;

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
import ru.khmel.mpi.user.dto.AuthDto;
import ru.khmel.mpi.user.dto.UserDto;
import ru.khmel.mpi.exception.ValidationException;
import ru.khmel.mpi.user.service.UserService;

import javax.validation.Valid;
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
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Метод возвращает список пользователей
     *
     * @return
     */
    @ApiOperation(value = "Список пользователей", notes = "Метод для получения списка пользователей", response = List.class)
    @RequestMapping(value = "/user/list", method= {GET})
    public List<UserDto> getUserList() {
        return this.userService.getUserList();
    }

    /**
     * Метод возвращает текущего пользователя
     *
     * @return
     */
    @ApiOperation(value = "Получить текущего пользователя", notes = "Метод для получения текущего пользователя", response = UserDto.class)
    @RequestMapping(value = "/user/current", method= {GET})
    public UserDto getCurrentUser() {
        return this.userService.getCurrentUser();
    }

    /**
     * Метод возвращает пользователя по ID
     *
     * @param userId
     *
     * @return
     */
    @ApiOperation(value = "Получить пользователя по ID", notes = "Метод для получения пользователя по ID", response = UserDto.class)
    @RequestMapping(value = "/user/get/{userId}", method= {GET})
    public UserDto getUser(@PathVariable("userId") long userId) {
        return this.userService.getUser(userId);
    }

    /**
     * Метод добавляет пользователя
     *
     * @param userDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Добавить пользователя", notes = "Метод для добавления пользователя", response = UserDto.class)
    @RequestMapping(value = "/user/add", method= {POST})
    public UserDto addUser(@RequestBody UserDto userDto) throws ValidationException {
        return this.userService.addUser(userDto);
    }

    /**
     * Метод обновляет пользователя
     *
     * @param userDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Обновить пользователя", notes = "Метод для обновления пользователя", response = UserDto.class)
    @RequestMapping(value = "/user/update", method= {POST})
    public UserDto updateUser(@RequestBody UserDto userDto) throws ValidationException {
        return this.userService.updateUser(userDto);
    }

    /**
     * Метод проставляет флаг удаления для пользователя
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Удалить текущего пользователя", notes = "Метод для удаления текущего пользователя", response = ResponseEntity.class)
    @RequestMapping(value = "/user/delete", method= {GET})
    public ResponseEntity<?> deleteUser() throws ValidationException {
        this.userService.deleteUser();

        return ResponseEntity.ok().build();
    }

    /**
     * Метод проставляет флаг удаления для пользователя
     *
     * @param userId
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Удалить пользователя", notes = "Метод для удаления пользователя", response = ResponseEntity.class)
    @RequestMapping(value = "/user/delete/{userId}", method= {GET})
    public ResponseEntity<?> deleteUser(@PathVariable("userId") long userId) throws ValidationException {
        this.userService.deleteUser(userId);

        return ResponseEntity.ok().build();
    }

    /**
     * Метод возвращает токен при успешной аутентификации
     *
     * @param authDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Аутентификация и получение токена", notes = "Метод для аутентификации и получения токена", response = String.class)
    @RequestMapping(value = "/user/public/auth", method= {POST})
    public UserDto getAuth(@Valid @RequestBody AuthDto authDto) throws ValidationException {
        return this.userService.getUser(authDto);
    }

    /**
     * Выход из системы
     */
    @ApiOperation(value = "Выход из системы", notes = "Метод для выхода из системы", response = ResponseEntity.class)
    @RequestMapping(value = "/user/logout", method= {GET})
    public ResponseEntity<?> logout() {
        this.userService.logout();

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
