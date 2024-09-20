package com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.impl;

import com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.AuthorizationController;
import com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.dto.LoginRequestDto;
import com.fiap.hackaton.healthmed.auth_api.application.ports.input.useCase.CreateAuthorizationUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AuthorizationControllerImpl implements AuthorizationController {

    @Autowired
    private final CreateAuthorizationUseCase createAuthorizationUseCase;

    public ResponseEntity<String> authorization(LoginRequestDto loginRequest) {
        String auth = createAuthorizationUseCase.createAuthorization(
                loginRequest.email(),
                loginRequest.password()
        );
         return ResponseEntity.ok(auth);
    }
}