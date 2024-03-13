package pl.migibud.htmlscrapper.internal.database.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.migibud.htmlscrapper.internal.database.api.AdvertisementRepository;
import pl.migibud.htmlscrapper.internal.scrapper.api.Advertisement;

import java.util.List;

@Repository
@RequiredArgsConstructor
class AdvertisementRepositoryImpl implements AdvertisementRepository {
    
    private final AdvertisementJpaRepository advertisementJpaRepository;
    private final AdvertisementMapper advertisementMapper;

    @Transactional
    @Override
    public void save(List<Advertisement> advertisements) {
        final var advertisementEntities = advertisementMapper.map(advertisements);
        advertisementJpaRepository.saveAll(advertisementEntities);
    }
    

}
