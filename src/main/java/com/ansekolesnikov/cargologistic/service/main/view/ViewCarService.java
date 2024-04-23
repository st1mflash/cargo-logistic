package com.ansekolesnikov.cargologistic.service.main.view;

import com.ansekolesnikov.cargologistic.model.file.LocalFile;
import com.ansekolesnikov.cargologistic.service.main.CargoService;
import com.ansekolesnikov.cargologistic.validation.FileValidation;
import org.springframework.stereotype.Service;

@Service
public class ViewCarService implements CargoService {
    private static final String PATH_IMPORT = "src/main/resources/import/cargo/";

    public ViewCarService() {

    }

    @Override
    public String runService(String fileName) {
        LocalFile localFile = new LocalFile(PATH_IMPORT + fileName);
        FileValidation fileValidation = new FileValidation(localFile);

        if (fileValidation.isValid()) {
            return new ViewCarServiceUtils().getCarsInfoFromFile(localFile);
        } else {
            return fileValidation.getUserErrorMessage();
        }
    }
}