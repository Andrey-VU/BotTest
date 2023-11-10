package org.tutorial;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.net.SocketTimeoutException;

public class App
{
    public static void main( String[] args ) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        Bot bot = new Bot();                  //We moved this line out of the register method, to access it later
        botsApi.registerBot(bot);
//        User user = new User(1L, "And", true);
        bot.sendText(1L, "Hello World!");  //The L just turns the Integer into a Long
    }
}
