package com.fiap.hackaton.healthmed.auth_api.application.services.impl;

import com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.dto.CreatePatientCredentialsDto;
import com.fiap.hackaton.healthmed.auth_api.adapters.outbound.gateway.CognitoApiClient;
import com.fiap.hackaton.healthmed.auth_api.application.services.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminInitiateAuthResponse;

@RequiredArgsConstructor
@Service
public class CognitoServiceImpl implements AuthorizationService {

    @Autowired
    private final CognitoApiClient cognitoApiClient;

    @Override
    public String authenticateUser(String email, String password) {
        AdminInitiateAuthResponse authResponse = cognitoApiClient.requestAuthRequest(email, password);
        return authResponse.authenticationResult().idToken();
    }

    @Override
    public Boolean createUser(CreatePatientCredentialsDto createPatientCredentials) {
        return cognitoApiClient.createUser(createPatientCredentials);
    }

}