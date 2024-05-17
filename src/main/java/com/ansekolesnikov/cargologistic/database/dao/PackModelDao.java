package com.ansekolesnikov.cargologistic.database.dao;

import com.ansekolesnikov.cargologistic.model.pack.PackModel;
import com.ansekolesnikov.cargologistic.database.repository.PackModelRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@NoArgsConstructor
@Component
@Transactional
public class PackModelDao {
    @Autowired
    private PackModelRepository packModelRepository;

    public PackModel findById(int id) {
        return packModelRepository.findById(id).orElse(null);
    }

    public PackModel findByName(String name) {
        return packModelRepository.findByName(name);
    }

    public PackModel findByCode(String code) {
        return packModelRepository.findByCode(code);
    }

    public void insert(PackModel packModel) {
        packModelRepository.save(packModel);
    }

    public void update(PackModel packModel) {
        packModelRepository.save(packModel);
    }

    public void delete(PackModel packModel) {
        packModelRepository.delete(packModel);
    }
}
