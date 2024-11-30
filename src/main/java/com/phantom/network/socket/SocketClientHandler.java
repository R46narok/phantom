package com.phantom.network.socket;

import com.phantom.network.http.HttpRequest;
import com.phantom.network.http.HttpRequestParser;
import com.phantom.proxy.ProxyOrchestrator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Slf4j
@AllArgsConstructor
public class SocketClientHandler extends Thread {
    private final Socket socket;
    private final ProxyOrchestrator proxyOrchestrator;

    public synchronized void handleClient() {
        this.start();
    }

    @Override
    public void run() {
        try {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            while (socket.isBound()) {
                HttpRequest request = readNextRequest(in);

                log.info("Received a {} request to {} from {} in {}", request.getMethod(), request.getPath(),
                        socket.getRemoteSocketAddress(), Thread.currentThread().getName());

                proxyOrchestrator.addBlockedRequest(request);
            }

            socket.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private HttpRequest readNextRequest(InputStream in) throws IOException {

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] temp = new byte[1024];
        int bytesRead;

        while ((bytesRead = in.read(temp)) != -1) {
            log.info("Received {} bytes from thread {}", bytesRead, Thread.currentThread().getName());
            buffer.write(temp, 0, bytesRead);
            if (containsEndOfHeaders(buffer.toByteArray())) {
                break;
            }
        }

        byte[] rawBytes = buffer.toByteArray();
        HttpRequestParser parser = new HttpRequestParser();
        return parser.parseFromBytes(rawBytes);
    }

    private boolean containsEndOfHeaders(byte[] data) {
        for (int i = 0; i < data.length - 3; i++) {
            if (data[i] == '\r' && data[i + 1] == '\n' && data[i + 2] == '\r' && data[i + 3] == '\n') {
                return true;
            }
        }
        return false;
    }
}
