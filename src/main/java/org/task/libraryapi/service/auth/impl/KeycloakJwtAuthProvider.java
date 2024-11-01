package org.task.libraryapi.service.auth.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.task.libraryapi.service.auth.JwtAuthProvider;
import org.task.libraryapi.web.requests.auth.SignInRequest;
import org.task.libraryapi.web.responses.auth.SignInResponse;

import java.io.IOException;


@Builder
public class KeycloakJwtAuthProvider implements JwtAuthProvider {
    private final String clientUrl;
    private final String clientId;
    private final String clientSecret;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();

    private final String tokenEndpoint = "token";

    private final HttpHeaders headers;

    @Override
    public SignInResponse getAccessToken(SignInRequest request) throws IOException {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("grant_type", "password");
        body.add("username", request.login());
        body.add("password", request.password());

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    clientUrl + tokenEndpoint,
                    HttpMethod.POST,
                    new HttpEntity<>(body, headers),
                    String.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new IOException("Failed to retrieve access token: " + response.getStatusCode());
            }

            String responseBody = response.getBody();
            if (responseBody == null) {
                throw new IOException("Empty response body");
            }

            JsonNode jsonNode = objectMapper.readTree(responseBody);

            return SignInResponse.builder()
                    .accessToken(jsonNode.get("access_token").asText())
                    .refreshToken(jsonNode.get("refresh_token").asText())
                    .expiresIn(jsonNode.get("expires_in").asLong())
                    .build();
        } catch (HttpClientErrorException e) {
            throw new IOException("Client error occurred while retrieving access token: " + e.getMessage(), e);
        } catch (HttpServerErrorException e) {
            throw new IOException("Server error occurred while retrieving access token: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new IOException("An unexpected error occurred while retrieving access token: " + e.getMessage(), e);
        }
    }

    @Override
    public String refreshAccessToken(String token) throws IOException {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("grant_type", "refresh_token");
        body.add("refresh_token", token);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    clientUrl + tokenEndpoint,
                    HttpMethod.POST,
                    new HttpEntity<>(headers, body),
                    String.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new IOException("Failed to refresh access token: " + response.getStatusCode());
            }

            String responseBody = response.getBody();
            if (responseBody == null) {
                throw new IOException("Empty response body");
            }

            JsonNode jsonNode = objectMapper.readTree(responseBody);
            return jsonNode.get("access_token").asText();
        } catch (HttpClientErrorException e) {
            throw new IOException("Client error occurred while refreshing access token: " + e.getMessage(), e);
        } catch (HttpServerErrorException e) {
            throw new IOException("Server error occurred while refreshing access token: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new IOException("An unexpected error occurred while refreshing access token: " + e.getMessage(), e);
        }
    }
}
