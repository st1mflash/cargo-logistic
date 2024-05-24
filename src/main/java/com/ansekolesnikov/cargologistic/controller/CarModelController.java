package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.main.car.CarService;
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
    private CarService carService;

    @GetMapping("/{id}")
    public Map<String, String> getCarModel(@PathVariable int id) {
        CommandLine command = new CommandLine("car get " + id);
        return carService.runService(command)
                .toMap();
    }

    @GetMapping
    public List<Map<String, String>> getListCarModels() {
        CommandLine command = new CommandLine("car list");
        return carService.runService(command)
                .toListMap();
    }

    @PostMapping
    public Map<String, String> createCarModel(@RequestBody Map<String, String> carModel) {
        CommandLine command = new CommandLine(
                "car insert " + carModel.get("name") + " " +
                        carModel.get("width") + " " +
                        carModel.get("height")
        );
        return carService.runService(command)
                .toMap();
    }

    @PutMapping
    public Map<String, String> updateCarModel(@RequestBody Map<String, String> carModel) {
        Map<String, String> result = new HashMap<>();
        try {
            for (String key : carModel.keySet()) {
                if (!Objects.equals(key, "id")) {
                    CommandLine command = new CommandLine("car update " + carModel.get("id") + " " +
                            key + " " + carModel.get(key)
                    );
                    result = carService.runService(command).toMap();
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
        CommandLine command = new CommandLine("car delete " + carModel.get("id"));
        Map<String, String> result = new HashMap<>();
        if(carService.runService(command).toMap() != null) {
            result.put("status", "success");
        } else {
            result.put("status", "fail");
        }
        return result;
    }

}
