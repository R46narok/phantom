package com.phantom.shell.impl;

import com.phantom.shell.InputProvider;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class ConsoleInputProvider implements InputProvider {
    private final BufferedReader reader;

    public ConsoleInputProvider() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String readNextLine() throws IOException {
        return reader.readLine();
    }
}
