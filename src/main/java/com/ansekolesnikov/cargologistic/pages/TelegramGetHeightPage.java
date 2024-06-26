package com.ansekolesnikov.cargologistic.pages;

import com.ansekolesnikov.cargologistic.states.UserState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@RequiredArgsConstructor
@Component
public class TelegramGetHeightPage implements ITelegramPage {
    private final TelegramMenuPage telegramMenuPage;
    @Override
    public ITelegramPage nextPage() {
        return telegramMenuPage;
    }

    @Override
    public SendMessage loadPage(UserState userState) {
        SendMessage message = new SendMessage();
        message.setText("Введите высоту:");
        return message;
    }
}
