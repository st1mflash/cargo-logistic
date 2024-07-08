package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.constants.MessageConstant;
import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.LocalFile;
import com.ansekolesnikov.cargologistic.entity.Pack;
import com.ansekolesnikov.cargologistic.interfaces.ILocalFileService;
import com.ansekolesnikov.cargologistic.mappers.CarModelMapper;
import com.ansekolesnikov.cargologistic.mappers.PackModelMapper;
import com.ansekolesnikov.cargologistic.repository.PackModelRepository;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocalFileService implements ILocalFileService {
    private static final Logger LOGGER = Logger.getLogger(LocalFile.class.getName());
    @Override
    public List<Car> importCarsFromFile(LocalFile localFile, CarModelMapper carModelMapper) {
        try {
            return importListJsonCars(localFile)
                    .stream()
                    .map(carModelMapper::toCar)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error(MessageConstant.CAR_IMPORT_ERROR + e);
            return null;
        }
    }

    @Override
    public List<Pack> importPacksFromFile(LocalFile localFile, PackModelRepository packModelRepository, PackModelMapper packModelMapper) {
        try {
            return Arrays
                    .stream(localFile.getContent().split("\\n\\s*\\n"))
                    .map(c -> c.replaceAll(" ", "").charAt(0))
                    .map(packModelRepository::findByCode)
                    .map(packModelMapper::toPack)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error(MessageConstant.PACK_IMPORT_ERROR + e);
            return null;
        }
    }

    private List<JSONObject> importListJsonCars(LocalFile localFile) {
        List<JSONObject> listJSONObj = new ArrayList<>();
        String[] arrClearContent = localFile.getContent()
                .replaceAll("[{\\[\\]]", "")
                .split("},");
        for (String s : arrClearContent) {
            listJSONObj.add(new JSONObject("{" + s + "}"));
        }
        return listJSONObj;
    }
}
