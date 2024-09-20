package com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web;

import com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.dto.CreatePatientCredentialsDto;
import com.fiap.hackaton.healthmed.auth_api.common.constants.PathConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(path = PathConstants.CREDENTIALS, produces = APPLICATION_JSON_VALUE)
public interface CredentialsController {

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Boolean> create(@RequestBody CreatePatientCredentialsDto request);

}