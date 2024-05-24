package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.service.service_input.ServiceInput;
import com.ansekolesnikov.cargologistic.entity.TelegramUserMessage;
import com.ansekolesnikov.cargologistic.controller.TelegramBotController;
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
                yield toStringBotInfo();
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
