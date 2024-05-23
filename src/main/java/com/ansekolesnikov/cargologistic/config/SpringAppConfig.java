package com.ansekolesnikov.cargologistic.config;

import com.ansekolesnikov.cargologistic.database.FlywayMigration;
import com.ansekolesnikov.cargologistic.service.main.load.file.LoadFileService;
import com.ansekolesnikov.cargologistic.service.main.view.ViewFileService;
import com.ansekolesnikov.cargologistic.service.database.DatabaseService;
import com.ansekolesnikov.cargologistic.service.telegram.TelegramService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAppConfig {
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

    @Bean
    public DatabaseService databaseService() {
        DatabaseService databaseService = new DatabaseService(DB_URL, DB_USERNAME, DB_PASSWORD);
        new FlywayMigration(DB_URL, DB_USERNAME, DB_PASSWORD);
        return databaseService;
    }

    @Bean
    public TelegramService telegramService() {
        TelegramService telegramService = new TelegramService();
        telegramService.startBot(TELEGRAM_BOT_TOKEN, TELEGRAM_BOT_USERNAME);
        return telegramService;
    }
}
