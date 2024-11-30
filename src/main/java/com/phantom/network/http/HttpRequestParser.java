package com.phantom.network.http;

import java.io.UnsupportedEncodingException;

public class HttpRequestParser {

    public HttpRequest parseFromBytes(byte[] rawData) throws UnsupportedEncodingException {
        HttpRequest request = new HttpRequest();

        String rawRequest = new String(rawData, "UTF-8");

        int headerEndIndex = rawRequest.indexOf("\r\n\r\n");
        if (headerEndIndex == -1) {
            throw new IllegalArgumentException("Invalid HTTP request: no end of headers found");
        }

        String headerPart = rawRequest.substring(0, headerEndIndex);
        String bodyPart = headerEndIndex + 4 < rawRequest.length() ?
                rawRequest.substring(headerEndIndex + 4) :
                "";

        String[] lines = headerPart.split("\r\n");
        String[] requestLine = lines[0].split(" ");
        if (requestLine.length != 3) {
            throw new IllegalArgumentException("Invalid HTTP request line");
        }

        request.setMethod(requestLine[0]);
        request.setPath(requestLine[1]);
        request.setVersion(requestLine[2]);

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            int colonIndex = line.indexOf(":");
            if (colonIndex != -1) {
                String headerName = line.substring(0, colonIndex).trim();
                String headerValue = line.substring(colonIndex + 1).trim();
                request.getHeaders().put(headerName, headerValue);
            }
        }

        request.setBody(bodyPart);

        return request;
    }
}
