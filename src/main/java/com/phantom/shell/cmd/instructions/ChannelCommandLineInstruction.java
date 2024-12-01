package com.phantom.shell.cmd.instructions;

import com.phantom.service.ChannelService;
import com.phantom.shell.cmd.CommandLineAction;
import com.phantom.shell.cmd.CommandLineInstruction;
import com.phantom.shell.cmd.CommandLineInstructionDescriptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ChannelCommandLineInstruction implements CommandLineInstruction {
    private final ChannelService channelService;

    @Override
    public String instructionName() {
        return CommandLineAction.CHANNELS.getName();
    }

    @Override
    public void execute(CommandLineInstructionDescriptor descriptor) {
        channelService.getAllChannels("localhost", 8080);
    }
}
