package pl.joboffers.domain.login.dto;

public record RegistrationResultDto(String id,
                                    boolean created,
                                    String username) {
}
