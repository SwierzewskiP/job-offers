package pl.joboffers.domain.login.dto;

import lombok.Builder;

@Builder
public record UserDto(String id,
                      String name,
                      String password) {
}
