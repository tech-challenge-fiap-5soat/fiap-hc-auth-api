package com.fiap.hackaton.healthmed.auth_api.application.ports.input.useCase.impl;

import com.fiap.hackaton.healthmed.auth_api.application.ports.input.useCase.CreateAuthorizationUseCase;
import com.fiap.hackaton.healthmed.auth_api.application.services.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateAuthorizationUseCaseImpl implements CreateAuthorizationUseCase {

    @Autowired
    private final AuthorizationService authorizationService;

    @Override
    public String createAuthorization(String email, String password) {
        return authorizationService.authenticateUser(email, password);
    }
}