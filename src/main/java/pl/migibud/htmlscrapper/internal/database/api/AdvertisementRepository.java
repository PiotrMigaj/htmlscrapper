package pl.migibud.htmlscrapper.internal.database.api;

import pl.migibud.htmlscrapper.internal.scrapper.api.Advertisement;

import java.util.List;

public interface AdvertisementRepository {

    void save(List<Advertisement> advertisements);
}
