package pl.joboffers.domain.login;

import lombok.Builder;

@Builder
record User(String id,
                   String username,
                   String password) {
}
