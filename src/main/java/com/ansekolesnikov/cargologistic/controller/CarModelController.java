package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.service.service_input.ServiceInput;
import com.ansekolesnikov.cargologistic.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/car_model")
public class CarModelController {
    @Autowired
    private CarModelService carModelService;

    @GetMapping("/{id}")
    public Map<String, String> getCarModel(@PathVariable int id) {
        ServiceInput command = new ServiceInput("car get " + id);
        return carModelService.runService(command)
                .toMap();
    }

    @GetMapping
    public List<Map<String, String>> getListCarModels() {
        ServiceInput command = new ServiceInput("car list");
        return carModelService.runService(command)
                .toListMap();
    }

    @PostMapping
    public Map<String, String> createCarModel(@RequestBody Map<String, String> carModel) {
        ServiceInput command = new ServiceInput(
                "car insert " + carModel.get("name") + " " +
                        carModel.get("width") + " " +
                        carModel.get("height")
        );
        return carModelService.runService(command)
                .toMap();
    }

    @PutMapping
    public Map<String, String> updateCarModel(@RequestBody Map<String, String> carModel) {
        Map<String, String> result = new HashMap<>();
        try {
            for (String key : carModel.keySet()) {
                if (!Objects.equals(key, "id")) {
                    ServiceInput command = new ServiceInput("car update " + carModel.get("id") + " " +
                            key + " " + carModel.get(key)
                    );
                    result = carModelService.runService(command).toMap();
                }
            }
            return result;
        } catch (RuntimeException e) {
            result.clear();
            result.put("status", "fail");
            return result;
        }
    }

    @DeleteMapping
    public Map<String, String> deleteCarModel(@RequestBody Map<String, String> carModel) {
        ServiceInput command = new ServiceInput("car delete " + carModel.get("id"));
        Map<String, String> result = new HashMap<>();
        if(carModelService.runService(command).toMap() != null) {
            result.put("status", "success");
        } else {
            result.put("status", "fail");
        }
        return result;
    }

}
