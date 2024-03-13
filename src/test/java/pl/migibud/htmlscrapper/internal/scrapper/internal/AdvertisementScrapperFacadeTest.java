package pl.migibud.htmlscrapper.internal.scrapper.internal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.migibud.htmlscrapper.internal.scrapper.api.AdvertisementScrapper;

@SpringBootTest
class AdvertisementScrapperFacadeTest {

    @Autowired
    private AdvertisementScrapper advertisementScrapper;
    
    
    @Test
    void shouldTest(){
        advertisementScrapper.checkAdvertisements();
    }
}