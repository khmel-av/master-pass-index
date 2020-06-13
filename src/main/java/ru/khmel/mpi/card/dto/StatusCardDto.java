package ru.khmel.mpi.card.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Статус пропуска
 *
 * @author Khmel Anton
 */
@Getter
@Setter
@EqualsAndHashCode(of = "name")
@NoArgsConstructor
public class StatusCardDto {
    private String name;
}
