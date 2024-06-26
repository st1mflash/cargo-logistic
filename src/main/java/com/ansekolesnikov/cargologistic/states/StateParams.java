package com.ansekolesnikov.cargologistic.states;

import com.ansekolesnikov.cargologistic.enums.StateEnum;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import com.ansekolesnikov.cargologistic.pages.ITelegramPage;
import com.ansekolesnikov.cargologistic.pages.TelegramPages;
import com.ansekolesnikov.cargologistic.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StateParams {
    private final TelegramPages telegramPages;
    private final PackModelService packModelService;
    private final CarModelService carModelService;
    private final ViewFileService viewFileService;
    private final LoadFileService loadFileService;
    private final LoadListService loadListService;

    public ITelegramPage getPage(StateEnum state) {
        return switch (state) {
            case GET_LIST_PACK,
                 GET_LIST_CAR -> telegramPages.getTelegramResultPage();
            case INPUT_ID_FOR_GET_PACK,
                 INPUT_ID_FOR_GET_CAR,
                 INPUT_ID_FOR_UPDATE_PACK,
                 INPUT_ID_FOR_UPDATE_CAR,
                 INPUT_ID_FOR_DELETE_PACK,
                 INPUT_ID_FOR_DELETE_CAR -> telegramPages.getTelegramGetIdPage();
            case INPUT_NAME_FOR_INSERT_PACK,
                 INPUT_NAME_FOR_INSERT_CAR,
                 INPUT_NAME_FOR_UPDATE_PACK,
                 INPUT_NAME_FOR_UPDATE_CAR -> telegramPages.getTelegramGetNamePage();
            case INPUT_WIDTH_FOR_INSERT_PACK,
                 INPUT_WIDTH_FOR_INSERT_CAR,
                 INPUT_WIDTH_FOR_UPDATE_PACK,
                 INPUT_WIDTH_FOR_UPDATE_CAR -> telegramPages.getTelegramGetWidthPage();
            case INPUT_HEIGHT_FOR_INSERT_PACK,
                 INPUT_HEIGHT_FOR_INSERT_CAR,
                 INPUT_HEIGHT_FOR_UPDATE_PACK,
                 INPUT_HEIGHT_FOR_UPDATE_CAR -> telegramPages.getTelegramGetHeightPage();
            case INPUT_CODE_FOR_INSERT_PACK,
                 INPUT_CODE_FOR_UPDATE_PACK -> telegramPages.getTelegramGetCodePage();
            case INPUT_SCHEME_FOR_INSERT_PACK,
                 INPUT_SCHEME_FOR_UPDATE_PACK -> telegramPages.getTelegramGetSchemePage();
            case INPUT_PARAM_FOR_UPDATE_PACK,
                 INPUT_PARAM_FOR_UPDATE_CAR -> telegramPages.getTelegramGetParamNamePage();
        };
    }

    public IRunnableByStringService getService(StateEnum state) {
        return null;
    }
}
