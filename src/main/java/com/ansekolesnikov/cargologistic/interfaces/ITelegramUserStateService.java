package com.ansekolesnikov.cargologistic.interfaces;

import com.ansekolesnikov.cargologistic.states.TelegramUserState;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface ITelegramUserStateService {
    TelegramUserState updateUserStateByMessage(TelegramUserState userState, Message message);

    TelegramUserState loadUserState(Long userId);
}
