package com.phantom.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommandAction {
    MASTER("master"),
    CONNECT("connect");

    private final String name;
}
