package pl.migibud.htmlscrapper.internal.scrapper.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pl.migibud.htmlscrapper.internal.scrapper.api.Advertisement;
import pl.migibud.htmlscrapper.internal.timeprovider.api.TimeProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Slf4j
@Component
@RequiredArgsConstructor
class AdvertisementScrapperEngine {
    

    private static final String URL = "https://www.otodom.pl/pl/wyniki/wynajem/mieszkanie/dolnoslaskie/wroclaw/wroclaw/wroclaw/psie-pole?ownerTypeSingleSelect=ALL&distanceRadius=0&viewType=listing&limit=72&page=%s";
    private static final int ADVERTISEMENT_MAX_NUMBER = 72;
    
    private final TimeProvider timeProvider;

    @Async("threadPoolTaskExecutor")
    public Future<List<Advertisement>> readAdvertisementsFromPageAsync(int pageNumber){
        return CompletableFuture.supplyAsync(()->readAdvertisementsFromPage(pageNumber));
    }
    
    List<Advertisement> readAdvertisementsFromPage(int pageNumber){
        
        final var advertisements = new ArrayList<Advertisement>();
        try {
            // Fetch the HTML content of the webpage
            Document doc = Jsoup
                .connect(URL.formatted(pageNumber))
                .get();

            // Select the div containing the listings
            Element listingsDiv = doc.selectFirst("div[data-cy=search.listing.organic]");

            // Select all articles within the listings div
            Elements articles = listingsDiv.select("article[data-cy=listing-item]");
            log.info("Size: {}", articles.size());

            // Iterate over each article
            for (Element article : articles) {
                // Select and print the desired tags
                Element priceElement = article.selectFirst("span.css-1uwck7i.e1a3ad6s0");
                String price = priceElement.text();
                
                Element titleElement = article.selectFirst("p[data-cy=listing-item-title]");
                String title = titleElement.text();

                Element addressElement = article.selectFirst("p[data-testid=advert-card-address]");
                String address = addressElement.text();

                // Select the descriptionElements with the specified class
                Element descriptionElement = article.selectFirst(".css-uki0wd.e12r8p6s1");
                String description = descriptionElement.text();

                log.info("Price: {}", price);
                log.info("Title: {}", title);
                log.info("Address: {}", address);
                log.info("Description: {}", description);
                log.info("");

                advertisements.add(new Advertisement(price,title,address,description,timeProvider.dateNow()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("PAGE NUM: "+pageNumber+" RETURNED LIST WITH SIZE: "+advertisements.size());
        return advertisements;
    }

    int getTotalNumberOfPages() {
        try {
            Document doc = Jsoup.connect(URL.formatted(1)).get();
            Element divElement = doc.selectFirst("div.css-o0w5yo.e1fw9pn56");
            if (divElement != null) {
                String text = divElement.text();
                System.out.println(text);
                return Arrays
                    .stream(text.split(" "))
                    .reduce((first, second) -> second)
                    .map(Integer::parseInt)
                    .map(integer -> Math.ceil((double)integer / ADVERTISEMENT_MAX_NUMBER))
                    .map(Double::intValue)
                    .orElse(-1);
            }
            log.warn("Element not found!");
            return -1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
