package com.ansekolesnikov.cargologistic.entity;

import com.ansekolesnikov.cargologistic.constants.MessageConstant;
import com.ansekolesnikov.cargologistic.mappers.CarModelMapper;
import com.ansekolesnikov.cargologistic.mappers.PackModelMapper;
import com.ansekolesnikov.cargologistic.repository.PackModelRepository;
import lombok.Data;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class LocalFile {
    private String name;
    private String format;
    private String path;
    private String content;
    private static final Logger LOGGER = Logger.getLogger(LocalFile.class.getName());
    //todo методы лучше вынести в какой нибудь LocalFileService
    public List<JSONObject> importListJsonCars() {
        List<JSONObject> listJSONObj = new ArrayList<>();
        String[] arrClearContent = content
                .replaceAll("[{\\[\\]]", "")
                .split("},");
        for (String s : arrClearContent) {
            listJSONObj.add(new JSONObject("{" + s + "}"));
        }
        return listJSONObj;
    }

    public List<Car> importCarsFromFile(CarModelMapper carModelMapper) {
        try {
            return importListJsonCars()
                    .stream()
                    .map(carModelMapper::toCar)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error(MessageConstant.CAR_IMPORT_ERROR + e);
            return null;
        }
    }

    public List<Pack> importPacksFromFile(PackModelRepository packModelRepository, PackModelMapper packModelMapper) {
        try {
            return Arrays
                    .stream(content.split("\\n\\s*\\n"))
                    .map(c -> c.replaceAll(" ", "").charAt(0))
                    .map(packModelRepository::findByCode)
                    .map(packModelMapper::toPack)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            LOGGER.error(MessageConstant.PACK_IMPORT_ERROR + e);
            return null;
        }
    }
}
