package com.ansekolesnikov.cargologistic.states;

import com.ansekolesnikov.cargologistic.constants.ButtonConstant;
import com.ansekolesnikov.cargologistic.enums.CarModelParameterEnum;
import com.ansekolesnikov.cargologistic.enums.PackModelParameterEnum;
import com.ansekolesnikov.cargologistic.enums.StateEnum;
import com.ansekolesnikov.cargologistic.pages.TelegramPages;
import com.ansekolesnikov.cargologistic.service.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Data
public class UserStateMachine {
    private final UserStateParams userStateParams;
    private final TelegramPages telegramPages;
    private final PackModelService packModelService;
    private final CarModelService carModelService;
    private final ViewFileService viewFileService;
    private final LoadFileService loadFileService;
    private final LoadListService loadListService;

    public UserState changeUserStateByInputMessage(UserState userState, String message) {
        switch (message) {
            case ButtonConstant.BTN_UPDATE_NAME_PACK,
                 ButtonConstant.BTN_UPDATE_HEIGHT_PACK,
                 ButtonConstant.BTN_UPDATE_WIDTH_PACK,
                 ButtonConstant.BTN_UPDATE_SCHEME_PACK,
                 ButtonConstant.BTN_UPDATE_CODE_PACK ->
                    updateUserState(userState, StateEnum.INPUT_PARAM_VALUE_FOR_UPDATE_PACK, PackModelParameterEnum.toStringFromButton(message));
            case ButtonConstant.BTN_UPDATE_NAME_CAR,
                 ButtonConstant.BTN_UPDATE_WIDTH_CAR,
                 ButtonConstant.BTN_UPDATE_HEIGHT_CAR ->
                    updateUserState(userState, StateEnum.INPUT_PARAM_VALUE_FOR_UPDATE_CAR, CarModelParameterEnum.toStringFromButton(message));
            case ButtonConstant.BTN_PACK_LIST -> updateUserState(userState, StateEnum.GET_LIST_PACK);
            case ButtonConstant.BTN_CAR_LIST -> updateUserState(userState, StateEnum.GET_LIST_CAR);
            case ButtonConstant.BTN_GET_PACK -> updateUserState(userState, StateEnum.INPUT_ID_FOR_GET_PACK);
            case ButtonConstant.BTN_GET_CAR -> updateUserState(userState, StateEnum.INPUT_ID_FOR_GET_CAR);
            case ButtonConstant.BTN_INSERT_PACK -> updateUserState(userState, StateEnum.INPUT_NAME_FOR_INSERT_PACK);
            case ButtonConstant.BTN_INSERT_CAR -> updateUserState(userState, StateEnum.INPUT_NAME_FOR_INSERT_CAR);
            case ButtonConstant.BTN_DELETE_PACK -> updateUserState(userState, StateEnum.INPUT_ID_FOR_DELETE_PACK);
            case ButtonConstant.BTN_DELETE_CAR -> updateUserState(userState, StateEnum.INPUT_ID_FOR_DELETE_CAR);
            case ButtonConstant.BTN_UPDATE_PACK -> updateUserState(userState, StateEnum.INPUT_ID_FOR_UPDATE_PACK);
            case ButtonConstant.BTN_UPDATE_CAR -> updateUserState(userState, StateEnum.INPUT_ID_FOR_UPDATE_CAR);
            case ButtonConstant.BTN_LOAD_FILE -> updateUserState(userState, StateEnum.INPUT_FILENAME_FOR_LOAD_FILE);
            case ButtonConstant.BTN_LOAD_LIST -> updateUserState(userState, StateEnum.INPUT_NAME_CAR_FOR_LOAD_LIST);
            case ButtonConstant.BTN_VIEW_FILE -> updateUserState(userState, StateEnum.INPUT_FILENAME_FOR_VIEW_FILE);
            default -> {
                System.out.println(userState.getCurrentState());
                switch (userState.getCurrentState()) {
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
                         INPUT_FILENAME_FOR_VIEW_FILE -> updateUserState(userState, StateEnum.RESULT, message);

                    case INPUT_NAME_FOR_INSERT_PACK ->
                            updateUserState(userState, StateEnum.INPUT_CODE_FOR_INSERT_PACK, message);
                    case INPUT_CODE_FOR_INSERT_PACK ->
                            updateUserState(userState, StateEnum.INPUT_SCHEME_FOR_INSERT_PACK, message);
                    case INPUT_SCHEME_FOR_INSERT_PACK ->
                            updateUserState(userState, StateEnum.INPUT_WIDTH_FOR_INSERT_PACK, message);
                    case INPUT_WIDTH_FOR_INSERT_PACK ->
                            updateUserState(userState, StateEnum.INPUT_HEIGHT_FOR_INSERT_PACK, message);
                    case INPUT_NAME_FOR_INSERT_CAR ->
                            updateUserState(userState, StateEnum.INPUT_WIDTH_FOR_INSERT_CAR, message);
                    case INPUT_WIDTH_FOR_INSERT_CAR ->
                            updateUserState(userState, StateEnum.INPUT_HEIGHT_FOR_INSERT_CAR, message);
                    case INPUT_ID_FOR_UPDATE_PACK ->
                            updateUserState(userState, StateEnum.INPUT_PARAM_NAME_FOR_UPDATE_PACK, message);
                    case INPUT_ID_FOR_UPDATE_CAR ->
                            updateUserState(userState, StateEnum.INPUT_PARAM_NAME_FOR_UPDATE_CAR, message);
                    case INPUT_FILENAME_FOR_LOAD_FILE ->
                            updateUserState(userState, StateEnum.INPUT_ALGORITHM_FOR_LOAD_FILE, message);
                    case INPUT_ALGORITHM_FOR_LOAD_FILE ->
                            updateUserStateAlgorithm(userState, StateEnum.INPUT_COUNT_CAR_FOR_LOAD_FILE, message);
                    case INPUT_NAME_CAR_FOR_LOAD_LIST ->
                            updateUserState(userState, StateEnum.INPUT_ALGORITHM_FOR_LOAD_LIST, message);
                    case INPUT_ALGORITHM_FOR_LOAD_LIST ->
                            updateUserStateAlgorithm(userState, StateEnum.INPUT_COUNT_CAR_FOR_LOAD_LIST, message);
                    case INPUT_COUNT_CAR_FOR_LOAD_LIST ->
                            updateUserState(userState, StateEnum.INPUT_NAME_PACK_FOR_LOAD_LIST, message);

                }
            }
        }
        return userState;
    }

