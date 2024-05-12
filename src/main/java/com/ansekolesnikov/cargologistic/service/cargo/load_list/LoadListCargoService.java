package com.ansekolesnikov.cargologistic.service.cargo.load_list;

import com.ansekolesnikov.cargologistic.model.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.cargo.CargoService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class LoadListCargoService implements CargoService {
    @Override
    public String runService(CommandLine commandLine) {
        return null;
    }
}
