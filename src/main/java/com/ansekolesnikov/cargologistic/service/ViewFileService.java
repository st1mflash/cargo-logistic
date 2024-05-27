package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.entity.Car;
import com.ansekolesnikov.cargologistic.entity.LocalFile;
import com.ansekolesnikov.cargologistic.entity.utils.CarUtils;
import com.ansekolesnikov.cargologistic.entity.utils.LocalFileImportUtils;
import com.ansekolesnikov.cargologistic.interfaces.RunnableService;
import com.ansekolesnikov.cargologistic.service.service_input.ServiceInput;
import com.ansekolesnikov.cargologistic.service.service_output.ServiceOutput;
import com.ansekolesnikov.cargologistic.service.service_output.ViewFileServiceOutput;
import com.ansekolesnikov.cargologistic.validation.FileValidation;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Service
public class ViewFileService implements RunnableService {
    @Value("${directory.car.import}")
    private String PATH_IMPORT_CAR;
    @Autowired
    private LocalFileImportUtils localFileImportUtils;
    @Autowired
    private CarUtils carUtils;

    @Override
    public ServiceOutput runService(ServiceInput serviceInput) {
        LocalFile localFile = new LocalFile(PATH_IMPORT_CAR + serviceInput.getViewFileServiceInput().getFileName());
        FileValidation fileValidation = new FileValidation(localFile);
        ViewFileServiceOutput serviceOutput = new ViewFileServiceOutput();

        if (fileValidation.isValid()) {
            serviceOutput.setText(toStringCarsFromFile(localFile));
        } else {
            serviceOutput.setText(fileValidation.getUserErrorMessage());
        }
        return serviceOutput;
    }

    public String toStringCarsFromFile(LocalFile localFile) {
        List<Car> importedCarList = localFileImportUtils.importCarsFromFile(localFile);
        if (importedCarList != null) {
            return toStringListCars(importedCarList);
        } else {
            return "Указанный файл не содержит информации о грузовиках.";
        }
    }

    public String toStringListCars(List<Car> carList) {
        StringBuilder result = new StringBuilder();
        for (Car car : carList) {
            result.append(carUtils.toStringCarInfo(car));
        }
        return result.toString();
    }
}