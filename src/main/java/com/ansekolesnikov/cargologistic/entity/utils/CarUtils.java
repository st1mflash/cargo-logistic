package com.ansekolesnikov.cargologistic.entity.utils;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.Pack;
import com.ansekolesnikov.cargologistic.entity.PackModel;
import com.ansekolesnikov.cargologistic.entity.algorithms.LoadAlgorithmHalf;
import com.ansekolesnikov.cargologistic.entity.algorithms.LoadAlgorithmMax;
import com.ansekolesnikov.cargologistic.entity.algorithms.LoadAlgorithmType;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@NoArgsConstructor
@Component
public class CarUtils {
    @Autowired
    private PackModelDao packModelDao;
    @Autowired
    private LoadAlgorithmMax loadAlgorithmMax;
    @Autowired
    private LoadAlgorithmHalf loadAlgorithmHalf;
    @Autowired
    private LoadAlgorithmType loadAlgorithmType;

    public void loadPackToCar(Car car, Pack pack, AlgorithmEnum algorithm) {
        switch (algorithm) {
            case MAX:
                loadAlgorithmMax.load(car, pack);
                break;
            case HALF:
                loadAlgorithmHalf.load(car, pack);
                break;
            case TYPE:
                loadAlgorithmType.load(car, pack);
                break;
            default:
                break;
        }
    }

    public String toStringCarInfo(Car car) {
        StringBuilder fullInfoString = new StringBuilder(
                "Идентификатор: #" + car.getIdCar()
                        + "\nПараметры кузова: " + car.getCargoWidthModel() + "х" + car.getCargoHeightModel()
                        + "\nЗагруженность: " + car.calcPercentLoad() + "%"
                        + "\nСостав кузова:"
        );

        StringBuilder cargoString = new StringBuilder();
        for (int i = 0; i < car.getCargoHeightModel(); i++) {
            for (int j = 0; j < car.getCargoWidthModel(); j++) {
                cargoString.append(car.getCargo()[i][j]);
            }
        }

        for (Character code : Arrays.stream(cargoString.toString().split(""))
                .distinct()
                .map(c -> c.charAt(0))
                .filter(c -> c != '0')
                .toList()
        ) {
            int countPackages = calculateCountPackInCarByCode(car, code);
            fullInfoString.append((countPackages != 0 ? "\n- посылка '" + code + "': " + countPackages + " шт." : ""));
        }

        fullInfoString.append("\nСхема кузова:\n").append(car.toStringCarCargoScheme()).append("\n\n");
        return fullInfoString.toString();
    }

    public int calculateCountPackInCarByCode(Car car, Character code) {
        PackModel packModel = packModelDao.findByCode(code);
        int packSize = packModel.getScheme().replaceAll("0", "").length();
        return Arrays.deepToString(car.getCargo()).replaceAll("[^" + code + "]", "").length() / packSize;
    }
}
