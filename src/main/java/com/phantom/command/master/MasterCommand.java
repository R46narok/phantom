package com.phantom.command.master;

import com.phantom.command.Command;
import com.phantom.command.CommandAction;
import com.phantom.proxy.ProxyOrchestrator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class MasterCommand implements Command<MasterCommandConfig> {
    private final MasterCommandConfigParser parser;
    private final ProxyOrchestrator proxyOrchestrator;

    @Override
    public String getName() {
        return CommandAction.MASTER.getName();
    }

    @Override
    public void execute(ApplicationArguments args) {
        MasterCommandConfig config = parser.parse(args);
        this.execute(config);
    }

    @Override
    public void execute(MasterCommandConfig config) {
        proxyOrchestrator.run(config.getProxyPort());
    }
}
