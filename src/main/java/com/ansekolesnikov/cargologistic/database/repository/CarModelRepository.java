package com.ansekolesnikov.cargologistic.database.repository; //todo вынести папку repository из database

import com.ansekolesnikov.cargologistic.annotations.CargoCar;
import com.ansekolesnikov.cargologistic.entity.CarModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@CargoCar
@Repository
public interface CarModelRepository extends JpaRepository<CarModelEntity, Integer> {
    CarModelEntity findByName(String name);
}
