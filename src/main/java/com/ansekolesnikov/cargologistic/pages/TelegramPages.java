package com.ansekolesnikov.cargologistic.pages;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Data
public class TelegramPages {
    private final TelegramMenuPage telegramMenuPage;
    private final TelegramResultPage telegramResultPage;
    private final TelegramGetIdPage telegramGetIdPage;
    private final TelegramGetNamePage telegramGetNamePage;
    private final TelegramGetCodePage telegramGetCodePage;
    private final TelegramGetSchemePage telegramGetSchemePage;
    private final TelegramGetWidthPage telegramGetWidthPage;
    private final TelegramGetHeightPage telegramGetHeightPage;
    private final TelegramGetPackParamNamePage telegramGetPackParamNamePage;
    private final TelegramGetCarParamNamePage telegramGetCarParamNamePage;
    private final TelegramGetParamValuePage telegramGetParamValuePage;
    private final TelegramGetFileNamePage telegramGetFileNamePage;
    private final TelegramGetAlgorithmPage telegramGetAlgorithmPage;
    private final TelegramGetCountCarsPage telegramGetCountCarsPage;
    private final TelegramGetCarModelNamePage telegramGetCarModelNamePage;
    private final TelegramGetPackModelNamePage telegramGetPackModelNamePage;
}
