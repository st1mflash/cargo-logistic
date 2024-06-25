package com.ansekolesnikov.cargologistic.pages;

import com.ansekolesnikov.cargologistic.states.TelegramUserState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@RequiredArgsConstructor
@Component
public class TelegramGetWidthPage implements ITelegramPage {
    private final TelegramGetHeightPage telegramGetHeightPage;
    @Override
    public ITelegramPage nextPage() {
        return telegramGetHeightPage;
    }

    @Override
    public SendMessage loadPageOnMessage(TelegramUserState userState, SendMessage message) {
        message.setText("Введите ширину:");
        return message;
    }
}
