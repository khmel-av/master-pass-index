package ru.khmel.mpi.address.jpa.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Адрес
 *
 * @author Khmel Anton
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Table(name = "address", schema = "mpi")
public class Address {
    private static final long serialVersionUID = 1L;

    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * Идентификатор района населенного пункта
     */
    @JoinColumn(name = "district_locality_id")
    private Long districtLocalityId;
    /**
     * Идентификатор улицы
     */
    @JoinColumn(name = "street_id")
    private Long streetId;
    /**
     * Дом
     */
    @Column(name = "home")
    private String home;
    /**
     * Корпус/строение
     */
    @Column(name = "bild")
    private String bild;
    /**
     * Квартира
     */
    @Column(name = "flat")
    private String flat;
    /**
     * Комната
     */
    @Column(name = "room")
    private String room;
    /**
     * Индекс
     */
    @Column(name = "zipcode")
    private String zipcode;
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
