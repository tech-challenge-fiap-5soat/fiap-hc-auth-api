package com.fiap.hackaton.healthmed.auth_api.application.services;

import com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.dto.CreatePatientCredentialsDto;

public interface AuthorizationService {
    String authenticateUser(String email, String password);
    Boolean createUser(CreatePatientCredentialsDto createPatientCredentials);
}