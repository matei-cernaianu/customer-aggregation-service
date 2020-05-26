package com.matei.customeraccountaggregation.service.external;

import com.matei.customeraccountaggregation.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ExternalAccountService {

    private static final String X_AUTH_HEADER = "X-AUTH";

    @Autowired
    private ExternalAuthService externalAuthService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.accounts.uri}")
    private String externalAccountURI;

    public List<AccountDTO> getNewAccounts(String username) {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(X_AUTH_HEADER, getExternalAccessToken(username));
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return restTemplate.exchange(
                externalAccountURI,
                HttpMethod.GET,
                new HttpEntity(headers),
                new ParameterizedTypeReference<List<AccountDTO>>() {}).getBody();
    }

    private String getExternalAccessToken(String username) {
        return externalAuthService.getLoginAccessToken(username).getToken();
    }
}


