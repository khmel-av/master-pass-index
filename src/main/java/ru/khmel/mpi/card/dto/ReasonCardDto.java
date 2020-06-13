package ru.khmel.mpi.card.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Причина для выдачи пропуска
 *
 * @author Khmel Anton
 */
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class ReasonCardDto {
    private Long id;
    private String name;
    private String description;
}
