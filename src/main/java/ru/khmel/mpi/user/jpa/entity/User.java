package ru.khmel.mpi.user.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Пользователи
 *
 * @author Khmel Anton
 */
@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Table(name = "user", schema = "mpi")
public class User implements UserDetails {
  private static final long serialVersionUID = 1L;

  /**
   * Идентификатор
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  /**
   * Логин
   */
  @Size(min=6, message = "Не меньше 6 знаков")
  @Column(name = "username")
  private String username;
  /**
   * Пароль
   */
  @Size(min=6, message = "Не меньше 6 знаков")
  @Column(name = "password")
  private String password;
  /**
   * Роли
   */
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;
  /**
   * Имя
   */
  @Column(name = "first_name")
  private String firstName;
  /**
   * Фамилия
   */
  @Column(name = "last_name")
  private String lastName;
  /**
   * Отчество
   */
  @Column(name = "middle_name")
  private String middleName;
  /**
   * Путь к фотографии
   */
  @Column(name = "photo_path")
  private String photoPath;
  /**
   * Дата рождения
   */
  @Column(name = "birth")
  private LocalDate birth;
  /**
   * Электронная почта
   */
  @Column(name = "email")
  private String email;
  /**
   * Идентификатор адреса
   */
  @Column(name = "address_id")
  private Long addressId;
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

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles.stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());
  }

  //    @Override
  public String getPassword() {
    return password;
  }
}
