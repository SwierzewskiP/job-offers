package pl.joboffers.domain.login;

import org.junit.jupiter.api.Test;
import pl.joboffers.domain.login.dto.RegisterUserDto;
import pl.joboffers.domain.login.dto.RegistrationResultDto;
import pl.joboffers.domain.login.dto.UserDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.ThrowableAssert.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertAll;

class LoginAndRegisterFacadeTest {

    LoginAndRegisterFacade loginFacade = new LoginAndRegisterFacade(new InMemoryLoginRepository());

    @Test
    void should_register_user() {
        // given
        RegisterUserDto registerUserDto = new RegisterUserDto("username", "pass");

        // when
        RegistrationResultDto register = loginFacade.register(registerUserDto);

        // then
        assertAll(
                () -> assertThat(register.created()).isTrue(),
                () -> assertThat(register.username()).isEqualTo("username")
        );
    }

    @Test
    void should_find_user_by_user_name() {
        // given
        RegisterUserDto registerUserDto = new RegisterUserDto("username", "pass");
        RegistrationResultDto register = loginFacade.register(registerUserDto);

        // when
        UserDto userByName = loginFacade.findByUserName(register.username());

        // then
        assertThat(userByName).isEqualTo(new UserDto(register.id(), "username", "pass"));
    }
    @Test
    void should_throw_exception_when_user_not_found() {
        // given
        String username = "someUser";

        // when
        Throwable thrown = catchThrowable(() -> loginFacade.findByUserName(username));

        // then
        assertThat(thrown)
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage("User not found");
    }
}