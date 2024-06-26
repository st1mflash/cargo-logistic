package com.ansekolesnikov.cargologistic.states;

import com.ansekolesnikov.cargologistic.enums.StateEnum;
import com.ansekolesnikov.cargologistic.pages.TelegramPages;
import com.ansekolesnikov.cargologistic.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserStateChanger {
    private final TelegramPages telegramPages;
    private final StateParams stateParams;
    private final PackModelService packModelService;
    private final CarModelService carModelService;
    private final ViewFileService viewFileService;
    private final LoadFileService loadFileService;
    private final LoadListService loadListService;

    public void changeState(UserState userState, StateEnum newState) {
        userState.setState(newState);
        userState.setPage(stateParams.getPage(newState));
        switch (newState) {
            case GET_LIST_PACK -> toPackListState(userState);
            case GET_LIST_CAR -> toCarListState(userState);
            case INPUT_ID_FOR_GET_PACK -> toPackGetInputIdState(userState);
            case INPUT_ID_FOR_GET_CAR -> toCarGetInputIdState(userState);
            case INPUT_NAME_FOR_INSERT_PACK -> toPackInsertInputNameState(userState);
            case INPUT_NAME_FOR_INSERT_CAR -> toCarInsertInputNameState(userState);
            case INPUT_ID_FOR_DELETE_PACK -> toPackDeleteInputIdState(userState);
            case INPUT_ID_FOR_DELETE_CAR -> toCarDeleteInputIdState(userState);
            case INPUT_ID_FOR_UPDATE_PACK -> toPackUpdateInputIdState(userState);
            case INPUT_ID_FOR_UPDATE_CAR -> toCarUpdateInputIdState(userState);
            case INPUT_NAME_FOR_UPDATE_PACK -> toPackUpdateInputNameState(userState);
            case INPUT_NAME_FOR_UPDATE_CAR -> toCarUpdateInputNameState(userState);
        }
    }

    private void toPackListState(UserState userState) {
        userState.setService(packModelService);
        userState.setRequestString("pack list");
    }

    private void toCarListState(UserState userState) {
        userState.setService(carModelService);
        userState.setRequestString("car list");
    }

    private void toPackGetInputIdState(UserState userState) {
        userState.setService(packModelService);
    }

    private void toCarGetInputIdState(UserState userState) {
        userState.setService(carModelService);
    }

    private void toPackInsertInputNameState(UserState userState) {
        userState.setService(packModelService);
    }

    private void toCarInsertInputNameState(UserState userState) {
        userState.setService(carModelService);
    }

    private void toPackDeleteInputIdState(UserState userState) {
        userState.setService(packModelService);
    }

    private void toCarDeleteInputIdState(UserState userState) {
        userState.setService(carModelService);
    }

    private void toPackUpdateInputIdState(UserState userState) {
        userState.setService(packModelService);
    }

    private void toCarUpdateInputIdState(UserState userState) {
        userState.setService(carModelService);
    }

    private void toPackUpdateInputNameState(UserState userState) {
        userState.setRequestString(userState.getRequestString() + " name");
    }

    private void toCarUpdateInputNameState(UserState userState) {
        userState.setRequestString(userState.getRequestString() + " name");
    }
}
