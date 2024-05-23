package com.ansekolesnikov.cargologistic.controller.rest.delete;


import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.service.main.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/car_model")
public class DeleteCarModelRestApiController {
    @Autowired
    private CarService carService;

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
