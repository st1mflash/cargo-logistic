package com.ansekolesnikov.cargologistic.states;

import com.ansekolesnikov.cargologistic.constants.ButtonConstant;
import com.ansekolesnikov.cargologistic.constants.CommandConstant;
import com.ansekolesnikov.cargologistic.enums.CarModelParameterEnum;
import com.ansekolesnikov.cargologistic.enums.PackModelParameterEnum;
import com.ansekolesnikov.cargologistic.enums.StateEnum;
import com.ansekolesnikov.cargologistic.pages.TelegramPages;
import com.ansekolesnikov.cargologistic.service.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
@Data
public class TelegramStateMachine {
    private final TelegramPages telegramPages;
    private final TelegramStateParams telegramStateParams;
    private final PackModelService packModelService;
    private final CarModelService carModelService;
    private final ViewFileService viewFileService;
    private final LoadFileService loadFileService;
    private final LoadListService loadListService;

    public TelegramState changeStateByMessage(TelegramState telegramState, String message) {
        switch (message) {
            case ButtonConstant.BTN_UPDATE_NAME_PACK,
                 ButtonConstant.BTN_UPDATE_HEIGHT_PACK,
                 ButtonConstant.BTN_UPDATE_WIDTH_PACK,
                 ButtonConstant.BTN_UPDATE_SCHEME_PACK,
                 ButtonConstant.BTN_UPDATE_CODE_PACK ->
                    updateState(telegramState, StateEnum.INPUT_PARAM_VALUE_FOR_UPDATE_PACK, Objects.requireNonNull(PackModelParameterEnum.toStringFromButton(message)).toLowerCase());
            case ButtonConstant.BTN_UPDATE_NAME_CAR,
                 ButtonConstant.BTN_UPDATE_WIDTH_CAR,
                 ButtonConstant.BTN_UPDATE_HEIGHT_CAR ->
                    updateState(telegramState, StateEnum.INPUT_PARAM_VALUE_FOR_UPDATE_CAR, Objects.requireNonNull(CarModelParameterEnum.toStringFromButton(message)).toLowerCase());
            case ButtonConstant.BTN_PACK_LIST -> updateState(telegramState, StateEnum.GET_LIST_PACK);
            case ButtonConstant.BTN_CAR_LIST -> updateState(telegramState, StateEnum.GET_LIST_CAR);
            case ButtonConstant.BTN_GET_PACK -> updateState(telegramState, StateEnum.INPUT_ID_FOR_GET_PACK);
            case ButtonConstant.BTN_GET_CAR -> updateState(telegramState, StateEnum.INPUT_ID_FOR_GET_CAR);
            case ButtonConstant.BTN_INSERT_PACK -> updateState(telegramState, StateEnum.INPUT_NAME_FOR_INSERT_PACK);
            case ButtonConstant.BTN_INSERT_CAR -> updateState(telegramState, StateEnum.INPUT_NAME_FOR_INSERT_CAR);
            case ButtonConstant.BTN_DELETE_PACK -> updateState(telegramState, StateEnum.INPUT_ID_FOR_DELETE_PACK);
            case ButtonConstant.BTN_DELETE_CAR -> updateState(telegramState, StateEnum.INPUT_ID_FOR_DELETE_CAR);
            case ButtonConstant.BTN_UPDATE_PACK -> updateState(telegramState, StateEnum.INPUT_ID_FOR_UPDATE_PACK);
            case ButtonConstant.BTN_UPDATE_CAR -> updateState(telegramState, StateEnum.INPUT_ID_FOR_UPDATE_CAR);
            case ButtonConstant.BTN_LOAD_FILE -> updateState(telegramState, StateEnum.INPUT_FILENAME_FOR_LOAD_FILE);
            case ButtonConstant.BTN_LOAD_LIST -> updateState(telegramState, StateEnum.INPUT_NAME_CAR_FOR_LOAD_LIST);
            case ButtonConstant.BTN_VIEW_FILE -> updateState(telegramState, StateEnum.INPUT_FILENAME_FOR_VIEW_FILE);
            default -> {
                switch (telegramState.getCurrentState()) {
                    case INPUT_ID_FOR_GET_PACK,
                         INPUT_ID_FOR_GET_CAR,
                         INPUT_HEIGHT_FOR_INSERT_PACK,
                         INPUT_HEIGHT_FOR_INSERT_CAR,
                         INPUT_ID_FOR_DELETE_PACK,
                         INPUT_ID_FOR_DELETE_CAR,
                         INPUT_NAME_FOR_UPDATE_PACK,
                         INPUT_NAME_FOR_UPDATE_CAR,
                         INPUT_WIDTH_FOR_UPDATE_PACK,
                         INPUT_WIDTH_FOR_UPDATE_CAR,
                         INPUT_HEIGHT_FOR_UPDATE_PACK,
                         INPUT_HEIGHT_FOR_UPDATE_CAR,
                         INPUT_CODE_FOR_UPDATE_PACK,
                         INPUT_SCHEME_FOR_UPDATE_PACK,
                         INPUT_PARAM_VALUE_FOR_UPDATE_PACK,
                         INPUT_PARAM_VALUE_FOR_UPDATE_CAR,
                         INPUT_COUNT_CAR_FOR_LOAD_FILE,
                         INPUT_NAME_PACK_FOR_LOAD_LIST,
                         INPUT_FILENAME_FOR_VIEW_FILE -> updateState(telegramState, StateEnum.RESULT, message);

                    case INPUT_NAME_FOR_INSERT_PACK ->
                            updateState(telegramState, StateEnum.INPUT_CODE_FOR_INSERT_PACK, message);
                    case INPUT_CODE_FOR_INSERT_PACK ->
                            updateState(telegramState, StateEnum.INPUT_SCHEME_FOR_INSERT_PACK, message);
                    case INPUT_SCHEME_FOR_INSERT_PACK ->
                            updateState(telegramState, StateEnum.INPUT_WIDTH_FOR_INSERT_PACK, message);
                    case INPUT_WIDTH_FOR_INSERT_PACK ->
                            updateState(telegramState, StateEnum.INPUT_HEIGHT_FOR_INSERT_PACK, message);
                    case INPUT_NAME_FOR_INSERT_CAR ->
                            updateState(telegramState, StateEnum.INPUT_WIDTH_FOR_INSERT_CAR, message);
                    case INPUT_WIDTH_FOR_INSERT_CAR ->
                            updateState(telegramState, StateEnum.INPUT_HEIGHT_FOR_INSERT_CAR, message);
                    case INPUT_ID_FOR_UPDATE_PACK ->
                            updateState(telegramState, StateEnum.INPUT_PARAM_NAME_FOR_UPDATE_PACK, message);
                    case INPUT_ID_FOR_UPDATE_CAR ->
                            updateState(telegramState, StateEnum.INPUT_PARAM_NAME_FOR_UPDATE_CAR, message);
                    case INPUT_FILENAME_FOR_LOAD_FILE ->
                            updateState(telegramState, StateEnum.INPUT_ALGORITHM_FOR_LOAD_FILE, message);
                    case INPUT_ALGORITHM_FOR_LOAD_FILE ->
                            updateStateAlgorithm(telegramState, StateEnum.INPUT_COUNT_CAR_FOR_LOAD_FILE, message);
                    case INPUT_NAME_CAR_FOR_LOAD_LIST ->
                            updateState(telegramState, StateEnum.INPUT_ALGORITHM_FOR_LOAD_LIST, message);
                    case INPUT_ALGORITHM_FOR_LOAD_LIST ->
                            updateStateAlgorithm(telegramState, StateEnum.INPUT_COUNT_CAR_FOR_LOAD_LIST, message);
                    case INPUT_COUNT_CAR_FOR_LOAD_LIST ->
                            updateState(telegramState, StateEnum.INPUT_NAME_PACK_FOR_LOAD_LIST, message);

                }
            }
        }
        return telegramState;
    }

