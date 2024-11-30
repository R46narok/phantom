package com.phantom.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommandAction {
    MASTER("master");

    private final String name;
}
