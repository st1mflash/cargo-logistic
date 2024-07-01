package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.constants.MessageConstant;
import com.ansekolesnikov.cargologistic.controller.TelegramBotController;
import com.ansekolesnikov.cargologistic.pages.TelegramPages;
import com.ansekolesnikov.cargologistic.states.TelegramState;
import com.ansekolesnikov.cargologistic.states.TelegramStateMachine;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

@Getter
@Setter
@Component
public class TelegramBotService {
    private final LoadFileService loadFileService;
    private final LoadListService loadListService;
    private final ViewFileService viewFileService;
    private final PackModelService packModelService;
    private final CarModelService carModelService;
    private final TelegramPages telegramPages;
    private final TelegramStateMachine telegramStateMachine;
    private String telegramBotToken;
    private String telegramBotUsername;
    private static final Logger LOGGER = Logger.getLogger(TelegramBotService.class.getName());

    public TelegramBotService(
            LoadFileService loadFileService,
            LoadListService loadListService,
            ViewFileService viewFileService,
            PackModelService packModelService,
            CarModelService carModelService,
            TelegramPages telegramPages,
            TelegramStateMachine telegramStateMachine,
            @Value("${telegram.bot.username}") String telegramBotUsername,
            @Value("${telegram.bot.token}") String telegramBotToken
    ) {
        this.loadFileService = loadFileService;
        this.loadListService = loadListService;
        this.viewFileService = viewFileService;
        this.packModelService = packModelService;
        this.carModelService = carModelService;
        this.telegramPages = telegramPages;
        this.telegramStateMachine = telegramStateMachine;

        try {
            new TelegramBotsApi(DefaultBotSession.class)
                    .registerBot(
                            new TelegramBotController(this, telegramBotToken, telegramBotUsername)
                    );
        } catch (TelegramApiException e) {
            LOGGER.error(MessageConstant.TELEGRAM_START_ERROR + e);
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


    public TelegramState loadOrCreateState(Long userId, Map<Long, TelegramState> map) {
        if(map.get(userId) != null) {
            return map.get(userId);
        } else {
            TelegramState telegramState = new TelegramState(telegramPages.getTelegramMenuPage());
            map.put(userId, telegramState);
            return telegramState;
        }
    }

    public TelegramState updateState(String message, TelegramState telegramState) {
        return telegramStateMachine.changeStateByMessage(telegramState, message);
    }
}
