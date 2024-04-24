package com.ansekolesnikov.cargologistic.service.cargo.view;

import com.ansekolesnikov.cargologistic.model.file.LocalFile;
import com.ansekolesnikov.cargologistic.service.cargo.CargoService;
import com.ansekolesnikov.cargologistic.service.utils.ViewCargoServiceUtils;
import com.ansekolesnikov.cargologistic.validation.FileValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ViewCargoService implements CargoService {
    @Value("${directory.car.import}")
    private String PATH_IMPORT_CAR;
    @Autowired
    ViewCargoServiceUtils serviceUtils;

    public ViewCargoService() {

    }

    @Override
    public String runService(String fileName) {
        LocalFile localFile = new LocalFile(PATH_IMPORT_CAR + fileName);
        FileValidation fileValidation = new FileValidation(localFile);

        if (fileValidation.isValid()) {
            return serviceUtils.getCarsInfoFromFile(localFile);
        } else {
            return fileValidation.getUserErrorMessage();
        }
    }
}