package org.task.libraryapi.service.auth;

import org.task.libraryapi.web.requests.auth.SignInRequest;
import org.task.libraryapi.web.responses.auth.SignInResponse;

import java.io.IOException;

public interface JwtAuthProvider {
    SignInResponse getAccessToken(SignInRequest request) throws IOException;
    String refreshAccessToken(String token) throws IOException;
}
