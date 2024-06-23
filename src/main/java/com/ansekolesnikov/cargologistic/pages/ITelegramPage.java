package com.ansekolesnikov.cargologistic.pages;

import com.ansekolesnikov.cargologistic.states.TelegramUserState;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public interface ITelegramPage {
    ITelegramPage nextPage();
    SendMessage loadPageOnMessage(TelegramUserState userState, SendMessage message);
}
