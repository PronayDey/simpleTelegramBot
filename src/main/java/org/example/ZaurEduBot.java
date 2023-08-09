package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.groupadministration.PromoteChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Math.toIntExact;

public class ZaurEduBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {

            // We check if the update has a message and the message has text
            if (update.hasMessage() && update.getMessage().hasText()) {
                String message_text = update.getMessage().getText();
                long chat_id = update.getMessage().getChatId();
                if (update.getMessage().getText().equals("/start")) {


                    SendMessage message = new SendMessage() ;// Create a message object object
                          message .setChatId(chat_id);
                           message.setText("You send /start");
                    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                    List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                    List<InlineKeyboardButton> rowInline = new ArrayList<>();
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText("Update message text");
                    button.setCallbackData("update_msg_text");
                    rowInline.add(button);
                    // Set the keyboard to the markup
                    rowsInline.add(rowInline);
                    // Add it to the message
                    markupInline.setKeyboard(rowsInline);
                    message.setReplyMarkup(markupInline);
                    try {
                        execute(message); // Sending our message object to user
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {

                }

            } else if (update.hasCallbackQuery()) {
                // Set variables
                String call_data = update.getCallbackQuery().getData();
                int message_id = update.getCallbackQuery().getMessage().getMessageId();
                long chat_id = update.getCallbackQuery().getMessage().getChatId();

                if (call_data.equals("update_msg_text")) {
                    String answer = "Updated message text";
                    EditMessageText new_message = new EditMessageText();
                    new_message .setChatId(chat_id);
                    new_message .setMessageId(message_id);
                    new_message .setText(answer);
                    try {
                        System.out.println(chat_id);
                        System.out.println(message_id);
                        execute(new_message);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        @Override
        public String getBotToken () {
            return "6289412503:AAELVaHDO8qITAIzcyihFsvOda7MWNawg3A";
        }

        @Override
        public String getBotUsername () {
            return "ProntyBot";
        }




}