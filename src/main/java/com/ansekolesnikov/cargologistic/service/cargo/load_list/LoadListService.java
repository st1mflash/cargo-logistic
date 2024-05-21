package com.ansekolesnikov.cargologistic.service.cargo.load_list;

import com.ansekolesnikov.cargologistic.database.dao.CarModelDao;
import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.car.CarModel;
import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.entity.command.load_list.LoadListCommandLine;
import com.ansekolesnikov.cargologistic.entity.pack.Pack;
import com.ansekolesnikov.cargologistic.service.cargo.RunnableService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Service
public class LoadListService implements RunnableService {
    @Autowired
    private CarModelDao carModelDao;
    @Autowired
    private PackModelDao packModelDao;
    @Autowired
    private LoadListServiceUtils loadListServiceUtils;
    private LoadListCommandLine loadListCommandLine;

    @Override
    public String runService(CommandLine commandLine) {
        loadListCommandLine = commandLine.getLoadListCommandLine();
        CarModel carModel = carModelDao.findByName(loadListCommandLine.getCarModel());
        List<Pack> pack =
                loadListServiceUtils
                        .createPacksByNameFromDatabase(
                                packModelDao,
                                loadListCommandLine.getPacks()
                        );

        return loadListServiceUtils.toStringCarsPacksInfo(
                pack,
                loadListServiceUtils.loadCars(
                        carModel,
                        pack,
                        loadListCommandLine.getCountCars(),
                        loadListCommandLine.getAlgorithm()
                )
        );
    }
}
