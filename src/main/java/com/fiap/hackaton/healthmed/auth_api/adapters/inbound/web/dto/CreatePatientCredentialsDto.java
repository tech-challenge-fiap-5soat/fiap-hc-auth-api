package com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CreatePatientCredentialsDto(
        String name,
        String cpf,
        String email,
        String password,
        UserAuthType userType
) {}