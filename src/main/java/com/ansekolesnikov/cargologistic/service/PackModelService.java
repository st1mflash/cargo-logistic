package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.annotations.CargoPack;
import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.dto.PackModelDto;
import com.ansekolesnikov.cargologistic.entity.PackModelEntity;
import com.ansekolesnikov.cargologistic.enums.DatabaseOperationEnum;
import com.ansekolesnikov.cargologistic.enums.PackModelParameterEnum;
import com.ansekolesnikov.cargologistic.interfaces.IPackModelService;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@CargoPack
@RequiredArgsConstructor
@Service
public class PackModelService implements
        IRunnableByStringService,
        IPackModelService {
    private final PackModelDao packModelDao;
    private static final Logger LOGGER = Logger.getLogger(PackModelService.class.getName());

    @Override
    public PackModelDto getPackModel(int id) {
        LOGGER.info("Запрос информации о модели автомобиля.");
        return packModelDao.findById(id);
    }

    @Override
    public List<PackModelDto> getPackModelList() {
        LOGGER.info("Запрос информации о всех моделях автомобиля.");
        return packModelDao.findAll();
    }

    @Override
    public PackModelDto addPackModel(PackModelDto packModelDto) {
        LOGGER.info("Создание модели автомобиля.");
        return packModelDao.insert(packModelDto);
    }

    @Override
    public PackModelDto updatePackModel(PackModelDto packModelDto) {
        LOGGER.info("Обновление модели автомобиля");
        return packModelDao.update(packModelDto);
    }

    @Override
    public Map<String, String> deletePackModel(int id) {
        LOGGER.info("Удаление модели автомобиля");
        Map<String, String> result = new HashMap<>();
        try {
            packModelDao.delete(id);
            result.put("status", "success");
            return result;
        } catch (RuntimeException e) {
            result.put("status", "failed");
            return result;
        }
    }

    @Override
    public String run(String request) {
        DatabaseOperationEnum operation = DatabaseOperationEnum.initEnumFromString(request.split(" ")[1]);
        switch (Objects.requireNonNull(operation)) {
            case LIST:
                StringBuilder packList = new StringBuilder();
                getPackModelList().stream()
                        .map(PackModelEntity::to)
                        .forEach(c -> packList.append(c).append("\n\n"));
                return packList.toString();
            case GET:
                return PackModelEntity.to(getPackModel(Integer.parseInt(request.split(" ")[2]))).toString();
            case INSERT:
                PackModelDto packModelDto = PackModelDto.builder()
                        .name(request.split(" ")[2])
                        .width(Integer.parseInt(request.split(" ")[3]))
                        .height(Integer.parseInt(request.split(" ")[4]))
                        .build();
                return PackModelEntity.to(addPackModel(packModelDto)).toString();
            case UPDATE:
                return PackModelEntity.to(updatePackByParams(request)).toString();
            case DELETE:
                deletePackModel(Integer.parseInt(request.split(" ")[2]));
                return "Успешное удаление";
            default:
                return "Не удалось определить команду";
        }
    }

    private PackModelDto updatePackByParams(String commandString) {
        PackModelParameterEnum parameterEnum = PackModelParameterEnum.initEnumFromString(commandString.split(" ")[3]);
        String value = commandString.split(" ")[4];
        PackModelDto packModelDto = packModelDao.findById(Integer.parseInt(commandString.split(" ")[2]));
        switch (Objects.requireNonNull(parameterEnum)) {
            case NAME:
                packModelDto.setName(value);
                break;
            case CODE:
                packModelDto.setCode(value.charAt(0));
                break;
            case SCHEME:
                packModelDto.setScheme(value);
                break;
            case WIDTH:
                packModelDto.setWidth(Integer.parseInt(value));
                break;
            case HEIGHT:
                packModelDto.setHeight(Integer.parseInt(value));
                break;
            default:
                break;
        }
        return updatePackModel(packModelDto);
    }
}
