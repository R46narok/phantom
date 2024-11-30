package com.phantom.network.http;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class HttpRequest {
    private String path;
    private String method;
    private String version;
    private Map<String, String> headers;
    private String body;

    public HttpRequest() {
        headers = new HashMap<>();
    }

}
