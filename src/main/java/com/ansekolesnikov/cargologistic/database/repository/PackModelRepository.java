package com.ansekolesnikov.cargologistic.database.repository;

import com.ansekolesnikov.cargologistic.model.pack.PackModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackModelRepository extends JpaRepository<PackModel, Integer> {
    PackModel findByName(String name);
    PackModel findByCode(String code);
}