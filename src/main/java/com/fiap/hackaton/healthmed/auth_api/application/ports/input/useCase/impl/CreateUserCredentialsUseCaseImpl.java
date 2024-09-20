package com.fiap.hackaton.healthmed.auth_api.application.ports.input.useCase.impl;

import com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.dto.CreatePatientCredentialsDto;
import com.fiap.hackaton.healthmed.auth_api.application.ports.input.useCase.CreateUserCredentialsUseCase;
import com.fiap.hackaton.healthmed.auth_api.application.services.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateUserCredentialsUseCaseImpl implements CreateUserCredentialsUseCase {
    @Autowired
    private final AuthorizationService authorizationService;

    @Override
    public Boolean createUser(CreatePatientCredentialsDto createPatientCredentials) {
        return authorizationService.createUser(createPatientCredentials);
    }
}