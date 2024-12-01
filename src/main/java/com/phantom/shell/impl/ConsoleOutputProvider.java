package com.phantom.shell.impl;

import com.phantom.shell.OutputProvider;
import org.springframework.stereotype.Component;

@Component
public class ConsoleOutputProvider implements OutputProvider {
    @Override
    public void write(String text) {
        System.out.print(text);
    }
}
