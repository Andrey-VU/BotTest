package org.tutorial;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot  extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "BotTest";
    }

    @Override
    public String getBotToken() {
        return "6498565786:AAGkNrdCD0xQhj15UutY6LQovc3IVYMB8CM";
//        In the future, you should consider storing your token in a dedicated settings file
//        or in environment variables. Keeping it in the code is fine for the scope of this tutorial,
//        however, it's not very versatile and is generally considered bad practice.
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        var user = msg.getFrom();

        System.out.println(user.getFirstName() + " wrote " + msg.getText());
    }

    public void sendText(Long who, String what){
        SendMessage sm = SendMessage.builder()
            .chatId(who.toString()) //Who are we sending a message to
            .text(what).build();    //Message content
        try {
            execute(sm);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
}
