package com.ansekolesnikov.cargologistic.repository;

import com.ansekolesnikov.cargologistic.entity.PackModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackModelRepository extends JpaRepository<PackModelEntity, Integer> {
    PackModelEntity findByName(String name);
    PackModelEntity findByCode(Character code);
}
