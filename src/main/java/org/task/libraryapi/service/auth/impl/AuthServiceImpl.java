package org.task.libraryapi.service.auth.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.task.libraryapi.service.auth.AuthService;
import org.task.libraryapi.service.auth.JwtAuthProvider;
import org.task.libraryapi.web.requests.auth.SignInRequest;
import org.task.libraryapi.web.responses.auth.SignInResponse;

import javax.naming.AuthenticationException;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final JwtAuthProvider jwtAuthProvider;

    /**
     * Authenticates a user and retrieves an access token.
     * @param request The sign-in request containing user credentials.
     * @return SignInResponse containing access token, refresh token, and expiration.
     */
    @Override
    public SignInResponse signIn(SignInRequest request) throws AuthenticationException {
        try {
            return jwtAuthProvider.getAccessToken(request);
        } catch (IOException e) {
            throw new AuthenticationException(
                    "An error occurred during user sign-in: " + e.getMessage()
            );
        } catch (Exception e) {
            throw new RuntimeException(
                    "An unexpected error occurred during user sign-in: " + e.getMessage(), e
            );
        }
    }

    /**
     * Refreshes an access token using a refresh token.
     * @param token The refresh token to exchange for a new access token.
     * @return New access token as a string.
     */
    @Override
    public String refreshToken(String token) throws AuthenticationException {
        try {
            return jwtAuthProvider.refreshAccessToken(token);
        } catch (IOException e) {
            throw new AuthenticationException(
                    "An error occurred during token refresh: " + e.getMessage()
            );
        } catch (Exception e) {
            throw new RuntimeException(
                    "An unexpected error occurred during token refresh: " + e.getMessage(), e
            );
        }
    }
}
