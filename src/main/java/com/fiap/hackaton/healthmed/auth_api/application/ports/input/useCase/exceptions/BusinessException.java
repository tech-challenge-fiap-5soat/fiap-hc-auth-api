package com.fiap.hackaton.healthmed.auth_api.application.ports.input.useCase.exceptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}