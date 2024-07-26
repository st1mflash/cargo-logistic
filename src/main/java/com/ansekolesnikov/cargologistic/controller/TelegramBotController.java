package com.ansekolesnikov.cargologistic.controller;

import com.ansekolesnikov.cargologistic.service.TelegramBotService;
import com.ansekolesnikov.cargologistic.service.TelegramUserStateService;
import com.ansekolesnikov.cargologistic.states.TelegramUserState;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.jvnet.hk2.annotations.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.api.objects.webapp.WebAppInfo;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TelegramBotController extends TelegramLongPollingBot {
    private final TelegramBotService telegramBotService;
    private final TelegramUserStateService userStateService;
    private final String BOT_TOKEN;
    private final String BOT_USERNAME;
    private static final Logger LOGGER = Logger.getLogger(TelegramBotController.class.getName());

    @Override
    public void onUpdateReceived(Update update) {
        try {
            Message message = update.getMessage();


            if (message != null && message.hasText()) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(message.getChatId());
                sendMessage.setText("Нажмите на кнопку, чтобы открыть внешнюю страницу приложения");

                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rows = new ArrayList<>();
                List<InlineKeyboardButton> row = new ArrayList<>();
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText("Открыть Mini Apps приложение");
                //button.setCallbackData("https://google.com/");
                button.setWebApp(new WebAppInfo("https://google.com/"));
                row.add(button);
                rows.add(row);
                inlineKeyboardMarkup.setKeyboard(rows);
                sendMessage.setReplyMarkup(inlineKeyboardMarkup);

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }

            /*
            Long userId = update.getMessage().getFrom().getId();
            TelegramUserState telegramUserState;
            if (userStateService.isExistUserStateById(userId)) {
                telegramUserState = userStateService.updateUserState(
                        userStateService.loadUserState(userId),
                        update.getMessage()
                );
            } else {
                telegramUserState = userStateService.loadUserState(userId);
            }
            sendMessage(userId, loadPageByState(telegramUserState));

             */
        } catch (Exception e) {
            LOGGER.error(e);
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
