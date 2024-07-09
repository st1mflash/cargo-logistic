package com.ansekolesnikov.cargologistic.interfaces;

import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.Pack;
import com.ansekolesnikov.cargologistic.repository.PackModelRepository;

public interface ICarService {
    void loadPack(Car car, Pack pack);

    int calcPercentLoad(Car car);

    String toStringCarCargoScheme(Car car);

    String toStringCarInfo(Car car, PackModelRepository packModelRepository);

    int calculateCountPackInCarByCode(Car car, Character code, PackModelRepository packModelRepository);
}
