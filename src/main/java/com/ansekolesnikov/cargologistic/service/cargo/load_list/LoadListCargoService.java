package com.ansekolesnikov.cargologistic.service.cargo.load_list;

import com.ansekolesnikov.cargologistic.database.dao.CarModelDao;
import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.model.car.CarModel;
import com.ansekolesnikov.cargologistic.model.command.CommandLine;
import com.ansekolesnikov.cargologistic.model.command.load_list.LoadListCommandLine;
import com.ansekolesnikov.cargologistic.model.pack.Pack;
import com.ansekolesnikov.cargologistic.service.cargo.CargoService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Service
public class LoadListCargoService implements CargoService {
    @Autowired
    private CarModelDao carModelDao;
    @Autowired
    private PackModelDao packModelDao;
    private LoadListCommandLine loadListCommandLine;
    private LoadListCargoServiceUtils loadListCargoServiceUtils;

    public LoadListCargoService(
            LoadListCargoServiceUtils loadListCargoServiceUtils
    ) {
        this.loadListCargoServiceUtils = loadListCargoServiceUtils;
    }

    @Override
    public String runService(CommandLine commandLine) {
        loadListCommandLine = commandLine.getLoadListCommandLine();
        CarModel carModel = carModelDao.findByName(loadListCommandLine.getCarModel());
        List<Pack> pack =
                loadListCargoServiceUtils
                        .createPacksByNameFromDatabase(
                                packModelDao,
                                loadListCommandLine.getPacks()
                        );

        return loadListCargoServiceUtils.toStringCarsPacksInfo(
                pack,
                loadListCargoServiceUtils.loadCars(
                        carModel,
                        pack,
                        loadListCommandLine.getCountCars(),
                        loadListCommandLine.getAlgorithm()
                )
        );
    }
}
