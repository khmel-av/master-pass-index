package ru.khmel.mpi.card.jpa.entity;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;

/**
 * Область передвижения
 *
 * @author Khmel Anton
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Table(name = "scope_card", schema = "mpi")
public class ScopeCard {
    private static final long serialVersionUID = 1L;

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    private Long id;
    /**
     * Идентификатор страны
     */
    @JoinColumn(name = "country_id")
    private Long countryId;
    /**
     * Идентификатор области
     */
    @JoinColumn(name = "region_id")
    private Long regionId;
    /**
     * Идентификатор района
     */
    @JoinColumn(name = "district_id")
    private Long districtId;
    /**
     * Идентификатор населенного пункта
     */
    @JoinColumn(name = "locality_id")
    private Long localityId;
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
