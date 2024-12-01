package com.phantom.service;

import com.phantom.model.Channel;
import lombok.SneakyThrows;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class ChannelService {
    private final RestTemplate restTemplate;

    public ChannelService() {
        this.restTemplate = new RestTemplate();
    }

    public List<Channel> getAllChannels(String hostname, int port) {
        String url = buildUrl(hostname, port, "/api/proxy");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Channel>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<>() {});

        return response.getBody();
    }

    @SneakyThrows
    private String buildUrl(String hostname, int port, String path) {
        URI uri = new URI("http", null, hostname, port, path, null, null);
        return uri.toString();
    }
}
