package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.pack.PackModel;
import com.ansekolesnikov.cargologistic.service.service_input.ServiceInput;
import com.ansekolesnikov.cargologistic.service.service_output.ServiceOutput;
import com.ansekolesnikov.cargologistic.interfaces.EntityService;
import com.ansekolesnikov.cargologistic.interfaces.RunnableService;
import com.ansekolesnikov.cargologistic.service.service_output.PackModelServiceOutput;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class PackModelService implements RunnableService, EntityService {
    @Autowired
    private PackModelDao packModelDao;
    private static final Logger LOGGER = Logger.getLogger(PackModelService.class.getName());

    @Override
    public ServiceOutput runService(ServiceInput command) {
        try {
            return switch (command.getPackModelServiceInput().getOperation()) {
                case LIST -> listOperation();
                case GET -> getOperation(command);
                case INSERT -> insertOperation(command);
                case UPDATE -> updateOperation(command);
                case DELETE -> deleteOperation(command);
            };
        } catch (RuntimeException e) {
            LOGGER.error("Ошибка ввода команды. Текст команды: " + command.getPackModelServiceInput().getText());
            PackModelServiceOutput serviceOutput = new PackModelServiceOutput();
            serviceOutput.setText(
                    "Ошибка ввода.\n" +
                            "Проверьте правильность введенной операции (доступные: INSERT/UPDATE/DELETE/LIST)." + e
            );
            return serviceOutput;
        }
    }

    @Override
    public ServiceOutput listOperation() {
        PackModelServiceOutput serviceOutput = new PackModelServiceOutput();
        serviceOutput.create(packModelDao.findAll());
        return serviceOutput;
    }

    @Override
    public ServiceOutput getOperation(ServiceInput command) {
        PackModelServiceOutput serviceOutput = new PackModelServiceOutput();
        serviceOutput.create(packModelDao.findById(command.getPackModelServiceInput().getIdPack()));
        return serviceOutput;
    }

    @Override
    public ServiceOutput insertOperation(ServiceInput command) {
        PackModelServiceOutput serviceOutput = new PackModelServiceOutput();
        PackModel packModel = new PackModel(
                command.getPackModelServiceInput().getNamePack(),
                command.getPackModelServiceInput().getWidthSchemePack(),
                command.getPackModelServiceInput().getHeightSchemePack(),
                command.getPackModelServiceInput().getSchemePack(),
                command.getPackModelServiceInput().getCodePack()
        );
        packModelDao.insert(packModel);
        serviceOutput.create(packModel);
        return serviceOutput;
    }

    @Override
    public ServiceOutput updateOperation(ServiceInput command) {
        PackModelServiceOutput serviceOutput = new PackModelServiceOutput();
        PackModel packModel = packModelDao.findById(command.getPackModelServiceInput().getIdPack());
        switch (command.getPackModelServiceInput().getUpdatedParamName()) {
            case NAME:
                packModel.setName(
                        command.getPackModelServiceInput()
                                .getUpdatedParamValue()
                );
                break;
            case CODE:
                packModel.setCode(
                        command.getPackModelServiceInput()
                                .getUpdatedParamValue()
                                .charAt(0)
                );
                break;
            case SCHEME:
                packModel.setScheme(
                        command.getPackModelServiceInput()
                                .getUpdatedParamValue()
                );
                break;
            case WIDTH:
                packModel.setWidth(
                        Integer.parseInt(command.getPackModelServiceInput()
                                .getUpdatedParamValue())
                );
                break;
            case HEIGHT:
                packModel.setHeight(
                        Integer.parseInt(command.getPackModelServiceInput()
                                .getUpdatedParamValue())
                );
                break;
            default:
                break;
        }
        packModelDao.update(packModel);
        serviceOutput.create(packModel);
        return serviceOutput;
    }

    @Override
    public ServiceOutput deleteOperation(ServiceInput command) {
        PackModelServiceOutput serviceOutput = new PackModelServiceOutput();
        PackModel packModel = packModelDao.findById(command.getPackModelServiceInput().getIdPack());
        packModelDao.delete(packModel);
        serviceOutput.create(packModel);
        return serviceOutput;
    }
}