    private void updateState(TelegramState telegramState, StateEnum newState) {
        telegramState.setCurrentState(newState);
        telegramState.setPage(telegramStateParams.getPage(newState));
        if (newState != StateEnum.RESULT) {
            telegramState.setService(telegramStateParams.getService(newState));
        }
        updateStateRequest(telegramState, newState);
    }

    private void updateState(TelegramState telegramState, StateEnum newState, String userCustomText) {
        updateState(telegramState, newState);
        telegramState.setRequestString(telegramState.getRequestString() + " " + userCustomText);
    }

    private void updateStateAlgorithm(TelegramState telegramState, StateEnum newState, String message) {
        switch (message) {
            case ButtonConstant.BTN_ALGORITHM_MAX -> telegramState.setRequestString(telegramState.getRequestString() + " " + CommandConstant.MAX_ALGORITHM);
            case ButtonConstant.BTN_ALGORITHM_HALF ->
                    telegramState.setRequestString(telegramState.getRequestString() + " " + CommandConstant.HALF_ALGORITHM);
            case ButtonConstant.BTN_ALGORITHM_TYPE ->
                    telegramState.setRequestString(telegramState.getRequestString() + " " + CommandConstant.TYPE_ALGORITHM);
        }
        updateState(telegramState, newState);
    }


    private void updateStateRequest(TelegramState telegramState, StateEnum newState) {
        switch (newState) {
            case GET_LIST_PACK -> telegramState.setRequestString(CommandConstant.PACK_LIST);
            case GET_LIST_CAR -> telegramState.setRequestString(CommandConstant.CAR_LIST);
            case INPUT_ID_FOR_GET_PACK -> telegramState.setRequestString(CommandConstant.PACK_GET);
            case INPUT_ID_FOR_GET_CAR -> telegramState.setRequestString(CommandConstant.CAR_GET);
            case INPUT_NAME_FOR_INSERT_PACK -> telegramState.setRequestString(CommandConstant.PACK_INSERT);
            case INPUT_NAME_FOR_INSERT_CAR -> telegramState.setRequestString(CommandConstant.CAR_INSERT);
            case INPUT_ID_FOR_UPDATE_PACK -> telegramState.setRequestString(CommandConstant.PACK_UPDATE);
            case INPUT_ID_FOR_UPDATE_CAR -> telegramState.setRequestString(CommandConstant.CAR_UPDATE);
            case INPUT_ID_FOR_DELETE_PACK -> telegramState.setRequestString(CommandConstant.PACK_DELETE);
            case INPUT_ID_FOR_DELETE_CAR -> telegramState.setRequestString(CommandConstant.CAR_DELETE);
            case INPUT_FILENAME_FOR_LOAD_FILE -> telegramState.setRequestString(CommandConstant.LOAD_FILE);
            case INPUT_NAME_CAR_FOR_LOAD_LIST -> telegramState.setRequestString(CommandConstant.LOAD_LIST);
            case INPUT_FILENAME_FOR_VIEW_FILE -> telegramState.setRequestString(CommandConstant.VIEW_FILE);
        }
    }
}
