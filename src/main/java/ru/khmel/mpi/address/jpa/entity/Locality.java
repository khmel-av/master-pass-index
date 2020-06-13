package ru.khmel.mpi.address.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Справочник населенных пунктов
 *
 * @author Khmel Anton
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Table(name = "locality", schema = "mpi")
public class Locality {
    private static final long serialVersionUID = 1L;

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    private Long id;
    /**
     * Наименование
     */
    @Column(name = "name")
    private String name;
    /**
     * Описание
     */
    @Column(name = "description")
    private String description;
    /**
     * Тип населенного пункта
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "type_locality_id")
    private TypeLocality typeLocality;
    /**
     * Идентификатор области
     */
    @Column(name = "region_id")
    private Long regionId;
    /**
     * Идентификатор района
     */
    @Column(name = "district_id")
    private Long districtId;
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
