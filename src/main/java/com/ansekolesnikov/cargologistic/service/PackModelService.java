package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.constants.MessageConstant;
import com.ansekolesnikov.cargologistic.dto.PackModelDto;
import com.ansekolesnikov.cargologistic.entity.PackModelEntity;
import com.ansekolesnikov.cargologistic.entity.RequestString;
import com.ansekolesnikov.cargologistic.enums.DatabaseOperationEnum;
import com.ansekolesnikov.cargologistic.enums.PackModelParameterEnum;
import com.ansekolesnikov.cargologistic.interfaces.IPackModelService;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import com.ansekolesnikov.cargologistic.mappers.PackModelMapper;
import com.ansekolesnikov.cargologistic.repository.PackModelRepository;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PackModelService implements
        IRunnableByStringService,
        IPackModelService {
    private final PackModelMapper packModelMapper;
    private final PackModelRepository packModelRepository;
    private static final Logger LOGGER = Logger.getLogger(PackModelService.class.getName());

    @Override
    public PackModelDto getPackModel(int id) {
        LOGGER.info(MessageConstant.PACK_MODEL_INFO_REQUEST);
        return packModelMapper.toDto(packModelRepository.findById(id).orElse(null));
    }

    @Override
    public List<PackModelDto> getPackModelList() {
        LOGGER.info(MessageConstant.PACK_MODEL_LIST_INFO_REQUEST);
        return packModelRepository.findAll().stream()
                .map(packModelMapper::toDto)
                .toList();
    }

    @Override
    public PackModelDto addPackModel(PackModelDto packModelDto) {
        LOGGER.info(MessageConstant.PACK_MODEL_INSERT_REQUEST);
        return packModelMapper.toDto(
                packModelRepository.save(packModelMapper.toEntity(packModelDto))
        );
    }

    @Override
    public PackModelDto updatePackModel(PackModelDto packModelDto) {
        LOGGER.info(MessageConstant.PACK_MODEL_UPDATE_REQUEST);
        return packModelMapper.toDto(
                packModelRepository.save(packModelMapper.toEntity(packModelDto))
        );
    }

    @Override
    public Map<String, String> deletePackModel(int id) {
        LOGGER.info(MessageConstant.PACK_MODEL_DELETE_REQUEST);
        Map<String, String> result = new HashMap<>();
        try {
            packModelRepository.delete(Objects.requireNonNull(packModelRepository.findById(id).orElse(null)));
            result.put("status", "success");
            return result;
        } catch (RuntimeException e) {
            result.put("status", "failed");
            return result;
        }
    }

    @Override
    public String run(RequestString request) {
        try {
            DatabaseOperationEnum operation = request.getOperation();
            return switch (Objects.requireNonNull(operation)) {
                case GET -> processGetOperationToString(request);
                case LIST -> processListOperationToString();
                case INSERT -> processInsertOperationToString(request);
                case UPDATE -> processUpdateOperationToString(request);
                case DELETE -> processDeleteOperationToString(request);
            };
        } catch (RuntimeException e) {
            return MessageConstant.UNKNOWN_COMMAND;
        }
    }

    private String processGetOperationToString(RequestString request) {
        return toStringPackInfo(packModelMapper.toEntity(getPackModel(request.getEntityId())));
    }

    private String processListOperationToString() {
        StringBuilder packList = new StringBuilder();
        getPackModelList().stream()
                .map(packModelMapper::toEntity)
                .forEach(p -> packList.append(toStringPackInfo(p)).append("\n\n"));
        return packList.toString();
    }

    private String processInsertOperationToString(RequestString request) {
        PackModelDto packModelDto = PackModelDto.builder()
                .name(request.getEntityName())
                .code(request.getEntityCode())
                .scheme(request.getEntityScheme())
                .width(request.getEntityWidth())
                .height(request.getEntityHeight())
                .build();
        return toStringPackInfo(packModelMapper.toEntity(addPackModel(packModelDto)));
    }

    private String processUpdateOperationToString(RequestString request) {
        return toStringPackInfo(packModelMapper.toEntity(updatePackByParams(request)));
    }

    private String processDeleteOperationToString(RequestString request) {
        deletePackModel(request.getEntityId());
        return MessageConstant.SUCCESS_DELETE;
    }

    private PackModelDto updatePackByParams(RequestString request) {
        PackModelParameterEnum parameterEnum = request.getPackModelParameterName();
        String value = request.getEntityParameterValue();
        PackModelDto packModelDto = packModelMapper.toDto(packModelRepository.findById(request.getEntityId()).orElse(null));
        switch (Objects.requireNonNull(parameterEnum)) {
            case NAME -> packModelDto.setName(value);
            case CODE -> packModelDto.setCode(value.charAt(0));
            case SCHEME -> packModelDto.setScheme(value);
            case WIDTH -> packModelDto.setWidth(Integer.parseInt(value));
            case HEIGHT -> packModelDto.setHeight(Integer.parseInt(value));
        }
        return updatePackModel(packModelDto);
    }

    private String toStringPackInfo(PackModelEntity packModelEntity) {
        return "Идентификатор: #" + packModelEntity.getId()
                + "\nНазвание посылки: " + packModelEntity.getName()
                + "\nПараметры посылки: " + packModelEntity.getWidth() + "x" + packModelEntity.getHeight()
                + "\nСхема посылки:\n"
                + toStringPackScheme(packModelEntity);
    }

    private String toStringPackScheme(PackModelEntity packModelEntity) {
        StringBuilder packSchemeToString = new StringBuilder();
        String packScheme = packModelEntity.getScheme();
        for (int i = packModelEntity.getHeight() - 1; i >= 0; i--) {
            packSchemeToString.append("+");
            for (int j = 0; j < packModelEntity.getWidth(); j++) {
                if (Objects.equals("" + packScheme.charAt(i * packModelEntity.getWidth() + j), "0")) {
                    packSchemeToString.append(" ");
                } else {
                    packSchemeToString.append(packModelEntity.getCode());
                }
            }
            packSchemeToString.append("+\n");
        }
        packSchemeToString.append("+".repeat(Math.max(0, packModelEntity.getWidth() + 2)));
        return packSchemeToString.toString();
    }

}
