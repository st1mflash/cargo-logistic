package com.ansekolesnikov.cargologistic.service.cargo.view_file;

import com.ansekolesnikov.cargologistic.model.command.CommandLine;
import com.ansekolesnikov.cargologistic.model.command.view_file.ViewFileCommandLine;
import com.ansekolesnikov.cargologistic.model.file.LocalFile;
import com.ansekolesnikov.cargologistic.service.cargo.CargoService;
import com.ansekolesnikov.cargologistic.validation.FileValidation;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class ViewFileCargoService implements CargoService {
    @Value("${directory.car.import}")
    private String PATH_IMPORT_CAR;
    @Autowired
    ViewFileCargoServiceUtils serviceUtils;
    private ViewFileCommandLine viewFileCommandLine;

    @Override
    public String runService(CommandLine commandLine) {
        viewFileCommandLine = commandLine.getViewFileCommandLine();
        LocalFile localFile = new LocalFile(PATH_IMPORT_CAR + viewFileCommandLine.getFileName());
        FileValidation fileValidation = new FileValidation(localFile);

        if (fileValidation.isValid()) {
            return serviceUtils.getCarsInfoFromFile(localFile);
        } else {
            return fileValidation.getUserErrorMessage();
        }
    }
}