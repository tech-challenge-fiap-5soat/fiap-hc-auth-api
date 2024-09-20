package com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.dto;

import lombok.Builder;

@Builder
public record LoginRequestDto(
        String email,
        String password
) {
}