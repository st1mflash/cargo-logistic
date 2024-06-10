package com.ansekolesnikov.cargologistic.database.repository;

import com.ansekolesnikov.cargologistic.entity.CarModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarModelRepository extends JpaRepository<CarModelEntity, Integer> {
    CarModelEntity findByName(String name);
}
