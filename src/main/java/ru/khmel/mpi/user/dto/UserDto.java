package ru.khmel.mpi.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.khmel.mpi.address.dto.AddressDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Пользователь
 *
 * @author Khmel Anton
 */
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String shortName;
    private String photoPath;
    private LocalDate birth;
    private String email;
    private AddressDto address;
    private List<String> roles;
    private String login;
    private String password;
    private LocalDateTime createDate;

    public String getBirth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return this.birth.format(formatter);
    }

    public LocalDate getBirthday() {
        return this.birth;
    }

    public String getCreateDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        if (this.createDate != null) {
            return this.createDate.format(formatter);
        } else {
            return null;
        }
    }
}
