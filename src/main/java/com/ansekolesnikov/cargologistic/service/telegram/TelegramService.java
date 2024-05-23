package com.ansekolesnikov.cargologistic.service.telegram;

import com.ansekolesnikov.cargologistic.entity.command.CommandLine;
import com.ansekolesnikov.cargologistic.entity.telegram.TelegramUserMessage;
import com.ansekolesnikov.cargologistic.handler.TelegramHandler;
import com.ansekolesnikov.cargologistic.service.main.car.CarService;
import com.ansekolesnikov.cargologistic.service.main.load.file.LoadFileService;
import com.ansekolesnikov.cargologistic.service.main.load.list.LoadListService;
import com.ansekolesnikov.cargologistic.service.main.pack.PackService;
import com.ansekolesnikov.cargologistic.service.main.view.ViewFileService;
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
public class TelegramService {
    @Autowired
    private LoadFileService loadFileCargoService;
    @Autowired
    private LoadListService loadListCargoService;
    @Autowired
    private ViewFileService viewFileCargoService;
    @Autowired
    private PackService packService;
    @Autowired
    private CarService carService;
    @Autowired
    private TelegramServiceUtils serviceUtils;
    private static final Logger LOGGER = Logger.getLogger(TelegramService.class.getName());

    public void startBot(String bot_token, String bot_username) {
        try {
            new TelegramBotsApi(DefaultBotSession.class)
                    .registerBot(
                            new TelegramHandler(this, bot_token, bot_username)
                    );
        } catch (TelegramApiException e) {
            LOGGER.error("Ошибка запуска телеграм-бота. Подробнее: " + e);
        }
    }

    public String toStringBotAnswer(TelegramUserMessage inputMessage) {
        CommandLine serviceCommandLine = new CommandLine(inputMessage.getText());
        String textAnswer = switch (inputMessage.getCommand()) {
            case INFO -> {
                LOGGER.info("Запрос информации о командах бота. Telegram ID пользователя: '" + inputMessage.getChatId() + "'");
                yield serviceUtils.toStringBotInfo();
            }
            case LOAD_FILE -> {
                LOGGER.info("Запрос загрузки из файла. Telegram ID пользователя: '" + inputMessage.getChatId() + "'");
                yield loadFileCargoService.runService(serviceCommandLine)
                        .getResultLoadFileServiceRun()
                        .getStringResult();
            }
            case LOAD_LIST -> {
                LOGGER.info("Запрос ручной загрузки. Telegram ID пользователя: '" + inputMessage.getChatId() + "'");
                yield loadListCargoService.runService(serviceCommandLine)
                        .getResultLoadListServiceRun()
                        .getStringResult();
            }
            case VIEW_FILE -> {
                LOGGER.info("Запрос отображения информации о грузовиках из файла. Telegram ID пользователя: '" + inputMessage.getChatId() + "'");
                yield viewFileCargoService.runService(serviceCommandLine)
                        .getResultViewFileServiceRun()
                        .getStringResult();
            }
            case CAR -> {
                LOGGER.info("Запрос на создание/изменение/удаление модели автомобиля. Telegram ID пользователя: '" + inputMessage.getChatId() + "'");
                yield carService.runService(serviceCommandLine)
                        .getResultCarServiceRun()
                        .getStringResult();
            }
            case PACK -> {
                LOGGER.info("Запрос на создание/изменение/удаление посылки. Telegram ID пользователя: '" + inputMessage.getChatId() + "'");
                yield packService.runService(serviceCommandLine)
                        .getResultPackServiceRun()
                        .getStringResult();
            }
        };

        return serviceUtils.convertStringToTelegramCodeStyle(textAnswer);
    }
}
