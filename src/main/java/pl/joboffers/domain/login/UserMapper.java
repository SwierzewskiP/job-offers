package pl.joboffers.domain.login;

import pl.joboffers.domain.login.dto.UserDto;

public class UserMapper {

    static UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.id())
                .name(user.username())
                .password(user.password())
                .build();
    }
}
