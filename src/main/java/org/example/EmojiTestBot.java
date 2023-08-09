package org.example;


import org.apache.commons.lang3.StringEscapeUtils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmojiTestBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {

        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String user_first_name = update.getMessage().getChat().getFirstName();
            String user_last_name = update.getMessage().getChat().getLastName();
            long user_id = update.getMessage().getChat().getId();
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            String answer = "Hii "+ update.getMessage().getFrom().getFirstName() + " \uD83D\uDE4B\u200D♂️,\n\n" ;
            SendMessage message = new SendMessage(); // Create a message object object
                  message .setChatId(chat_id);
                   message .setText(answer);
            log(user_first_name, user_last_name, Long.toString(user_id), message_text, answer);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }




    @Override
    public String getBotUsername() {
        // Return bot username
        // If bot username is @MyAmazingBot, it must return 'MyAmazingBot'
        return "ProntyBot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
        return "6289412503:AAELVaHDO8qITAIzcyihFsvOda7MWNawg3A";
    }
    private void log(String first_name, String last_name, String user_id, String txt, String bot_answer) {
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + first_name + " " + last_name + ". (id = " + user_id + ") \n Text - " + txt);
        System.out.println("Bot answer: \n Text - " + bot_answer);
    }
}
