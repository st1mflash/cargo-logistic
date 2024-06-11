package com.ansekolesnikov.cargologistic.service;

import com.ansekolesnikov.cargologistic.controller.TelegramBotController;
import com.ansekolesnikov.cargologistic.database.dao.CarModelDao;
import com.ansekolesnikov.cargologistic.dto.CarModelDto;
import com.ansekolesnikov.cargologistic.entity.CarModelEntity;
import com.ansekolesnikov.cargologistic.entity.TelegramUserMessage;
import com.ansekolesnikov.cargologistic.enums.CarModelParameterEnum;
import com.ansekolesnikov.cargologistic.enums.DatabaseOperationEnum;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Objects;

@Setter
@Service
public class TelegramBotService {
    private final CarModelDao carModelDao;

    private final LoadFileService loadFileService;
    private final LoadListService loadListService;
    private final ViewFileService viewFileService;
    private final PackModelService packModelService;
    private final CarModelService carModelService;
    private static final Logger LOGGER = Logger.getLogger(TelegramBotService.class.getName());

    private final String TELEGRAM_BOT_USERNAME;
    private final String TELEGRAM_BOT_TOKEN;

    public TelegramBotService(
            CarModelDao carModelDao,

            LoadFileService loadFileService,
            LoadListService loadListService,
            ViewFileService viewFileService,
            PackModelService packModelService,
            CarModelService carModelService,
            @Value("${telegram.bot.username}") String telegramBotUsername,
            @Value("${telegram.bot.token}") String telegramBotToken
    ) {
        this.carModelDao = carModelDao;

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

    public String toStringBotAnswer(TelegramUserMessage userMessage) {
        String textAnswer = switch (userMessage.getCommand()) {
            case INFO -> {
                LOGGER.info("Запрос информации о командах бота. Telegram ID пользователя: '" + userMessage.getChatId() + "'");
                yield toStringBotInfo();
            }
            case LOAD_FILE -> {
                LOGGER.info("Запрос загрузки из файла. Telegram ID пользователя: '" + userMessage.getChatId() + "'");
                yield loadFileService.runByStringService(userMessage.getText());
            }
            case LOAD_LIST -> {
                LOGGER.info("Запрос ручной загрузки. Telegram ID пользователя: '" + userMessage.getChatId() + "'");
                yield "";//loadListService.runService(serviceRequest)
                      //  .toString();
            }
            case VIEW_FILE -> {
                LOGGER.info("Запрос отображения информации о грузовиках из файла. Telegram ID пользователя: '" + userMessage.getChatId() + "'");
                yield "";//viewFileService.runService(serviceRequest)
                        //.toString();
            }
            case CAR -> {
                LOGGER.info("Запрос на создание/изменение/удаление модели автомобиля. Telegram ID пользователя: '" + userMessage.getChatId() + "'");
                yield carModelService.runByStringService(userMessage.getText());//runCarService(userMessage.getText());
            }
            case PACK -> {
                LOGGER.info("Запрос на создание/изменение/удаление посылки. Telegram ID пользователя: '" + userMessage.getChatId() + "'");
                yield packModelService.runByStringService(userMessage.getText());//packModelService.runService(serviceRequest)
                      //  .toString();
            }
        };

        return convertStringToTelegramCodeStyle(textAnswer);
    }

    private String runCarService(String userText) {
        DatabaseOperationEnum operation = DatabaseOperationEnum.initEnumFromString(userText.split(" ")[1]);
        switch (Objects.requireNonNull(operation)) {
            case LIST:
                StringBuilder carList = new StringBuilder();
                carModelService.getCarModelList().stream()
                        .map(CarModelEntity::to)
                        .forEach(c -> carList.append(c).append("\n\n"));
                return carList.toString();
            case GET:
                return CarModelEntity.to(carModelService.getCarModel(Integer.parseInt(userText.split(" ")[2]))).toString();
            case INSERT:
                CarModelDto carModelDto = CarModelDto.builder()
                        .name(userText.split(" ")[2])
                        .width(Integer.parseInt(userText.split(" ")[3]))
                        .height(Integer.parseInt(userText.split(" ")[4]))
                        .build();
                return CarModelEntity.to(carModelService.addCarModel(carModelDto)).toString();
            case UPDATE:
                return CarModelEntity.to(updateCarByParams(userText)).toString();
            case DELETE:
                carModelService.deleteCarModel(Integer.parseInt(userText.split(" ")[2]));
                return "Успешное удаление";
            default:
                return "Не удалось определить команду";
        }
    }

    private CarModelDto updateCarByParams(String commandString) {
        CarModelParameterEnum parameterEnum = CarModelParameterEnum.initEnumFromString(commandString.split(" ")[3]);
        String value = commandString.split(" ")[4];
        CarModelDto carModelDto = carModelDao.findById(Integer.parseInt(commandString.split(" ")[2]));
        switch (Objects.requireNonNull(parameterEnum)) {
            case NAME:
                carModelDto.setName(value);
                break;
            case WIDTH:
                carModelDto.setWidth(Integer.parseInt(value));
                break;
            case HEIGHT:
                carModelDto.setHeight(Integer.parseInt(value));
                break;
            default:
                break;
        }
        return carModelService.updateCarModel(carModelDto);
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
