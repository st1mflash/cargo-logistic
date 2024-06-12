package com.ansekolesnikov.cargologistic.database.dao;

import com.ansekolesnikov.cargologistic.annotations.CargoCar;
import com.ansekolesnikov.cargologistic.annotations.Dao;
import com.ansekolesnikov.cargologistic.database.repository.CarModelRepository;
import com.ansekolesnikov.cargologistic.entity.CarModelEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Dao
@CargoCar
@RequiredArgsConstructor
@Component
@Transactional
public class CarModelDao {
    private final CarModelRepository carModelRepository;

    public CarModelEntity findById(int id) {
        return carModelRepository.findById(id).orElse(null);
    }

    public CarModelEntity findByName(String name) {
        return carModelRepository.findByName(name);
    }

    public List<CarModelEntity> findAll() {
        return carModelRepository.findAll();
    }

    public CarModelEntity insert(CarModelEntity carModelEntity) {
        return carModelRepository.save(carModelEntity);
    }

    public CarModelEntity update(CarModelEntity carModelEntity) {
        return carModelRepository.save(carModelEntity);
    }

    public void delete(int id) {
        carModelRepository.deleteById(id);
    }
}
