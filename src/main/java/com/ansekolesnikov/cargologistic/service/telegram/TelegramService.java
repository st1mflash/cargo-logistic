package com.ansekolesnikov.cargologistic.service.telegram;

import com.ansekolesnikov.cargologistic.handler.TelegramHandler;
import com.ansekolesnikov.cargologistic.model.command.CommandLine;
import com.ansekolesnikov.cargologistic.model.command.load_list.LoadListCommandLine;
import com.ansekolesnikov.cargologistic.model.telegram.TelegramUserMessage;
import com.ansekolesnikov.cargologistic.service.cargo.car.CarService;
import com.ansekolesnikov.cargologistic.service.cargo.load_file.LoadFileCargoService;
import com.ansekolesnikov.cargologistic.service.cargo.load_list.LoadListCargoService;
import com.ansekolesnikov.cargologistic.service.cargo.pack.PackService;
import com.ansekolesnikov.cargologistic.service.cargo.view_file.ViewFileCargoService;
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
    private LoadFileCargoService loadFileCargoService;
    @Autowired
    private LoadListCargoService loadListCargoService;
    @Autowired
    private ViewFileCargoService viewFileCargoService;
    @Autowired
    private PackService packService;
    @Autowired
    private CarService carService;
    @Autowired
    private TelegramServiceUtils serviceUtils;
    private static final Logger LOGGER = Logger.getLogger(TelegramService.class.getName());

    public void startBot(String bot_token, String bot_username) {
        try {
            new TelegramBotsApi(DefaultBotSession.class).registerBot(new TelegramHandler(this, bot_token, bot_username));
        } catch (TelegramApiException e) {
            LOGGER.error("Ошибка запуска телеграм-бота. Подробнее: " + e);
        }
    }

    public String getAnswer(TelegramUserMessage inputMessage) {
        String params;
        CommandLine commandLine;
        switch (inputMessage.getInputCommand()) {
            case "load_file":
                LOGGER.info("Запрос загрузки из файла '" + inputMessage.getInputFileName() + "' алгоритмом '" + inputMessage.getInputAlgorithm() + "' в " + inputMessage.getInputCountCars() + " ед. транспорта.");

                commandLine = new CommandLine(inputMessage.getText());
                return serviceUtils.formatToCodeStyle(loadFileCargoService.runService(commandLine));

            case "load_list":
                commandLine = new CommandLine(inputMessage.getText());
                return serviceUtils.formatToCodeStyle(loadListCargoService.runService(commandLine));

            case "view_file":
                LOGGER.info("Запрос отображения информации о грузовиках из файла '" + inputMessage.getInputFileName() + "'");

                commandLine = new CommandLine(inputMessage.getText());
                return serviceUtils.formatToCodeStyle(viewFileCargoService.runService(commandLine));

            case "car":
                commandLine = new CommandLine(inputMessage.getText());
                //return serviceUtils.formatToCodeStyle()

            case "pack":
                commandLine = new CommandLine(inputMessage.getText());
                return serviceUtils.formatToCodeStyle(carService.runService(commandLine));

            default:
                LOGGER.error("Не удалось определить введенную команду. Telegram ID пользователя: '" + inputMessage.getChatId() + "', текст сообщения: '" + inputMessage.getText() + "'");

                return serviceUtils.formatToCodeStyle("Не удалось определить введенную команду");
        }
    }
}
