package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.constants.ButtonConstant;
import com.ansekolesnikov.cargologistic.enums.StateEnum;
import com.ansekolesnikov.cargologistic.interfaces.ITelegramUserStateService;
import com.ansekolesnikov.cargologistic.mappers.ButtonMapper;
import com.ansekolesnikov.cargologistic.pages.TelegramPages;
import com.ansekolesnikov.cargologistic.states.TelegramUserStateParams;
import com.ansekolesnikov.cargologistic.states.TelegramUserState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static com.ansekolesnikov.cargologistic.constants.ButtonConstant.*;
import static com.ansekolesnikov.cargologistic.constants.CommandConstant.*;

@RequiredArgsConstructor
@Service
public class TelegramUserUserStateService implements ITelegramUserStateService {
    private final TelegramUserStateParams telegramUserStateParams;
    private final TelegramPages telegramPages;
    private final ButtonMapper buttonMapper;

    private final Map<Long, TelegramUserState> userStates = new HashMap<>();

    @Override
    public TelegramUserState updateUserStateByMessage(TelegramUserState userState, Message message) {
        if (isButton(message)) {
            return updateUserStateByButton(userState, message);
        } else {
            return updateUserStateByText(userState, message);
        }
    }

    @Override
    public TelegramUserState loadUserState(Long userId) {
        if (userStates.get(userId) != null) {
            return userStates.get(userId);
        } else {
            TelegramUserState telegramUserState = new TelegramUserState();
            telegramUserState.setPage(telegramPages.getTelegramMenuPage());
            userStates.put(userId, telegramUserState);
            return telegramUserState;
        }
    }

    private boolean isButton(Message message) {
        Class<ButtonConstant> constantsClass = ButtonConstant.class;
        String messageText = message.getText();
        try {
            for (Field field : constantsClass.getDeclaredFields()) {
                String constantValue = (String) field.get(null);
                if (messageText.equals(constantValue)) {
                    return true;
                }
            }
            return false;
        } catch (IllegalAccessException ignored) {
            return false;
        }
    }

    private TelegramUserState updateUserStateByButton(TelegramUserState userState, Message message) {
        String button = message.getText();
        switch (button) {
            case BTN_UPDATE_NAME_PACK,
                 BTN_UPDATE_HEIGHT_PACK,
                 BTN_UPDATE_WIDTH_PACK,
                 BTN_UPDATE_SCHEME_PACK,
                 BTN_UPDATE_CODE_PACK ->
                    updateUserStateWithAppendCommand(userState, StateEnum.INPUT_PARAM_VALUE_FOR_UPDATE_PACK, buttonMapper.toParameterName(button));
            case BTN_UPDATE_NAME_CAR,
                 BTN_UPDATE_WIDTH_CAR,
                 BTN_UPDATE_HEIGHT_CAR ->
                    updateUserStateWithAppendCommand(userState, StateEnum.INPUT_PARAM_VALUE_FOR_UPDATE_CAR, buttonMapper.toParameterName(button));
            case BTN_PACK_LIST -> updateUserState(userState, StateEnum.GET_LIST_PACK);
            case BTN_CAR_LIST -> updateUserState(userState, StateEnum.GET_LIST_CAR);
            case BTN_GET_PACK -> updateUserState(userState, StateEnum.INPUT_ID_FOR_GET_PACK);
            case BTN_GET_CAR -> updateUserState(userState, StateEnum.INPUT_ID_FOR_GET_CAR);
            case BTN_INSERT_PACK -> updateUserState(userState, StateEnum.INPUT_NAME_FOR_INSERT_PACK);
            case BTN_INSERT_CAR -> updateUserState(userState, StateEnum.INPUT_NAME_FOR_INSERT_CAR);
            case BTN_DELETE_PACK -> updateUserState(userState, StateEnum.INPUT_ID_FOR_DELETE_PACK);
            case BTN_DELETE_CAR -> updateUserState(userState, StateEnum.INPUT_ID_FOR_DELETE_CAR);
            case BTN_UPDATE_PACK -> updateUserState(userState, StateEnum.INPUT_ID_FOR_UPDATE_PACK);
            case BTN_UPDATE_CAR -> updateUserState(userState, StateEnum.INPUT_ID_FOR_UPDATE_CAR);
            case BTN_LOAD_FILE -> updateUserState(userState, StateEnum.INPUT_FILENAME_FOR_LOAD_FILE);
            case BTN_LOAD_LIST -> updateUserState(userState, StateEnum.INPUT_NAME_CAR_FOR_LOAD_LIST);
            case BTN_VIEW_FILE -> updateUserState(userState, StateEnum.INPUT_FILENAME_FOR_VIEW_FILE);
        }
        return userState;
    }

