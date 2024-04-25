package com.javarush.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static com.javarush.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "JR_New_My_001_Test_Bot"; // TODO: добавьте имя бота в кавычках
    public static final String TOKEN = ""; //TODO: добавьте токен бота в кавычках

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        // TODO: основной функционал бота будем писать здесь
        // отобразим сообщение о начале игры
        if (getMessageText().equals("/start")){
            sendPhotoMessageAsync("step_1_pic");
            setUserGlory(0);
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Взлом холодильника","step_1_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_1_btn")){
            sendPhotoMessageAsync("step_2_pic");
            addUserGlory(20);
            sendTextMessageAsync(STEP_2_TEXT, Map.of(
                    "Взять сосиску! +20 славы", "step_2_btn",
                    "Взять рыбку! +20 славы", "step_2_btn",
                    "Скинуть банку с огурцами! +20 славы", "step_2_btn"));
        }
        //взламываем робот пылесос
        if (getCallbackQueryButtonKey().equals("step_2_btn")){
            sendPhotoMessageAsync("step_3_pic");
            addUserGlory(20);
            sendTextMessageAsync(STEP_3_TEXT, Map.of("Взлом робота пылесоса","step_3_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_3_btn")){
            sendPhotoMessageAsync("step_4_pic");
            addUserGlory(30);
            sendTextMessageAsync(STEP_4_TEXT, Map.of(
                    "Отправить робопылесос за едой! +30 славы", "step_4_btn",
                    "Проехаться на робопылесосе! +30 славы", "step_4_btn",
                    "Убегать от робопылесоса! +30 славы", "step_4_btn"));
        }
        //взламываем камеру Go-Pro
        if (getCallbackQueryButtonKey().equals("step_4_btn")){
            sendPhotoMessageAsync("step_5_pic");
            addUserGlory(30);
            sendTextMessageAsync(STEP_5_TEXT, Map.of("Надеть и включить GoPro!", "step_5_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_5_btn")){
            sendPhotoMessageAsync("step_6_pic");
            addUserGlory(40);
            sendTextMessageAsync(STEP_6_TEXT, Map.of(
                    "Бегать по крышам, снимать на GoPro! +40 славы", "step_6_btn",
                    "С GoPro нападать на других котов из засады! +40 славы", "step_6_btn",
                    "С GoPro нападать на собак из засады! +40 славы", "step_6_btn"));
        }
        //взламываем комп
        if (getCallbackQueryButtonKey().equals("step_6_btn")) {
            sendPhotoMessageAsync("step_7_pic");
            addUserGlory(40);
            sendTextMessageAsync(STEP_7_TEXT, Map.of("Взлом пароля", "step_7_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_7_btn")){
            sendPhotoMessageAsync("step_8_pic");
            addUserGlory(50);
            sendTextMessageAsync(STEP_8_TEXT, Map.of("Выйти во двор", "step_8_btn"));
        }
        //хвастаемся другим котам
        if (getCallbackQueryButtonKey().equals("step_8_btn")){
            sendPhotoMessageAsync("final_pic");
            addUserGlory(50);
            sendTextMessageAsync(FINAL_TEXT, Map.of("Закончить игру", "step_9_btn"));
        }
        //Отображение количества набранных очков славы
        if (getMessageText().equals("/glory")){
            sendTextMessageAsync("Количество набранных очков славы: " + getUserGlory() + " очков!!! \n Можно начать сначала, жми /start");
        }
        if (getCallbackQueryButtonKey().equals("step_9_btn")){
            sendImageMessageAsync("C:\\Users\\Евгений\\Desktop\\TelegramBot\\src\\main\\resources\\images\\final_end_pic.jpg");
            sendTextMessageAsync("Можно начать сначала, жми /start \n Посмотреть набранные очки славы, жми /glory ");
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}
//        if (getMessageText().equals("/start")){
//                sendTextMessageAsync("Привет");
//                }
//
//                if (getMessageText().equals("/bye")){
//                sendTextMessageAsync("Пока");
//                }
//
//                if (getMessageText().equals("Как здоровье?")){
//                sendTextMessageAsync("Отлично");
//                }
//
//                if (getMessageText().contains("бомба")){
//                sendTextMessageAsync("WARNING");
//                }
//
//                if (getMessageText().equals("картинка")){
//                sendPhotoMessageAsync("step_8_pic");
//                }
//
//                if (getMessageText().equals("кот")){
//                sendTextMessageAsync("выбери кота)", Map.of("кот 1", "cat1", "кот 2", "cat2"));
//                }
//
//                if (getCallbackQueryButtonKey().equals("cat1")){
//                sendPhotoMessageAsync("step_1_pic");
//                }
//
//                if (getCallbackQueryButtonKey().equals("cat2")){
//                sendPhotoMessageAsync("step_2_pic");
//                }
//
//                if (getMessageText().equals("smile")){
//                var message = getLastSentMessage();
//                editTextMessageAsync(message.getMessageId(), message.getText() + " :-)");
//                }
//
//                if (getMessageText().equals("/bye")){
//                sendTextMessageAsync("Asta la vista, baby!");
//                }
//
//                // специально не обернул в if(), в условии ничего не сказано
//                sendTextMessageAsync("Ваше любимое животное?", Map.of("Кот", "cat4", "Собака", "dog6"));
//
//                if (getCallbackQueryButtonKey().equals("cat4")){
//                sendPhotoMessageAsync("step_4_pic");
//                }
//
//                if (getCallbackQueryButtonKey().equals("dog6")){
//                sendPhotoMessageAsync("step_6_pic");
//                }

