package com.phantom;

import com.phantom.command.Command;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationRunnerImpl implements ApplicationRunner {
    private final ApplicationContext context;
    private final List<Command<?>> commands;

    @Override
    public void run(ApplicationArguments arguments) {
        try {
            var command = commands.stream()
                    .filter(t -> t.isApplicable(arguments))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException(""));
            command.execute(arguments);
        }
        catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
