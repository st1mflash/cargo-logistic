package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.constants.MessageConstant;
import com.ansekolesnikov.cargologistic.dto.CarModelDto;
import com.ansekolesnikov.cargologistic.entity.CarModelEntity;
import com.ansekolesnikov.cargologistic.entity.RequestString;
import com.ansekolesnikov.cargologistic.enums.CarModelParameterEnum;
import com.ansekolesnikov.cargologistic.enums.DatabaseOperationEnum;
import com.ansekolesnikov.cargologistic.interfaces.ICarModelService;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import com.ansekolesnikov.cargologistic.mappers.CarModelMapper;
import com.ansekolesnikov.cargologistic.repository.CarModelRepository;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CarModelService implements
        IRunnableByStringService,
        ICarModelService {
    private final CarModelRepository carModelRepository;
    private final CarModelMapper carModelMapper;

    private static final Logger LOGGER = Logger.getLogger(CarModelService.class.getName());

    @Override
    public CarModelDto getCarModel(int id) {
        LOGGER.info(MessageConstant.CAR_MODEL_INFO_REQUEST);
        return carModelMapper.toDto(carModelRepository.findById(id).orElse(null));
    }

    @Override
    public List<CarModelDto> getCarModelList() {
        LOGGER.info(MessageConstant.CAR_MODEL_LIST_INFO_REQUEST);
        return carModelRepository.findAll().stream()
                .map(carModelMapper::toDto)
                .toList();
    }

    @Override
    public CarModelDto addCarModel(CarModelDto carModelDto) {
        LOGGER.info(MessageConstant.CAR_MODEL_INSERT_REQUEST);
        return carModelMapper.toDto(
                carModelRepository.save(carModelMapper.toEntity(carModelDto))
        );
    }

    @Override
    public CarModelDto updateCarModel(CarModelDto carModelDto) {
        LOGGER.info(MessageConstant.CAR_MODEL_UPDATE_REQUEST);
        return carModelMapper.toDto(
                carModelRepository.save(carModelMapper.toEntity(carModelDto))
        );
    }

    @Override
    public Map<String, String> deleteCarModel(int id) {
        LOGGER.info(MessageConstant.CAR_MODEL_DELETE_REQUEST);
        Map<String, String> result = new HashMap<>();
        try {
            carModelRepository.delete(Objects.requireNonNull(carModelRepository.findById(id).orElse(null)));
            result.put("status", "success");
            return result;
        } catch (RuntimeException e) {
            result.put("status", "failed");
            return result;
        }
    }

    @Override
    public String run(RequestString request) {
        DatabaseOperationEnum operation = request.getOperation();
        switch (Objects.requireNonNull(operation)) {
            case LIST:
                StringBuilder carList = new StringBuilder();
                getCarModelList().stream()
                        .map(carModelMapper::toEntity)
                        .forEach(c -> carList.append(toStringCarInfo(c)).append("\n\n"));
                return carList.toString();
            case GET:
                CarModelEntity carGetting = carModelMapper.toEntity(getCarModel(request.getEntityId()));
                return toStringCarInfo(carGetting);
            case INSERT:
                CarModelDto carModelDto = CarModelDto.builder()
                        .name(request.getEntityName())
                        .width(request.getEntityWidth())
                        .height(request.getEntityHeight())
                        .build();
                CarModelEntity carInserted = carModelMapper.toEntity(addCarModel(carModelDto));
                return toStringCarInfo(carInserted);
            case UPDATE:
                CarModelEntity carUpdated = carModelMapper.toEntity(updateCarByParams(request));
                return toStringCarInfo(carUpdated);
            case DELETE:
                deleteCarModel(request.getEntityId());
                return MessageConstant.SUCCESS_DELETE;
            default:
                return MessageConstant.UNKNOWN_COMMAND;
        }
    }

    private CarModelDto updateCarByParams(RequestString request) {
        CarModelParameterEnum parameterEnum = request.getCarModelParameterName();
        String value = request.getEntityParameterValue();
        CarModelDto carModelDto = carModelMapper.toDto(
                carModelRepository.findById(request.getEntityId()).orElse(null)
        );
        switch (Objects.requireNonNull(parameterEnum)) {
            case NAME -> carModelDto.setName(value);
            case WIDTH -> carModelDto.setWidth(Integer.parseInt(value));
            case HEIGHT -> carModelDto.setHeight(Integer.parseInt(value));
        }
        return updateCarModel(carModelDto);
    }

    private String toStringCarInfo(CarModelEntity carModelEntity) {
        return "Идентификатор: #" + carModelEntity.getId()
                + "\nНазвание модели: " + carModelEntity.getName()
                + "\nПараметры кузова: " + carModelEntity.getWidth() + "x" + carModelEntity.getHeight();
    }
}
