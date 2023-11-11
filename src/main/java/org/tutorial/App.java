package org.tutorial;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class App {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        var botName = args[0];
        var botToken = args[1];
        Bot bot = new Bot(botName, botToken);
        botsApi.registerBot(bot);
        System.out.println("bot is started");;
    }
}