    private TelegramUserState updateUserStateByText(TelegramUserState userState, Message message) {
        String customText = message.getText();
        switch (userState.getState()) {
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
                 INPUT_FILENAME_FOR_VIEW_FILE ->
                    updateUserStateWithAppendCommand(userState, StateEnum.RESULT, customText);

            case INPUT_NAME_FOR_INSERT_PACK ->
                    updateUserStateWithAppendCommand(userState, StateEnum.INPUT_CODE_FOR_INSERT_PACK, customText);
            case INPUT_CODE_FOR_INSERT_PACK ->
                    updateUserStateWithAppendCommand(userState, StateEnum.INPUT_SCHEME_FOR_INSERT_PACK, customText);
            case INPUT_SCHEME_FOR_INSERT_PACK ->
                    updateUserStateWithAppendCommand(userState, StateEnum.INPUT_WIDTH_FOR_INSERT_PACK, customText);
            case INPUT_WIDTH_FOR_INSERT_PACK ->
                    updateUserStateWithAppendCommand(userState, StateEnum.INPUT_HEIGHT_FOR_INSERT_PACK, customText);
            case INPUT_NAME_FOR_INSERT_CAR ->
                    updateUserStateWithAppendCommand(userState, StateEnum.INPUT_WIDTH_FOR_INSERT_CAR, customText);
            case INPUT_WIDTH_FOR_INSERT_CAR ->
                    updateUserStateWithAppendCommand(userState, StateEnum.INPUT_HEIGHT_FOR_INSERT_CAR, customText);
            case INPUT_ID_FOR_UPDATE_PACK ->
                    updateUserStateWithAppendCommand(userState, StateEnum.INPUT_PARAM_NAME_FOR_UPDATE_PACK, customText);
            case INPUT_ID_FOR_UPDATE_CAR ->
                    updateUserStateWithAppendCommand(userState, StateEnum.INPUT_PARAM_NAME_FOR_UPDATE_CAR, customText);
            case INPUT_FILENAME_FOR_LOAD_FILE ->
                    updateUserStateWithAppendCommand(userState, StateEnum.INPUT_ALGORITHM_FOR_LOAD_FILE, customText);
            case INPUT_ALGORITHM_FOR_LOAD_FILE ->
                    updateUserStateWithAppendCommandAlgorithm(userState, StateEnum.INPUT_COUNT_CAR_FOR_LOAD_FILE, customText);
            case INPUT_NAME_CAR_FOR_LOAD_LIST ->
                    updateUserStateWithAppendCommand(userState, StateEnum.INPUT_ALGORITHM_FOR_LOAD_LIST, customText);
            case INPUT_ALGORITHM_FOR_LOAD_LIST ->
                    updateUserStateWithAppendCommandAlgorithm(userState, StateEnum.INPUT_COUNT_CAR_FOR_LOAD_LIST, customText);
            case INPUT_COUNT_CAR_FOR_LOAD_LIST ->
                    updateUserStateWithAppendCommand(userState, StateEnum.INPUT_NAME_PACK_FOR_LOAD_LIST, customText);
        }
        return userState;
    }

    private void updateUserState(TelegramUserState userState, StateEnum state) {
        userState.setState(state);
        userState.setPage(telegramUserStateParams.getPageForState(state));
        if (state != StateEnum.RESULT) {
            userState.setService(telegramUserStateParams.getServiceForState(state));
        }
        updateCommandByUserState(userState, state);
    }

    private void updateUserStateWithAppendCommand(TelegramUserState userState, StateEnum state, String userCustomText) {
        updateUserState(userState, state);
        userState.setCommand(userState.getCommand() + " " + userCustomText);
    }

    private void updateUserStateWithAppendCommandAlgorithm(TelegramUserState userState, StateEnum state, String message) {
        switch (message) {
            case BTN_ALGORITHM_MAX -> userState.setCommand(userState.getCommand() + " " + MAX_ALGORITHM);
            case BTN_ALGORITHM_HALF -> userState.setCommand(userState.getCommand() + " " + HALF_ALGORITHM);
            case BTN_ALGORITHM_TYPE -> userState.setCommand(userState.getCommand() + " " + TYPE_ALGORITHM);
        }
        updateUserState(userState, state);
    }

    private void updateCommandByUserState(TelegramUserState userState, StateEnum state) {
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
