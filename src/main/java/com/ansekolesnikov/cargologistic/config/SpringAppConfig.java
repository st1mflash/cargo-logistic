package com.ansekolesnikov.cargologistic.config;

import com.ansekolesnikov.cargologistic.database.FlywayMigration;
import com.ansekolesnikov.cargologistic.service.cargo.car.CarService;
import com.ansekolesnikov.cargologistic.service.cargo.load_file.LoadFileCargoService;
import com.ansekolesnikov.cargologistic.service.cargo.load_list.LoadListCargoService;
import com.ansekolesnikov.cargologistic.service.cargo.pack.PackService;
import com.ansekolesnikov.cargologistic.service.cargo.view_file.ViewFileCargoService;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import com.ansekolesnikov.cargologistic.service.telegram.TelegramService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
        //@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class SpringAppConfig {
    private static final Logger LOGGER = Logger.getLogger(SpringAppConfig.class.getName());

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
    private FlywayMigration flywayMigration;
    private LoadFileCargoService loadFileCargoService;
    private LoadListCargoService loadListCargoService = new LoadListCargoService();
    private ViewFileCargoService viewFileCargoService = new ViewFileCargoService();
    private TelegramService telegramService = new TelegramService();
    private CarService carService = new CarService();
    private PackService packService = new PackService();
    private DatabaseService databaseService;


    @Bean
    public DatabaseService databaseService() {
        databaseService = new DatabaseService(DB_URL, DB_USERNAME, DB_PASSWORD);
        flywayMigration = new FlywayMigration(DB_URL, DB_USERNAME, DB_PASSWORD);
        LOGGER.info("Сервис работы базы данных - успешно запущен.");
        return databaseService;
    }

    @Bean
    public TelegramService telegramService() {
        telegramService.startBot(TELEGRAM_BOT_TOKEN, TELEGRAM_BOT_USERNAME);
        LOGGER.info("Сервис работы телеграм ботов - успешно запущен.");
        return telegramService;
    }

    @Bean
    public PackService packService() {
        packService = new PackService(databaseService);
        return packService;
    }

    @Bean
    public CarService carService() {
        carService = new CarService(databaseService);
        return carService;
    }

    @Bean
    public LoadListCargoService loadListCargoService() {
        loadListCargoService = new LoadListCargoService(databaseService);
        return loadListCargoService;
    }
}
