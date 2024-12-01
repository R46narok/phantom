package com.phantom.command.connect;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class ConnectCommandParser {
    private static final String HOSTNAME = "hostname";
    private static final String PORT = "port";
    private static final String DEFAULT_HOSTNAME = "localhost";
    private static final int DEFAULT_PORT = 4444;

    public ConnectCommandConfig parse(ApplicationArguments args) {
        int port = args.containsOption(PORT) ?
                Integer.parseInt(args.getOptionValues(PORT).get(0)) : DEFAULT_PORT;
        String hostname = args.containsOption(HOSTNAME) ?
                args.getOptionValues(HOSTNAME).get(0) : DEFAULT_HOSTNAME;

        ConnectCommandConfig config = ConnectCommandConfig.builder()
                .hostname(hostname)
                .port(port)
                .build();

        validate(config);

        return config;
    }

    private void validate(ConnectCommandConfig config) {
        if (config.getPort() < 0) {
            throw new IllegalArgumentException("Port must be greater than 0");
        }
        if (config.getHostname() == null || config.getHostname().isEmpty()) {
            throw new IllegalArgumentException("Hostname must not be empty");
        }
    }
}
