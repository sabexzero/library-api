package org.task.libraryapi.service.auth;

import org.task.libraryapi.web.requests.auth.SignInRequest;
import org.task.libraryapi.web.responses.auth.SignInResponse;

import javax.naming.AuthenticationException;

public interface AuthService {
    SignInResponse signIn(SignInRequest request) throws AuthenticationException;
    String refreshToken(String refreshToken) throws AuthenticationException;
}
