package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.service.service_input.ServiceInput;
import com.ansekolesnikov.cargologistic.entity.TelegramUserMessage;
import com.ansekolesnikov.cargologistic.controller.TelegramBotController;
import com.ansekolesnikov.cargologistic.service.utils.TelegramServiceUtils;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@NoArgsConstructor
@Setter
@Service
public class TelegramBotService {
    @Autowired
    private LoadFileService loadFileCargoService;
    @Autowired
    private LoadListService loadListCargoService;
    @Autowired
    private ViewFileService viewFileCargoService;
    @Autowired
    private PackModelService packModelService;
    @Autowired
    private CarModelService carModelService;
    @Autowired
    private TelegramServiceUtils serviceUtils;
    private static final Logger LOGGER = Logger.getLogger(TelegramBotService.class.getName());

    public void startBot(String bot_token, String bot_username) {
        try {
            new TelegramBotsApi(DefaultBotSession.class)
                    .registerBot(
                            new TelegramBotController(this, bot_token, bot_username)
                    );
        } catch (TelegramApiException e) {
            LOGGER.error("Ошибка запуска телеграм-бота. Подробнее: " + e);
        }
    }

    public String toStringBotAnswer(TelegramUserMessage inputMessage) {
        ServiceInput serviceServiceInput = new ServiceInput(inputMessage.getText());
        String textAnswer = switch (inputMessage.getCommand()) {
            case INFO -> {
                LOGGER.info("Запрос информации о командах бота. Telegram ID пользователя: '" + inputMessage.getChatId() + "'");
                yield serviceUtils.toStringBotInfo();
            }
            case LOAD_FILE -> {
                LOGGER.info("Запрос загрузки из файла. Telegram ID пользователя: '" + inputMessage.getChatId() + "'");
                yield loadFileCargoService.runService(serviceServiceInput)
                        .toString();
            }
            case LOAD_LIST -> {
                LOGGER.info("Запрос ручной загрузки. Telegram ID пользователя: '" + inputMessage.getChatId() + "'");
                yield loadListCargoService.runService(serviceServiceInput)
                        .toString();
            }
            case VIEW_FILE -> {
                LOGGER.info("Запрос отображения информации о грузовиках из файла. Telegram ID пользователя: '" + inputMessage.getChatId() + "'");
                yield viewFileCargoService.runService(serviceServiceInput)
                        .toString();
            }
            case CAR -> {
                LOGGER.info("Запрос на создание/изменение/удаление модели автомобиля. Telegram ID пользователя: '" + inputMessage.getChatId() + "'");
                yield carModelService.runService(serviceServiceInput)
                        .toString();
            }
            case PACK -> {
                LOGGER.info("Запрос на создание/изменение/удаление посылки. Telegram ID пользователя: '" + inputMessage.getChatId() + "'");
                yield packModelService.runService(serviceServiceInput)
                        .toString();
            }
        };

        return serviceUtils.convertStringToTelegramCodeStyle(textAnswer);
    }
}
