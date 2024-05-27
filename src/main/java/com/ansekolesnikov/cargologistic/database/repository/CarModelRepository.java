package com.ansekolesnikov.cargologistic.database.repository;

import com.ansekolesnikov.cargologistic.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Integer> {
    CarModel findByNameModel(String name);
}
