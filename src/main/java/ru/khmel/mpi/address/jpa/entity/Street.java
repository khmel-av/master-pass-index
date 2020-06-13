package ru.khmel.mpi.address.jpa.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Справочник улиц
 *
 * @author Khmel Anton
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Table(name = "street", schema = "mpi")
public class Street {
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
     * Идентификатор населенного пункта
     */
    @Column(name = "locality_id")
    private long localityId;
    /**
     * Идентификатор района населенного пункта
     */
    @Column(name = "district_locality_id")
    private long districtLocalityId;
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
