package pl.migibud.htmlscrapper.internal.scrapper.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.migibud.htmlscrapper.internal.database.api.AdvertisementRepository;
import pl.migibud.htmlscrapper.internal.scrapper.api.Advertisement;
import pl.migibud.htmlscrapper.internal.scrapper.api.AdvertisementScrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
class AdvertisementScrapperFacade implements AdvertisementScrapper {
    
    private final AdvertisementScrapperEngine advertisementScrapperEngine;
    private final AdvertisementRepository advertisementRepository;

    @Override
    public void checkAdvertisements() {
        advertisementRepository.save(readAdvertisementsAsync());
    }

    private List<Advertisement> readAdvertisementsAsync() {

        final var totalNumberOfPages = advertisementScrapperEngine.getTotalNumberOfPages();
        final var futures = new ArrayList<Future<List<Advertisement>>>();
        for (int i=1;i<=totalNumberOfPages;i++){
            final var future = advertisementScrapperEngine.readAdvertisementsFromPageAsync(i);
            futures.add(future);
        }
        final var advertisements = new ArrayList<Advertisement>();
        for (var future: futures) {
            try {
                advertisements.addAll(future.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return advertisements;
    }

    private List<Advertisement> readAdvertisements() {

        int totalNumberOfPages = advertisementScrapperEngine.getTotalNumberOfPages();
        System.out.println(totalNumberOfPages);
        return IntStream
            .range(1, totalNumberOfPages+1)
            .mapToObj(advertisementScrapperEngine::readAdvertisementsFromPage)
            .flatMap(Collection::stream)
            .toList();
    }
}
