package com.matei.customeraccountaggregation.service.external;

import com.matei.customeraccountaggregation.dto.TransactionDTO;
import lombok.extern.slf4j.Slf4j;
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
public class ExternalTransactionService {

    private static final String X_AUTH_HEADER = "X-AUTH";

    @Autowired
    private ExternalAuthService externalAuthService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.transactions.uri}")
    private String externalTransactionURI;

    public List<TransactionDTO> getNewTransactions(String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(X_AUTH_HEADER, getExternalAccessToken(username));
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity entity = new HttpEntity(headers);
        return restTemplate.exchange(externalTransactionURI, HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<TransactionDTO>>() {}).getBody();
    }

    private String getExternalAccessToken(String username) {
        return externalAuthService.getLoginAccessToken(username).getToken();
    }
}
