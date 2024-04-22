package com.ansekolesnikov.cargologistic.model.telegram;

public class TelegramUtils {
    public static String getCommandByUserString(String userText) {
        return userText.toLowerCase().split(" ")[0];
    }

    public static String getFileNameByUserString(String userText) {
        return userText.toLowerCase().split(" ")[1];
    }

    public static String getAlgorithmByUserString(String userText) {
        return userText.toLowerCase().split(" ")[2];
    }

    public static String getCountCardsByUserString(String userText) {
        return userText.toLowerCase().split(" ")[3];
    }
}
