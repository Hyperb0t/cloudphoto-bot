package ru.itis.kpfu.telegram.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import lombok.SneakyThrows;
import ru.itis.kpfu.telegram.model.Message;
import ru.itis.kpfu.telegram.model.Response;
import ru.itis.kpfu.telegram.model.TelegramBotBuilder;
import ru.itis.kpfu.telegram.model.Update;

import java.io.InputStream;
import java.util.Objects;

public class TelegramService {

    private final TelegramBot bot = TelegramBotBuilder.builder().build();
    private final FaceScanService faceScanService = new FaceScanService();

    public Response process(Update update) {
        try {
            System.out.println(update);
            var message = update.getMessage();
            return parseMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(update.toString());
            return Response.builder()
                    .statusCode(200)
                    .body("{\"message\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    private Response parseMessage(Message message) {
        var reply = message.getReply_to_message();
        var chat = message.getChat();
        var messageText = message.getText();

        if (message.getText().startsWith("/find")) {
            var blobs = faceScanService.findByName(messageText.substring(messageText.indexOf(" ") + 1));
            if (blobs.size() == 0) {
                sendToBot(chat.getId(), "Could not find any photos with required name");
            } else {
                blobs.forEach(blob -> sendToBot(chat.getId(), blob.getObjectContent()));
            }

            return Response.builder()
                    .statusCode(200)
                    .build();
        }

        if (Objects.nonNull(reply)) {
            faceScanService.setName(reply.getCaption().split("\n")[1], messageText);
            sendToBot(chat.getId(), "Successfully set name for a photo");
            return Response.builder()
                    .statusCode(200)
                    .build();
        }

        if (message.getText().equals("/start")){
            faceScanService.createChatId(message.getChat().getId().toString());
        }

        bot.execute(new SendMessage(message.getChat().getId(), "Cannot' recognize command"));
        return Response.builder()
                .statusCode(200)
                .build();
    }

    @SneakyThrows
    public void sendToBot(Long chatId, InputStream is) {
        SendMessage messageRequest = new SendMessage(chatId, "Sending you photo");
        SendPhoto sendRequest = new SendPhoto(chatId, is.readAllBytes());
        bot.execute(messageRequest);
        bot.execute(sendRequest);
    }

    public void sendToBot(Long chatId, String text) {
        SendMessage sendRequest = new SendMessage(chatId, text);
        bot.execute(sendRequest);
    }
}
