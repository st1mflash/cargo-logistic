package com.ansekolesnikov.cargologistic.database.dao;

import com.ansekolesnikov.cargologistic.entity.PackModel;
import com.ansekolesnikov.cargologistic.database.repository.PackModelRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class PackModelDao {
    private final PackModelRepository packModelRepository;

    public PackModelDao(PackModelRepository packModelRepository) {
        this.packModelRepository = packModelRepository;
    }

    public PackModel findById(int id) {
        return packModelRepository.findById(id).orElse(null);
    }

    public PackModel findByName(String name) {
        return packModelRepository.findByName(name);
    }

    public PackModel findByCode(Character code) {
        return packModelRepository.findByCode(code);
    }

    public List<PackModel> findAll() {
        return packModelRepository.findAll();
    }

    public PackModel insert(PackModel packModel) {
        return packModelRepository.save(packModel);
    }

    public PackModel update(PackModel packModel) {
        return packModelRepository.save(packModel);
    }

    public void delete(PackModel packModel) {
        packModelRepository.delete(packModel);
    }
}
