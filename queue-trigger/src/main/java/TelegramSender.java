import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.response.SendResponse;

public class TelegramSender {

    private final TelegramBot telegramBot;

    public TelegramSender(String botToken) {
        this.telegramBot = new TelegramBot(botToken);
    }

    public void sendPhoto(String chatId, byte[] photoBytes, String captionKey) {
        SendPhoto sendPhoto = new SendPhoto(chatId, photoBytes).caption("кто это? \n" + captionKey);
        telegramBot.execute(sendPhoto);
    }

}
