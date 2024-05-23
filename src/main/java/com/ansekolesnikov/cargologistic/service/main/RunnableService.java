package com.ansekolesnikov.cargologistic.service.main;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;

public interface RunnableService {
    ResultServiceRun runService(CommandLine commandLine);
}
