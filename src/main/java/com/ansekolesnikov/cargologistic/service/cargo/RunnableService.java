package com.ansekolesnikov.cargologistic.service.cargo;

import com.ansekolesnikov.cargologistic.model.command.CommandLine;

public interface RunnableService {
    String runService(CommandLine commandLine);
}
