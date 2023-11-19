package pl.swierzewski.domain.login;

import pl.swierzewski.domain.login.dto.UserDto;

public class UserMapper {

    static UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.id())
                .name(user.username())
                .password(user.password())
                .build();
    }
}
