package com.ansekolesnikov.cargologistic.repository;

import com.ansekolesnikov.cargologistic.model.car.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Integer> {
    CarModel findByNameModel(String name);
}
