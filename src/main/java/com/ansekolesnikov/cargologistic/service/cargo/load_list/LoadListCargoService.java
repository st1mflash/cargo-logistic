package com.ansekolesnikov.cargologistic.service.cargo.load_list;

import com.ansekolesnikov.cargologistic.model.car.CarModel;
import com.ansekolesnikov.cargologistic.model.command.CommandLine;
import com.ansekolesnikov.cargologistic.model.command.load_list.LoadListCommandLine;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.service.cargo.CargoService;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
@Service
public class LoadListCargoService implements CargoService {
    private DatabaseService databaseService;
    private LoadListCommandLine loadListCommandLine;

    public LoadListCargoService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    @Override
    public String runService(CommandLine commandLine) {
        loadListCommandLine = commandLine.getLoadListCommandLine();
        CarModel carModel = databaseService
                .getOperationsDatabase()
                .getCarOperations()
                .queryByName(loadListCommandLine.getCarModel());
        String algorithm = loadListCommandLine.getAlgorithm();
        int countCars = loadListCommandLine.getCountCars();
        List<Pack> packs = Arrays
                .stream(loadListCommandLine.getPacks())
                .map(p -> databaseService
                        .getOperationsDatabase()
                        .getPackOperations()
                        .queryByName(p)
                )
                .toList();

        System.out.println(carModel.getIdModel() + " - " + carModel.getNameModel());
        for(Pack pack: packs) {
            System.out.println(pack.getId() + " - " + pack.getName());
        }

        return null;
    }
}
