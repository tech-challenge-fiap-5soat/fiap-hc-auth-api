package com.fiap.hackaton.healthmed.auth_api.application.ports.input.useCase;

public interface CreateAuthorizationUseCase {
    String createAuthorization(String email, String password);
}