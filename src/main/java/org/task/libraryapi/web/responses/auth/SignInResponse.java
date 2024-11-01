package org.task.libraryapi.web.responses.auth;

import lombok.Builder;

@Builder
public record SignInResponse(
        String accessToken,
        String refreshToken,
        Long expiresIn
) {
}
