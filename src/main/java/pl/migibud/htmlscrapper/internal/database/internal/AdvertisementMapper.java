package pl.migibud.htmlscrapper.internal.database.internal;

import org.springframework.stereotype.Component;
import pl.migibud.htmlscrapper.internal.scrapper.api.Advertisement;

import java.util.List;
import java.util.stream.Collectors;

@Component
class AdvertisementMapper {

    AdvertisementEntity map(Advertisement advertisement) {
        return AdvertisementEntity
            .builder()
            .address(advertisement.address())
            .title(advertisement.title())
            .price(advertisement.price())
            .description(advertisement.description())
            .createdAt(advertisement.createdAt())
            .build();
    }

    List<AdvertisementEntity> map(List<Advertisement> advertisements) {
        return advertisements
            .stream()
            .map(this::map)
            .collect(Collectors.toList());
    }

}
