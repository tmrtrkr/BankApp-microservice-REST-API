package com.projects.app.bankApplication.DataAccess;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class HttpRequestService {


    @Value("${authentication.url}")
    private String authenticationUrl;

    public String getAuthenticationUrl() {
        return authenticationUrl;
    }

    // Basic get request template with exception handling
    public String GetRequestTemplate(String url, Map<String, String> headers) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders httpHeaders = new HttpHeaders();

            if (headers != null) { // Check if headers map is not null
                headers.forEach(httpHeaders::set); // Add headers if present
            }

            HttpEntity<String> entity = new HttpEntity<>("", httpHeaders);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (RestClientException e) {
            // Handle the specific exception related to REST client operations
            return "Failed to fetch data: " + e.getMessage();
        } catch (Exception e) {
            // Handle unexpected exceptions
            return "An unexpected error occurred: " + e.getMessage();
        }
    }

    // HTTP request to Authentication with exception handling
    public String httpGetUserIDByToken(String clientToken, String IP) {
        try {

            String AuthenticationURL = getAuthenticationUrl();

            Map<String, String> headers = new HashMap<>();
            headers.put("Token", clientToken);
            headers.put("IP", IP);

            return GetRequestTemplate(AuthenticationURL, headers);
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirement
            return "Failed to authenticate user: " + e.getMessage();
        }
    }
}
