package com.ansekolesnikov.cargologistic.database.repository;

import com.ansekolesnikov.cargologistic.annotations.CargoPack;
import com.ansekolesnikov.cargologistic.entity.PackModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@CargoPack
@Repository
public interface PackModelRepository extends JpaRepository<PackModelEntity, Integer> {
    PackModelEntity findByName(String name);
    PackModelEntity findByCode(Character code);
}
