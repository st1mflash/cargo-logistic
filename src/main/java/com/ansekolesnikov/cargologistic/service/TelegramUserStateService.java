package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.enums.StateEnum;
import com.ansekolesnikov.cargologistic.factory.TelegramUserStateFactory;
import com.ansekolesnikov.cargologistic.interfaces.ITelegramUserStateService;
import com.ansekolesnikov.cargologistic.selector.ButtonSelector;
import com.ansekolesnikov.cargologistic.states.TelegramUserStateParams;
import com.ansekolesnikov.cargologistic.states.TelegramUserState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Map;

import static com.ansekolesnikov.cargologistic.constants.ButtonConstant.*;
import static com.ansekolesnikov.cargologistic.constants.CommandConstant.*;

@RequiredArgsConstructor
@Service
public class TelegramUserStateService implements ITelegramUserStateService {
    private final TelegramUserStateParams telegramUserStateParams;
    private final TelegramUserStateFactory telegramUserStateFactory;
    private final ButtonSelector buttonSelector;

    private final Map<Long, TelegramUserState> userStates = new HashMap<>();

    @Override
    public TelegramUserState loadUserState(Long userId) {
        return telegramUserStateFactory.createOrReturnUserState(userStates, userId);
    }

    @Override
    public TelegramUserState updateUserState(TelegramUserState userState, Message message) {
        return telegramUserStateFactory.updateUserState(this, userState, message);
    }

    @Override
    public boolean isExistUserStateById(Long userId) {
        return userStates.containsKey(userId);
    }

    public void updateByMessageWithAppendCommand(TelegramUserState userState, Message message) {
        StateEnum nextState = userState.getState().nextState();
        String commandParameter = (
                buttonSelector.toCommandParameter(message.getText()) == null ?
                        message.getText() : buttonSelector.toCommandParameter(message.getText()));
        updateUserStateWithAppendCommand(userState, nextState, commandParameter);
    }

    public void updateUserState(TelegramUserState userState, StateEnum state) {
        userState.setState(state);
        userState.setPage(telegramUserStateParams.getPageForState(state));
        if (state != StateEnum.RESULT) {
            userState.setService(telegramUserStateParams.getServiceForState(state));
        }
        refreshCommandByState(userState, state);
    }

    private void updateUserStateWithAppendCommand(TelegramUserState userState, StateEnum state, String userCustomText) {
        updateUserState(userState, state);
        if (isAlgorithm(userCustomText)) {
            appendAlgorithmToCommand(userState, userCustomText);
        } else {
            userState.setCommand(userState.getCommand() + " " + userCustomText);
        }
    }

    private boolean isAlgorithm(String userCustomText) {
        return userCustomText.equals(BTN_ALGORITHM_MAX)
                || userCustomText.equals(BTN_ALGORITHM_HALF)
                || userCustomText.equals(BTN_ALGORITHM_TYPE);
    }

    private void appendAlgorithmToCommand(TelegramUserState userState, String algorithmButton) {
        switch (algorithmButton) {
            case BTN_ALGORITHM_MAX -> userState.setCommand(userState.getCommand() + " " + MAX_ALGORITHM);
            case BTN_ALGORITHM_HALF -> userState.setCommand(userState.getCommand() + " " + HALF_ALGORITHM);
            case BTN_ALGORITHM_TYPE -> userState.setCommand(userState.getCommand() + " " + TYPE_ALGORITHM);
        }
    }

    private void refreshCommandByState(TelegramUserState userState, StateEnum state) {
        switch (state) {
            case GET_LIST_PACK -> userState.setCommand(PACK_LIST);
            case GET_LIST_CAR -> userState.setCommand(CAR_LIST);
            case INPUT_ID_FOR_GET_PACK -> userState.setCommand(PACK_GET);
            case INPUT_ID_FOR_GET_CAR -> userState.setCommand(CAR_GET);
            case INPUT_NAME_FOR_INSERT_PACK -> userState.setCommand(PACK_INSERT);
            case INPUT_NAME_FOR_INSERT_CAR -> userState.setCommand(CAR_INSERT);
            case INPUT_ID_FOR_UPDATE_PACK -> userState.setCommand(PACK_UPDATE);
            case INPUT_ID_FOR_UPDATE_CAR -> userState.setCommand(CAR_UPDATE);
            case INPUT_ID_FOR_DELETE_PACK -> userState.setCommand(PACK_DELETE);
            case INPUT_ID_FOR_DELETE_CAR -> userState.setCommand(CAR_DELETE);
            case INPUT_FILENAME_FOR_LOAD_FILE -> userState.setCommand(LOAD_FILE);
            case INPUT_NAME_CAR_FOR_LOAD_LIST -> userState.setCommand(LOAD_LIST);
            case INPUT_FILENAME_FOR_VIEW_FILE -> userState.setCommand(VIEW_FILE);
        }
    }
}
