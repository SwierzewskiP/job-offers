package pl.joboffers.domain.login;

import lombok.AllArgsConstructor;
import pl.joboffers.domain.login.dto.RegisterUserDto;
import pl.joboffers.domain.login.dto.RegistrationResultDto;
import pl.joboffers.domain.login.dto.UserDto;

@AllArgsConstructor
public class LoginAndRegisterFacade {

    public static final String USER_NOT_FOUND = "User not found";

    private final LoginRepository repository;

    public UserDto findByUserName(String username) {
        return repository.findByUsername(username)
                .map(UserMapper::mapToDto)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        }

    public RegistrationResultDto register(RegisterUserDto registerUserDto) {
        final User user = User.builder()
                .username(registerUserDto.username())
                .password(registerUserDto.password())
                .build();
        User savedUser = repository.save(user);
        return new RegistrationResultDto(savedUser.id(), true, savedUser.username());
    }
}