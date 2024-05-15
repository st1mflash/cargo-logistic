package com.ansekolesnikov.cargologistic.service.cargo.load_list;

import com.ansekolesnikov.cargologistic.model.car.CarModel;
import com.ansekolesnikov.cargologistic.model.command.CommandLine;
import com.ansekolesnikov.cargologistic.model.command.load_list.LoadListCommandLine;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.service.cargo.CargoService;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Service
public class LoadListCargoService implements CargoService {
    @Autowired
    private DatabaseService databaseService;
    private LoadListCommandLine loadListCommandLine;
    @Autowired
    private LoadListCargoServiceUtils loadListCargoServiceUtils;

    public LoadListCargoService(DatabaseService databaseService) {
        this.databaseService = databaseService;
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
        List<Pack> packs =
                loadListCargoServiceUtils
                        .createPacksByNameFromDatabase(
                                databaseService,
                                loadListCommandLine.getPacks()
                        );

        return loadListCargoServiceUtils.toStringCarsPacksInfo(
                packs,
                loadListCargoServiceUtils.loadCars(
                        carModel,
                        packs,
                        loadListCommandLine.getCountCars(),
                        loadListCommandLine.getAlgorithm()
                )
        );
    }
}
