package com.ansekolesnikov.cargologistic.service.cargo.load_list;

import com.ansekolesnikov.cargologistic.model.car.CarModel;
import com.ansekolesnikov.cargologistic.model.command.CommandLine;
import com.ansekolesnikov.cargologistic.model.command.load_list.LoadListCommandLine;
import com.ansekolesnikov.cargologistic.model.pack.PackModel;
import com.ansekolesnikov.cargologistic.service.cargo.CargoService;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Service
public class LoadListCargoService implements CargoService {
    private DatabaseService databaseService;
    private LoadListCommandLine loadListCommandLine;
    private LoadListCargoServiceUtils loadListCargoServiceUtils;

    public LoadListCargoService(
            DatabaseService databaseService,
            LoadListCargoServiceUtils loadListCargoServiceUtils
    ) {
        this.databaseService = databaseService;
        this.loadListCargoServiceUtils = loadListCargoServiceUtils;
    }

    @Override
    public String runService(CommandLine commandLine) {
        loadListCommandLine = commandLine.getLoadListCommandLine();
        CarModel carModel =
                loadListCargoServiceUtils
                        .createCarModelByNameFromDatabase(
                                databaseService,
                                loadListCommandLine.getCarModel()
                        );
        List<PackModel> packModels =
                loadListCargoServiceUtils
                        .createPacksByNameFromDatabase(
                                databaseService,
                                loadListCommandLine.getPacks()
                        );

        return loadListCargoServiceUtils.toStringCarsPacksInfo(
                packModels,
                loadListCargoServiceUtils.loadCars(
                        carModel,
                        packModels,
                        loadListCommandLine.getCountCars(),
                        loadListCommandLine.getAlgorithm()
                )
        );
    }
}
