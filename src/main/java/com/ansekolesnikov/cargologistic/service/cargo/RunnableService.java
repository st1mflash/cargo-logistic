package com.ansekolesnikov.cargologistic.service.cargo;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;

public interface RunnableService {
    String runService(CommandLine commandLine);
}
