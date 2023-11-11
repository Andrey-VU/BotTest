package org.tutorial;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Objects;

public class Bot extends TelegramLongPollingBot {
    private final String botName;
    InlineKeyboardButton show;
    InlineKeyboardButton add;
    private final InlineKeyboardMarkup keyboardMarkup;

    public Bot(String botName, String botToken) {
        super(botToken);
        this.botName = botName;

        this.show = InlineKeyboardButton.builder()
                .text("Показать, кто уже записан")
                .callbackData("show")
                .build();

        this.add = InlineKeyboardButton.builder()
                .text("Записаться в волонтеры")
                .callbackData("next")
                .build();

        keyboardMarkup = InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(show, add))
                .build();
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(Update update) {
        var userId = !Objects.isNull(update.getCallbackQuery()) ? update.getCallbackQuery().getFrom().getId() : update.getMessage().getFrom().getId();
        var buttonName = !Objects.isNull(update.getCallbackQuery()) ? update.getCallbackQuery().getData() : null;

        if (!Objects.isNull(buttonName))
            sendText(userId, buttonName);

        sendMenu(userId, "Выберите действие", keyboardMarkup);
    }

    public void sendMenu(Long userId, String text, InlineKeyboardMarkup kb) {
        SendMessage message = SendMessage.builder()
                .chatId(userId.toString())
                .parseMode("HTML")
                .text(text)
                .replyMarkup(kb)
                .build();

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendText(Long userId, String text) {
        SendMessage message = SendMessage.builder()
                .chatId(userId.toString()) //Who are we sending a message to
                .text(text).build();    //Message content
        try {
            execute(message);                        //Actually sending the message
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);      //Any error will be printed here
        }
    }
}