package com.fiap.hackaton.healthmed.auth_api.application.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.dto.CreatePatientCredentialsDto;
import com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.dto.UserAuthType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthorizationServiceTest {

    private AuthorizationService authorizationService;

    @BeforeEach
    void setUp() {
        authorizationService = mock(AuthorizationService.class);
    }

    @Test
    void authenticateUserWithValidCredentials() {
        when(authorizationService.authenticateUser("valid@example.com", "password")).thenReturn("token");

        String result = authorizationService.authenticateUser("valid@example.com", "password");

        assertEquals("token", result);
    }

    @Test
    void authenticateUserWithInvalidCredentials() {
        when(authorizationService.authenticateUser("invalid@example.com", "wrongpassword")).thenReturn(null);

        String result = authorizationService.authenticateUser("invalid@example.com", "wrongpassword");

        assertNull(result);
    }

    @Test
    void createUserWithValidCredentials() {
        CreatePatientCredentialsDto dto = new CreatePatientCredentialsDto(
                "patient",
                "123123123",
                "valid@example.com",
                "password",
                UserAuthType.PATIENT
        );

        when(authorizationService.createUser(dto)).thenReturn(true);

        Boolean result = authorizationService.createUser(dto);

        assertTrue(result);
    }

    @Test
    void createUserWithInvalidCredentials() {
        CreatePatientCredentialsDto dto = new CreatePatientCredentialsDto(
                "patient",
                "",
                "invalid@example.com",
                "password",
                UserAuthType.PATIENT
        );

        when(authorizationService.createUser(dto)).thenReturn(false);
        Boolean result = authorizationService.createUser(dto);

        assertFalse(result);
    }
}