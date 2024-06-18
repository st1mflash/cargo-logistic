package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.annotations.CargoPack;
import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.dto.PackModelDto;
import com.ansekolesnikov.cargologistic.entity.RequestRunnableService;
import com.ansekolesnikov.cargologistic.enums.DatabaseOperationEnum;
import com.ansekolesnikov.cargologistic.enums.PackModelParameterEnum;
import com.ansekolesnikov.cargologistic.interfaces.IPackModelService;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import com.ansekolesnikov.cargologistic.mappers.PackModelMapper;
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
    private final PackModelMapper packModelMapper;
    private final PackModelDao packModelDao;
    private static final Logger LOGGER = Logger.getLogger(PackModelService.class.getName());

    @Override
    public PackModelDto getPackModel(int id) {
        LOGGER.info("Запрос информации о модели автомобиля.");
        return packModelMapper.toDto(packModelDao.findById(id));
    }

    @Override
    public List<PackModelDto> getPackModelList() {
        LOGGER.info("Запрос информации о всех моделях автомобиля.");
        return packModelDao.findAll().stream()
                .map(packModelMapper::toDto)
                .toList();
    }

    @Override
    public PackModelDto addPackModel(PackModelDto packModelDto) {
        LOGGER.info("Создание модели автомобиля.");
        return packModelMapper.toDto(
                packModelDao.insert(packModelMapper.toEntity(packModelDto))
        );
    }

    @Override
    public PackModelDto updatePackModel(PackModelDto packModelDto) {
        LOGGER.info("Обновление модели автомобиля");
        return packModelMapper.toDto(
                packModelDao.update(packModelMapper.toEntity(packModelDto))
        );
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
    public String run(RequestRunnableService request) {
        try {
            DatabaseOperationEnum operation = request.getOperation();
            return switch (Objects.requireNonNull(operation)) {
                case GET -> processGetOperationToString(request);
                case LIST -> processListOperationToString();
                case INSERT -> processInsertOperationToString(request);
                case UPDATE -> processUpdateOperationToString(request);
                case DELETE -> processDeleteOperationToString(request);
            };
        } catch (RuntimeException e) {
            return toStringError(); //todo выносить такое не надо
        }
    }

    private String processGetOperationToString(RequestRunnableService request) {
        return packModelMapper.toEntity(getPackModel(request.getEntityId())).toString();
    }

    private String processListOperationToString() {
        StringBuilder packList = new StringBuilder();
        getPackModelList().stream()
                .map(packModelMapper::toEntity)
                .forEach(c -> packList.append(c).append("\n\n"));
        return packList.toString();
    }

    private String processInsertOperationToString(RequestRunnableService request) {
        PackModelDto packModelDto = PackModelDto.builder()
                .name(request.getEntityName())
                .width(request.getEntityWidth())
                .height(request.getEntityHeight())
                .build();
        return packModelMapper.toEntity(addPackModel(packModelDto)).toString();
    }

    private String processUpdateOperationToString(RequestRunnableService request) {
        return packModelMapper.toEntity(updatePackByParams(request)).toString();
    }

    //todo Возвращать void
    private String processDeleteOperationToString(RequestRunnableService request) {
        deletePackModel(request.getEntityId());
        return "Успешное удаление";
    }

    private String toStringError() {
        return "Не удалось определить команду";
    }

    private PackModelDto updatePackByParams(RequestRunnableService request) {
        PackModelParameterEnum parameterEnum = request.getPackModelParameterName();
        String value = request.getEntityParameterValue();
        PackModelDto packModelDto = packModelMapper.toDto(packModelDao.findById(request.getEntityId()));
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
