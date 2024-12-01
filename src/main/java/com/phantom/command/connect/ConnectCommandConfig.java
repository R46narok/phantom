package com.phantom.command.connect;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ConnectCommandConfig {
    private final String hostname;
    private final int port;
}
