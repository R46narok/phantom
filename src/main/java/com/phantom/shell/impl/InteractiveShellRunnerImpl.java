package com.phantom.shell.impl;

import com.phantom.shell.InputProvider;
import com.phantom.shell.InteractiveShellRunner;
import com.phantom.shell.OutputProvider;
import com.phantom.shell.cmd.CommandLineInstructionRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InteractiveShellRunnerImpl implements InteractiveShellRunner {
    private final InputProvider inputProvider;
    private final OutputProvider outputProvider;
    private final CommandLineInstructionRegistry commandRegistry;

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) { // TODO
                printShellHeader();
                String line = inputProvider.readNextLine();
                commandRegistry.executeInstruction(line);
            }
        }
        catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void printShellHeader() {
        outputProvider.write("shell> ");
    }
}
