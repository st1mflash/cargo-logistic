package com.ansekolesnikov.cargologistic.service.telegram;

import com.ansekolesnikov.cargologistic.handler.TelegramHandler;
import com.ansekolesnikov.cargologistic.model.telegram.TelegramUserMessage;
import com.ansekolesnikov.cargologistic.service.utils.TelegramServiceUtils;
import com.ansekolesnikov.cargologistic.service.cargo.load.LoadCargoService;
import com.ansekolesnikov.cargologistic.service.cargo.view.ViewCargoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Service
public class TelegramService {
    @Autowired
    private LoadCargoService loadCargoService;
    @Autowired
    private ViewCargoService viewCargoService;
    @Autowired
    private TelegramServiceUtils serviceUtils;
    private static final Logger LOGGER = Logger.getLogger(TelegramService.class.getName());


    public TelegramService() {
    }

    public void startBot(String bot_token, String bot_username) {
        try {
            new TelegramBotsApi(DefaultBotSession.class).registerBot(new TelegramHandler(this, bot_token, bot_username));
        } catch (TelegramApiException e) {
            LOGGER.error("Ошибка запуска телеграм-бота. Подробнее: " + e);
        }
    }

    public String getAnswer(TelegramUserMessage inputMessage) {
        String params;
        switch (inputMessage.getInputCommand()) {
            case "load":
                LOGGER.info("Запрос загрузки из файла '" + inputMessage.getInputFileName() + "' алгоритмом '" + inputMessage.getInputAlgorithm() + "' в " + inputMessage.getInputCountCars() + " ед. транспорта.");

                params = serviceUtils.getStringParams(inputMessage);
                return serviceUtils.formatToCodeStyle(loadCargoService.runService(params));

            case "view":
                LOGGER.info("Запрос отображения информации о грузовиках из файла '" + inputMessage.getInputFileName() + "'");

                params = inputMessage.getInputFileName();
                return serviceUtils.formatToCodeStyle(viewCargoService.runService(params));

            default:
                LOGGER.error("Не удалось определить введенную команду");

                return serviceUtils.formatToCodeStyle("Не удалось определить введенную команду");
        }
    }
}
