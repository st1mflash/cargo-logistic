package com.ansekolesnikov.cargologistic.pages;

import com.ansekolesnikov.cargologistic.constants.ButtonConstant;
import com.ansekolesnikov.cargologistic.enums.DatabaseOperationEnum;
import com.ansekolesnikov.cargologistic.service.CarModelService;
import com.ansekolesnikov.cargologistic.service.PackModelService;
import com.ansekolesnikov.cargologistic.states.TelegramUserState;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Data
public class TelegramUserStateConfigurator {
    private final TelegramPages telegramPages;
    private final PackModelService packModelService;
    private final CarModelService carModelService;

    public TelegramUserState updateUserStateByUserMessage(TelegramUserState telegramUserState, String textMessage) {
        switch (textMessage) {
            case ButtonConstant.BTN_PACK_LIST -> {
                telegramUserState.setService(packModelService);
                telegramUserState.setRequestString("pack list");
                telegramUserState.setOperation(DatabaseOperationEnum.LIST);
                telegramUserState.setPage(telegramPages.getTelegramResultPage());
            }
            case ButtonConstant.BTN_CAR_LIST -> {
                telegramUserState.setService(carModelService);
                telegramUserState.setRequestString("car list");
                telegramUserState.setOperation(DatabaseOperationEnum.LIST);
                telegramUserState.setPage(telegramPages.getTelegramResultPage());
            }
            case ButtonConstant.BTN_GET_PACK -> {
                telegramUserState.setOperation(DatabaseOperationEnum.GET);
                telegramUserState.setService(packModelService);
                telegramUserState.setPage(telegramPages.getTelegramGetIdPage());
            }
            case ButtonConstant.BTN_GET_CAR -> {
                telegramUserState.setOperation(DatabaseOperationEnum.GET);
                telegramUserState.setService(carModelService);
                telegramUserState.setPage(telegramPages.getTelegramGetIdPage());
            }
            case ButtonConstant.BTN_INSERT_PACK -> {
                telegramUserState.setOperation(DatabaseOperationEnum.INSERT);
                telegramUserState.setService(packModelService);
                telegramUserState.setPage(telegramPages.getTelegramGetNamePage());
            }
            case ButtonConstant.BTN_INSERT_CAR -> {
                telegramUserState.setOperation(DatabaseOperationEnum.INSERT);
                telegramUserState.setService(carModelService);
                telegramUserState.setPage(telegramPages.getTelegramGetNamePage());
            }
            case ButtonConstant.BTN_DELETE_PACK -> {
                telegramUserState.setOperation(DatabaseOperationEnum.DELETE);
                telegramUserState.setService(packModelService);
                telegramUserState.setPage(telegramPages.getTelegramGetIdPage());
            }
            case ButtonConstant.BTN_DELETE_CAR -> {
                telegramUserState.setOperation(DatabaseOperationEnum.DELETE);
                telegramUserState.setService(carModelService);
                telegramUserState.setPage(telegramPages.getTelegramGetIdPage());
            }
            case ButtonConstant.BTN_UPDATE_PACK -> {
                telegramUserState.setOperation(DatabaseOperationEnum.UPDATE);
                telegramUserState.setService(packModelService);
                telegramUserState.setPage(telegramPages.getTelegramGetIdPage());
            }
            case ButtonConstant.BTN_UPDATE_CAR -> {
                telegramUserState.setOperation(DatabaseOperationEnum.UPDATE);
                telegramUserState.setService(carModelService);
                telegramUserState.setPage(telegramPages.getTelegramGetIdPage());
            }
            case ButtonConstant.BTN_UPDATE_NAME -> {
                telegramUserState.setOperation(DatabaseOperationEnum.UPDATE);
                telegramUserState.setRequestString(telegramUserState.getRequestString() + " name");
                telegramUserState.setPage(telegramPages.getTelegramGetParamValuePage());
            }
            case ButtonConstant.BTN_UPDATE_WIDTH -> {
                telegramUserState.setOperation(DatabaseOperationEnum.UPDATE);
                telegramUserState.setRequestString(telegramUserState.getRequestString() + " width");
                telegramUserState.setPage(telegramPages.getTelegramGetParamValuePage());
            }
            case ButtonConstant.BTN_UPDATE_HEIGHT -> {
                telegramUserState.setOperation(DatabaseOperationEnum.UPDATE);
                telegramUserState.setRequestString(telegramUserState.getRequestString() + " height");
                telegramUserState.setPage(telegramPages.getTelegramGetParamValuePage());
            }
            case ButtonConstant.BTN_UPDATE_CODE -> {
                telegramUserState.setOperation(DatabaseOperationEnum.UPDATE);
                telegramUserState.setRequestString(telegramUserState.getRequestString() + " code");
                telegramUserState.setPage(telegramPages.getTelegramGetParamValuePage());
            }
            case ButtonConstant.BTN_UPDATE_SCHEME -> {
                telegramUserState.setOperation(DatabaseOperationEnum.UPDATE);
                telegramUserState.setRequestString(telegramUserState.getRequestString() + " scheme");
                telegramUserState.setPage(telegramPages.getTelegramGetParamValuePage());
            }
            default -> {
                if (telegramUserState.getService().getClass() == PackModelService.class
                        && telegramUserState.getOperation() == DatabaseOperationEnum.GET) {
                    telegramUserState.setRequestString("pack get " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramResultPage());
                } else if (telegramUserState.getService().getClass() == CarModelService.class
                        && telegramUserState.getOperation() == DatabaseOperationEnum.GET) {
                    telegramUserState.setRequestString("car get " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramResultPage());
                } else if (telegramUserState.getService().getClass() == PackModelService.class
                        && telegramUserState.getOperation() == DatabaseOperationEnum.INSERT
                        && telegramUserState.getPage() == telegramPages.getTelegramGetNamePage()) {
                    telegramUserState.setRequestString("pack insert " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramGetCodePage());
                } else if (telegramUserState.getService().getClass() == PackModelService.class
                        && telegramUserState.getOperation() == DatabaseOperationEnum.INSERT
                        && telegramUserState.getPage() == telegramPages.getTelegramGetCodePage()) {
                    telegramUserState.setRequestString(telegramUserState.getRequestString() + " " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramGetSchemePage());
                } else if (telegramUserState.getService().getClass() == PackModelService.class
                        && telegramUserState.getOperation() == DatabaseOperationEnum.INSERT
                        && telegramUserState.getPage() == telegramPages.getTelegramGetSchemePage()) {
                    telegramUserState.setRequestString(telegramUserState.getRequestString() + " " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramGetWidthPage());
                } else if (telegramUserState.getService().getClass() == PackModelService.class
                        && telegramUserState.getOperation() == DatabaseOperationEnum.INSERT
                        && telegramUserState.getPage() == telegramPages.getTelegramGetWidthPage()) {
                    telegramUserState.setRequestString(telegramUserState.getRequestString() + " " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramGetHeightPage());
                } else if (telegramUserState.getService().getClass() == PackModelService.class
                        && telegramUserState.getOperation() == DatabaseOperationEnum.INSERT
                        && telegramUserState.getPage() == telegramPages.getTelegramGetHeightPage()) {
                    telegramUserState.setRequestString(telegramUserState.getRequestString() + " " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramResultPage());
                } else if (telegramUserState.getService().getClass() == CarModelService.class
                        && telegramUserState.getOperation() == DatabaseOperationEnum.INSERT
                        && telegramUserState.getPage() == telegramPages.getTelegramGetNamePage()) {
                    telegramUserState.setRequestString("car insert " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramGetWidthPage());
                } else if (telegramUserState.getService().getClass() == CarModelService.class
                        && telegramUserState.getOperation() == DatabaseOperationEnum.INSERT
                        && telegramUserState.getPage() == telegramPages.getTelegramGetWidthPage()) {
                    telegramUserState.setRequestString(telegramUserState.getRequestString() + " " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramGetHeightPage());
                } else if (telegramUserState.getService().getClass() == CarModelService.class
                        && telegramUserState.getOperation() == DatabaseOperationEnum.INSERT
                        && telegramUserState.getPage() == telegramPages.getTelegramGetHeightPage()) {
                    telegramUserState.setRequestString(telegramUserState.getRequestString() + " " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramResultPage());
                } else if (telegramUserState.getService().getClass() == PackModelService.class
                        && telegramUserState.getOperation() == DatabaseOperationEnum.DELETE) {
                    telegramUserState.setRequestString("pack delete " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramResultPage());
                } else if (telegramUserState.getService().getClass() == CarModelService.class
                        && telegramUserState.getOperation() == DatabaseOperationEnum.DELETE) {
                    telegramUserState.setRequestString("car delete " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramResultPage());
                } else if (telegramUserState.getService().getClass() == PackModelService.class
                        && telegramUserState.getOperation() == DatabaseOperationEnum.UPDATE
                        && telegramUserState.getPage() == telegramPages.getTelegramGetIdPage()) {
                    telegramUserState.setRequestString("pack update " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramGetParamNamePage());
                } else if (telegramUserState.getService().getClass() == PackModelService.class
                        && telegramUserState.getOperation() == DatabaseOperationEnum.UPDATE
                        && telegramUserState.getPage() == telegramPages.getTelegramGetParamValuePage()) {
                    telegramUserState.setRequestString(telegramUserState.getRequestString() + " " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramResultPage());
                } else if (telegramUserState.getService().getClass() == CarModelService.class
                        && telegramUserState.getOperation() == DatabaseOperationEnum.UPDATE
                        && telegramUserState.getPage() == telegramPages.getTelegramGetIdPage()) {
                    telegramUserState.setRequestString("car update " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramGetParamNamePage());
                } else if (telegramUserState.getService().getClass() == CarModelService.class
                        && telegramUserState.getOperation() == DatabaseOperationEnum.UPDATE
                        && telegramUserState.getPage() == telegramPages.getTelegramGetParamValuePage()) {
                    telegramUserState.setRequestString(telegramUserState.getRequestString() + " " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramResultPage());
                }
            }
        }
        return telegramUserState;
    }
}
