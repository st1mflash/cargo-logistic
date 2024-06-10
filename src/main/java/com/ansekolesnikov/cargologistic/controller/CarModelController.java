package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.entity.CarModel;
import com.ansekolesnikov.cargologistic.service.CarModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/car_model")
public class CarModelController {
    private final CarModelService carModelService;

    @GetMapping("/{id}")
    public CarModel getCarModel(@PathVariable int id) {
        return carModelService.getCarModel(id);
    }

    @GetMapping
    public List<CarModel> getCarModelList() {
        return carModelService.getCarModelList();
    }

    @PostMapping
    public CarModel addCarModel(@RequestBody CarModel carModel) {
        return carModelService.addCarModel(carModel);
    }

    @PutMapping
    public CarModel updateCarModel(@RequestBody CarModel carModel) {
        return carModelService.updateCarModel(carModel);
    }

    @DeleteMapping
    public Map<String, String> deleteCarModel(@RequestBody CarModel carModel) {
        return carModelService.deleteCarModel(carModel);
    }
}
