package com.phantom.shell.cmd;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommandLineInstructionRegistry {
    private final List<CommandLineInstruction> instructions;

    public void executeInstruction(String rawInstruction) {
        var descriptor = new CommandLineInstructionDescriptor(rawInstruction);
        var instruction = instructions.stream()
                .filter(t -> t.instructionName().equals(descriptor.getInstruction()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Instruction not found"));
        instruction.execute(descriptor);
    }
}
