package com.ansekolesnikov.cargologistic.interfaces;

import com.ansekolesnikov.cargologistic.entity.CarModel;

import java.util.List;
import java.util.Map;

public interface ICarModelService {
    List<CarModel> getCarModelList();
    CarModel getCarModel(int id);
    CarModel addCarModel(CarModel car);
    CarModel updateCarModel(CarModel car);
    Map<String, String> deleteCarModel(CarModel car);
}
