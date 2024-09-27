package com.fiap.hackaton.healthmed.auth_api.application.input.useCase;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.fiap.hackaton.healthmed.auth_api.application.ports.input.useCase.exceptions.BusinessException;
import com.fiap.hackaton.healthmed.auth_api.application.ports.input.useCase.impl.CreateAuthorizationUseCaseImpl;
import com.fiap.hackaton.healthmed.auth_api.application.services.AuthorizationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CreateAuthorizationUseCaseTest {

    @Mock
    private AuthorizationService authorizationService;

    @InjectMocks
    private CreateAuthorizationUseCaseImpl createAuthorizationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAuthorizationWithValidCredentialsReturnsToken() {
        when(createAuthorizationUseCase.createAuthorization("validEmail@example.com", "validPassword"))
            .thenReturn("validToken");
        assertEquals("validToken", createAuthorizationUseCase.createAuthorization("validEmail@example.com", "validPassword"));
    }

    @Test
    void createAuthorizationWithInvalidCredentialsReturnsNull() {
        when(createAuthorizationUseCase.createAuthorization("invalidEmail@example.com", "invalidPassword"))
            .thenReturn(null);
        assertNull(createAuthorizationUseCase.createAuthorization("invalidEmail@example.com", "invalidPassword"));
    }

    @Test
    void createAuthorizationWithEmptyEmailThrowsException() {
        assertThrows(BusinessException.class, () -> createAuthorizationUseCase.createAuthorization("", "password"));
    }

    @Test
    void createAuthorizationWithEmptyPasswordThrowsException() {
        assertThrows(BusinessException.class, () -> createAuthorizationUseCase.createAuthorization("email@example.com", ""));
    }

    @Test
    void createAuthorizationWithNullEmailThrowsException() {
        assertThrows(BusinessException.class, () -> createAuthorizationUseCase.createAuthorization(null, "password"));
    }

    @Test
    void createAuthorizationWithNullPasswordThrowsException() {
        assertThrows(BusinessException.class, () -> createAuthorizationUseCase.createAuthorization("email@example.com", null));
    }
}