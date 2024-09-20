package com.fiap.hackaton.healthmed.auth_api.adapters.outbound.gateway;

import com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.dto.CreatePatientCredentialsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CognitoApiClient {
    private final CognitoIdentityProviderClient cognitoClient;
    private final String clientId;
    private final String userPoolId;

    public CognitoApiClient(@Value("${aws.cognito.clientId}") String clientId,
                            @Value("${aws.cognito.userPoolId}") String userPoolId) {
        this.clientId = clientId;
        this.userPoolId = userPoolId;
        this.cognitoClient = CognitoIdentityProviderClient.builder()
                .credentialsProvider(DefaultCredentialsProvider.create())
                .region(Region.US_EAST_1)
                .build();
    }

    public AdminInitiateAuthResponse requestAuthRequest(String email, String password) {

        Map<String, String> authParams = new HashMap<>();
        authParams.put("USERNAME", email);
        authParams.put("PASSWORD", password);

        AdminInitiateAuthRequest authRequest = AdminInitiateAuthRequest.builder()
                .authFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .clientId(clientId)
                .userPoolId(userPoolId)
                .authParameters(authParams)
                .build();

        return cognitoClient.adminInitiateAuth(authRequest);
    }

    public Boolean createUser(CreatePatientCredentialsDto createPatientCredentials) {
        try {
            List<AttributeType> userAttributes = getAttributesFromPatientCredentials(createPatientCredentials);

            AdminCreateUserRequest createUserRequest = AdminCreateUserRequest.builder()
                    .userPoolId(userPoolId)
                    .username(createPatientCredentials.email())
                    .temporaryPassword(createPatientCredentials.password())
                    .userAttributes(userAttributes)
                    .messageAction("SUPPRESS")
                    .build();

            AdminCreateUserResponse response = cognitoClient.adminCreateUser(createUserRequest);

            AdminSetUserPasswordRequest setUserPasswordRequest = AdminSetUserPasswordRequest.builder()
                    .userPoolId(userPoolId)
                    .username(createPatientCredentials.email())
                    .password(createPatientCredentials.password())
                    .permanent(true)
                    .build();
            cognitoClient.adminSetUserPassword(setUserPasswordRequest);

            System.out.println("Usuário criado com sucesso: " + response.user().username());
            return  true;
        } catch (CognitoIdentityProviderException e) {
            System.err.println("Erro ao criar usuário: " + e.awsErrorDetails().errorMessage());
            return false;
        }
    }

    private static List<AttributeType> getAttributesFromPatientCredentials(CreatePatientCredentialsDto createPatientCredentials) {
        List<AttributeType> userAttributes = new ArrayList<>();
        userAttributes.add(
                AttributeType.builder()
                        .name("email")
                        .value(createPatientCredentials.email())
                        .build()
        );
        userAttributes.add(
                AttributeType.builder()
                        .name("custom:cpf")
                        .value(createPatientCredentials.cpf())
                        .build()
        );
        userAttributes.add(
                AttributeType.builder()
                        .name("custom:UserAuthType")
                        .value(createPatientCredentials.userType().name())
                        .build()
        );
        userAttributes.add(
                AttributeType.builder()
                        .name("name")
                        .value(createPatientCredentials.name())
                        .build()
        );

        userAttributes.add(AttributeType.builder()
                .name("email_verified")
                .value("true")
                .build());
        return userAttributes;
    }
}