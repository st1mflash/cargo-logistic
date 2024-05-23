package com.ansekolesnikov.cargologistic.service.main;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;

public interface EntityService {
    ResultServiceRun listOperation();
    ResultServiceRun getOperation(CommandLine command);
    ResultServiceRun insertOperation(CommandLine command);
    ResultServiceRun updateOperation(CommandLine command);
    ResultServiceRun deleteOperation(CommandLine command);
}
