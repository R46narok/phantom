package com.phantom.shell.cmd;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommandLineAction {
    CHANNELS("channels");

    private final String name;
}
