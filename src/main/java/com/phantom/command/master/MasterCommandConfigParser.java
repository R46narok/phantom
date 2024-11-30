package com.phantom.command.master;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class MasterCommandConfigParser {
    private static final String PORT = "port";
    private static final int DEFAULT_PORT = 4444;

    public MasterCommandConfig parse(ApplicationArguments args) {
        int port = args.containsOption(PORT) ?
                Integer.parseInt(args.getOptionValues(PORT).get(0)) : DEFAULT_PORT;

        MasterCommandConfig config = MasterCommandConfig.builder()
                .proxyPort(port)
                .build();

        validate(config);

        return config;
    }

    private void validate(MasterCommandConfig config) {
        if (config.getProxyPort() < 0) {
            throw new IllegalArgumentException("Proxy port must be greater than 0");
        }
    }
}
