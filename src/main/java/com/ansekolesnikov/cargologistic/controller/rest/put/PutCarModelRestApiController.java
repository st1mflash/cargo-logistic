package com.ansekolesnikov.cargologistic.controller.rest.put;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.main.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("api/car_model")
public class PutCarModelRestApiController {
    @Autowired
    private CarService carService;

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
}
