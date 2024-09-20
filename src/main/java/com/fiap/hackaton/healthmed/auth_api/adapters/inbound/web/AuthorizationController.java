package com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web;

import com.fiap.hackaton.healthmed.auth_api.adapters.inbound.web.dto.LoginRequestDto;
import com.fiap.hackaton.healthmed.auth_api.common.constants.PathConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequestMapping(path = PathConstants.AUTH, produces = APPLICATION_JSON_VALUE)
public interface AuthorizationController {

    @PostMapping(produces = APPLICATION_JSON_VALUE)
    ResponseEntity<String> authorization(@RequestBody LoginRequestDto loginRequest);
}