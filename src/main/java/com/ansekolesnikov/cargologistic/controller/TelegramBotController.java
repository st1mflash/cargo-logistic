package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.mappers.TelegramMessageMapper;
import com.ansekolesnikov.cargologistic.service.TelegramBotService;
import com.ansekolesnikov.cargologistic.states.TelegramUserState;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class TelegramBotController extends TelegramLongPollingBot {
    private final TelegramBotService telegramBotService;
    private final TelegramMessageMapper telegramMessageMapper;
    //private final TelegramPackListState telegramPackListState;
    private final String BOT_TOKEN;
    private final String BOT_USERNAME;
    private static final Logger LOGGER = Logger.getLogger(TelegramBotController.class.getName());

    private final Map<Long, TelegramUserState> userStates = new HashMap<>();

    @Override
    public void onUpdateReceived(Update update) {
        try {
            Long chatId = update.getMessage().getChatId();
            TelegramUserState userState = telegramBotService.findOrCreateUserStateModel(chatId, userStates);
            userState = telegramBotService.updateUserState(update, userState);

            SendMessage message = userState.getPage().loadPageOnMessage(userState, new SendMessage());



            //sendMessage = telegramPackListState.setupStateOnTelegramMessage(sendMessage);
            //sendMessage.setChatId(update.getMessage().getChatId().toString());

/*
            TelegramMenuState telegramMenuState = new TelegramMenuState();
            SendMessage sendMessage = new SendMessage();
            sendMessage = telegramMenuState.setupStateOnTelegramMessage(sendMessage);
            sendMessage.setChatId(update.getMessage().getChatId().toString());
*/
            sendMessage(chatId, message);

            /*
            TelegramUserMessage userMessage = telegramMessageMapper.toTelegramUserMessage(update.getMessage());
            IRunnableByStringService service = telegramBotService.selectService(userMessage);
            RequestString request = new RequestString(service.getClass(), userMessage.getText());
            sendMessage(
                    userMessage.getChatId(),
                    service.run(request)
            );
            */
        } catch (Exception e) {
            LOGGER.error(e);
            sendMessage(
                    update.getMessage().getChatId(),
                    "Возникла ошибка.\n\n" + telegramBotService.toStringBotInfo()
            );
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    public void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();

        message.setParseMode("Markdown");
        message.setChatId(String.valueOf(chatId));
        message.setText("```Ответ:\n" + text + "```");

        try {
            execute(message);
        } catch (TelegramApiException e) {
            LOGGER.error("Ошибка отправки сообщения. Подробнее: " + e);
        }
    }

    public void sendMessage(Long chatId, SendMessage sendMessage) {
        sendMessage.setChatId(chatId);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            LOGGER.error(e);
        }
    }
}