    private void updateUserState(UserState userState, StateEnum newState) {
        userState.setCurrentState(newState);
        userState.setPage(userStateParams.getPage(newState));
        if (newState != StateEnum.RESULT) {
            userState.setService(userStateParams.getService(newState));
        }
        updateUserStateRequest(userState, newState);
    }

    private void updateUserState(UserState userState, StateEnum newState, String userCustomText) {
        updateUserState(userState, newState);
        userState.setRequestString(userState.getRequestString() + " " + userCustomText);
        System.out.println(userState.getRequestString());
    }

    private void updateUserStateAlgorithm(UserState userState, StateEnum newState, String message) {
        switch (message) {
            case ButtonConstant.BTN_ALGORITHM_MAX -> userState.setRequestString(userState.getRequestString() + " max");
            case ButtonConstant.BTN_ALGORITHM_HALF ->
                    userState.setRequestString(userState.getRequestString() + " half");
            case ButtonConstant.BTN_ALGORITHM_TYPE ->
                    userState.setRequestString(userState.getRequestString() + " type");
        }
        updateUserState(userState, newState);
    }


    private void updateUserStateRequest(UserState userState, StateEnum newState) {
        switch (newState) {
            case GET_LIST_PACK -> userState.setRequestString("pack list");
            case GET_LIST_CAR -> userState.setRequestString("car list");
            case INPUT_ID_FOR_GET_PACK -> userState.setRequestString("pack get");
            case INPUT_ID_FOR_GET_CAR -> userState.setRequestString("car get");
            case INPUT_NAME_FOR_INSERT_PACK -> userState.setRequestString("pack insert");
            case INPUT_NAME_FOR_INSERT_CAR -> userState.setRequestString("car insert");
            case INPUT_ID_FOR_UPDATE_PACK -> userState.setRequestString("pack update");
            case INPUT_ID_FOR_UPDATE_CAR -> userState.setRequestString("car update");
            case INPUT_ID_FOR_DELETE_PACK -> userState.setRequestString("pack delete");
            case INPUT_ID_FOR_DELETE_CAR -> userState.setRequestString("car delete");
            case INPUT_FILENAME_FOR_LOAD_FILE -> userState.setRequestString("load_file");
            case INPUT_NAME_CAR_FOR_LOAD_LIST -> userState.setRequestString("load_list");
            case INPUT_FILENAME_FOR_VIEW_FILE -> userState.setRequestString("view_file");
        }
    }
}
