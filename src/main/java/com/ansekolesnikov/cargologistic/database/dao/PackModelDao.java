package com.ansekolesnikov.cargologistic.database.dao;

import com.ansekolesnikov.cargologistic.annotations.CargoPack;
import com.ansekolesnikov.cargologistic.annotations.Dao;
import com.ansekolesnikov.cargologistic.database.repository.PackModelRepository;
import com.ansekolesnikov.cargologistic.entity.PackModelEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Dao
@CargoPack
@RequiredArgsConstructor
@Component
@Transactional
public class PackModelDao {
    private final PackModelRepository packModelRepository;

    public PackModelEntity findById(int id) {
        return packModelRepository.findById(id).orElse(null);
    }

    public PackModelEntity findByName(String name) {
        return packModelRepository.findByName(name);
    }

    public PackModelEntity findByCode(Character code) {
        return packModelRepository.findByCode(code);
    }

    public List<PackModelEntity> findAll() {
        return packModelRepository.findAll();
    }

    public PackModelEntity insert(PackModelEntity packModelEntity) {
        return packModelRepository.save(packModelEntity);
    }

    public PackModelEntity update(PackModelEntity packModelEntity) {
        return packModelRepository.save(packModelEntity);
    }

    public void delete(int id) {
        packModelRepository.deleteById(id);
    }
}
