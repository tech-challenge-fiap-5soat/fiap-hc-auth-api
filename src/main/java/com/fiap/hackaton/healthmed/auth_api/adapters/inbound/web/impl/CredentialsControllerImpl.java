package com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.impl;

import com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.CredentialsController;
import com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.dto.CreatePatientCredentialsDto;
import com.fiap.hackaton.healthmed.auth_api.application.ports.input.useCase.CreateUserCredentialsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class CredentialsControllerImpl implements CredentialsController {

    @Autowired
    private final CreateUserCredentialsUseCase createUserCredentialsUseCase;

    @Override
    public ResponseEntity<Boolean> create(CreatePatientCredentialsDto request) {

        Boolean createdUser = createUserCredentialsUseCase.createUser(request);

        return ResponseEntity.ok(createdUser);
    }
}