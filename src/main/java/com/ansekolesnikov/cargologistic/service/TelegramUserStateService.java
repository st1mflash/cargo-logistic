package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.enums.StateEnum;
import com.ansekolesnikov.cargologistic.interfaces.ITelegramUserStateService;
import com.ansekolesnikov.cargologistic.mappers.ButtonMapper;
import com.ansekolesnikov.cargologistic.pages.TelegramPages;
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
    private final TelegramPages telegramPages;
    private final ButtonMapper buttonMapper;

    private final Map<Long, TelegramUserState> userStates = new HashMap<>();

    @Override
    public TelegramUserState updateUserState(TelegramUserState userState, Message message) {
        if(isButtonWithoutAppendCommand(message)) {
            updateUserState(userState, buttonMapper.toNextStateEnum(message.getText()));
        } else {
            updateByMessageWithAppendCommand(userState, message);
        }
        return userState;
    }

    @Override
    public TelegramUserState loadUserState(Long userId) {
        if (userStates.containsKey(userId)) {
            return userStates.get(userId);
        } else {
            TelegramUserState telegramUserState = new TelegramUserState();
            telegramUserState.setPage(telegramPages.getTelegramMenuPage());
            userStates.put(userId, telegramUserState);
            return telegramUserState;
        }
    }

    @Override
    public boolean isExistUserStateById(Long userId) {
        return userStates.containsKey(userId);
    }

    private void updateByMessageWithAppendCommand(TelegramUserState userState, Message message) {
        StateEnum nextState = userState.getState().nextState();
        String commandParameter = (
                buttonMapper.toCommandParameter(message.getText()) == null ?
                        message.getText() : buttonMapper.toCommandParameter(message.getText()));
        updateUserStateWithAppendCommand(userState, nextState, commandParameter);
    }

    private boolean isButtonWithoutAppendCommand(Message message) {
        return switch (message.getText()) {
            case BTN_PACK_LIST,
                 BTN_CAR_LIST,
                 BTN_GET_PACK,
                 BTN_GET_CAR,
                 BTN_INSERT_PACK,
                 BTN_INSERT_CAR,
                 BTN_DELETE_PACK,
                 BTN_DELETE_CAR,
                 BTN_UPDATE_PACK,
                 BTN_UPDATE_CAR,
                 BTN_LOAD_FILE,
                 BTN_LOAD_LIST,
                 BTN_VIEW_FILE -> true;
            default -> false;
        };
    }

    private void updateUserState(TelegramUserState userState, StateEnum state) {
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
