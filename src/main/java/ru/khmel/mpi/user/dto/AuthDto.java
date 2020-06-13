package ru.khmel.mpi.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Аутентификация
 *
 * @author Khmel Anton
 */
@Data
@EqualsAndHashCode(of = "login")
@NoArgsConstructor
public class AuthDto {
  @NotBlank(message = "Не заполнено поле login")
  @Size(min = 6, max = 20, message = "Поле OrderNum должно быть длиной не менее 6 и не более 20 символов")
  private String login;
  @NotBlank(message = "Не заполнено поле password")
  @Size(min = 6, max = 20, message = "Поле password должно быть длиной не менее 6 и не более 20 символов")
  private String password;
}
