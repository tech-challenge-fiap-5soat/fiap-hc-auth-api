package com.fiap.hackaton.healthmed.auth_api.application.input.useCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.dto.CreatePatientCredentialsDto;
import com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.dto.UserAuthType;
import com.fiap.hackaton.healthmed.auth_api.application.ports.input.useCase.CreateUserCredentialsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateUserCredentialsUseCaseTest {

    private CreateUserCredentialsUseCase createUserCredentialsUseCase;
    private CreatePatientCredentialsDto validCredentials;
    private CreatePatientCredentialsDto invalidCredentials;

    @BeforeEach
    void setUp() {
        createUserCredentialsUseCase = mock(CreateUserCredentialsUseCase.class);
        validCredentials = new CreatePatientCredentialsDto("validUser", "123123123", "patient@mail.com","password1", UserAuthType.PATIENT);
        invalidCredentials = new CreatePatientCredentialsDto("", "", "", "", null);
    }

    @Test
    void createUserWithValidCredentialsReturnsTrue() {
        when(createUserCredentialsUseCase.createUser(validCredentials)).thenReturn(true);
        assertTrue(createUserCredentialsUseCase.createUser(validCredentials));
    }

    @Test
    void createUserWithInvalidCredentialsReturnsFalse() {
        when(createUserCredentialsUseCase.createUser(invalidCredentials)).thenReturn(false);
        assertFalse(createUserCredentialsUseCase.createUser(invalidCredentials));
    }
}