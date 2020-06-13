package ru.khmel.mpi.card.controller;

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
import ru.khmel.mpi.card.dto.PassCardDto;
import ru.khmel.mpi.card.dto.ReasonCardDto;
import ru.khmel.mpi.card.dto.ScopeCardDto;
import ru.khmel.mpi.card.dto.StatusCardDto;
import ru.khmel.mpi.card.dto.TypeCardDto;
import ru.khmel.mpi.exception.ValidationException;
import ru.khmel.mpi.card.service.PassCardService;

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
public class PassCardController {
    @Autowired
    private PassCardService passCardService;

    /**
     * Метод возвращает список пропусков для пользователя
     *
     * @return
     */
    @ApiOperation(value = "Список пропусков", notes = "Метод для получения списка пропусков", response = List.class)
    @RequestMapping(value = "/pass/card/list/{userId}", method= {GET})
    public List<PassCardDto> getPassCardList(@PathVariable("userId") long userId) {
        return this.passCardService.getPassCardList(userId);
    }

    /**
     * Метод возвращает пропуск по номеру
     *
     * @return
     */
    @ApiOperation(value = "Пропуск по номеру", notes = "Метод для получения пропуска по номеру", response = PassCardDto.class)
    @RequestMapping(value = "/pass/card/{number}", method= {GET})
    public PassCardDto getPassCardByNumber(@PathVariable("number") String number) {
        return this.passCardService.getPassCardByNumber(number);
    }

    /**
     * Метод возвращает список причин для выдачи пропуска по части названия
     *
     * @param reasonCardName
     *
     * @return
     */
    @ApiOperation(value = "Список причин для выдачи пропуска", notes = "Метод для получения списка причин для выдачи пропуска", response = List.class)
    @RequestMapping(value = "/reason/card/list/{reasonCardName}", method= {GET})
    public List<ReasonCardDto> getReasonCardList(@PathVariable("reasonCardName") String reasonCardName) {
        return this.passCardService.getReasonCardList(reasonCardName);
    }

    /**
     * Метод возвращает список областей передвижения для пользователя
     *
     * @return
     */
    @ApiOperation(value = "Список областей передвижения", notes = "Метод для получения списка областей передвижения", response = List.class)
    @RequestMapping(value = "/scope/card/list", method= {GET})
    public List<ScopeCardDto> getScopeCardList() {
        return this.passCardService.getScopeCardList();
    }

    /**
     * Метод возвращает список статусов пропуска
     *
     * @return
     */
    @ApiOperation(value = "Список статусов", notes = "Метод для получения списка статусов", response = List.class)
    @RequestMapping(value = "/status/card/list", method= {GET})
    public List<StatusCardDto> getStatusCardList() {
        return this.passCardService.getStatusCardList();
    }

    /**
     * Метод возвращает список видов передвижения по части названия
     *
     * @param typeCardName
     *
     * @return
     */
    @ApiOperation(value = "Список видов передвижения", notes = "Метод для получения списка видов передвижения", response = List.class)
    @RequestMapping(value = "/type/card/list/{typeCardName}", method= {GET})
    public List<TypeCardDto> getTypeCardList(@PathVariable("typeCardName") String typeCardName) {
        return this.passCardService.getTypeCardList(typeCardName);
    }

    /**
     * Метод возвращает причину для выдачи пропуска по ID
     *
     * @param reasonCardId
     *
     * @return
     */
    @ApiOperation(value = "Получить причину для выдачи пропуска по ID", notes = "Метод для получения причину для выдачи пропуска по ID", response = ReasonCardDto.class)
    @RequestMapping(value = "/reason/card/get/{reasonCardId}", method= {GET})
    public ReasonCardDto getReasonCard(@PathVariable("reasonCardId") long reasonCardId) {
        return this.passCardService.getReasonCard(reasonCardId);
    }

    /**
     * Метод добавляет причину для выдачи пропуска
     *
     * @param reasonCardDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Добавить причину для выдачи пропуска", notes = "Метод для добавления причину для выдачи пропуска", response = ReasonCardDto.class)
    @RequestMapping(value = "/reason/card/add", method= {POST})
    public ReasonCardDto addReasonCard(@RequestBody ReasonCardDto reasonCardDto) throws ValidationException {
        return this.passCardService.addReasonCard(reasonCardDto);
    }

    /**
     * Метод обновляет причину для выдачи пропуска
     *
     * @param reasonCardDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Обновить причину для выдачи пропуска", notes = "Метод для обновления причину для выдачи пропуска", response = ReasonCardDto.class)
    @RequestMapping(value = "/reason/card/update", method= {POST})
    public ReasonCardDto updateReasonCard(@RequestBody ReasonCardDto reasonCardDto) throws ValidationException {
        return this.passCardService.updateReasonCard(reasonCardDto);
    }

    /**
     * Метод проставляет флаг удаления для причину для выдачи пропуска
     *
     * @param reasonCardId
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Удалить причину для выдачи пропуска", notes = "Метод для удаления причину для выдачи пропуска", response = ResponseEntity.class)
    @RequestMapping(value = "/reason/card/delete/{reasonCardId}", method= {GET})
    public ResponseEntity<?> deleteReasonCard(@PathVariable("reasonCardId") long reasonCardId) throws ValidationException {
        this.passCardService.deleteReasonCard(reasonCardId);

        return ResponseEntity.ok().build();
    }

    /**
     * Метод возвращает область передвижения для пользователя по ID
     *
     * @param scopeCardId
     *
     * @return
     */
    @ApiOperation(value = "Получить передвижения для пользователя по ID", notes = "Метод для получения передвижения для пользователя по ID", response = ScopeCardDto.class)
    @RequestMapping(value = "/scope/card/get/{scopeCardId}", method= {GET})
    public ScopeCardDto getScopeCard(@PathVariable("scopeCardId") long scopeCardId) {
        return this.passCardService.getScopeCard(scopeCardId);
    }

