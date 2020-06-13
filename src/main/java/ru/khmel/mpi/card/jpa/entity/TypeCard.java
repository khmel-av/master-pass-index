package ru.khmel.mpi.card.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Справочник видов передвижения
 *
 * @author Khmel Anton
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Table(name = "type_card", schema = "mpi")
public class TypeCard {
    private static final long serialVersionUID = 1L;

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    private Long id;
    /**
     * Наименование вида передвижения
     */
    @Column(name = "name")
    private String name;
    /**
     * Описание
     */
    @Column(name = "description")
    private String description;
    /**
     * Дата и время последнего изменения
     */
    @Column(name = "last_edit_date")
    private LocalDateTime LastEditDate;
    /**
     * Дата создания
     */
    @Column(name = "create_date")
    private LocalDateTime createDate;
    /**
     * Флаг удаления
     */
    @Column(name = "active")
    private Boolean active;
}
