package ru.khmel.mpi.user.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.khmel.mpi.address.dto.AddressDto;
import ru.khmel.mpi.user.dto.AuthDto;
import ru.khmel.mpi.user.dto.UserDto;
import ru.khmel.mpi.exception.ValidationException;
import ru.khmel.mpi.user.jpa.entity.ERole;
import ru.khmel.mpi.user.jpa.entity.Role;
import ru.khmel.mpi.user.jpa.entity.User;
import ru.khmel.mpi.user.jpa.repository.RoleRepository;
import ru.khmel.mpi.user.jpa.repository.UserRepository;
import ru.khmel.mpi.address.service.AddressService;
import ru.khmel.mpi.user.service.UserService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Сервис для работы с пользователем
 *
 * @author Khmel Anton
 */
@Service
//@AllArgsConstructor
public class UserServiceImpl implements UserService {
  @Value(value = "${Security.AccessTokenValidityMinit}")
  private long accessTokenValidityMinit;
  @Value(value = "${Security.SigningKey}")
  private String signingKey;

  @Autowired
  private final UserRepository userRepository;
  @Autowired
  private final RoleRepository roleRepository;
  @Autowired
  private AddressService addressService;

  public UserServiceImpl(final UserRepository userRepository,
                         final RoleRepository roleRepository) {
    super();
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
  }

  /**
   * @Inherited
   */
  @Override
  public List<UserDto> getUserList() {
    List<User> userList = this.userRepository.findAll();
    Function<User, UserDto> mapUser = p -> {
      return getUserDto(p);
    };

    return userList.stream()
        .map(mapUser)
        .collect(Collectors.toList());
  }

  /**
   * @Inherited
   */
  @Override
  public UserDto getCurrentUser() {
    long userId = 0L;
    Optional<User> userOpt = this.userRepository.findById(userId);
    if (userOpt.isPresent()) {
      return getUserDto(userOpt.get());
    }

    return null;
  }

  /**
   * @Inherited
   */
  @Override
  public UserDto getUser(long userId) {
    Optional<User> userOpt = this.userRepository.findById(userId);
    if (userOpt.isPresent()) {
      return getUserDto(userOpt.get());
    }

    return null;
  }

  /**
   * @Inherited
   */
  @Override
  public UserDto addUser(UserDto userDto) throws ValidationException {
//    List<User> userList = this.userRepository.findByUsername(userDto.getFirstName());
//    if (!userList.isEmpty()) {
//      throw new ValidationException("Пользователь: " + userDto.getFirstName() +
//          " уже существует");
//    }
    AddressDto addressDto = this.addressService.addAddress(userDto.getAddress());

    User user = getUser(userDto);
    user.setId(null);
    user.setAddressId(addressDto.getId());
    user.setCreateDate(LocalDateTime.now());
    Optional<Role> roleOpt = this.roleRepository.findById(3L);
    if (roleOpt.isPresent()) {
      Set<Role> roles = new HashSet<Role>();
      roles.add(roleOpt.get());

      user.setRoles(roles);
    }


    this.userRepository.save(user);

    return getUserDto(user);
  }

  /**
   * @Inherited
   */
  @Override
  public UserDto updateUser(UserDto userDto) throws ValidationException {
    Optional<User> userOpt = this.userRepository.findById(userDto.getId());
    if (!userOpt.isPresent()) {
      throw new ValidationException("Пользователь: " + userDto.getFirstName() +
          " для обновления не найден");
    }
    User user = getUser(userDto);

    this.userRepository.save(user);

    return getUserDto(user);
  }

  /**
   * @Inherited
   */
  @Override
  public void deleteUser() throws ValidationException {
    long userId = 0L;
    Optional<User> userOpt = this.userRepository.findById(userId);
    if (!userOpt.isPresent()) {
      throw new ValidationException("Пользователь, по ID: " + userId +
          " для удаления не найден");
    }
    User user = userOpt.get();
    user.setActive(false);
    user.setLastEditDate(LocalDateTime.now());

    this.userRepository.save(user);
  }

