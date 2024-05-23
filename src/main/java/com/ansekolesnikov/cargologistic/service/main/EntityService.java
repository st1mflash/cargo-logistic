package com.ansekolesnikov.cargologistic.service.main;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.result.ResultServiceRun;

public interface EntityService {
    ResultServiceRun listOperation();
    ResultServiceRun insertOperation(CommandLine command);
    ResultServiceRun updateOperation(CommandLine command);
    ResultServiceRun deleteOperation(CommandLine command);
}
