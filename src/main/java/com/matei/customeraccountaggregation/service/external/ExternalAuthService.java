package com.matei.customeraccountaggregation.service.external;

import com.matei.customeraccountaggregation.dto.TokenDTO;
import com.matei.customeraccountaggregation.exception.ExternalServiceUnavailableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class ExternalAuthService {

    private static final String USERNAME_HEADER = "username";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.auth.uri}")
    private String authUri;

    public TokenDTO getLoginAccessToken(String username) {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(USERNAME_HEADER, username);
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        try {
            return restTemplate.postForObject(authUri, new HttpEntity(headers), TokenDTO.class);
        } catch (Exception e) {
            throw new ExternalServiceUnavailableException("Service is down", e);
        }
    }
}
