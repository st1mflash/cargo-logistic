package com.ansekolesnikov.cargologistic.pages;

import com.ansekolesnikov.cargologistic.states.TelegramUserState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@RequiredArgsConstructor
@Component
public class TelegramGetParamValuePage implements ITelegramPage {
    private final TelegramMenuPage telegramMenuPage;
    @Override
    public ITelegramPage nextPage() {
        return telegramMenuPage;
    }

    @Override
    public SendMessage loadPageOnMessage(TelegramUserState userState, SendMessage message) {
        message.setText("Введите новое значение:");
        return message;
    }
}
