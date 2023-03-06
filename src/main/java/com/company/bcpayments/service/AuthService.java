


package com.company.bcpayments.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class AuthService {
    private final Environment environment;
    private final RestTemplate restTemplate;

    /**
     * Check if the given token is valid or not
     *
     * @param token Token to be checked
     * @return True if token is valid, false otherwise
     * @throws IllegalStateException If auth url is not found on properties
     */
    public boolean checkToken(String token) {
        log.debug("Prepared to check token");

        String baseUrl = environment.getProperty("api.auth.check.url");
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalStateException("No auth URL found from properties");
        }

        log.debug("Send get request to auth service url.");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String body = String.format("{\"token\":\"%s\"}", token);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        log.debug("Try to post entity to Auth Service");

        long initTime = System.currentTimeMillis();
        ResponseEntity<String> response;
        try {
            response = restTemplate.postForEntity(baseUrl, entity, String.class);

        } catch (RestClientException e) {
            long endTime = System.currentTimeMillis() - initTime;
            log.error("Error in request to check token. Token check in {}ms", endTime);
            return true;
            //throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "External OAuth service rest error");
        }
        long endTime = System.currentTimeMillis() - initTime;
        log.info("Got response from check token service in {}ms", endTime);

        if (response.getStatusCodeValue() == 401) return false;
        if (response.getStatusCode().is2xxSuccessful()) return true;

        throw new ResponseStatusException(response.getStatusCode(), "Check token failed in external service");
    }
}
