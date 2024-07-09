package com.ansekolesnikov.cargologistic.interfaces;

import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.LocalFile;
import com.ansekolesnikov.cargologistic.entity.Pack;
import com.ansekolesnikov.cargologistic.mappers.CarModelMapper;
import com.ansekolesnikov.cargologistic.mappers.PackModelMapper;
import com.ansekolesnikov.cargologistic.repository.PackModelRepository;

import java.util.List;

public interface ILocalFileService {
    List<Car> importCarsFromFile(LocalFile localFile, CarModelMapper carModelMapper);

    List<Pack> importPacksFromFile(LocalFile localFile, PackModelRepository packModelRepository, PackModelMapper packModelMapper);
}
