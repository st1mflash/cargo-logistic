package com.ansekolesnikov.cargologistic.service.main.view;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.entity.command.view_file.ViewFileCommandLine;
import com.ansekolesnikov.cargologistic.entity.file.LocalFile;
import com.ansekolesnikov.cargologistic.service.main.ResultServiceRun;
import com.ansekolesnikov.cargologistic.service.main.RunnableService;
import com.ansekolesnikov.cargologistic.validation.FileValidation;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class ViewFileService implements RunnableService {
    private String pathImportCar;
    @Autowired
    ViewFileServiceUtils serviceUtils;

    public ViewFileService(String pathImportCar) {
        this.pathImportCar = pathImportCar;
    }

    @Override
    public ResultServiceRun runService(CommandLine commandLine) {
        ViewFileCommandLine viewFileCommandLine = commandLine.getViewFileCommandLine();
        LocalFile localFile = new LocalFile(pathImportCar + viewFileCommandLine.getFileName());
        FileValidation fileValidation = new FileValidation(localFile);
        ResultViewFileServiceRun result = new ResultViewFileServiceRun();

        if (fileValidation.isValid()) {
            result.setText(serviceUtils.getCarsInfoFromFile(localFile));
        } else {
            result.setText(fileValidation.getUserErrorMessage());
        }
        return result;
    }
}