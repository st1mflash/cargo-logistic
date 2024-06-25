package com.ansekolesnikov.cargologistic.pages;

import com.ansekolesnikov.cargologistic.states.TelegramUserState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@RequiredArgsConstructor
@Component
public class TelegramGetCodePage implements ITelegramPage {
    private final TelegramGetSchemePage telegramGetSchemePage;
    @Override
    public ITelegramPage nextPage() {
        return telegramGetSchemePage;
    }

    @Override
    public SendMessage loadPageOnMessage(TelegramUserState userState, SendMessage message) {
        message.setText("Введите код:");
        return message;
    }
}
