package Data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;


public class PriceParse {

    public static String Parse(String URL, int name) throws IOException {
        Document doc = Jsoup.connect(URL).get();
        AtomicReference<String> title = new AtomicReference<>("");

        if(name==0) { //wildberries
            Elements price = doc.getElementsByAttributeValue("class", "price-block__final-price");
            title.set(price.text());
        }
        else if(name==1){ //ozon
            Elements price = doc.getElementsByAttributeValue("slot", "content");
            price.forEach(pricing -> {
                Element level1 = pricing.child(1);
                Element level2 = level1.child(0);
                Element level3 = level2.child(0);
                Element level4 = level3.child(0);
                title.set(level4.text());
            });
        }
        else if(name==2){ // yandex.market
            Elements price = doc.getElementsByAttributeValue("class", "_3NaXx _3kWlK");
            price.forEach(pricing -> {
                Element spanPrice = pricing.child(0);
                title.set(spanPrice.child(0).text()+"₽");
            });
        }
        else if(name==3){ //citilink
            Elements price = doc.getElementsByAttributeValue("class", "ProductHeader__price-default_current-price js--ProductHeader__price-default_current-price");
            title.set(price.text()+"₽");
        }
        /*
        final String[] pr = new String[2];
        price.forEach(pricing -> {
            Element spanPrice = pricing.child(0);
            String title = spanPrice.child(0).text();
            pr[1] = title;
            System.out.println(pr[1]);
        });
        */
        return title.get();
    }

    public static String ParseTitle(String URL) throws IOException {
        Document doc = Jsoup.connect(URL).get();
        String title = doc.title();
        return title;
    }

    public static String imgURL(String URL) throws IOException {
        Document doc = Jsoup.connect(URL).get();
        Element img = doc.select("img").get(1);
        String imgSrc = img.absUrl("src");
        return imgSrc;
    }
}
