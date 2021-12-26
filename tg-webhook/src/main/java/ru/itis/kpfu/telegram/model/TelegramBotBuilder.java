package ru.itis.kpfu.telegram.model;

import com.pengrad.telegrambot.TelegramBot;

import java.util.Objects;

public class TelegramBotBuilder {

    private final TelegramBot telegramBot;

    public TelegramBotBuilder() {
        var prop = System.getenv("BOT_TOKEN");
        if (Objects.nonNull(prop)) {
            this.telegramBot = new TelegramBot(prop);
        } else {
            throw new IllegalArgumentException("Bot token hasn't been provided");
        }
    }

    public static TelegramBotBuilder builder() {
        return new TelegramBotBuilder();
    }

    public TelegramBot build() {
        return telegramBot;
    }

}
