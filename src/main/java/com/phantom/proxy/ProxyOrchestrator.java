package com.phantom.proxy;

import com.phantom.model.Channel;
import com.phantom.network.http.HttpRequest;
import com.phantom.network.socket.SocketConnectionPool;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ProxyOrchestrator {
    private final Map<Long, Queue<HttpRequest>> blockedRequests = new HashMap<>();

    @Getter
    private final List<Channel> channels = new ArrayList<>();

    public synchronized void run(int port) {
        log.info("Starting proxy on port {}", port);

        SocketConnectionPool pool = new SocketConnectionPool(port, this);
        pool.listen();
    }

    public HttpRequest getNextRequest(Long threadId) {
        return blockedRequests.get(threadId).peek();
    }

    public void addBlockedRequest(HttpRequest request) {
        Long threadId = Thread.currentThread().getId();
        String threadName = Thread.currentThread().getName();
        if (!blockedRequests.containsKey(threadId)) {
            blockedRequests.put(threadId, new LinkedList<>());
            channels.add(new Channel(threadId, threadName));
        }

        blockedRequests.get(threadId).add(request);
    }

}
