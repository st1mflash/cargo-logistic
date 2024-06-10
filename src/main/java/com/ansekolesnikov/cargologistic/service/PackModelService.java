package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.entity.PackModel;
import com.ansekolesnikov.cargologistic.interfaces.IPackModelService;
import com.ansekolesnikov.cargologistic.service.service_input.ServiceRequest;
import com.ansekolesnikov.cargologistic.service.service_output.ServiceOutput;
import com.ansekolesnikov.cargologistic.interfaces.EntityService;
import com.ansekolesnikov.cargologistic.interfaces.RunnableService;
import com.ansekolesnikov.cargologistic.service.service_output.PackModelServiceOutput;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PackModelService implements
        RunnableService,
        EntityService,
        IPackModelService {
    private final PackModelDao packModelDao;
    private static final Logger LOGGER = Logger.getLogger(PackModelService.class.getName());

    public PackModelService(PackModelDao packModelDao) {
        this.packModelDao = packModelDao;
    }

    @Override
    public ServiceOutput runService(ServiceRequest command) {
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
    public ServiceOutput getOperation(ServiceRequest command) {
        PackModelServiceOutput serviceOutput = new PackModelServiceOutput();
        serviceOutput.create(packModelDao.findById(command.getPackModelServiceInput().getIdPack()));
        return serviceOutput;
    }

    @Override
    public ServiceOutput insertOperation(ServiceRequest command) {
        PackModelServiceOutput serviceOutput = new PackModelServiceOutput();
        PackModel packModel = PackModel.builder()
                .name(command.getPackModelServiceInput().getNamePack())
                .width(command.getPackModelServiceInput().getWidthSchemePack())
                .height(command.getPackModelServiceInput().getHeightSchemePack())
                .scheme(command.getPackModelServiceInput().getSchemePack())
                .code(command.getPackModelServiceInput().getCodePack())
                .build();
        packModelDao.insert(packModel);
        serviceOutput.create(packModel);
        return serviceOutput;
    }

    @Override
    public ServiceOutput updateOperation(ServiceRequest command) {
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
    public ServiceOutput deleteOperation(ServiceRequest command) {
        PackModelServiceOutput serviceOutput = new PackModelServiceOutput();
        PackModel packModel = packModelDao.findById(command.getPackModelServiceInput().getIdPack());
        packModelDao.delete(packModel);
        serviceOutput.create(packModel);
        return serviceOutput;
    }

    @Override
    public PackModel getPackModel(int id) {
        return packModelDao.findById(id);
    }

    @Override
    public List<PackModel> getPackModelList() {
        return packModelDao.findAll();
    }

    @Override
    public PackModel addPackModel(PackModel packModel) {
        return packModelDao.insert(packModel);
    }

    @Override
    public PackModel updatePackModel(PackModel packModel) {
        return packModelDao.update(packModel);
    }

    @Override
    public Map<String, String> deletePackModel(PackModel packModel) {
        Map<String, String> result = new HashMap<>();
        try {
            packModelDao.delete(packModel);
            result.put("status", "success");
            return result;
        } catch (RuntimeException e) {
            result.put("status", "failed");
            return result;
        }
    }
}
