package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.database.dao.PackModelDao;
import com.ansekolesnikov.cargologistic.dto.PackModelDto;
import com.ansekolesnikov.cargologistic.interfaces.EntityService;
import com.ansekolesnikov.cargologistic.interfaces.IPackModelService;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import com.ansekolesnikov.cargologistic.service.service_input.ServiceRequest;
import com.ansekolesnikov.cargologistic.service.service_output.PackModelServiceOutput;
import com.ansekolesnikov.cargologistic.service.service_output.ServiceOutput;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PackModelService implements
        IRunnableByStringService,
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
    public ServiceOutput listOperation() {/*
        PackModelServiceOutput serviceOutput = new PackModelServiceOutput();
        serviceOutput.create(packModelDao.findAll());*/
        return null;
    }

    @Override
    public ServiceOutput getOperation(ServiceRequest command) {/*
        PackModelServiceOutput serviceOutput = new PackModelServiceOutput();
        serviceOutput.create(packModelDao.findById(command.getPackModelServiceInput().getIdPack()));*/
        return null;
    }

    @Override
    public ServiceOutput insertOperation(ServiceRequest command) {
/*        PackModelServiceOutput serviceOutput = new PackModelServiceOutput();
        PackModelEntity packModelEntity = PackModelEntity.builder()
                .name(command.getPackModelServiceInput().getNamePack())
                .width(command.getPackModelServiceInput().getWidthSchemePack())
                .height(command.getPackModelServiceInput().getHeightSchemePack())
                .scheme(command.getPackModelServiceInput().getSchemePack())
                .code(command.getPackModelServiceInput().getCodePack())
                .build();
        packModelDao.insert(packModelEntity);
        serviceOutput.create(packModelEntity);*/
        return null;
    }

    @Override
    public ServiceOutput updateOperation(ServiceRequest command) {
/*        PackModelServiceOutput serviceOutput = new PackModelServiceOutput();
        PackModelEntity packModelEntity = packModelDao.findById(command.getPackModelServiceInput().getIdPack());
        switch (command.getPackModelServiceInput().getUpdatedParamName()) {
            case NAME:
                packModelEntity.setName(
                        command.getPackModelServiceInput()
                                .getUpdatedParamValue()
                );
                break;
            case CODE:
                packModelEntity.setCode(
                        command.getPackModelServiceInput()
                                .getUpdatedParamValue()
                                .charAt(0)
                );
                break;
            case SCHEME:
                packModelEntity.setScheme(
                        command.getPackModelServiceInput()
                                .getUpdatedParamValue()
                );
                break;
            case WIDTH:
                packModelEntity.setWidth(
                        Integer.parseInt(command.getPackModelServiceInput()
                                .getUpdatedParamValue())
                );
                break;
            case HEIGHT:
                packModelEntity.setHeight(
                        Integer.parseInt(command.getPackModelServiceInput()
                                .getUpdatedParamValue())
                );
                break;
            default:
                break;
        }
        packModelDao.update(packModelEntity);
        serviceOutput.create(packModelEntity);*/
        return null;
    }

    @Override
    public ServiceOutput deleteOperation(ServiceRequest command) {
/*        PackModelServiceOutput serviceOutput = new PackModelServiceOutput();
        PackModelEntity packModelEntity = packModelDao.findById(command.getPackModelServiceInput().getIdPack());
        packModelDao.delete(packModelEntity);
        serviceOutput.create(packModelEntity);*/
        return null;
    }

    @Override
    public PackModelDto getPackModel(int id) {
        return packModelDao.findById(id);
    }

    @Override
    public List<PackModelDto> getPackModelList() {
        return packModelDao.findAll();
    }

    @Override
    public PackModelDto addPackModel(PackModelDto packModelDto) {
        return packModelDao.insert(packModelDto);
    }

    @Override
    public PackModelDto updatePackModel(PackModelDto packModelDto) {
        return packModelDao.update(packModelDto);
    }

    @Override
    public Map<String, String> deletePackModel(PackModelDto packModelDto) {
        Map<String, String> result = new HashMap<>();
        try {
            packModelDao.delete(packModelDto.getId());
            result.put("status", "success");
            return result;
        } catch (RuntimeException e) {
            result.put("status", "failed");
            return result;
        }
    }
}
