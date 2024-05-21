package com.ansekolesnikov.cargologistic.config;

import com.ansekolesnikov.cargologistic.controller.ShellController;
import com.ansekolesnikov.cargologistic.database.FlywayMigration;
import com.ansekolesnikov.cargologistic.service.cargo.load_file.LoadFileService;
import com.ansekolesnikov.cargologistic.service.cargo.load_file.LoadFileServiceUtils;
import com.ansekolesnikov.cargologistic.service.cargo.load_list.LoadListService;
import com.ansekolesnikov.cargologistic.service.cargo.load_list.LoadListServiceUtils;
import com.ansekolesnikov.cargologistic.service.cargo.view_file.ViewFileService;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import com.ansekolesnikov.cargologistic.service.telegram.TelegramService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAppConfig {
    //private static final Logger LOGGER = Logger.getLogger(SpringAppConfig.class.getName());

    @Value("${telegram.bot.username}")
    private String TELEGRAM_BOT_USERNAME;
    @Value("${telegram.bot.token}")
    private String TELEGRAM_BOT_TOKEN;
    @Value("${spring.datasource.url}")
    private String DB_URL;
    @Value("${spring.datasource.username}")
    private String DB_USERNAME;
    @Value("${spring.datasource.password}")
    private String DB_PASSWORD;
    @Value("${directory.car.import}")
    private String PATH_IMPORT_CAR;
    @Value("${directory.pack.import}")
    private String PATH_IMPORT_PACKAGE;

    private ShellController shellController;
    private FlywayMigration flywayMigration;
    private LoadFileService loadFileService;
    private LoadListService loadListService;
    private ViewFileService viewFileService;
    private TelegramService telegramService = new TelegramService();
    private DatabaseService databaseService;
    private LoadListServiceUtils loadListServiceUtils = new LoadListServiceUtils();
    private LoadFileServiceUtils loadFileServiceUtils = new LoadFileServiceUtils();

    @Bean
    public DatabaseService databaseService() {
        databaseService = new DatabaseService(DB_URL, DB_USERNAME, DB_PASSWORD);
        flywayMigration = new FlywayMigration(DB_URL, DB_USERNAME, DB_PASSWORD);
        //LOGGER.info("Сервис работы базы данных - успешно запущен.");
        return databaseService;
    }

    @Bean
    public TelegramService telegramService() {
        telegramService.startBot(TELEGRAM_BOT_TOKEN, TELEGRAM_BOT_USERNAME);
        //LOGGER.info("Сервис работы телеграм ботов - успешно запущен.");
        return telegramService;
    }

    @Bean
    public ShellController shellController() {
        shellController = new ShellController(loadFileService, viewFileService);
        return shellController;
    }

    @Bean
    public ViewFileService viewFileService() {
        viewFileService = new ViewFileService(PATH_IMPORT_CAR);
        return viewFileService;
    }

    @Bean
    public LoadListService loadListService() {
        loadListService = new LoadListService(
                loadListServiceUtils
        );
        return loadListService;
    }

    @Bean
    public LoadFileService loadFileService() {
        loadFileService = new LoadFileService(
                databaseService,
                loadFileServiceUtils,
                PATH_IMPORT_PACKAGE
        );
        return loadFileService;
    }
}
