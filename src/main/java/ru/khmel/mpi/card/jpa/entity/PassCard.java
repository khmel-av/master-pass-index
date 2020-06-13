package ru.khmel.mpi.card.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * Пропуск
 *
 * @author Khmel Anton
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "pass_card", schema = "mpi")
public class PassCard {
    private static final long serialVersionUID = 1L;

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * Номер
     */
    @Column(name = "number")
    private UUID number;
    /**
     * Список причин для выдачи пропуска
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "pass_card_reason_card",
        joinColumns = @JoinColumn(name = "pass_card_id"),
        inverseJoinColumns = @JoinColumn(name = "reason_card_id"))
    private Set<ReasonCard> resons;
    /**
     * Список территорий для передвижения
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "pass_card_scope_card",
        joinColumns = @JoinColumn(name = "pass_card_id"),
        inverseJoinColumns = @JoinColumn(name = "scope_card_id"))
    private Set<ScopeCard> scopes;
    /**
     * Список видов передвижения
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "pass_card_type_card",
        joinColumns = @JoinColumn(name = "pass_card_id"),
        inverseJoinColumns = @JoinColumn(name = "type_card_id"))
    private Set<TypeCard> types;
    /**
     * Статус
     */
    @Column(name = "status_card_id")
    private StatusCard statusCard;
    /**
     * Идентификатор пользователя
     */
    @Column(name = "user_id")
    private Long userId;
    /**
     * Дата и время окончания
     */
    @Column(name = "start_date")
    private LocalDateTime startDate;
    /**
     * Дата и время окончания
     */
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;
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
     * Дата и время создания
     */
    @Column(name = "create_date")
    private LocalDateTime createDate;
    /**
     * Флаг удаления
     */
    @Column(name = "active")
    private Boolean active;
}
