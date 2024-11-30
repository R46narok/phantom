package com.phantom.command;

import org.springframework.boot.ApplicationArguments;

public interface Command<T> {
    default boolean isApplicable(ApplicationArguments args) {
        return args.getNonOptionArgs().contains(getName());
    }

    String getName();
    void execute(ApplicationArguments args);
    void execute(T args);
}
