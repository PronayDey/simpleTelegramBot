
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.*;
import java.util.Random;

public class MyAmazingBot extends TelegramLongPollingBot {



    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            System.out.println(messageText);
            long chatId = update.getMessage().getChatId();
            try {

                Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);


                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE chat_id = ?");
                pstmt.setLong(1, chatId);
                ResultSet rs = pstmt.executeQuery();
                boolean userExists = rs.next();
                rs.close();
                pstmt.close();


                if (userExists) {

                    pstmt = conn.prepareStatement("UPDATE users SET last_message = ? WHERE chat_id = ?");
                    pstmt.setString(1, messageText);
                    pstmt.setLong(2, chatId);
                    pstmt.executeUpdate();
                    pstmt.close();
                } else {

                    pstmt = conn.prepareStatement("INSERT INTO users (chat_id, last_message) VALUES (?, ?)");
                    pstmt.setLong(1, chatId);
                    pstmt.setString(2, messageText);
                    pstmt.executeUpdate();
                    pstmt.close();
                }


                conn.close();

                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                message.setText("Your message has been received!");
                execute(message);

            } catch (SQLException e) {

                e.printStackTrace();
            } catch (TelegramApiException e) {

                e.printStackTrace();
            }
        }
    }


    @Override
    public String getBotUsername() {

        return "ProntyBot";
    }

    @Override
    public String getBotToken() {

        return "6289412503:AAELVaHDO8qITAIzcyihFsvOda7MWNawg3A";
    }
}
