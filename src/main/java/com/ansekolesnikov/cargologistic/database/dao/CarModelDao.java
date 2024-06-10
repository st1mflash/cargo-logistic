package com.ansekolesnikov.cargologistic.database.dao;

import com.ansekolesnikov.cargologistic.entity.CarModel;
import com.ansekolesnikov.cargologistic.database.repository.CarModelRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class CarModelDao {
    private final CarModelRepository carModelRepository;

    public CarModelDao(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    public CarModel findById(int id) {
        return carModelRepository.findById(id).orElse(null);
    }

    public CarModel findByName(String name) {
        return carModelRepository.findByName(name);
    }

    public List<CarModel> findAll() {
        return carModelRepository.findAll();
    }

    public CarModel insert(CarModel carModel) {
        carModelRepository.save(carModel);
        return carModel;
    }

    public CarModel update(CarModel carModel) {
        return carModelRepository.save(carModel);
    }

    public void delete(CarModel carModel) {
        carModelRepository.delete(carModel);
    }
}
