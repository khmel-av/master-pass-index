package ru.khmel.mpi.user.jpa.entity;

import lombok.*;
import ru.khmel.mpi.user.jpa.entity.ERole;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Роль пользователя
 *
 * @author Khmel Anton
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Table(name = "role", schema = "mpi")
public class Role
//        implements GrantedAuthority
{
    private static final long serialVersionUID = 1L;

    /**
     * Идентификатор
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Наименование
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
    /**
     * Список пользователей
     */
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

//    @Override
    public String getAuthority() {
        return getName().name();
    }
}
