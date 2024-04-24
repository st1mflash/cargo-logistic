package com.ansekolesnikov.cargologistic.service.telegram;

import com.ansekolesnikov.cargologistic.handler.TelegramHandler;
import com.ansekolesnikov.cargologistic.model.telegram.TelegramUserMessage;
import com.ansekolesnikov.cargologistic.service.main.load.LoadCarService;
import com.ansekolesnikov.cargologistic.service.main.view.ViewCarService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Service
public class TelegramService {
    @Autowired
    private LoadCarService loadCarService;
    @Autowired
    private ViewCarService viewCarService;
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
        switch (inputMessage.getInputCommand()) {
            case "load":
                LOGGER.info("Запрос загрузки из файла '" + inputMessage.getInputFileName() + "' алгоритмом '" + inputMessage.getInputAlgorithm() + "' в " + inputMessage.getInputCountCars() + " ед. транспорта.");
                return convertTextToTelegramCodeStyle(loadCarService.runService(
                                inputMessage.getInputFileName(),
                                inputMessage.getInputAlgorithm(),
                                inputMessage.getInputCountCars()
                        )
                );
            case "view":
                LOGGER.info("Запрос отображения информации о грузовиках из файла '" + inputMessage.getInputFileName() + "'");
                return convertTextToTelegramCodeStyle(viewCarService.runService(
                                inputMessage.getInputFileName()
                        )
                );

            default:
                LOGGER.error("Не удалось определить введенную команду");
                return convertTextToTelegramCodeStyle("Не удалось определить введенную команду");
        }
    }

    private String convertTextToTelegramCodeStyle(String text) {
        return "```Ответ:\n" + text + "```";
    }
}
