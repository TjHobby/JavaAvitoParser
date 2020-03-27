package sample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

public class Pageprocessing implements Runnable{
    String query;
    Database database;
    Settings settings;
    Thread thread;
    Telegram bot;
    public Pageprocessing(String query, Settings settings, Telegram bot) {
        this.query = query;
        database = new Database(settings);
        this.settings = settings;
        this.bot = bot;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
       try {
            ArrayList<Adverstiment> adList = getAdverstiments();
            for (Adverstiment ad : adList) {
                if (!database.isListed(ad)) {
                    database.saveAdverstiment(ad);
                    if(ad.getCreatingDate().contains("час")||ad.getCreatingDate().contains("минут"))
                        bot.sendMessage(ad.getName(),ad.getPrice(),ad.getUrl(),ad.getCreatingDate());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Adverstiment> getAdverstiments() throws IOException {
        String url = "https://www.avito.ru/"+ (settings.isKurskOnly() ? "kursk?q=" : "rossiya?q=") + query;
        Document page = Jsoup.connect(url).get();
        int numAds = Integer.parseInt(page.getElementsByClass("page-title-count-1oJOc").text().replaceAll(" ", ""));
        if (numAds == 0)
            return null;
        double numPages = Math.ceil(numAds / 49.0);
        ArrayList adList = new ArrayList<Adverstiment>();
        for (int i = 1; i <= numPages; i++) {
            Document pageAds = Jsoup.connect(url + "&p=" + i).get();
            Elements ads = pageAds.getElementsByClass("description item_table-description");
            for (Element ad : ads) {
                adList.add(new Adverstiment(ad.getElementsByClass("snippet-link").text().replaceAll("'", ""),
                        ad.getElementsByClass("snippet-price").text().replaceAll("[/ //₽//[а-я]//[А-Я]]", "").equalsIgnoreCase("") ? 0 : Integer.parseInt(ad.getElementsByClass("snippet-price").text().replaceAll("[/ //₽//[а-я]//[А-Я]]", "")),
                        ad.getElementsByClass("item-address__string").text(),
                        ad.getElementsByAttribute("data-item-id").hasClass("js-catalog-delivery catalog-delivery catalog-delivery_with-margin") ? true : false,
                        "http://avito.ru" + ad.getElementsByClass("snippet-link").attr("href"), ad.getElementsByClass("snippet-date-info").text()));
            }

        }
        return adList;
    }

}

