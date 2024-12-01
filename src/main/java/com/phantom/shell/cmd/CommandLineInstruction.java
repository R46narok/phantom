package com.phantom.shell.cmd;

public interface CommandLineInstruction {
    String instructionName();

    void execute(CommandLineInstructionDescriptor descriptor);
}
