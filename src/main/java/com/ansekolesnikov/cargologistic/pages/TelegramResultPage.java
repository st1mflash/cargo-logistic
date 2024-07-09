package com.ansekolesnikov.cargologistic.pages;

import com.ansekolesnikov.cargologistic.mappers.RequestStringMapper;
import com.ansekolesnikov.cargologistic.states.TelegramUserState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@RequiredArgsConstructor
@Component
public class TelegramResultPage implements ITelegramPage {
    private final TelegramMenuPage telegramMenuPage;
    private final RequestStringMapper requestStringMapper;

    @Override
    public SendMessage loadPage(TelegramUserState telegramUserState) {
        SendMessage message = telegramMenuPage.loadPage(telegramUserState);
        message.setParseMode("Markdown");
        message.setText("```Ответ:\n" +
                telegramUserState.getService().run(
                        requestStringMapper.toRequestString(
                                telegramUserState.getService().getClass(),
                                telegramUserState.getCommand())) + "```"
        );
        return message;
    }
}
