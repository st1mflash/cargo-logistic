package com.ansekolesnikov.cargologistic.service.cargo;

import com.ansekolesnikov.cargologistic.model.command.CommandLine;

public interface CargoService {
    String runService(CommandLine commandLine);
}