  /**
   * @Inherited
   */
  @Override
  public void deleteUser(long userId) throws ValidationException {
    Optional<User> userOpt = this.userRepository.findById(userId);
    if (!userOpt.isPresent()) {
      throw new ValidationException("Пользователь, по ID: " + userId +
          " для удаления не найден");
    }
    User user = userOpt.get();
    user.setActive(false);
    user.setLastEditDate(LocalDateTime.now());

    this.userRepository.save(user);
  }

  /**
   * @Inherited
   */
  @Override
  public String getAuth(AuthDto authDto) throws ValidationException {
    List<User> userList = this.userRepository
        .findByUsernameAndPassword(authDto.getLogin(), authDto.getPassword());
    if (userList != null && !userList.isEmpty()) {
      return generateToken(getUserDto(userList.get(0)).getId());
    }

    return null;
  }

  /**
   * @Inherited
   */
  @Override
  public UserDto getUser(AuthDto authDto) throws ValidationException {
    List<User> userList = this.userRepository
        .findByUsernameAndPassword(authDto.getLogin(), authDto.getPassword());
    if (userList != null && !userList.isEmpty()) {
      return getUserDto(userList.get(0));
    }

    return null;
  }

  /**
   * @Inherited
   */
  @Override
  public void logout() {

  }

  private UserDto getUserDto(User user) {
    UserDto userDto = new UserDto();
    userDto.setId(user.getId());
    userDto.setFirstName(user.getFirstName());
    userDto.setLastName(user.getLastName());
    userDto.setMiddleName(user.getMiddleName());
    userDto.setShortName(getShortName(user.getFirstName(),
        user.getLastName(), user.getMiddleName()));
    userDto.setBirth(user.getBirth());
    userDto.setEmail(user.getEmail());
    userDto.setAddress(this.addressService.getAddress(user.getAddressId()));
    userDto.setRoles(getRoleDtoList(user.getRoles()));
    userDto.setCreateDate(user.getCreateDate());

    return userDto;
  }

  private String getShortName(String firstName, String lastName,
                              String middleName) {
    StringBuilder sb = new StringBuilder();
    sb.append(lastName);
    sb.append(" ");
    sb.append(firstName.substring(0, 1));
    sb.append(".");
    if (middleName != null && !middleName.isEmpty()) {
      sb.append(middleName.substring(0, 1));
      sb.append(".");
    }
    return sb.toString();
  }

  private List<String> getRoleDtoList(Set<Role> roles) {
    List<String> roleDtoList = new ArrayList();
    for (Role item : roles) {
      if (item.getName() == ERole.ROLE_USER) {
        roleDtoList.add("user");
      } else if (item.getName() == ERole.ROLE_SUB_ADMIN) {
        roleDtoList.add("sub_admin");
      } else if (item.getName() == ERole.ROLE_ADMIN) {
        roleDtoList.add("admin");
      }
    }

    return roleDtoList;
  }

  private User getUser(UserDto userDto) {
    User user = new User();
    user.setId(userDto.getId());
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setMiddleName(userDto.getMiddleName());
    user.setBirth(userDto.getBirthday());
    user.setEmail(userDto.getEmail());
    user.setUsername(userDto.getLogin());
    user.setPassword(userDto.getPassword());
    user.setLastEditDate(LocalDateTime.now());
    user.setActive(true);

    return user;
  }

  /**
   * @param userId
   *
   * @return
   * Генерация токена
   */
  private String generateToken(Long userId) {
    Map<String, Object> tokenData = new HashMap<>();
    tokenData.put("userId", userId);

    return Jwts.builder()
        .setClaims(tokenData)
        .setIssuedAt(new Timestamp(System.currentTimeMillis()))
        .setExpiration(new Timestamp(System.currentTimeMillis() + accessTokenValidityMinit * 60 * 1000))
        .signWith(SignatureAlgorithm.HS256, signingKey)
        .compact();
  }
}
