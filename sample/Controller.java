package sample;

import java.util.TimerTask;

public class Controller extends TimerTask {
    Settings settings;
    Telegram bot;
    public Controller(Settings settings) {
        this.settings = settings;
        bot = new Telegram(settings.getTgToken());
        run();
    }

    private String[] queryToArray(String query) {
        String[] result = query.split(", ");
        return result;
    }

    @Override
    public void run() {
        for(String s: queryToArray(settings.getKeywords())){
          Pageprocessing child = new Pageprocessing(s,settings,bot);
        }
    }
}