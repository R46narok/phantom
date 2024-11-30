package com.phantom.network.socket;

import com.phantom.proxy.ProxyOrchestrator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
@AllArgsConstructor
public class SocketConnectionPool extends Thread {
    private final int port;
    private final ProxyOrchestrator orchestrator;

    public synchronized void listen() {
        this.start();
    }

    @Override
    public void run() {
        try (ServerSocket server = new ServerSocket(port)) {
            while (server.isBound()) {
                Socket client = server.accept();
                SocketClientHandler handler = new SocketClientHandler(client, orchestrator);
                handler.handleClient();
            }
        }
        catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
