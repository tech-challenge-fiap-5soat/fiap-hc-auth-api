package com.fiap.hackaton.healthmed.auth_api.application.ports.input.useCase;

import com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.dto.CreatePatientCredentialsDto;

public interface CreateUserCredentialsUseCase {
    Boolean createUser(CreatePatientCredentialsDto createPatientCredentials);
}