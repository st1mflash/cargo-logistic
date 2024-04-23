package com.ansekolesnikov.cargologistic.service.main.view;

import com.ansekolesnikov.cargologistic.model.file.LocalFile;
import com.ansekolesnikov.cargologistic.service.main.CargoService;
import com.ansekolesnikov.cargologistic.validation.FileValidation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ViewCarService implements CargoService {
    @Value("${directory.car.import}")
    private String PATH_IMPORT_CAR;

    public ViewCarService() {

    }

    @Override
    public String runService(String fileName) {
        LocalFile localFile = new LocalFile(PATH_IMPORT_CAR + fileName);
        FileValidation fileValidation = new FileValidation(localFile);

        if (fileValidation.isValid()) {
            return new ViewCarServiceUtils().getCarsInfoFromFile(localFile);
        } else {
            return fileValidation.getUserErrorMessage();
        }
    }
}