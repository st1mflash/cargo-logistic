package com.ansekolesnikov.cargologistic.pages;

import com.ansekolesnikov.cargologistic.constants.ButtonConstant;
import com.ansekolesnikov.cargologistic.enums.DatabaseOperationEnum;
import com.ansekolesnikov.cargologistic.enums.StateEnum;
import com.ansekolesnikov.cargologistic.service.*;
import com.ansekolesnikov.cargologistic.states.UserState;
import com.ansekolesnikov.cargologistic.states.UserStateChanger;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Data
public class UserStateConfigurator {
    private final UserStateChanger userStateChanger;
    private final TelegramPages telegramPages;
    private final PackModelService packModelService;
    private final CarModelService carModelService;
    private final ViewFileService viewFileService;
    private final LoadFileService loadFileService;
    private final LoadListService loadListService;

    public UserState updateUserStateByUserMessage(UserState userState, String textMessage) {
        switch (textMessage) {
            case ButtonConstant.BTN_PACK_LIST -> userStateChanger.changeState(userState, StateEnum.GET_LIST_PACK);
            case ButtonConstant.BTN_CAR_LIST -> userStateChanger.changeState(userState, StateEnum.GET_LIST_CAR);
            case ButtonConstant.BTN_GET_PACK -> userStateChanger.changeState(userState, StateEnum.INPUT_ID_FOR_GET_PACK);
            case ButtonConstant.BTN_GET_CAR -> userStateChanger.changeState(userState, StateEnum.INPUT_ID_FOR_GET_CAR);
            case ButtonConstant.BTN_INSERT_PACK -> userStateChanger.changeState(userState, StateEnum.INPUT_NAME_FOR_INSERT_PACK);
            case ButtonConstant.BTN_INSERT_CAR -> userStateChanger.changeState(userState, StateEnum.INPUT_NAME_FOR_INSERT_CAR);
            case ButtonConstant.BTN_DELETE_PACK -> userStateChanger.changeState(userState, StateEnum.INPUT_ID_FOR_DELETE_PACK);
            case ButtonConstant.BTN_DELETE_CAR -> userStateChanger.changeState(userState, StateEnum.INPUT_ID_FOR_DELETE_CAR);
            case ButtonConstant.BTN_UPDATE_PACK -> userStateChanger.changeState(userState, StateEnum.INPUT_ID_FOR_UPDATE_PACK);
            case ButtonConstant.BTN_UPDATE_CAR -> userStateChanger.changeState(userState, StateEnum.INPUT_ID_FOR_UPDATE_CAR);
            case ButtonConstant.BTN_UPDATE_NAME -> {
                if(userState.getState() == StateEnum.INPUT_ID_FOR_UPDATE_PACK) {
                    userStateChanger.changeState(userState, StateEnum.INPUT_NAME_FOR_UPDATE_PACK);
                } else if(userState.getState() == StateEnum.INPUT_ID_FOR_UPDATE_CAR) {
                    userStateChanger.changeState(userState, StateEnum.INPUT_NAME_FOR_UPDATE_CAR);
                }
            }
            case ButtonConstant.BTN_UPDATE_WIDTH -> {
                userState.setOperation(DatabaseOperationEnum.UPDATE);
                userState.setRequestString(userState.getRequestString() + " width");
                userState.setPage(telegramPages.getTelegramGetParamValuePage());
            }
            case ButtonConstant.BTN_UPDATE_HEIGHT -> {
                userState.setOperation(DatabaseOperationEnum.UPDATE);
                userState.setRequestString(userState.getRequestString() + " height");
                userState.setPage(telegramPages.getTelegramGetParamValuePage());
            }
            case ButtonConstant.BTN_UPDATE_CODE -> {
                userState.setOperation(DatabaseOperationEnum.UPDATE);
                userState.setRequestString(userState.getRequestString() + " code");
                userState.setPage(telegramPages.getTelegramGetParamValuePage());
            }
            case ButtonConstant.BTN_UPDATE_SCHEME -> {
                userState.setOperation(DatabaseOperationEnum.UPDATE);
                userState.setRequestString(userState.getRequestString() + " scheme");
                userState.setPage(telegramPages.getTelegramGetParamValuePage());
            }
            case ButtonConstant.BTN_LOAD_FILE -> {
                userState.setService(loadFileService);
                userState.setRequestString("load_file");
                userState.setPage(telegramPages.getTelegramGetFileNamePage());
            }
            case ButtonConstant.BTN_LOAD_LIST -> {
                userState.setService(loadListService);
                userState.setRequestString("load_list");
                userState.setPage(telegramPages.getTelegramGetCarModelNamePage());
            }
            case ButtonConstant.BTN_VIEW_FILE -> {
                userState.setService(viewFileService);
                userState.setRequestString("view_file");
                userState.setPage(telegramPages.getTelegramGetFileNamePage());
            }
            default -> {
                if (userState.getState() == StateEnum.INPUT_ID_FOR_GET_PACK) {
                    userState.setRequestString("pack get " + textMessage);
                    userState.setPage(telegramPages.getTelegramResultPage());
                } else if (userState.getState() == StateEnum.INPUT_ID_FOR_GET_CAR) {
                    userState.setRequestString("car get " + textMessage);
                    userState.setPage(telegramPages.getTelegramResultPage());
                } else if (userState.getState() == StateEnum.INPUT_NAME_FOR_INSERT_PACK) {
                    userState.setRequestString("pack insert " + textMessage);
                    userState.setPage(telegramPages.getTelegramGetCodePage());
                } else if (userState.getService().getClass() == PackModelService.class
                        && userState.getOperation() == DatabaseOperationEnum.INSERT
                        && userState.getPage() == telegramPages.getTelegramGetCodePage()) {
                    userState.setRequestString(userState.getRequestString() + " " + textMessage);
                    userState.setPage(telegramPages.getTelegramGetSchemePage());
                } else if (userState.getService().getClass() == PackModelService.class
                        && userState.getOperation() == DatabaseOperationEnum.INSERT
                        && userState.getPage() == telegramPages.getTelegramGetSchemePage()) {
                    userState.setRequestString(userState.getRequestString() + " " + textMessage);
                    userState.setPage(telegramPages.getTelegramGetWidthPage());
                } else if (userState.getService().getClass() == PackModelService.class
                        && userState.getOperation() == DatabaseOperationEnum.INSERT
                        && userState.getPage() == telegramPages.getTelegramGetWidthPage()) {
                    userState.setRequestString(userState.getRequestString() + " " + textMessage);
                    userState.setPage(telegramPages.getTelegramGetHeightPage());
                } else if (userState.getService().getClass() == PackModelService.class
                        && userState.getOperation() == DatabaseOperationEnum.INSERT
                        && userState.getPage() == telegramPages.getTelegramGetHeightPage()) {
                    userState.setRequestString(userState.getRequestString() + " " + textMessage);
                    userState.setPage(telegramPages.getTelegramResultPage());
                } else if (userState.getState() == StateEnum.INPUT_NAME_FOR_INSERT_CAR) {
                    userState.setRequestString("car insert " + textMessage);
                    userState.setPage(telegramPages.getTelegramGetWidthPage());
                } else if (userState.getService().getClass() == CarModelService.class
                        && userState.getOperation() == DatabaseOperationEnum.INSERT
                        && userState.getPage() == telegramPages.getTelegramGetWidthPage()) {
                    userState.setRequestString(userState.getRequestString() + " " + textMessage);
                    userState.setPage(telegramPages.getTelegramGetHeightPage());
                } else if (userState.getService().getClass() == CarModelService.class
                        && userState.getOperation() == DatabaseOperationEnum.INSERT
                        && userState.getPage() == telegramPages.getTelegramGetHeightPage()) {
                    userState.setRequestString(userState.getRequestString() + " " + textMessage);
                    userState.setPage(telegramPages.getTelegramResultPage());
                } else if (userState.getService().getClass() == PackModelService.class
                        && userState.getOperation() == DatabaseOperationEnum.DELETE) {
                    userState.setRequestString("pack delete " + textMessage);
                    userState.setPage(telegramPages.getTelegramResultPage());
                } else if (userState.getService().getClass() == CarModelService.class
                        && userState.getOperation() == DatabaseOperationEnum.DELETE) {
                    userState.setRequestString("car delete " + textMessage);
                    userState.setPage(telegramPages.getTelegramResultPage());
                } else if (userState.getService().getClass() == PackModelService.class
                        && userState.getOperation() == DatabaseOperationEnum.UPDATE
                        && userState.getPage() == telegramPages.getTelegramGetIdPage()) {
                    userState.setRequestString("pack update " + textMessage);
                    userState.setPage(telegramPages.getTelegramGetParamNamePage());
                } else if (userState.getService().getClass() == PackModelService.class
                        && userState.getOperation() == DatabaseOperationEnum.UPDATE
                        && userState.getPage() == telegramPages.getTelegramGetParamValuePage()) {
                    userState.setRequestString(userState.getRequestString() + " " + textMessage);
                    userState.setPage(telegramPages.getTelegramResultPage());
                } else if (userState.getService().getClass() == CarModelService.class
                        && userState.getOperation() == DatabaseOperationEnum.UPDATE
                        && userState.getPage() == telegramPages.getTelegramGetIdPage()) {
                    userState.setRequestString("car update " + textMessage);
                    userState.setPage(telegramPages.getTelegramGetParamNamePage());
                } else if (userState.getService().getClass() == CarModelService.class
                        && userState.getOperation() == DatabaseOperationEnum.UPDATE
                        && userState.getPage() == telegramPages.getTelegramGetParamValuePage()) {
                    userState.setRequestString(userState.getRequestString() + " " + textMessage);
                    userState.setPage(telegramPages.getTelegramResultPage());
                } else if (userState.getService().getClass() == LoadFileService.class
                        && userState.getPage() == telegramPages.getTelegramGetFileNamePage()) {
                    userState.setRequestString(userState.getRequestString() + " " + textMessage);
                    userState.setPage(telegramPages.getTelegramGetAlgorithmPage());
                } else if (userState.getService().getClass() == LoadFileService.class
                        && userState.getPage() == telegramPages.getTelegramGetAlgorithmPage()) {
                    switch (textMessage) {
                        case ButtonConstant.BTN_ALGORITHM_MAX -> userState.setRequestString(userState.getRequestString() + " max");
                        case ButtonConstant.BTN_ALGORITHM_HALF -> userState.setRequestString(userState.getRequestString() + " half");
                        case ButtonConstant.BTN_ALGORITHM_TYPE -> userState.setRequestString(userState.getRequestString() + " type");
                    }
                    userState.setPage(telegramPages.getTelegramGetCountCarsPage());
                } else if (userState.getService().getClass() == LoadFileService.class
                        && userState.getPage() == telegramPages.getTelegramGetCountCarsPage()) {
                    userState.setRequestString(userState.getRequestString() + " " + textMessage);
                    userState.setPage(telegramPages.getTelegramResultPage());
                } else if (userState.getService().getClass() == LoadListService.class
                        && userState.getPage() == telegramPages.getTelegramGetCarModelNamePage()) {
                    userState.setRequestString(userState.getRequestString() + " " + textMessage);
                    userState.setPage(telegramPages.getTelegramGetAlgorithmPage());
                } else if (userState.getService().getClass() == LoadListService.class
                        && userState.getPage() == telegramPages.getTelegramGetAlgorithmPage()) {
                    switch (textMessage) {
                        case ButtonConstant.BTN_ALGORITHM_MAX -> userState.setRequestString(userState.getRequestString() + " max");
                        case ButtonConstant.BTN_ALGORITHM_HALF -> userState.setRequestString(userState.getRequestString() + " half");
                        case ButtonConstant.BTN_ALGORITHM_TYPE -> userState.setRequestString(userState.getRequestString() + " type");
                    }
                    userState.setPage(telegramPages.getTelegramGetCountCarsPage());
                } else if (userState.getService().getClass() == LoadListService.class
                        && userState.getPage() == telegramPages.getTelegramGetCountCarsPage()) {
                    userState.setRequestString(userState.getRequestString() + " " + textMessage + ":");
                    userState.setPage(telegramPages.getTelegramGetPackModelNamePage());
                } else if (userState.getService().getClass() == LoadListService.class
                        && userState.getPage() == telegramPages.getTelegramGetPackModelNamePage()) {
                    userState.setRequestString(userState.getRequestString() + "\n" + textMessage);
                    userState.setPage(telegramPages.getTelegramResultPage());
                } else if (userState.getService().getClass() == ViewFileService.class
                        && userState.getPage() == telegramPages.getTelegramGetFileNamePage()) {
                    userState.setRequestString(userState.getRequestString() + " " + textMessage);
                    userState.setPage(telegramPages.getTelegramResultPage());
                }
            }
        }
        return userState;
    }
}
