package com.ansekolesnikov.cargologistic.service.cargo;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;

public interface EntityService {
    String listOperation();
    String insertOperation(CommandLine command);
    String updateOperation(CommandLine command);
    String deleteOperation(CommandLine command);
}
