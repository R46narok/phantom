package com.phantom.shell.cmd;

import lombok.Getter;

@Getter
public class CommandLineInstructionDescriptor {
    private final String instruction;

    public CommandLineInstructionDescriptor(String rawInstruction) {
        String[] parts = rawInstruction.split(" ");

        instruction = parts[0];
    }
}
