package com.ansekolesnikov.cargologistic.database.dao;

import com.ansekolesnikov.cargologistic.annotations.Dao;
import com.ansekolesnikov.cargologistic.database.repository.PackModelRepository;
import com.ansekolesnikov.cargologistic.dto.PackModelDto;
import com.ansekolesnikov.cargologistic.entity.PackModelEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Dao
@RequiredArgsConstructor
@Component
@Transactional
public class PackModelDao {
    private final PackModelRepository packModelRepository;

    public PackModelDto findById(int id) {
        return PackModelDto.to(Objects.requireNonNull(packModelRepository.findById(id).orElse(null)));
    }

    public PackModelDto findByName(String name) {
        return packModelRepository.findByName(name);
    }

    public PackModelDto findByCode(Character code) {
        return packModelRepository.findByCode(code);
    }

    public List<PackModelDto> findAll() {
        return packModelRepository.findAll().stream()
                .map(PackModelDto::to)
                .toList();
    }

    public PackModelDto insert(PackModelDto packModelDto) {
        return PackModelDto.to(packModelRepository.save(PackModelEntity.to(packModelDto)));
    }

    public PackModelDto update(PackModelDto packModelDto) {
        return PackModelDto.to(packModelRepository.save(PackModelEntity.to(packModelDto)));
    }

    public void delete(int id) {
        packModelRepository.deleteById(id);
    }
}
