package pl.migibud.htmlscrapper.internal.scheduler.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.migibud.htmlscrapper.api.AdvertisementScheduler;
import pl.migibud.htmlscrapper.internal.scrapper.api.AdvertisementScrapper;

@Component
@RequiredArgsConstructor
class AdvertisementSchedulerImpl implements AdvertisementScheduler {
    
    private final AdvertisementScrapper advertisementScrapper;

    @Scheduled(cron = "${scrapper.scheduling.cron}", zone = "Europe/Warsaw")
    @Override
    public void runAdvertisementScrapping() {
        advertisementScrapper.checkAdvertisements();
    }
}
