package Data;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class PriceParse {
    public static String Parse(String URL) throws IOException {
        Document doc = Jsoup.connect(URL).get();
        Elements price = doc.getElementsByAttributeValue("class", "price-block__final-price");
        String title = price.text();
        /*
        final String[] pr = new String[2];
        price.forEach(pricing -> {
            Element spanPrice = pricing.child(0);
            String title = spanPrice.child(0).text();
            pr[1] = title;
            System.out.println(pr[1]);
        });
        */
        return title;
    }
}
