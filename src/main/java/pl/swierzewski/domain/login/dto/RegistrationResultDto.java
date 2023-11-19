package pl.swierzewski.domain.login.dto;

public record RegistrationResultDto(String id,
                                    boolean created,
                                    String username) {
}
