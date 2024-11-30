package com.phantom.command.master;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MasterCommandConfig {
    private final int proxyPort;
}
