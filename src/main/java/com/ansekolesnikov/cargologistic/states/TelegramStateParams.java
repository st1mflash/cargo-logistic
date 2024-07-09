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
public class TelegramStateParams {
    private final TelegramPages telegramPages;
    private final PackModelService packModelService;
    private final CarModelService carModelService;
    private final ViewFileService viewFileService;
    private final LoadFileService loadFileService;
    private final LoadListService loadListService;

    public ITelegramPage getPageForState(StateEnum state) {
        return switch (state) {
            case RESULT,
                 GET_LIST_PACK,
                 GET_LIST_CAR -> telegramPages.getTelegramResultPage();
            case INPUT_ID_FOR_GET_PACK,
                 INPUT_ID_FOR_GET_CAR,
                 INPUT_ID_FOR_UPDATE_PACK,
                 INPUT_ID_FOR_UPDATE_CAR,
                 INPUT_ID_FOR_DELETE_PACK,
                 INPUT_ID_FOR_DELETE_CAR -> telegramPages.getTelegramGetIdPage();
            case INPUT_NAME_FOR_INSERT_PACK,
                 INPUT_NAME_FOR_INSERT_CAR -> telegramPages.getTelegramGetNamePage();
            case INPUT_WIDTH_FOR_INSERT_PACK,
                 INPUT_WIDTH_FOR_INSERT_CAR -> telegramPages.getTelegramGetWidthPage();
            case INPUT_HEIGHT_FOR_INSERT_PACK,
                 INPUT_HEIGHT_FOR_INSERT_CAR -> telegramPages.getTelegramGetHeightPage();
            case INPUT_CODE_FOR_INSERT_PACK -> telegramPages.getTelegramGetCodePage();
            case INPUT_SCHEME_FOR_INSERT_PACK -> telegramPages.getTelegramGetSchemePage();
            case INPUT_PARAM_NAME_FOR_UPDATE_PACK -> telegramPages.getTelegramGetPackParamNamePage();
            case INPUT_PARAM_NAME_FOR_UPDATE_CAR -> telegramPages.getTelegramGetCarParamNamePage();
            case INPUT_FILENAME_FOR_LOAD_FILE,
                 INPUT_FILENAME_FOR_VIEW_FILE -> telegramPages.getTelegramGetFileNamePage();
            case INPUT_ALGORITHM_FOR_LOAD_FILE,
                 INPUT_ALGORITHM_FOR_LOAD_LIST -> telegramPages.getTelegramGetAlgorithmPage();
            case INPUT_NAME_PACK_FOR_LOAD_LIST -> telegramPages.getTelegramGetPackModelNamePage();
            case INPUT_NAME_CAR_FOR_LOAD_LIST -> telegramPages.getTelegramGetCarModelNamePage();
            case INPUT_COUNT_CAR_FOR_LOAD_FILE,
                 INPUT_COUNT_CAR_FOR_LOAD_LIST -> telegramPages.getTelegramGetCountCarsPage();
            case INPUT_NAME_FOR_UPDATE_PACK,
                 INPUT_NAME_FOR_UPDATE_CAR,
                 INPUT_WIDTH_FOR_UPDATE_PACK,
                 INPUT_WIDTH_FOR_UPDATE_CAR,
                 INPUT_HEIGHT_FOR_UPDATE_PACK,
                 INPUT_HEIGHT_FOR_UPDATE_CAR,
                 INPUT_CODE_FOR_UPDATE_PACK,
                 INPUT_SCHEME_FOR_UPDATE_PACK,
                 INPUT_PARAM_VALUE_FOR_UPDATE_PACK,
                 INPUT_PARAM_VALUE_FOR_UPDATE_CAR -> telegramPages.getTelegramGetParamValuePage();
        };
    }

    public IRunnableByStringService getServiceForState(StateEnum state) {
        return switch (state) {
            case RESULT -> null;
            case GET_LIST_PACK,
                 INPUT_ID_FOR_GET_PACK,
                 INPUT_CODE_FOR_INSERT_PACK,
                 INPUT_CODE_FOR_UPDATE_PACK,
                 INPUT_HEIGHT_FOR_UPDATE_PACK,
                 INPUT_HEIGHT_FOR_INSERT_PACK,
                 INPUT_ID_FOR_DELETE_PACK,
                 INPUT_ID_FOR_UPDATE_PACK,
                 INPUT_NAME_FOR_INSERT_PACK,
                 INPUT_NAME_FOR_UPDATE_PACK,
                 INPUT_PARAM_NAME_FOR_UPDATE_CAR,
                 INPUT_PARAM_NAME_FOR_UPDATE_PACK,
                 INPUT_SCHEME_FOR_INSERT_PACK,
                 INPUT_SCHEME_FOR_UPDATE_PACK,
                 INPUT_WIDTH_FOR_INSERT_PACK,
                 INPUT_WIDTH_FOR_UPDATE_PACK,
                 INPUT_PARAM_VALUE_FOR_UPDATE_PACK -> packModelService;
            case GET_LIST_CAR,
                 INPUT_HEIGHT_FOR_INSERT_CAR,
                 INPUT_HEIGHT_FOR_UPDATE_CAR,
                 INPUT_ID_FOR_DELETE_CAR,
                 INPUT_ID_FOR_GET_CAR,
                 INPUT_ID_FOR_UPDATE_CAR,
                 INPUT_NAME_FOR_INSERT_CAR,
                 INPUT_NAME_FOR_UPDATE_CAR,
                 INPUT_WIDTH_FOR_INSERT_CAR,
                 INPUT_WIDTH_FOR_UPDATE_CAR,
                 INPUT_PARAM_VALUE_FOR_UPDATE_CAR -> carModelService;
            case INPUT_FILENAME_FOR_VIEW_FILE -> viewFileService;
            case INPUT_ALGORITHM_FOR_LOAD_FILE,
                 INPUT_FILENAME_FOR_LOAD_FILE,
                 INPUT_COUNT_CAR_FOR_LOAD_FILE -> loadFileService;
            case INPUT_NAME_PACK_FOR_LOAD_LIST,
                 INPUT_NAME_CAR_FOR_LOAD_LIST,
                 INPUT_ALGORITHM_FOR_LOAD_LIST,
                 INPUT_COUNT_CAR_FOR_LOAD_LIST -> loadListService;
        };
    }
}
