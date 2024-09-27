package com.fiap.hackaton.healthmed.auth_api.application.ports.input.useCase.impl;

import com.fiap.hackaton.healthmed.auth_api.application.ports.input.useCase.CreateAuthorizationUseCase;
import com.fiap.hackaton.healthmed.auth_api.application.ports.input.useCase.exceptions.BusinessException;
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
        if (!isValidCredentials(email, password)) {
            throw new BusinessException("Invalid credentials");
        }
        return authorizationService.authenticateUser(email, password);
    }

    private Boolean isValidCredentials(String email, String password) {
        return email != null && !email.isEmpty() && password != null && !password.isEmpty();
    }
}