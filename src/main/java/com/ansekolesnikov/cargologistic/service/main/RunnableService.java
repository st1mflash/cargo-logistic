package com.ansekolesnikov.cargologistic.service.main;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.result.ResultServiceRun;

public interface RunnableService {
    ResultServiceRun runService(CommandLine commandLine);
}
