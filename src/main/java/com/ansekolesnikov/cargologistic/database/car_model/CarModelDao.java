package com.ansekolesnikov.cargologistic.database.car_model;

import com.ansekolesnikov.cargologistic.model.car.CarModel;
import com.ansekolesnikov.cargologistic.repository.CarModelRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@NoArgsConstructor
@Component
@Transactional
public class CarModelDao {
    @Autowired
    private CarModelRepository carModelRepository;

    public CarModel findById(int id) {
        return carModelRepository.findById(id).orElse(null);
    }

    public CarModel findByName(String name) {
        return carModelRepository.findByNameModel(name);
    }

    public void insert(CarModel carModel) {
        carModelRepository.save(carModel);
    }

    public void update(CarModel carModel) {
        carModelRepository.save(carModel);
    }

    public void delete(CarModel carModel) {
        carModelRepository.delete(carModel);
    }
}
