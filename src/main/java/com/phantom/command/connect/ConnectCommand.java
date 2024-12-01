package com.phantom.command.connect;

import com.phantom.command.Command;
import com.phantom.command.CommandAction;
import com.phantom.shell.InteractiveShellRunner;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConnectCommand implements Command<ConnectCommandConfig> {
    private final ConnectCommandParser parser;
    private final InteractiveShellRunner shellRunner;

    @Override
    public String getName() {
        return CommandAction.CONNECT.getName();
    }

    @Override
    public void execute(ApplicationArguments args) {
        ConnectCommandConfig config = parser.parse(args);
        this.execute(config);
    }

    @Override
    public void execute(ConnectCommandConfig config) {
        shellRunner.run();
    }
}
