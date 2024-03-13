package pl.migibud.htmlscrapper.internal.database.internal;

import org.springframework.data.jpa.repository.JpaRepository;

interface AdvertisementJpaRepository extends JpaRepository<AdvertisementEntity,Long> {
}
