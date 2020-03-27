package sample;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;

public class Telegram{
    TelegramBot bot;
    public Telegram(String apiKey) {
        bot = new TelegramBot(apiKey);
    }
    public void sendMessage(String name, int price, String url, String pubTime){
        bot.execute(new SendMessage(301604628, ("Новая объява на авито! "+name+". Цена: "+price+". Опубликована: "+pubTime+". Ссылка: "+url)));
    }
}
