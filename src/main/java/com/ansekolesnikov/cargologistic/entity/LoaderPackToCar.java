package com.ansekolesnikov.cargologistic.entity;

import com.ansekolesnikov.cargologistic.algorithms.LoadAlgorithmHalf;
import com.ansekolesnikov.cargologistic.algorithms.LoadAlgorithmMax;
import com.ansekolesnikov.cargologistic.algorithms.LoadAlgorithmType;
import com.ansekolesnikov.cargologistic.enums.AlgorithmEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
//todo Это сервис Selector а не entity
public class LoaderPackToCar {
    private final LoadAlgorithmMax loadAlgorithmMax;
    private final LoadAlgorithmHalf loadAlgorithmHalf;
    private final LoadAlgorithmType loadAlgorithmType;

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
}
