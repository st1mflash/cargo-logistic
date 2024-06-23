package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.constants.MessageConstant;
import com.ansekolesnikov.cargologistic.controller.TelegramBotController;
import com.ansekolesnikov.cargologistic.entity.TelegramUserMessage;
import com.ansekolesnikov.cargologistic.interfaces.IRunnableByStringService;
import com.ansekolesnikov.cargologistic.mappers.TelegramMessageMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Getter
@Setter
@Component
public class TelegramBotService {
    private final LoadFileService loadFileService;
    private final LoadListService loadListService;
    private final ViewFileService viewFileService;
    private final PackModelService packModelService;
    private final CarModelService carModelService;
    private final TelegramMessageMapper telegramMessageMapper;
    private String telegramBotToken;
    private String telegramBotUsername;
    private static final Logger LOGGER = Logger.getLogger(TelegramBotService.class.getName());

    public TelegramBotService(
            LoadFileService loadFileService,
            LoadListService loadListService,
            ViewFileService viewFileService,
            PackModelService packModelService,
            CarModelService carModelService,
            TelegramMessageMapper telegramMessageMapper,
            @Value("${telegram.bot.username}") String telegramBotUsername,
            @Value("${telegram.bot.token}") String telegramBotToken
    ) {
        this.loadFileService = loadFileService;
        this.loadListService = loadListService;
        this.viewFileService = viewFileService;
        this.packModelService = packModelService;
        this.carModelService = carModelService;
        this.telegramMessageMapper = telegramMessageMapper;

        try {
            new TelegramBotsApi(DefaultBotSession.class)
                    .registerBot(
                            new TelegramBotController(this, telegramMessageMapper, telegramBotToken, telegramBotUsername)
                    );
        } catch (TelegramApiException e) {
            LOGGER.error(MessageConstant.TELEGRAM_START_ERROR + e);
        }
    }

    public IRunnableByStringService selectService(TelegramUserMessage userMessage) {
        try {
            return switch (userMessage.getCommand()) {
                case CAR -> carModelService;
                case PACK -> packModelService;
                case LOAD_FILE -> loadFileService;
                case LOAD_LIST -> loadListService;
                case VIEW_FILE -> viewFileService;
                default -> null;
            };
        } catch (NullPointerException e) {
            return null;
        }
    }

    public String toStringBotInfo() {
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
