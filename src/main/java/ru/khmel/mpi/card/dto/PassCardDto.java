package ru.khmel.mpi.card.dto;

import lombok.*;
import ru.khmel.mpi.user.dto.UserDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * Пропуск
 *
 * @author Khmel Anton
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class PassCardDto {
    private Long id;
    private UUID number;
    private List<ReasonCardDto> reasons;
    private List<ScopeCardDto> scopes;
    private List<TypeCardDto> types;
    private StatusCardDto status;
    private LocalDateTime startDate;
    private LocalDateTime expirationDate;
    private UserDto user;
    private String description;
    private LocalDateTime createDate;

    public String getStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        return this.startDate.format(formatter);
    }

    public LocalDateTime getStDate() {
        return this.startDate;
    }

    public String getExpirationDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        return this.expirationDate.format(formatter);
    }

    public LocalDateTime getExpDate() {
        return this.expirationDate;
    }

    public String getCreateDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        return this.createDate.format(formatter);
    }
}
