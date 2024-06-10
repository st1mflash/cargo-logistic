package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.service.service_input.PackModelServiceRequest;
import com.ansekolesnikov.cargologistic.service.service_input.ServiceRequest;
import com.ansekolesnikov.cargologistic.entity.TelegramUserMessage;
import com.ansekolesnikov.cargologistic.controller.TelegramBotController;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Setter
@Service
public class TelegramBotService {
    private final LoadFileService loadFileService;
    private final LoadListService loadListService;
    private final ViewFileService viewFileService;
    private final PackModelService packModelService;
    private final CarModelService carModelService;
    private static final Logger LOGGER = Logger.getLogger(TelegramBotService.class.getName());

    private final String TELEGRAM_BOT_USERNAME;
    private final String TELEGRAM_BOT_TOKEN;

    public TelegramBotService(
            LoadFileService loadFileService,
            LoadListService loadListService,
            ViewFileService viewFileService,
            PackModelService packModelService,
            CarModelService carModelService,
            @Value("${telegram.bot.username}") String telegramBotUsername,
            @Value("${telegram.bot.token}") String telegramBotToken
    ) {
        this.loadFileService = loadFileService;
        this.loadListService = loadListService;
        this.viewFileService = viewFileService;
        this.packModelService = packModelService;
        this.carModelService = carModelService;

        this.TELEGRAM_BOT_USERNAME = telegramBotUsername;
        this.TELEGRAM_BOT_TOKEN = telegramBotToken;

        try {
            new TelegramBotsApi(DefaultBotSession.class)
                    .registerBot(
                            new TelegramBotController(this, TELEGRAM_BOT_TOKEN, TELEGRAM_BOT_USERNAME)
                    );
        } catch (TelegramApiException e) {
            LOGGER.error("Ошибка запуска телеграм-бота. Подробнее: " + e);
        }
    }

    public String toStringBotAnswer(TelegramUserMessage inputMessage) {
        ServiceRequest serviceRequest = new ServiceRequest(inputMessage.getText());
        String textAnswer = switch (inputMessage.getCommand()) {
            case INFO -> {
                LOGGER.info("Запрос информации о командах бота. Telegram ID пользователя: '" + inputMessage.getChatId() + "'");
                yield toStringBotInfo();
            }
            case LOAD_FILE -> {
                LOGGER.info("Запрос загрузки из файла. Telegram ID пользователя: '" + inputMessage.getChatId() + "'");
                yield loadFileService.runService(serviceRequest)
                        .toString();
            }
            case LOAD_LIST -> {
                LOGGER.info("Запрос ручной загрузки. Telegram ID пользователя: '" + inputMessage.getChatId() + "'");
                yield loadListService.runService(serviceRequest)
                        .toString();
            }
            case VIEW_FILE -> {
                LOGGER.info("Запрос отображения информации о грузовиках из файла. Telegram ID пользователя: '" + inputMessage.getChatId() + "'");
                yield viewFileService.runService(serviceRequest)
                        .toString();
            }
            case CAR -> {
                LOGGER.info("Запрос на создание/изменение/удаление модели автомобиля. Telegram ID пользователя: '" + inputMessage.getChatId() + "'");
                yield carModelService.runService(serviceRequest)
                        .toString();
            }
            case PACK -> {
                LOGGER.info("Запрос на создание/изменение/удаление посылки. Telegram ID пользователя: '" + inputMessage.getChatId() + "'");
                yield packModelService.runService(serviceRequest)
                        .toString();
            }
        };

        return convertStringToTelegramCodeStyle(textAnswer);
    }

    private String convertStringToTelegramCodeStyle(String text) {
        return "```Ответ:\n" + text + "```";
    }

    private String toStringBotInfo() {
        return """
                Доступные команды:

                Загрузка машин данными из файла:
                load_file [название файла] [алгоритм: max/half/type] [кол-во машин]

                Загрузка машин введенными данными:
                load_list [модель машины] [алгоритм: max/half/type] [кол-во машин]:
                [модель посылки]
                [модель посылки]
                [ ... ]

                Отображение из файла загруженных машин:
                view_file [название файла]

                Отображение всех моделей машин:
                car list

                Добавление новой модели машины:
                car insert [название] [ширина кузова] [высота кузова]

                Обновление модели машины:
                car update [ID машины] [параметр: name/width/height] [новое значение]

                Удаление модели машины:
                car delete [ID машины]

                Отображение всех моделей посылок:
                pack list

                Добавление новой модели посылки:
                pack insert [название] [код] [схема] [ширина] [высота]

                Обновление модели посылки:
                pack update [ID посылки] [параметр: name/code/scheme/width/height] [новое значение]

                Удаление модели посылки:
                pack delete [ID посылки]""";
    }
}
