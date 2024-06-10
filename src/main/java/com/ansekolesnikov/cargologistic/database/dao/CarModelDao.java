package com.ansekolesnikov.cargologistic.database.dao;

import com.ansekolesnikov.cargologistic.dto.CarModelDto;
import com.ansekolesnikov.cargologistic.entity.CarModelEntity;
import com.ansekolesnikov.cargologistic.database.repository.CarModelRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Component
@Transactional
public class CarModelDao {
    private final CarModelRepository carModelRepository;

    public CarModelDao(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    public CarModelDto findById(int id) {
        return CarModelDto.to(Objects.requireNonNull(carModelRepository.findById(id).orElse(null)));
    }

    public CarModelEntity findByName(String name) {
        return carModelRepository.findByName(name);
    }

    public List<CarModelDto> findAll() {
        return carModelRepository.findAll().stream()
                .map(CarModelDto::to)
                .toList();
    }

    public CarModelDto insert(CarModelDto carModelDto) {
        return CarModelDto.to(carModelRepository.save(CarModelEntity.to(carModelDto)));
    }

    public CarModelDto update(CarModelDto carModelDto) {
        carModelRepository.save(CarModelEntity.to(carModelDto));
        return carModelDto;
    }

    public void delete(int id) {
        carModelRepository.deleteById(id);
    }
}
