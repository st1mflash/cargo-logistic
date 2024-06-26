package com.ansekolesnikov.cargologistic.pages;

import com.ansekolesnikov.cargologistic.states.UserState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@RequiredArgsConstructor
@Component
public class TelegramGetSchemePage implements ITelegramPage {
    private final TelegramGetWidthPage telegramGetWidthPage;
    @Override
    public ITelegramPage nextPage() {
        return telegramGetWidthPage;
    }

    @Override
    public SendMessage loadPage(UserState userState) {
        SendMessage message = new SendMessage();
        message.setText("Введите схему:");
        return message;
    }
}
