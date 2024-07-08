package com.ansekolesnikov.cargologistic.pages;

import com.ansekolesnikov.cargologistic.mappers.RequestStringMapper;
import com.ansekolesnikov.cargologistic.states.TelegramState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@RequiredArgsConstructor
@Component
public class TelegramResultPage implements ITelegramPage {
    private final TelegramMenuPage telegramMenuPage;
    private final RequestStringMapper requestStringMapper;

    @Override
    public SendMessage loadPage(TelegramState telegramState) {
        SendMessage message = telegramMenuPage.loadPage(telegramState);
        message.setParseMode("Markdown");
        message.setText("```Ответ:\n" +
                telegramState.getService().run(
                        requestStringMapper.toRequestString(
                                telegramState.getService().getClass(),
                                telegramState.getRequestString())) + "```"
        );
        return message;
    }
}
