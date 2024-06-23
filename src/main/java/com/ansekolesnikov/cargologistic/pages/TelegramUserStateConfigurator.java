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
            default -> {
                if (telegramUserState.getService().getClass() == packModelService.getClass()
                        && telegramUserState.getOperation() == DatabaseOperationEnum.GET) {
                    telegramUserState.setService(packModelService);
                    telegramUserState.setRequestString("pack get " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramResultPage());
                } else if (telegramUserState.getService().getClass() == carModelService.getClass()
                        && telegramUserState.getOperation() == DatabaseOperationEnum.GET) {
                    telegramUserState.setService(carModelService);
                    telegramUserState.setRequestString("car get " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramResultPage());
                } else if (telegramUserState.getService().getClass() == packModelService.getClass()
                        && telegramUserState.getOperation() == DatabaseOperationEnum.INSERT
                        && telegramUserState.getPage() == telegramPages.getTelegramGetNamePage()) {
                    telegramUserState.setService(packModelService);
                    telegramUserState.setRequestString("pack insert " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramGetCodePage());
                } else if (telegramUserState.getService().getClass() == packModelService.getClass()
                        && telegramUserState.getOperation() == DatabaseOperationEnum.INSERT
                        && telegramUserState.getPage() == telegramPages.getTelegramGetCodePage()) {
                    telegramUserState.setService(packModelService);
                    telegramUserState.setRequestString(telegramUserState.getRequestString() + " " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramGetSchemePage());
                } else if (telegramUserState.getService().getClass() == packModelService.getClass()
                        && telegramUserState.getOperation() == DatabaseOperationEnum.INSERT
                        && telegramUserState.getPage() == telegramPages.getTelegramGetSchemePage()) {
                    telegramUserState.setService(packModelService);
                    telegramUserState.setRequestString(telegramUserState.getRequestString() + " " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramGetWidthPage());
                } else if (telegramUserState.getService().getClass() == packModelService.getClass()
                        && telegramUserState.getOperation() == DatabaseOperationEnum.INSERT
                        && telegramUserState.getPage() == telegramPages.getTelegramGetWidthPage()) {
                    telegramUserState.setService(packModelService);
                    telegramUserState.setRequestString(telegramUserState.getRequestString() + " " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramGetHeightPage());
                } else if (telegramUserState.getService().getClass() == packModelService.getClass()
                        && telegramUserState.getOperation() == DatabaseOperationEnum.INSERT
                        && telegramUserState.getPage() == telegramPages.getTelegramGetHeightPage()) {
                    telegramUserState.setService(packModelService);
                    telegramUserState.setRequestString(telegramUserState.getRequestString() + " " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramResultPage());
                } else if (telegramUserState.getService().getClass() == carModelService.getClass()
                        && telegramUserState.getOperation() == DatabaseOperationEnum.INSERT
                        && telegramUserState.getPage() == telegramPages.getTelegramGetNamePage()) {
                    telegramUserState.setService(carModelService);
                    telegramUserState.setRequestString("car insert " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramGetWidthPage());
                } else if (telegramUserState.getService().getClass() == carModelService.getClass()
                        && telegramUserState.getOperation() == DatabaseOperationEnum.INSERT
                        && telegramUserState.getPage() == telegramPages.getTelegramGetWidthPage()) {
                    telegramUserState.setService(carModelService);
                    telegramUserState.setRequestString(telegramUserState.getRequestString() + " " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramGetHeightPage());
                } else if (telegramUserState.getService().getClass() == carModelService.getClass()
                        && telegramUserState.getOperation() == DatabaseOperationEnum.INSERT
                        && telegramUserState.getPage() == telegramPages.getTelegramGetHeightPage()) {
                    telegramUserState.setService(carModelService);
                    telegramUserState.setRequestString(telegramUserState.getRequestString() + " " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramResultPage());
                } else if (telegramUserState.getService().getClass() == packModelService.getClass()
                        && telegramUserState.getOperation() == DatabaseOperationEnum.DELETE) {
                    telegramUserState.setService(packModelService);
                    telegramUserState.setRequestString("pack delete " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramResultPage());
                } else if (telegramUserState.getService().getClass() == carModelService.getClass()
                        && telegramUserState.getOperation() == DatabaseOperationEnum.DELETE) {
                    telegramUserState.setService(carModelService);
                    telegramUserState.setRequestString("car delete " + textMessage);
                    telegramUserState.setPage(telegramPages.getTelegramResultPage());
                }
            }
        }
        return telegramUserState;
    }
}
