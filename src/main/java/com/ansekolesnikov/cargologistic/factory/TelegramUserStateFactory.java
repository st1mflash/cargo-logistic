package com.ansekolesnikov.cargologistic.factory;

import com.ansekolesnikov.cargologistic.mappers.ButtonMapper;
import com.ansekolesnikov.cargologistic.pages.TelegramPages;
import com.ansekolesnikov.cargologistic.service.TelegramUserStateService;
import com.ansekolesnikov.cargologistic.states.TelegramUserState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class TelegramUserStateFactory {
    private final ButtonMapper buttonMapper;
    private final TelegramPages telegramPages;

    public TelegramUserState updateUserState(TelegramUserStateService telegramUserStateService, TelegramUserState userState, Message message) {
        if (telegramUserStateService.isButtonWithoutAppendCommand(message)) {
            telegramUserStateService.updateUserState(userState, buttonMapper.toNextStateEnum(message.getText()));
        } else {
            telegramUserStateService.updateByMessageWithAppendCommand(userState, message);
        }
        return userState;
    }

    public TelegramUserState loadUserState(Map<Long, TelegramUserState> userStates, Long userId) {
        if (userStates.containsKey(userId)) {
            return userStates.get(userId);
        } else {
            TelegramUserState telegramUserState = new TelegramUserState();
            telegramUserState.setPage(telegramPages.getTelegramMenuPage());
            userStates.put(userId, telegramUserState);
            return telegramUserState;
        }
    }
}
