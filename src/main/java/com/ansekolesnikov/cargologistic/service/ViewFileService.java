package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.service.service_input.ServiceInput;
import com.ansekolesnikov.cargologistic.service.service_input.ViewFileServiceInput;
import com.ansekolesnikov.cargologistic.entity.LocalFile;
import com.ansekolesnikov.cargologistic.service.service_output.ServiceOutput;
import com.ansekolesnikov.cargologistic.interfaces.RunnableService;
import com.ansekolesnikov.cargologistic.service.service_output.ViewFileServiceOutput;
import com.ansekolesnikov.cargologistic.service.utils.ViewFileServiceUtils;
import com.ansekolesnikov.cargologistic.validation.FileValidation;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class ViewFileService implements RunnableService {
    @Value("${directory.car.import}")
    private String PATH_IMPORT_CAR;
    @Autowired
    ViewFileServiceUtils serviceUtils;

    @Override
    public ServiceOutput runService(ServiceInput serviceInput) {
        ViewFileServiceInput viewFileServiceInput = serviceInput.getViewFileServiceInput();
        LocalFile localFile = new LocalFile(PATH_IMPORT_CAR + viewFileServiceInput.getFileName());
        FileValidation fileValidation = new FileValidation(localFile);
        ViewFileServiceOutput result = new ViewFileServiceOutput();

        if (fileValidation.isValid()) {
            result.setText(serviceUtils.getCarsInfoFromFile(localFile));
        } else {
            result.setText(fileValidation.getUserErrorMessage());
        }
        return result;
    }
}