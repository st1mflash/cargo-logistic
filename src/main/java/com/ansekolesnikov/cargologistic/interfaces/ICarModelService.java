package com.ansekolesnikov.cargologistic.interfaces;

import com.ansekolesnikov.cargologistic.annotations.CargoCar;
import com.ansekolesnikov.cargologistic.dto.CarModelDto;
import com.ansekolesnikov.cargologistic.entity.CarModelEntity;

import java.util.List;
import java.util.Map;

@CargoCar
public interface ICarModelService {
    List<CarModelDto> getCarModelList();
    CarModelDto getCarModel(int id);
    CarModelDto addCarModel(CarModelDto car);
    CarModelDto updateCarModel(CarModelDto car);
    Map<String, String> deleteCarModel(int id);
}