    /**
     * Метод добавляет область передвижения для пользователя
     *
     * @param scopeCardDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Добавить область передвижения для пользователя", notes = "Метод для добавления область передвижения для пользователя", response = ScopeCardDto.class)
    @RequestMapping(value = "/scope/card/add", method= {POST})
    public ScopeCardDto addScopeCard(@RequestBody ScopeCardDto scopeCardDto) throws ValidationException {
        return this.passCardService.addScopeCard(scopeCardDto);
    }

    /**
     * Метод обновляет область передвижения для пользователя
     *
     * @param scopeCardDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Обновить область передвижения для пользователя", notes = "Метод для обновления области передвижения для пользователя", response = ScopeCardDto.class)
    @RequestMapping(value = "/scope/card/update", method= {POST})
    public ScopeCardDto updateScopeCard(@RequestBody ScopeCardDto scopeCardDto) throws ValidationException {
        return this.passCardService.updateScopeCard(scopeCardDto);
    }

    /**
     * Метод проставляет флаг удаления для области передвижения для пользователя
     *
     * @param scopeCardId
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Удалить область передвижения для пользователя", notes = "Метод для удаления области передвижения для пользователя", response = ResponseEntity.class)
    @RequestMapping(value = "/scope/card/delete/{scopeCardId}", method= {GET})
    public ResponseEntity<?> deleteScopeCard(@PathVariable("scopeCardId") long scopeCardId) throws ValidationException {
        this.passCardService.deleteScopeCard(scopeCardId);

        return ResponseEntity.ok().build();
    }

    /**
     * Метод возвращает вид передвижения по ID
     *
     * @param typeCardId
     *
     * @return
     */
    @ApiOperation(value = "Получить вид передвижения по ID", notes = "Метод для получения вида передвижения по ID", response = TypeCardDto.class)
    @RequestMapping(value = "/type/card/get/{typeCardId}", method= {GET})
    public TypeCardDto getTypeCard(@PathVariable("typeCardId") long typeCardId) {
        return this.passCardService.getTypeCard(typeCardId);
    }

    /**
     * Метод добавляет вид передвижения
     *
     * @param typeCardDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Добавить вид передвижения", notes = "Метод для добавления вида передвижения", response = TypeCardDto.class)
    @RequestMapping(value = "/type/card/add", method= {POST})
    public TypeCardDto addTypeCard(@RequestBody TypeCardDto typeCardDto) throws ValidationException {
        return this.passCardService.addTypeCard(typeCardDto);
    }

    /**
     * Метод обновляет вид передвижения
     *
     * @param typeCardDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Обновить вид передвижения", notes = "Метод для обновления вида передвижения", response = TypeCardDto.class)
    @RequestMapping(value = "/type/card/update", method= {POST})
    public TypeCardDto updateTypeCard(@RequestBody TypeCardDto typeCardDto) throws ValidationException {
        return this.passCardService.updateTypeCard(typeCardDto);
    }

    /**
     * Метод проставляет флаг удаления для вида передвижения
     *
     * @param typeCardId
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Удалить вид передвижения", notes = "Метод для удаления вида передвижения", response = ResponseEntity.class)
    @RequestMapping(value = "/type/card/delete/{typeCardId}", method= {GET})
    public ResponseEntity<?> deleteTypeCard(@PathVariable("typeCardId") long typeCardId) throws ValidationException {
        this.passCardService.deleteTypeCard(typeCardId);

        return ResponseEntity.ok().build();
    }

    /**
     * Метод возвращает пропуск по ID
     *
     * @param passCardId
     *
     * @return
     */
    @ApiOperation(value = "Получить пропуск по ID", notes = "Метод для получения пропуска по ID", response = PassCardDto.class)
    @RequestMapping(value = "/pass/card/get/{passCardId}", method= {GET})
    public PassCardDto getPassCard(@PathVariable("passCardId") long passCardId) {
        return this.passCardService.getPassCard(passCardId);
    }

    /**
     * Метод добавляет пропуск для пользователя
     *
     * @param passCardDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Добавить пропуск", notes = "Метод для добавления пропуска", response = PassCardDto.class)
    @RequestMapping(value = "/pass/card/add", method= {POST})
    public PassCardDto addPassCard(@RequestBody PassCardDto passCardDto) throws ValidationException {
        return this.passCardService.addPassCard(passCardDto);
    }

    /**
     * Метод обновляет пропуск для пользователя
     *
     * @param passCardDto
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Обновить пропуск", notes = "Метод для обновления пропуска", response = PassCardDto.class)
    @RequestMapping(value = "/pass/card/update", method= {POST})
    public PassCardDto updatePassCard(@RequestBody PassCardDto passCardDto) throws ValidationException {
        return this.passCardService.updatePassCard(passCardDto);
    }

    /**
     * Метод проставляет флаг удаления для пропуска для пользователя
     *
     * @param passCardId
     *
     * @return
     *
     * @throws ValidationException
     */
    @ApiOperation(value = "Удалить пропуск", notes = "Метод для удаления пропуска", response = ResponseEntity.class)
    @RequestMapping(value = "/pass/card/delete/{passCardId}", method= {GET})
    public ResponseEntity<?> deletePassCard(@PathVariable("passCardId") long passCardId) throws ValidationException {
        this.passCardService.deletePassCard(passCardId);

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
