package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.service.TelegramBotService;
import com.ansekolesnikov.cargologistic.service.TelegramUserUserStateService;
import com.ansekolesnikov.cargologistic.states.TelegramUserState;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@RequiredArgsConstructor
@Service
public class TelegramBotController extends TelegramLongPollingBot {
    private final TelegramBotService telegramBotService;
    private final TelegramUserUserStateService userStateService;
    private final String BOT_TOKEN;
    private final String BOT_USERNAME;
    private static final Logger LOGGER = Logger.getLogger(TelegramBotController.class.getName());

    @Override
    public void onUpdateReceived(Update update) {
        try {
            Long userId = update.getMessage().getFrom().getId();
            TelegramUserState telegramUserState = userStateService.updateUserStateByMessage(
                    userStateService.loadUserState(userId),
                    update.getMessage()
            );
            sendMessage(userId, loadPageByState(telegramUserState));
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

    private SendMessage loadPageByState(TelegramUserState telegramUserState) {
        return telegramUserState.getPage().loadPage(telegramUserState);
    }
}
