package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.annotations.CargoCar;
import com.ansekolesnikov.cargologistic.dto.CarModelDto;
import com.ansekolesnikov.cargologistic.service.CarModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CargoCar
@RequiredArgsConstructor
@RestController
@RequestMapping("api/car_model")
public class CarModelController {
    private final CarModelService carModelService;

    @GetMapping("/{id}")
    public CarModelDto getCarModel(@PathVariable int id) {
        return carModelService.getCarModel(id);
    }

    @GetMapping
    public List<CarModelDto> getCarModelList() {
        return carModelService.getCarModelList();
    }

    @PostMapping
    public CarModelDto addCarModel(@RequestBody CarModelDto carModelDto) {
        return carModelService.addCarModel(carModelDto);
    }

    @PutMapping
    public CarModelDto updateCarModel(@RequestBody CarModelDto carModelDto) {
        return carModelService.updateCarModel(carModelDto);
    }

    @DeleteMapping
    public Map<String, String> deleteCarModel(@RequestBody CarModelDto carModelDto) {
        return carModelService.deleteCarModel(carModelDto.getId());
    }
}
